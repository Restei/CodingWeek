package grp04.jeu.vues;

import grp04.jeu.modele.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class VuePartie implements Observateur{

    private boolean team = false;
    private boolean role = false;
    private GestionnairePartie partie;
    //private GestionnaireTemps temps;
    //private Grille grille;

    public VuePartie(GestionnairePartie partie /*, GestionnaireTemps temps, Grille grille*/){
        this.partie = partie;
        //this.temps = temps;
    }

    public void reagir(){

        //this.team = this.gest.getEquipe();
        //this.role = this.gest.getRole();
    }

    public void ShowGame(Stage primaryStage, int height, int width){
        Grille grille = partie.getPartie().getGrille();
        Font font = Font.font("Courier New", 30);
        Font font_small = Font.font("Courier New", 20);

        BorderPane bPane = new BorderPane();
        Insets insets = new Insets(10);

        HBox top = new HBox();
        Button temp = new Button("Temp");
        temp.setFont(font);
        temp.setTextAlignment(TextAlignment.CENTER);
        top.getChildren().add(temp);
        //VueChrono chrono = new VueChrono();
        //top.getChildren().add(chrono);
        Label player = new Label();
        if (role){
            player.setText("Agent");
        }
        else{
            player.setText("Espion");
        }
        player.setTextAlignment(TextAlignment.CENTER);
        player.setFont(font);
        top.getChildren().add(player);
        Button menu = new Button("Menu");
        menu.setFont(font);
        menu.setTextAlignment(TextAlignment.CENTER);
        top.getChildren().add(menu);
        top.setAlignment(Pos.CENTER);
        top.setSpacing(width*0.3);

        HBox bottom = new HBox();
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
        end.setOnMouseClicked(e -> this.partie.switchRole());
        end.setFont(font_small);
        end.setTextAlignment(TextAlignment.CENTER);
        bottom.getChildren().add(end);
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(width*0.15);

        VueGrille center  = new VueGrille(partie);



        VBox left = new VueCarteRestante(partie).sideBox(false);
        VBox right = new VueCarteRestante(partie).sideBox(true);

        bPane.setTop(top);
        bPane.setBottom(bottom); 
        bPane.setLeft(left); 
        bPane.setRight(right); 
        bPane.setCenter(center);
        BorderPane.setAlignment(right,Pos.CENTER);
        BorderPane.setAlignment(top,Pos.CENTER);
        BorderPane.setAlignment(left,Pos.CENTER);
        BorderPane.setAlignment(bottom,Pos.CENTER);

        BorderPane.setMargin(top, insets);
        BorderPane.setMargin(left, insets);
        BorderPane.setMargin(right, insets);
        //BorderPane.setMargin(center, insets);
        BorderPane.setMargin(bottom, insets);
        
        if (team){
            bPane.setStyle("-fx-background-color:rgb(109, 211, 236)");
        }
        else{
            bPane.setStyle("-fx-background-color:rgb(255, 124, 124)");
        }
        //Creating a scene object 
        Scene scene = new Scene(bPane);  
            
        //Adding scene to the stage 
        primaryStage.setScene(scene);          
        
        //Displaying the contents of the stage 
        primaryStage.show(); 

    }
}
