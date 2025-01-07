package grp04.jeu.vues;

import grp04.jeu.modele.Carte;
import grp04.jeu.modele.TypeCarte;
import javafx.scene.control.Button;

import java.util.Objects;

public class VueCarte extends Button implements Observateur {
    private final Carte carte;
    private int ligne;
    private int colonne;

    public void reagir(){
        if (this.carte.getRevele() || this.carte.getRole()){
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
        }
        else{
            this.setStyle("-fx-background-color: grey ; -fx-text-fill: black");
        }
    }
    public VueCarte(Carte carte,int ligne,int colonne){
        this.carte=carte;
        this.ligne = ligne;
        this.colonne = colonne;
        carte.ajouterObservateur(this);
        this.setPrefSize(100,100);
        this.setText(carte.getMot());
        if ( carte.getRevele() || carte.getRole()) {
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
                this.setStyle("-fx-background-color: white ;-fx-text-fill: black");
            }
        }
        else {
            this.setStyle("-fx-background-color: grey ; -fx-text-fill: black");
        }
        this.setOnMouseClicked(e -> {this.carte.getGrille().getPartie().getGestionnaire().jouer(ligne,colonne);});
    }
}
