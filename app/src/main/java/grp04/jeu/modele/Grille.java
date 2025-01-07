package grp04.jeu.modele;

import java.util.ArrayList;
import java.util.List;

public class Grille {

    // Début propriétés

    private Carte[][] grille;

    private int taille; //

    // Fin propriétés


    // Début constructeurs

    public Grille(int n, int m) {
        this.taille = n;
        this.grille = new Carte[n][m];
    }

    // Fin constructeurs


    // Début méthodes

    public void insertCarte(Carte carte, int x, int y) {
        grille[x][y] = carte;
    }

    public Carte getCarte(int x, int y) {
        return grille[x][y];
    }

    public int size(){
        return taille;
    }

    // Fin méthodes

}
