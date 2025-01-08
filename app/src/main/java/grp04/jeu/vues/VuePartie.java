package grp04.jeu.vues;

import grp04.jeu.Utils;
import grp04.jeu.modele.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class VuePartie extends BorderPane implements Observateur {
    private final GestionnairePartie gestionnairePartie;

    private final Label playerLabel;
    private Label bottomhint;
    private HBox bottomtextfield;

    private Button end;
    public VuePartie(GestionnairePartie gestionnairePartie /*, GestionnaireTemps temps, Grille grille*/) {

        // inscription auprès de gestionnairePartie
        this.gestionnairePartie = gestionnairePartie;
        this.gestionnairePartie.ajouterObservateur(this);


        // polices utilisées
        Font font = Utils.getInstance().getFont(1);
        Font font_small = Utils.getInstance().getFont(2);

        // sous-composants
        Insets insets = new Insets(10);

        // barre du haut
        HBox top = new HBox();
        top.setAlignment(Pos.CENTER);
        top.setSpacing(Utils.getInstance().getWindowWidth() * 0.3);

        // vue chrono
        VueChrono temp = new VueChrono(gestionnairePartie);


        top.getChildren().add(temp);

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
        bottom.setStyle("-fx-background-color: rgb(230,230,230)"); //blanc-gris

        // espacement
        Label blank = new Label("               ");
        blank.setFont(font_small);
        blank.setTextAlignment(TextAlignment.CENTER);
        bottom.getChildren().add(blank);

        //Creation du bouton de fin de tour
        this.end = new Button("Fin du tour");
        this.end.setOnMouseClicked(e -> this.gestionnairePartie.switchRole());
        this.end.setFont(font_small);
        this.end.setTextAlignment(TextAlignment.CENTER);

        //Creation du champ  d'indices
        this.bottomtextfield = new HBox();

        TextField word = new TextField();
        word.setPromptText("Indice");
        word.setFont(font_small);


        this.bottomhint = new Label();
        this.bottomhint.setFont(font_small);
        this.bottomhint.setPrefHeight(this.end.getHeight());

        TextField number = new TextField();
        number.setPromptText("Nb mots");
        number.setFont(font_small);


        this.bottomtextfield.setPrefHeight(this.end.getHeight());
        this.bottomtextfield.getChildren().add(word);
        this.bottomtextfield.getChildren().add(number);
        bottom.getChildren().add(this.bottomtextfield);



        bottom.getChildren().add(end);

        VBox left = new VueCarteRestante(gestionnairePartie,true);
        VBox right = new VueCarteRestante(gestionnairePartie,false);

        VueGrille center = new VueGrille(gestionnairePartie);
        center.setAlignment(Pos.CENTER);
        bottom.setMinHeight(50);
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

        //Recréation de bottom
        HBox bottom = new HBox();
        bottom.setAlignment(Pos.CENTER);
        bottom.setStyle("-fx-background-color: rgb(200,200,200)");
        bottom.setSpacing(Utils.getInstance().getWindowWidth() * 0.15);

        // espacement
        Label blank = new Label("               ");
        blank.setFont(Utils.getInstance().getFont(2));
        blank.setTextAlignment(TextAlignment.CENTER);

        //
        TextField word = (TextField) this.bottomtextfield.getChildren().getFirst();
        TextField number = (TextField) this.bottomtextfield.getChildren().getLast();
        number.setText(number.getText().replaceAll("[^\\d]", ""));
        word.setText(word.getText().replaceAll("[^\\w]", ""));
        this.bottomtextfield.getChildren().clear();
        this.bottomtextfield.getChildren().addAll(word,number);
        bottom.setMinHeight(50);


        if (this.gestionnairePartie.getRole()) {  // si role == agent
            this.playerLabel.setText("Agent");
            this.bottomhint.setText("Indice : " +word.getText() + number.getText());
            bottom.getChildren().addAll(this.bottomhint,end);
        } else {
            this.playerLabel.setText("Espion");

            bottom.getChildren().addAll(this.bottomtextfield,end);
        }

        this.setBottom(bottom);

        if (this.gestionnairePartie.getEquipe()) {  // if equipe == bleu
            this.setStyle("-fx-background-color:rgb(109, 211, 236)"); // bleu
        } else {
            this.setStyle("-fx-background-color:rgb(255, 124, 124)"); // rouge
        }


    }

}
