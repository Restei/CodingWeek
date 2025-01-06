package grp04.jeu.vues;

import grp04.jeu.modele.Carte;
import grp04.jeu.modele.TypeCarte;
import javafx.scene.control.Button;

import java.util.Objects;

public class VueCarte extends Button implements Observateur {
    private Carte carte;
    public void reagir(){
        this.setOnMouseClicked(e -> {


            //Vérifie la couleur de la carte et colorie
            if (Objects.equals(this.carte.getType(), TypeCarte.ROUGE)){
                this.setStyle("-fx-background-color: red;-fx-text-fill: white");

            }
            else if (Objects.equals(this.carte.getType(), TypeCarte.BLEU)){
                this.setStyle("-fx-background-color: blue; -fx-text-fill: white");

            }
            else if (Objects.equals(this.carte.getType(), TypeCarte.NOIRE)){
                this.setStyle("-fx-background-color: black;-fx-text-fill: white");

            }
            else {
                this.setStyle("-fx-background-color: white; -fx-text-fill: black");
            }
        });
    }

    public VueCarte(Carte carte,boolean revele){
        this.carte=carte;
        carte.ajouterObservateur(this);
        this.setPrefSize(100,100);
        this.setText(carte.getMot());
        if (!revele){
        this.setOnMouseClicked(e -> {


            //Vérifie la couleur de la carte et colorie
            if (Objects.equals(this.carte.getType(), TypeCarte.ROUGE)){
                this.setStyle("-fx-background-color: red;-fx-text-fill: white");

            }
            else if (Objects.equals(this.carte.getType(), TypeCarte.BLEU)){
                this.setStyle("-fx-background-color: blue; -fx-text-fill: white");

            }
            else if (Objects.equals(this.carte.getType(), TypeCarte.NOIRE)){
                this.setStyle("-fx-background-color: black;-fx-text-fill: white");

            }
            else {
                this.setStyle("-fx-background-color: white; -fx-text-fill: black");
            }
        });
        }
        else{
            //Vérifie la couleur de la carte et colorie
            if (Objects.equals(this.carte.getType(), TypeCarte.ROUGE)){
                this.setStyle("-fx-background-color: red;-fx-text-fill: white");

            }
            else if (Objects.equals(this.carte.getType(), TypeCarte.BLEU)){
                this.setStyle("-fx-background-color: blue; -fx-text-fill: white");

            }
            else if (Objects.equals(this.carte.getType(), TypeCarte.NOIRE)){
                this.setStyle("-fx-background-color: black;-fx-text-fill: white");

            }
            else {
                this.setStyle("-fx-text-fill: black");
            }
        }
    }
}
