package grp04.jeu.modele;

import static grp04.jeu.modele.TypeEquipe.*;
import static grp04.jeu.modele.TypeTimer.*;

public class Timer {

    // Début propriétés

    private TypeTimer type;
    private long timerEspion = 0;
    private long timerAgent = 0;
    private long timerEquipeBleu = 0;
    private long timerEquipeRouge = 0;

    // Fin propriétés


    // Début constructeur

    public Timer(TypeTimer type, long timerEspionBleu, long timerAgentRouge) {
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

    public long getTimerAgent() {
        return timerAgent;
    }

    public long getTimerEspion() {
        return timerEspion;
    }

    public long getTimerEquipe(TypeEquipe typeEquipe) {
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
