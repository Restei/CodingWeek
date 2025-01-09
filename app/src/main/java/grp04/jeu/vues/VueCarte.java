package grp04.jeu.vues;

import grp04.jeu.modele.Carte;
import grp04.jeu.modele.GestionnairePartie;
import grp04.jeu.modele.TypeCarte;
import javafx.scene.control.Button;

import java.net.URISyntaxException;
import java.util.Objects;

public class VueCarte extends Button implements Observateur {

    private int ligne;
    private int colonne;
    private GestionnairePartie gestionnaire;
    public void reagir(){
        Carte carte = gestionnaire.getPartie().getGrille().getCarte(ligne,colonne);
        if (carte.getRevele() || carte.getRole()){
            if (carte.getRevele()){
                this.setText("");
            }
            if (Objects.equals(carte.getType(), TypeCarte.ROUGE)){
                this.setStyle("-fx-background-color: red;-fx-text-fill: white");

            }
            else if (Objects.equals(carte.getType(), TypeCarte.BLEU)){
                this.setStyle("-fx-background-color: blue; -fx-text-fill: white");

            }
            else if (Objects.equals(carte.getType(), TypeCarte.NOIRE)){
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
    public VueCarte(GestionnairePartie gestionnaire,int ligne,int colonne){
        Carte carte = gestionnaire.getPartie().getGrille().getCarte(ligne,colonne);
        this.gestionnaire = gestionnaire;
        this.ligne = ligne;
        this.colonne = colonne;
        gestionnaire.ajouterObservateur(this);
        this.setPrefSize(100,100);
        this.setText(carte.getMot());
        if ( carte.getRevele() || carte.getRole()) {
            //Vérifie la couleur de la carte et colorie
            if (Objects.equals(carte.getType(), TypeCarte.ROUGE)){
                this.setStyle("-fx-background-color: red;-fx-text-fill: white");

            }
            else if (Objects.equals(carte.getType(), TypeCarte.BLEU)){
                this.setStyle("-fx-background-color: blue; -fx-text-fill: white");

            }
            else if (Objects.equals(carte.getType(), TypeCarte.NOIRE)){
                this.setStyle("-fx-background-color: black;-fx-text-fill: white");

            }
            else {
                this.setStyle("-fx-background-color: white ;-fx-text-fill: black");
            }
        }
        else {
            this.setStyle("-fx-background-color: grey ; -fx-text-fill: black");
        }
        this.setOnMouseClicked(e -> {if (!carte.getRole())
            try {
                gestionnaire.jouer(ligne,colonne);
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }});
    }
}
