package grp04.jeu.modele;

import java.io.Serializable;

import static grp04.jeu.modele.TypeEquipe.*;
import static grp04.jeu.modele.TypeTimer.*;

public class Timer implements Serializable {

    // Début propriétés

    private TypeTimer type;
    private int timerEspion = 0;
    private int timerAgent = 0;
    private int timerEquipeBleu = 0;
    private int timerEquipeRouge = 0;

    // Fin propriétés


    // Début constructeur

    public Timer(TypeTimer type, int timerEspionBleu, int timerAgentRouge) {
        if (type == INDIVIDUEL) {
            this.type = INDIVIDUEL;
            timerEspion = timerEspionBleu;
            timerAgent = timerAgentRouge;
        } else {
            this.type = type;
            timerEquipeBleu = timerEspionBleu;
            timerEquipeRouge = timerAgentRouge;
        }
    }

    // Fin constructeur


    // Début méthodes

    public int getTimerJoueur(TypeJoueur joueur) {
        if (joueur == TypeJoueur.AGENT) {
            return timerAgent;
        } else {
            return timerEspion;
        }
    }

    public int getTimerEquipe(TypeEquipe typeEquipe) {
        if (typeEquipe == BLEU) {
            return timerEquipeBleu;
        } else {
            return timerEquipeRouge;
        }
    }

    public TypeTimer getType() {
        return type;
    }

    // Fin méthodes

}
