package grp04.jeu.vues;

import grp04.jeu.Utils;
import grp04.jeu.modele.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class VuePartie extends BorderPane implements Observateur {
    private final GestionnairePartie gestionnairePartie;

    private final Label playerLabel;

    public VuePartie(GestionnairePartie gestionnairePartie /*, GestionnaireTemps temps, Grille grille*/) {

        // inscription auprès de gestionnairePartie
        this.gestionnairePartie = gestionnairePartie;
        this.gestionnairePartie.ajouterObservateur(this);


        // polices utilisées
        Font font = Font.font("Courier New", 30);
        Font font_small = Font.font("Courier New", 20);

        // sous-composants
        Insets insets = new Insets(10);

        // barre du haut
        HBox top = new HBox();
        top.setAlignment(Pos.CENTER);
        top.setSpacing(Utils.getInstance().getWindowWidth() * 0.3);

        // vue chrono
        VueChrono temp = new VueChrono(gestionnairePartie);


        top.getChildren().add(temp);
        // TODO :
        //VueChrono chrono = new VueChrono();
        //top.getChildren().add(chrono);

        // Rôle du joueur actuel
        this.playerLabel = new Label("Espion");
        this.playerLabel.setTextAlignment(TextAlignment.CENTER);
        this.playerLabel.setFont(font);
        top.getChildren().add(this.playerLabel);

        Button menu = new Button("Menu");
        menu.setFont(font);
        menu.setTextAlignment(TextAlignment.CENTER);
        top.getChildren().add(menu);

        HBox bottom = new HBox();
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(Utils.getInstance().getWindowWidth() * 0.15);

        // espacement
        Label blank = new Label("               ");
        blank.setFont(font_small);
        blank.setTextAlignment(TextAlignment.CENTER);

        bottom.getChildren().add(blank);

        HBox hint = new HBox();
        TextField word = new TextField();
        word.setPromptText("Indice");
        word.setFont(font_small);

        hint.getChildren().add(word);

        TextField number = new TextField();
        number.setPromptText("Nb mots");
        number.setFont(font_small);
        hint.getChildren().add(number);

        bottom.getChildren().add(hint);

        Button end = new Button("Fin du tour");
        end.setOnMouseClicked(e -> this.gestionnairePartie.switchRole());
        end.setFont(font_small);
        end.setTextAlignment(TextAlignment.CENTER);

        bottom.getChildren().add(end);

        VBox left = new VueCarteRestante(gestionnairePartie,true);
        VBox right = new VueCarteRestante(gestionnairePartie,false);

        VueGrille center = new VueGrille(gestionnairePartie);
        center.setAlignment(Pos.CENTER);

        this.setTop(top);
        this.setBottom(bottom);
        this.setLeft(left);
        this.setRight(right);
        this.setCenter(center);

        BorderPane.setAlignment(right, Pos.CENTER);
        BorderPane.setAlignment(top, Pos.CENTER);
        BorderPane.setAlignment(left, Pos.CENTER);
        BorderPane.setAlignment(bottom, Pos.CENTER);

        BorderPane.setMargin(top, insets);
        BorderPane.setMargin(left, insets);
        BorderPane.setMargin(right, insets);
        BorderPane.setMargin(center, insets);
        BorderPane.setMargin(bottom, insets);


    }

    public void reagir() {

        if (this.gestionnairePartie.getRole()) {  // si role == agent
            this.playerLabel.setText("Agent");
        } else {
            this.playerLabel.setText("Espion");
        }

        if (this.gestionnairePartie.getEquipe()) {  // if equipe == bleu
            this.setStyle("-fx-background-color:rgb(109, 211, 236)"); // bleu
        } else {
            this.setStyle("-fx-background-color:rgb(255, 124, 124)"); // rouge
        }
    }

}
