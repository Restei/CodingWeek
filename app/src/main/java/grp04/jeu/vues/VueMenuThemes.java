package grp04.jeu.vues;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import grp04.jeu.modele.GestionnaireThemes;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class VueMenuThemes extends VBox{

    public VueMenuThemes(ChargeurScene chargeurScene){
        Font font = Utils.getInstance().getFont(Utils.FontType.HEADER);
        Font smallfont = Utils.getInstance().getFont(Utils.FontType.SMALL_FONT);

        Button escape = new Button("Retour");
        HBox top = new HBox();
        Label blank = new Label("     ");
        Label title = new Label("Thèmes");
        title.setFont(font);
        blank.setFont(font);
        escape.setOnAction(event->chargeurScene.chargerMenuPrincipal());
        escape.setFont(smallfont);
        top.setAlignment(Pos.CENTER);
        top.setSpacing(Utils.getInstance().getWindowWidth()*0.25);
        top.getChildren().add(blank);
        top.getChildren().add(title);
        top.getChildren().add(escape);

        ScrollPane scrollPane = new ScrollPane();

        ComboBox<String> comboBox = new ComboBox<String>();
        ArrayList<String> liste = new ArrayList<String>();
        liste = GestionnaireThemes.themes();
        for (String name : liste){
            comboBox.getItems().add(name);
        }
        comboBox.setPrefWidth(Utils.getInstance().getWindowWidth()*0.4);
        comboBox.setOnAction(event->scrollPane.setContent(words(comboBox.getValue())));


        scrollPane.setPrefHeight(Utils.getInstance().getWindowHeight()*0.3);
        scrollPane.setMaxWidth(Utils.getInstance().getWindowWidth()*0.8);

        this.getChildren().add(top);
        this.getChildren().add(comboBox);
        this.getChildren().add(scrollPane);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(Utils.getInstance().getWindowWidth()*0.05);


    }

    private VBox words(String filename){
        
        VBox vBox = new VBox();
        ArrayList<String> liste = new ArrayList<String>();
        try {
            File myObj = new File("themes/"+filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                liste.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        for (String name : liste){
            vBox.getChildren().add(elementListe(name, vBox));
        }
        return vBox;

    }
    

    private Label elementListe(String name, VBox vBox){
        Font font = Utils.getInstance().getFont(Utils.FontType.SMALL_FONT);
        Label button = new Label(name);
        button.setFont(font);
        button.setStyle("-fx-background-radius: 0;-fx-border-width: 0;-fx-background-color: transparent;");
        button.setPrefWidth(Utils.getInstance().getWindowWidth()*0.798);
        return button;
    }

}
