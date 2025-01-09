package grp04.jeu.modele;

import java.io.Serializable;

public class Grille extends SujetObserve implements Serializable {

    // Début propriétés

    private final Carte[][] grille;

    // Fin propriétés


    // Début constructeurs

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

    /**
     * Permet d'actualiser l'état de la grille après le tour d'un joueur
     */
    public void switchRole(){
        for (Carte[] cartes : grille) {
            for (int j = 0; j < grille.length; j++) {
                cartes[j].switchRole();
            }
        }
    }
    public int size(){
        return grille.length;
    }

    // Fin méthodes

}
