package grp04.jeu.vues;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import grp04.jeu.modele.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class VuePartie extends BorderPane implements Observateur {
    private final GestionnairePartie gestionnairePartie;
    private final ChargeurScene chargeurScene;
    private final Overlay overlay;

    private final Label playerLabel;
    private Label bottomhint;
    private HBox bottomtextfield;


    public VuePartie(GestionnairePartie gestionnairePartie, ChargeurScene chargeurScene, Overlay overlay) {

        // inscription auprès de gestionnairePartie
        this.gestionnairePartie = gestionnairePartie;
        this.gestionnairePartie.ajouterObservateur(this);

        this.overlay = overlay; // utilisé pour afficher les menus
        this.chargeurScene = chargeurScene;



        // Espacement

        Region regiongauche = new Region();
        HBox.setHgrow(regiongauche, Priority.ALWAYS);


        Region regiondroite = new Region();
        HBox.setHgrow(regiondroite, Priority.ALWAYS);



        // polices utilisées
        Font font = Utils.getInstance().getFont(Utils.FontType.HEADER);
        Font font_small = Utils.getInstance().getFont(Utils.FontType.SMALL_FONT);

        // sous-composants
        Insets insets = new Insets(10);

        // barre du haut
        HBox top = new HBox();
        top.setAlignment(Pos.CENTER);
        top.setSpacing(Utils.getInstance().getWindowWidth() * 0.3);

        // vue chrono
        VueChrono temp = new VueChrono(gestionnairePartie);








        // Rôle du joueur actuel

        this.playerLabel = new Label("Espion");
        this.playerLabel.setTextAlignment(TextAlignment.CENTER);
        this.playerLabel.setFont(font);

        //Mot Actuel
        VueMotSelectionne mot = new VueMotSelectionne(gestionnairePartie);
        mot.setTextAlignment(TextAlignment.CENTER);
        mot.setFont(font);


        //Information sur le joueur
        VBox Info = new VBox();
        Info.getChildren().addAll(this.playerLabel,mot);


        Button menu = new Button("Menu");
        menu.setFont(font);
        menu.setTextAlignment(TextAlignment.CENTER);
        menu.setOnAction(e -> {
            PopupMenuPause popupMenuPause = new PopupMenuPause(this.gestionnairePartie, this.chargeurScene, this.overlay);
            this.overlay.ajouterEtAfficherPopup(popupMenuPause);

        } );
        top.getChildren().addAll(temp,regiongauche,Info,regiondroite,menu);

        // creation de barre d'indice
        HBox bottom = new HBox();

        Label indice = new Label("Indice :");
        indice.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));

        bottom.setStyle("-fx-background-color: rgb(230,230,230)"); //blanc-gris

        //Creation du bouton de fin de tour
        Button end = new Button("Fin du tour");
        end.setOnMouseClicked(e -> this.gestionnairePartie.switchRole());
        end.setFont(font_small);
        end.setTextAlignment(TextAlignment.CENTER);

        //Creation du champ  d'indices
        this.bottomtextfield = new HBox();

        TextField word = new TextField();
        word.setPromptText("Indice");
        word.setFont(font_small);


        this.bottomhint = new Label();
        this.bottomhint.setFont(font_small);
        this.bottomhint.setPrefHeight(end.getHeight());

        TextField number = new TextField();
        number.setPromptText("Nb mots");
        number.setFont(font_small);


        this.bottomtextfield.setPrefHeight(end.getHeight());
        this.bottomtextfield.getChildren().add(word);
        this.bottomtextfield.getChildren().add(number);




        bottom.getChildren().addAll(indice,regiongauche,this.bottomtextfield,regiondroite,end);

        VBox right = new VueCarteRestante(gestionnairePartie,true);
        VBox left = new VueCarteRestante(gestionnairePartie,false);

        VueGrille center = new VueGrille(gestionnairePartie);
        center.setAlignment(Pos.CENTER);
        bottom.setMinHeight(50);

        HBox.setMargin(indice,new Insets(15,15,15,15));
        HBox.setMargin(end,new Insets(10,10,10,10));
        HBox.setMargin(bottomtextfield,new Insets(10,10,10,10));
        HBox.setMargin(bottomhint,new Insets(10,10,10,10));

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



    }

    public void reagir() {

        //Recréation de bottom
        HBox bottom = (HBox) getBottom();

        TextField word = (TextField) this.bottomtextfield.getChildren().getFirst();
        TextField number = (TextField) this.bottomtextfield.getChildren().getLast();




        number.textProperty().addListener((observable,oldvalue,newvalue) -> {
            if (!newvalue.matches("\\d*")){
                number.setText(newvalue.replaceAll("\\D", ""));
            }
        }
        );
        word.textProperty().addListener((observable,oldvalue,newvalue) -> {
                    if (!newvalue.matches("[^\\d\\W]*")){
                        word.setText(newvalue.replaceAll("\\W", ""));
                        word.setText(newvalue.replaceAll("\\d", ""));
                    }
                }
        );

        this.bottomtextfield.getChildren().clear();
        this.bottomtextfield.getChildren().addAll(word,number);


        if (this.gestionnairePartie.getRole() ) {  // si role == agent
            if (!(bottom.getChildren().get(2) instanceof Label)){
                this.bottomhint.setText(word.getText() + "   " +  number.getText());
            }
            else {
                number.clear();
                word.clear();
            }
            this.playerLabel.setText("AGENT");
            bottom.getChildren().set(2,this.bottomhint);
        } else {
            this.playerLabel.setText("ESPION");

            bottom.getChildren().set(2,this.bottomtextfield);
        }

        this.setBottom(bottom);

        if (this.gestionnairePartie.getEquipe()) {  // if equipe == bleu
            this.setStyle("-fx-background-color:rgb(109, 211, 236)"); // bleu
        } else {
            this.setStyle("-fx-background-color:rgb(255, 124, 124)"); // rouge
        }

        if (this.gestionnairePartie.getPartie().getGagnant()!=null){
            PopupStatistique popupStatistique = new PopupStatistique(this.gestionnairePartie, this.chargeurScene,this.overlay);
            this.overlay.ajouterEtAfficherPopup(popupStatistique);
        }


    }

}
