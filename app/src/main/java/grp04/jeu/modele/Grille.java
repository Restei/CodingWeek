package grp04.jeu.modele;

import grp04.jeu.vues.Observateur;

import java.util.ArrayList;
import java.util.List;

public class Grille {

    // Début propriétés

    private List<List<Carte>> grille = new ArrayList<>();

    // Fin propriétés


    // Début constructeurs

    public Grille(int n, int m) {
        for (int i = 0; i < n; i++) {
            grille.add(new ArrayList<>());
            for (int j = 0; j < m; j++) {
                grille.add(null);
            }
        }
    }

    // Fin constructeurs


    // Début méthodes

    public void insertCarte(Carte carte, int x, int y) {
        grille.get(x).set(y, carte);
    }

    public Carte getCarte(int x, int y) {
        return grille.get(x).get(y);
    }

    // Fin méthodes

}
