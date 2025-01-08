package grp04;

import static grp04.jeu.modele.TypeCarte.BLEU;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import grp04.jeu.modele.Carte;
import grp04.jeu.modele.Grille;
import org.junit.jupiter.api.Test;

public class GrilleTest {

    @Test void CreationGrilleVideTest() {
        Grille grille = new Grille(5);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertNull(grille.getCarte(i, j));
            }
        }
    }

    @Test void CreationGrilleTest() {
        Grille grille = new Grille(5);
        Carte carte;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                carte = new Carte(BLEU, "test" + i + j);
                grille.insertCarte(carte, i, j);
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(grille.getCarte(i, j).getMot(), "test" + i + j);
            }
        }
    }

}
