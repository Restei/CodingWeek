package grp04.jeu.modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static grp04.jeu.modele.TypeCarte.*;

public class CreateurPartie {

    public static Partie createurPartie(int taille, int nbCarte, int nbCarteNoire, TypeTimer type, int timerEspionBleu, int timerAgentRouge, Theme theme) {

        // Génération de la grille
        Grille grille = new Grille(taille);
        Random random = new Random();
        Timer timer = new Timer(type, timerEspionBleu, timerAgentRouge);
        List<String> mots;
        if (theme != null) {
            mots = theme.getMots();
        } else {
            mots = new ArrayList<>();
            for (int k = 0; k < taille * taille; k++) {
                mots.add("mot" + k);
            }
        }

        if (nbCarte + nbCarteNoire > taille * taille) {
            System.err.println("Erreur createurPartie : nbCarte + nbCarteNoire > taille * taille");
        }

        if (mots.size() < taille * taille) {
            System.err.println("Erreur createurPartie : mots.size() < taille * taille");
        }

        // Liste permettent de tirer aléatoirement sans remise les indices des mots du thème.
        List<Integer> listeIndiceMots = new ArrayList<>();
        for (int k = 0; k < mots.size(); k++) {
            listeIndiceMots.add(k);
        }
        // Liste permettent de tirer aléatoirement sans remise les indices des lignes et clonnes de la grille.
        List<Integer[]> listeIndiceGrille = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                listeIndiceGrille.add(new Integer[]{i, j});
            }
        }

        // On place aléatoirement les cartes rouges.
        for (int k = 0; k < nbCarte + 1; k++) {
            // On sélectionne une paire (indiceLigne, indiceColonne) aléatoirement parmis celle restante.
            int indiceLigneColonne = random.nextInt(listeIndiceGrille.size());
            // On séléctionne un mot aléatoirement parmis ceux restant.
            int indiceMots = random.nextInt(listeIndiceMots.size());
            Carte carte = new Carte(ROUGE, mots.get(listeIndiceMots.remove(indiceMots)));
            Integer[] indices = listeIndiceGrille.remove(indiceLigneColonne);
            grille.insertCarte(carte, indices[0], indices[1]);
        }


        // On place aléatoirement les cartes bleu.
        for (int k = 0; k < nbCarte; k++) {
            // On sélectionne une paire (indieceLigne, indiceColonne) aléatoirement parmis celle restante.
            int indiceLigneColonne = random.nextInt(listeIndiceGrille.size());
            // On séléctionne un mot aléatoirement parmis ceux restant.
            int indiceMots = random.nextInt(listeIndiceMots.size());
            Carte carte = new Carte(BLEU, mots.get(listeIndiceMots.remove(indiceMots)));
            Integer[] indices = listeIndiceGrille.remove(indiceLigneColonne);
            grille.insertCarte(carte, indices[0], indices[1]);
        }

        // On place aléatoirement les cases noires.
        for (int k = 0; k < nbCarteNoire; k++) {
            // On sélectionne une paire (indieceLigne, indiceColonne) aléatoirement parmis celle restante.
            int indiceLigneColonne = random.nextInt(listeIndiceGrille.size());
            // On séléctionne un mot aléatoirement parmis ceux restant.
            int indiceMots = random.nextInt(listeIndiceMots.size());
            Carte carte = new Carte(NOIRE, mots.get(listeIndiceMots.remove(indiceMots)));
            Integer[] indices = listeIndiceGrille.remove(indiceLigneColonne);
            grille.insertCarte(carte, indices[0], indices[1]);
        }

        // On complète les cases vide de la grille avec des cartes civiles.
        for (Integer[] tabLigneColonne : listeIndiceGrille) {
            // On séléctionne un mot aléatoirement parmis ceux restant.
            int indiceMots = random.nextInt(listeIndiceMots.size());
            Carte carte = new Carte(CIVILE, mots.get(listeIndiceMots.remove(indiceMots)));
            grille.insertCarte(carte, tabLigneColonne[0], tabLigneColonne[1]);
        }

        return new Partie(grille, timer, nbCarte);
    }

}
