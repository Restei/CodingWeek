package grp04.jeu.modele;

import java.util.ArrayList;
import java.util.List;

public class Grille extends SujetObserve{

    // Début propriétés

    private Carte[][] grille;
    private Partie partie;
    // Fin propriétés


    // Début constructeurs

    public Grille(int n, Partie partie) {

        this.grille = new Carte[n][n];
        this.partie = partie;
    }

    public Grille(int n) {

        this.grille = new Carte[n][n];
    }

    // Fin constructeurs


    // Début méthodes

    public void insertCarte(Carte carte, int x, int y) {
        grille[x][y] = carte;
    }

    public Carte getCarte(int x, int y) {
        return grille[x][y];
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public Partie getPartie(){
        return this.partie;
    }

    public void switchRole(){
        for (int i =0;i<grille.length;i++){
            for (int j = 0;j< grille.length;j++){
                grille[i][j].switchRole();
                grille[i][j].NotifierObservateurs();
            }

        }
    }
    public int size(){
        return grille.length;
    }

    // Fin méthodes

}
