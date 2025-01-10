package grp04.jeu.vues;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import grp04.jeu.modele.GestionnaireThemes;
import grp04.jeu.modele.MenuButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class VueMenuThemes extends VBox{

    private String valeurSelectionnee;
    private ArrayList<String> wordList = new ArrayList<String>();
    private ArrayList<String> themeliste = new ArrayList<String>();


    public VueMenuThemes(ChargeurScene chargeurScene){
        Font font = Utils.getInstance().getFont(Utils.FontType.HEADER);
        Font smallfont = Utils.getInstance().getFont(Utils.FontType.SMALL_FONT);

        MenuButton escape = new MenuButton("Retour");
        HBox top = new HBox();
        Label blank = new Label("     ");
        Label title = new Label("Thèmes");
        title.setFont(font);
        blank.setFont(font);
        escape.onActionAndSound(event->chargeurScene.chargerMenuPrincipal());
        escape.setFont(smallfont);
        top.setAlignment(Pos.CENTER);
        top.setSpacing(Utils.getInstance().getWindowWidth()*0.25);
        top.getChildren().add(blank);
        top.getChildren().add(title);
        top.getChildren().add(escape);



        TextField wordField = new TextField();
        wordField.setPromptText("Ajouter un mot ou un thème :");
        wordField.setFont(smallfont);
        wordField.setPrefWidth(Utils.getInstance().getWindowWidth()*0.6);




        ScrollPane scrollPane = new ScrollPane();
        Label theme = new Label("Sélectionnez un thème :");
        theme.setFont(smallfont);

        ComboBox<String> comboBox = new ComboBox<String>();
        themeliste = GestionnaireThemes.themes();
        for (String name : themeliste){
            comboBox.getItems().add(name);
        }
        comboBox.setPrefWidth(Utils.getInstance().getWindowWidth()*0.4);
        comboBox.setOnAction(event->scrollPane.setContent(words(comboBox.getValue(), wordField)));

        scrollPane.setPrefHeight(Utils.getInstance().getWindowHeight()*0.4);
        scrollPane.setMaxWidth(Utils.getInstance().getWindowWidth()*0.8);


        HBox wordEdit = new HBox();

        MenuButton addWord = new MenuButton("Ajouter mot");
        addWord.setFont(smallfont);
        addWord.onActionAndSound(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if (!wordField.getText().trim().isEmpty() && comboBox.getValue()!=null){
                    if (!wordList.contains(wordField.getText().toUpperCase())){
                        GestionnaireThemes.ajouterMot(wordField.getText(), comboBox.getValue());
                        scrollPane.setContent(words(comboBox.getValue(), wordField));
                        wordField.setText("");
                    }
                }
            }
        });
        addWord.setDefaultButton(true);


        wordEdit.getChildren().add(wordField);
        wordEdit.getChildren().add(addWord);
        wordEdit.setAlignment(Pos.CENTER);
        wordEdit.setSpacing(Utils.getInstance().getWindowWidth()*0.05);

        HBox bottom = new HBox();


        MenuButton deleteWord = new MenuButton("Supprimer mot");
        deleteWord.setFont(smallfont);
        deleteWord.onActionAndSound(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                if (valeurSelectionnee!=null){
                    try {
                        String filePath = "themes/"+comboBox.getValue();
                        String result = fileToString(filePath);
                        //Replacing the word with desired one
                        result = result.replaceAll(valeurSelectionnee+"\n", "");
                        //Rewriting the contents of the file
                        PrintWriter writer = new PrintWriter(new File(filePath));
                        writer.append(result);
                        writer.flush();
                        writer.close();
                    } catch (Exception e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    scrollPane.setContent(words(comboBox.getValue(), wordField));
                }
            }
        });


        MenuButton addTheme = new MenuButton("Ajouter thème");
        addTheme.setFont(smallfont);
        addTheme.onActionAndSound(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                if (!wordField.getText().trim().isEmpty() && !themeliste.contains(wordField.getText())){
                    try{
                        File myObj = new File("themes/"+wordField.getText());
                        myObj.createNewFile();
                        comboBox.getItems().add(wordField.getText());
                        comboBox.setValue(wordField.getText());
                        valeurSelectionnee=wordField.getText();
                        scrollPane.setContent(words(wordField.getText(), wordField));
                        wordField.setText("");
                    }catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                      }
                }
            }
        });

        MenuButton deleteTheme = new MenuButton("Supprimer thème");
        deleteTheme.setFont(smallfont);
        deleteTheme.onActionAndSound(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                if (comboBox.getValue()!=null){
                    File file = new File("themes/"+comboBox.getValue());
                    file.delete();
                    comboBox.getItems().remove(comboBox.getValue());
                    valeurSelectionnee=comboBox.getValue();
                    scrollPane.setContent(words(comboBox.getValue(), wordField));
                }
            }
        });


        bottom.getChildren().add(deleteWord);
        bottom.getChildren().add(addTheme);
        bottom.getChildren().add(deleteTheme);
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(Utils.getInstance().getWindowWidth()*0.05);


        this.getChildren().add(top);
        this.getChildren().add(theme);
        this.getChildren().add(comboBox);
        this.getChildren().add(scrollPane);
        this.getChildren().add(wordEdit);
        this.getChildren().add(bottom);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(Utils.getInstance().getWindowWidth()*0.02);


    }

    private VBox words(String filename, TextField wordField){
        
        VBox vBox = new VBox();
        ArrayList<String> liste = new ArrayList<String>();
        File themesDirectory = new File("themes/");
        // Permet de vérifier si le dossier n'est pas vide.
        if (themesDirectory.isDirectory() && themesDirectory.listFiles().length > 0) {
            try {
                File myObj = new File("themes/" + filename);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    liste.add(myReader.nextLine());
                }
                myReader.close();
                wordList = liste;
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        for (String name : liste){
            vBox.getChildren().add(elementListe(name, vBox, wordField));
        }
        return vBox;

    }
    

    private MenuButton elementListe(String name, VBox vBox, TextField wordField){
        Font font = Utils.getInstance().getFont(Utils.FontType.SMALL_FONT);
        MenuButton button = new MenuButton(name);
        button.setFont(font);
        button.setStyle("-fx-background-radius: 0;-fx-border-width: 0;-fx-background-color: transparent;");
        button.setMaxWidth(Utils.getInstance().getWindowWidth()*0.798);
        button.setMinWidth(Utils.getInstance().getWindowWidth()*0.797);
        button.onActionAndSound(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    valeurSelectionnee=button.getText();
                    wordField.setText(name);
                    for (Node temp : vBox.getChildren()) {
                        temp.setStyle("-fx-background-color: transparent;");
                    }
                    button.setStyle("-fx-background-color: lightgrey;");
                }
            });
        return button;
    }

    private String fileToString(String filePath) throws Exception{
        String input = null;
        Scanner sc = new Scanner(new File(filePath));
        StringBuffer sb = new StringBuffer();
        while (sc.hasNextLine()) {
           input = sc.nextLine();
           sb.append(input+"\n");
        }
        sc.close();
        return sb.toString();
     }
}
