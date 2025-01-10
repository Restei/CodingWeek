package grp04.jeu.vues;

import grp04.jeu.modele.Carte;
import grp04.jeu.modele.GestionnairePartie;
import grp04.jeu.modele.TypeCarte;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;
import java.util.Objects;

public class VueCarte extends StackPane implements Observateur {

    private final int ligne;
    private final int colonne;
    private final GestionnairePartie gestionnaire;

    private final Label label;

    public VueCarte(GestionnairePartie gestionnaire, int ligne, int colonne) {

        Carte carte = gestionnaire.getPartie().getGrille().getCarte(ligne, colonne);
        this.gestionnaire = gestionnaire;
        this.ligne = ligne;
        this.colonne = colonne;
        gestionnaire.ajouterObservateur(this);

        this.setPrefSize(150, 100);

        this.label = new Label();


        this.label.setStyle("-fx-padding: 3px;"); // Ajoute un padding pour espacer le texte des bords
        this.getChildren().add(label);
        StackPane.setAlignment(label, Pos.BOTTOM_CENTER);

        label.setText(carte.getMot());


        this.setOnMouseClicked(e -> {
            if (!carte.getRole()) {
                gestionnaire.jouer(ligne, colonne);
                try {
                    Media media = new Media(getClass().getResource("/card-sounds-35956.mp3").toURI().toString());
                    MediaPlayer player = new MediaPlayer(media);
                    player.play();
                } catch (URISyntaxException exception) {
                    exception.printStackTrace();
                }
            }
        });
        this.setOnMouseEntered(e -> gestionnaire.ModifierCarteActuelle(this.label.getText()));
        this.setOnMouseExited(e -> gestionnaire.ModifierCarteActuelle(""));
    }

    public void reagir() {
        Carte carte = gestionnaire.getPartie().getGrille().getCarte(ligne, colonne);
        String cardStyleString = "-fx-border-color: oldlace; " +
                "-fx-border-width: 5px; " +
                "-fx-border-radius: 15px; " +
                "-fx-background-radius: 20px;";

        String labelStyleString = "-fx-border-width: 2px; " +
                "-fx-border-radius: 10px; " +
                "-fx-background-radius: 15px; " +
                "-fx-padding: 5px;"; // Ajoute un padding interne au label

        if (carte.getRevele() || carte.getRole()) {

            if (carte.getRevele()) {
                this.label.setVisible(false);
            }
            if (Objects.equals(carte.getType(), TypeCarte.ROUGE)) {
                cardStyleString += "-fx-background-color: red;";
                labelStyleString += "-fx-background-color: lightpink; -fx-border-color: black; ";

            } else if (Objects.equals(carte.getType(), TypeCarte.BLEU)) {
                cardStyleString += "-fx-background-color: blue;";
                labelStyleString += "-fx-background-color: lightskyblue; -fx-border-color: black; ";

            } else if (Objects.equals(carte.getType(), TypeCarte.NOIRE)) {
                cardStyleString += "-fx-background-color: black;";
                labelStyleString += "-fx-background-color: gray15; -fx-text-fill: white; -fx-border-color: white; ";

            } else {
                cardStyleString += "-fx-background-color: mintcream;";
                labelStyleString += "-fx-background-color: white; -fx-border-color: black; ";
            }
        } else {
            cardStyleString += "-fx-background-color: grey;";
            labelStyleString += "-fx-background-color: lightgray; -fx-border-color: black; ";
        }

        this.setStyle(cardStyleString);
        this.label.setStyle(labelStyleString);

    }

}
