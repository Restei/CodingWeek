package grp04.jeu.vues;

import java.time.format.TextStyle;

import grp04.jeu.modele.GestionnairePartie;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class VueCarteRestante extends VBox implements Observateur {

    private int red_cards;
    private int blue_cards;
    private GestionnairePartie gest;
    private Font font;
    public VueCarteRestante(GestionnairePartie gest){
        this.gest = gest;
        this.gest.ajouterObservateur(this);
        this.font = Font.font("Courier New", 30);

    }

    public void reagir(){
        this.setAlignment(Pos.CENTER);
        this.setMaxHeight(60);;
        this.setMinWidth(200);
        this.red_cards = this.gest.getNbCarteRouge();
        this.blue_cards = this.gest.getNbCarteBleu();

    }

    public VBox sideBox(boolean team){

        if (team){ // team blue
            Label text = new Label("Bleu");
            text.setFont(font);
            text.setTextAlignment(TextAlignment.CENTER);
            this.getChildren().add(text);
            Label number = new Label(String.valueOf(blue_cards));
            text.setFont(font);
            number.setFont(font);
            number.setTextAlignment(TextAlignment.CENTER);
            this.getChildren().add(number);
            this.setStyle("-fx-background-color:rgb(109, 121, 221)");
        }
        else{
            Label text = new Label("Rouge");
            text.setTextAlignment(TextAlignment.CENTER);
            this.getChildren().add(text);
            Label number = new Label(String.valueOf(red_cards));
            number.setFont(font);
            number.setTextAlignment(TextAlignment.CENTER);
            this.getChildren().add(number);
            this.setStyle("-fx-background-color:rgb(228, 60, 60)");
        }
        return new VBox();
    }

}
