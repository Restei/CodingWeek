package grp04.jeu.vues;

import grp04.jeu.ChargeurScene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MenuPrincipal extends VBox {

    public MenuPrincipal(ChargeurScene chargeurScene){

        Font font = Font.font("Courier New", 28);
        Font title = Font.font("Courier New", 50);
        Label titre = new Label("LinguaCrypt");

        titre.setFont(title);
        this.getChildren().add(titre);
        
        Label space = new Label(" ");
        space.setFont(title);
        this.getChildren().add(space);


        Button newGame = new Button("Nouvelle partie");
        newGame.setOnAction(event -> chargeurScene.pseudoGame());
        newGame.setFont(font);
        newGame.setStyle("-fx-background-color:rgb(109, 236, 126)");
        newGame.setPrefWidth(500);
        this.getChildren().add(newGame);
        

        Button loadGame = new Button("Charger partie");
        loadGame.setOnAction(event -> chargeurScene.menuSauvegarde());
        loadGame.setFont(font);
        loadGame.setStyle("-fx-background-color:rgb(109, 236, 126)");
        loadGame.setPrefWidth(500);
        this.getChildren().add(loadGame);
        

        Button addWord = new Button("Accéder à la liste de mots");
        addWord.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) 
            { 
                ; 
            }
        });
        addWord.setFont(font);
        addWord.setStyle("-fx-background-color:rgb(109, 236, 126)");
        addWord.setPrefWidth(500);
        this.getChildren().add(addWord);
        

        Button credits = new Button("Crédits");
        credits.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) 
            {
                ;
            }
        });
        credits.setFont(font);
        credits.setStyle("-fx-background-color:rgb(109, 236, 126)");
        credits.setPrefWidth(500);
        this.getChildren().add(credits);
        

        Button quit = new Button("Quitter");
        quit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) 
            { 
                Platform.exit(); 
            }
        });
        quit.setFont(font);
        quit.setStyle("-fx-background-color:rgb(109, 236, 126)");
        quit.setPrefWidth(500);
        this.getChildren().add(quit);


        this.setSpacing(40);
        this.setAlignment(Pos.CENTER);
    }
    
}
