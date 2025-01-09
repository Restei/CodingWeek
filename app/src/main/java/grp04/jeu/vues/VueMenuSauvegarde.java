package grp04.jeu.vues;

import java.util.ArrayList;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import grp04.jeu.modele.GestionnaireSauvegarde;
import grp04.jeu.modele.Partie;
import grp04.jeu.modele.Sauvegarde;
import grp04.jeu.modele.Statistique;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class VueMenuSauvegarde extends VBox{

    private String valeurARetourner = null;

    public VueMenuSauvegarde(ChargeurScene chargeurScene){
        Font font = Utils.getInstance().getFont(0);

        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox();

        ArrayList<String> list = new ArrayList<String>();
        list = GestionnaireSauvegarde.initialisation();
        for (String name : list){
            vBox.getChildren().add(elementListe(name, vBox));
        }
        scrollPane.setContent(vBox);
        scrollPane.setPrefHeight(Utils.getInstance().getWindowHeight()*0.6);
        scrollPane.setMaxWidth(Utils.getInstance().getWindowWidth()*0.8);
        this.getChildren().add(scrollPane);
        this.setAlignment(Pos.CENTER);


        HBox hBox = new HBox();
        Button cancel = new Button("Annuler");
        cancel.setPrefSize(Utils.getInstance().getWindowWidth()*0.1, Utils.getInstance().getWindowHeight()*0.1);
        cancel.setFont(font);
        cancel.setOnAction(event -> chargeurScene.chargerMenuPrincipal());

        Button delete = new Button("Supprimer");
        delete.setPrefSize(Utils.getInstance().getWindowWidth()*0.15, Utils.getInstance().getWindowHeight()*0.1);
        delete.setFont(font);
        delete.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) 
                {
                    if (valeurARetourner != null){
                        GestionnaireSauvegarde.supprimerSauvegarde(valeurARetourner);
                        chargeurScene.menuSauvegarde();
                    }
                }
            });

        Button load = new Button("Charger");
        load.setPrefSize(Utils.getInstance().getWindowWidth()*0.1, Utils.getInstance().getWindowHeight()*0.1);
        load.setFont(font);
        load.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) 
                {
                    if (valeurARetourner != null){
                        try {
                            Sauvegarde sauvegarde = GestionnaireSauvegarde.charger(valeurARetourner);
                            Partie partie = sauvegarde.dataPartie();
                            Statistique statistique = sauvegarde.dataStatistique();
                            chargeurScene.lancerPatie(partie, statistique);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });


        hBox.getChildren().add(cancel);
        hBox.getChildren().add(delete);
        hBox.getChildren().add(load);

        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(Utils.getInstance().getWindowWidth()*0.2);
        this.getChildren().add(hBox);

        this.setSpacing(Utils.getInstance().getWindowHeight()*0.1);

    }

    private Button elementListe(String name, VBox vBox){
        Font font = Utils.getInstance().getFont(1);
        Button button = new Button(name);
        button.setFont(font);
        button.setStyle("-fx-background-radius: 0;-fx-border-width: 0;-fx-background-color: transparent;");
        button.setPrefWidth(Utils.getInstance().getWindowWidth()*0.798);
        button.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) 
                {
                    valeurARetourner = button.getText();
                    
                    for (Node temp : vBox.getChildren()) {
                        temp.setStyle("-fx-background-color: transparent;");
                    }
                    button.setStyle("-fx-background-color: lightgrey;");
                }
            });
        return button;
    }
    
}
