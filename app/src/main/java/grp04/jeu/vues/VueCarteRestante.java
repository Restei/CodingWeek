package grp04.jeu.vues;

import java.time.format.TextStyle;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class VueCarteRestante implements Observateur {
    
    private int red_cards;
    private int blue_cards;
    //private GestionnairePartie gest;

    public VueCarteRestante(/*GestionnairePartie gest*/){
        //this.gest = gest;
    }

    public void reagir(){

        //this.red_cards = this.gest.getNbCarteRouge();
        //this.blue_cards = this.gest.getNbCarteBleu();

    }

    public VBox sideBox(boolean team){
        VBox vbox = new VBox(10);
        Font font = Font.font("Courier New", 30);

        if (team){ // team blue
            Label text = new Label("Bleu");
            text.setFont(font);
            text.setTextAlignment(TextAlignment.CENTER);
            vbox.getChildren().add(text);
            Label number = new Label(String.valueOf(blue_cards));
            number.setFont(font);
            number.setTextAlignment(TextAlignment.CENTER);
            vbox.getChildren().add(number);
            vbox.setStyle("-fx-background-color:rgb(109, 121, 221)");
        }
        else{
            Label text = new Label("Rouge");
            text.setFont(font);
            text.setTextAlignment(TextAlignment.CENTER);
            vbox.getChildren().add(text);
            Label number = new Label(String.valueOf(red_cards));
            number.setFont(font);
            number.setTextAlignment(TextAlignment.CENTER);
            vbox.getChildren().add(number);
            vbox.setStyle("-fx-background-color:rgb(228, 60, 60)");
        }

        vbox.setAlignment(Pos.CENTER);
        vbox.setMaxHeight(60);;
        vbox.setMinWidth(200);
        return vbox;
    }

}
