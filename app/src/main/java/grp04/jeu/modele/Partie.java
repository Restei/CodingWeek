package grp04.jeu.modele;

import java.io.Serializable;

import static grp04.jeu.modele.TypeEquipe.*;
import static grp04.jeu.modele.TypeJoueur.*;

public class Partie implements Serializable {

    // Début proriétés

    private final Grille grille;
    private final Timer timer;
    private TypeEquipe equipeQuiJoue = ROUGE;
    private TypeJoueur joueurQuiJoue = ESPION;
    private int nbCarteRouge;
    private int nbCarteBleu;
    private TypeEquipe gagnant = null;

    // Fin propriétés


    // Début constructeurs

    public Partie(Grille grille, Timer timer, int nbCarte) {
        this.grille = grille;
        this.timer = timer;
        this.nbCarteRouge = nbCarte + 1;
        this.nbCarteBleu = nbCarte;
    }

    // Fin constructeurs


    // Début méthodes

    public Grille getGrille() {
        return grille;
    }

    public Timer getTimer() {
        return timer;
    }

    public TypeEquipe getEquipeQuiJoue() {
        return equipeQuiJoue;
    }

    public TypeJoueur getJoueurQuiJoue() {
        return joueurQuiJoue;
    }

    public int getNbCarteRouge() {
        return nbCarteRouge;
    }

    public void setNbCarteRouge(int nbCarteRouge) {
        this.nbCarteRouge = nbCarteRouge;
    }

    public int getNbCarteBleu() {
        return nbCarteBleu;
    }

    public void setNbCarteBleu(int nbCarteBleu) {
        this.nbCarteBleu = nbCarteBleu;
    }

    public TypeEquipe getGagnant() {
        return gagnant;
    }

    public void setGagnant(TypeEquipe gagnant) {
        this.gagnant = gagnant;
    }

    /**
     * Permet d'actualiser la partie après qu'un joueur est joué
     */
    public void switchRole(){
        this.getGrille().switchRole();
        if (joueurQuiJoue == ESPION){
            joueurQuiJoue = AGENT;
        }
        else{
            joueurQuiJoue = ESPION;
            if (equipeQuiJoue == ROUGE){
                equipeQuiJoue = BLEU;
            }
            else {
                equipeQuiJoue = ROUGE;
            }
        }
    }

    // Fin méthodes

}
