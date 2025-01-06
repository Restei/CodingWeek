package grp04.jeu.modele;

import grp04.jeu.vues.Observateur;

import java.util.ArrayList;
import java.util.List;

public class Grille extends SujetObserve{

    // Début propriétés

    //private List<List<Carte>> grille = new ArrayList<>();
    private Carte[][] grille;

    private int taille;
    // Fin propriétés


    // Début constructeurs

    public Grille(int n, int m) {
        this.taille = n;
        this.grille = new Carte[n][m];
    }

    // Fin constructeurs


    // Début méthodes

    public void insertCarte(Carte carte, int x, int y) {
        //grille.get(x).set(y, carte);
        grille[x][y]=carte;
    }

    public Carte getCarte(int x, int y) {
        return /*grille.get(x).get(y);*/ grille[x][y];
    }

    public int size(){
        return taille;
    }

    // Fin méthodes

}
