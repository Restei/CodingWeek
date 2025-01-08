package grp04.jeu.vues;

import grp04.jeu.ChargeurScene;
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
    private final ChargeurScene chargeurScene;
    private final Overlay overlay;

    private final Label playerLabel;
    private Label wordhint;
    private Label numberhint;
    private TextField word;
    private TextField number;
    private Button end;

    public VuePartie(GestionnairePartie gestionnairePartie, ChargeurScene chargeurScene, Overlay overlay) {

        // inscription auprès de gestionnairePartie
        this.gestionnairePartie = gestionnairePartie;
        this.gestionnairePartie.ajouterObservateur(this);

        this.overlay = overlay; // utilisé pour afficher les menus
        this.chargeurScene = chargeurScene;


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
        menu.setOnAction(e -> {
            PopupMenuPause popupMenuPause = new PopupMenuPause(this.gestionnairePartie, this.chargeurScene, this.overlay);
            this.overlay.ajouterEtAfficherPopup(popupMenuPause);
            // this.gestionnairePartie.pauseChrono();
        } );
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
        this.word = new TextField();
        word.setPromptText("Indice");
        word.setFont(font_small);


        hint.getChildren().add(word);

        this.wordhint = new Label();
        this.wordhint.setFont(font_small);

        this.number = new TextField();

        this.number.setPromptText("Nb mots");
        this.number.setFont(font_small);

        this.numberhint = new Label();
        this.wordhint.setFont(font_small);

        hint.getChildren().add(number);

        bottom.getChildren().add(hint);

        this.end = new Button("Fin du tour");
        this.end.setOnMouseClicked(e -> this.gestionnairePartie.switchRole());
        this.end.setFont(font_small);
        this.end.setTextAlignment(TextAlignment.CENTER);

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
        HBox hint = new HBox();
        hint.setSpacing(0);
        this.wordhint.setText(word.getText());
        this.numberhint.setText(number.getText());
        if (this.gestionnairePartie.getRole()) {  // si role == agent
            this.playerLabel.setText("Agent");
            hint.getChildren().addAll(wordhint,numberhint,end);
        } else {
            this.playerLabel.setText("Espion");
            hint.getChildren().addAll(word,number,end);
        }
        this.setBottom(hint);
        hint.setAlignment(Pos.CENTER);
        hint.setSpacing(Utils.getInstance().getWindowWidth() * 0.15);
        if (this.gestionnairePartie.getEquipe()) {  // if equipe == bleu
            this.setStyle("-fx-background-color:rgb(109, 211, 236)"); // bleu
        } else {
            this.setStyle("-fx-background-color:rgb(255, 124, 124)"); // rouge
        }


    }

}
