package grp04.jeu.modele;

import static grp04.jeu.modele.TypeEquipe.*;
import static grp04.jeu.modele.TypeJoueur.*;

public class Partie {

    // Début proriétés
    private GestionnairePartie gestionnaire;
    private Grille grille;
    private Timer timer;
    private TypeEquipe equipeQuiJoue;
    private TypeJoueur joueurQuiJoue;
    private int nbCarteRouge;
    private int nbCarteBleu;
    private TypeEquipe gagnant = null;
    private long time = 0;

    // Fin propriétés


    // Début constructeurs

    public Partie(Grille grille, Timer timer, int nbCarte) {
        this.grille = grille;
        this.timer = timer;
        this.nbCarteRouge = nbCarte + 1;
        this.nbCarteBleu = nbCarte;
        equipeQuiJoue = ROUGE;
        joueurQuiJoue = ESPION;
    }

    public Partie(Grille grille, Timer timer, int nbCarte,GestionnairePartie gestionnaire) {
        this.grille = grille;
        this.timer = timer;
        this.nbCarteRouge = nbCarte + 1;
        this.nbCarteBleu = nbCarte;
        this.gestionnaire=gestionnaire;
        equipeQuiJoue = ROUGE;
        joueurQuiJoue = ESPION;

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

    public void setEquipeQuiJoue(TypeEquipe equipeQuiJoue) {
        this.equipeQuiJoue = equipeQuiJoue;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setGestionnaire(GestionnairePartie gestionnaire){
        this.gestionnaire = gestionnaire;
    }

    public GestionnairePartie getGestionnaire(){
        return this.gestionnaire;
    }
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
