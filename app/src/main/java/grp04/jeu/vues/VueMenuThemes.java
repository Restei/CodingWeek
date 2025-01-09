package grp04.jeu.vues;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import grp04.jeu.modele.GestionnaireThemes;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class VueMenuThemes extends VBox{

    private String valeurSelectionnee;


    public VueMenuThemes(ChargeurScene chargeurScene){
        Font font = Utils.getInstance().getFont(1);
        Font smallfont = Utils.getInstance().getFont(0);

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



        TextField wordField = new TextField();
        wordField.setPromptText("Ecrire ou choisir un mot");
        wordField.setFont(smallfont);
        wordField.setPrefWidth(Utils.getInstance().getWindowWidth()*0.6);




        ScrollPane scrollPane = new ScrollPane();

        ComboBox<String> comboBox = new ComboBox<String>();
        ArrayList<String> liste = new ArrayList<String>();
        liste = GestionnaireThemes.themes();
        for (String name : liste){
            comboBox.getItems().add(name);
        }
        comboBox.setPrefWidth(Utils.getInstance().getWindowWidth()*0.4);
        comboBox.setOnAction(event->scrollPane.setContent(words(comboBox.getValue(), wordField)));

        scrollPane.setPrefHeight(Utils.getInstance().getWindowHeight()*0.3);
        scrollPane.setMaxWidth(Utils.getInstance().getWindowWidth()*0.8);


        HBox wordEdit = new HBox();


        Button editWord = new Button("Modifier mot");
        editWord.setFont(smallfont);
        editWord.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if (wordField.getText()!=null){

                }
            }
        });
        
        Button addWord = new Button("Ajouter mot");
        addWord.setFont(smallfont);
        addWord.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if (wordField.getText()!=null){

                }
            }
        });
        

        wordEdit.getChildren().add(wordField);
        wordEdit.getChildren().add(addWord);
        wordEdit.setAlignment(Pos.CENTER);
        wordEdit.setSpacing(Utils.getInstance().getWindowWidth()*0.05);


        this.getChildren().add(top);
        this.getChildren().add(comboBox);
        this.getChildren().add(scrollPane);
        this.getChildren().add(wordEdit);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(Utils.getInstance().getWindowWidth()*0.05);


    }

    private VBox words(String filename, TextField wordField){
        
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
            vBox.getChildren().add(elementListe(name, vBox, wordField));
        }
        return vBox;

    }
    

    private Button elementListe(String name, VBox vBox, TextField wordField){
        Font font = Utils.getInstance().getFont(1);
        Button button = new Button(name);
        button.setFont(font);
        button.setStyle("-fx-background-radius: 0;-fx-border-width: 0;-fx-background-color: transparent;");
        button.setPrefWidth(Utils.getInstance().getWindowWidth()*0.798);
        button.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) 
                {
                    wordField.setText(name);
                    for (Node temp : vBox.getChildren()) {
                        temp.setStyle("-fx-background-color: transparent;");
                    }
                    button.setStyle("-fx-background-color: lightgrey;");
                }
            });
        return button;
    }

}
