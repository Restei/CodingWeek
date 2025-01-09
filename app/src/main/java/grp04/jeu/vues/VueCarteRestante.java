package grp04.jeu.vues;

import grp04.jeu.Utils;
import grp04.jeu.modele.GestionnairePartie;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class VueCarteRestante extends VBox implements Observateur {

    private GestionnairePartie gest;

    private Label number;
    private boolean team;


    public VueCarteRestante(GestionnairePartie gest,boolean team){
        this.gest = gest;
        this.gest.ajouterObservateur(this);
        this.setAlignment(Pos.CENTER);
        this.setMaxHeight(60);;
        this.setMinWidth(200);
        this.team = team;
        Font font = Utils.getInstance().getFont(Utils.FontType.HEADER);
        if (team){
            Label text = new Label("Bleu");
            text.setFont(font);
            text.setTextAlignment(TextAlignment.CENTER);
            this.getChildren().add(text);
            this.number = new Label(Integer.toString(gest.getNbCarteBleu()));
            this.number.setFont(font);
            this.number.setTextAlignment(TextAlignment.CENTER);
            this.getChildren().add(number);
            this.setStyle("-fx-background-color:rgb(109, 121, 221)");
        }
        else{
            Label text = new Label("Rouge");
            text.setFont(font);
            text.setTextAlignment(TextAlignment.CENTER);
            this.getChildren().add(text);
            this.number = new Label(Integer.toString(gest.getNbCarteBleu()));
            this.number.setFont(font);
            this.number.setTextAlignment(TextAlignment.CENTER);
            this.getChildren().add(number);
            this.setStyle("-fx-background-color:rgb(228, 60, 60)");
        }

    }

    public void reagir(){
        if (this.team){
            this.number.setText(Integer.toString(gest.getNbCarteBleu()));
        }
        else{
            this.number.setText(Integer.toString(gest.getNbCarteRouge()));
        }
    }
}
