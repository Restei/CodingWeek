package grp04.jeu.modele;

import static grp04.jeu.modele.TypeJoueur.*;
import static grp04.jeu.modele.TypeTimer.*;
import static java.lang.Thread.sleep;

// Classe permettent de gérer les timers.
public class GestionnaireTemps extends SujetObserve {

    // Début propriétés

    private Partie partie;

    // Fin propriétés

    // Début constructeurs

    public GestionnaireTemps(Partie partie) {
        this.partie = partie;
    }

    // Fin constructeurs


    // Début méthodes

    /**
     * Lance un timer lorsqu'un joueur commence son tour de jeu.
     * @throws InterruptedException nécessaire pour ne pas avoir d'erreur.
     */
    public void lanceTimer() throws InterruptedException {
        TypeTimer type = partie.getTimer().getType();
        long time;
        if (type == INDIVIDUEL) {
            // Si le joueur qui joue a un rôle d'espion, on lance le timer des espions.
            if (partie.getJoueurQuiJoue() == ESPION) {
                time = partie.getTimer().getTimerEspion();
                partie.setTime(time/1000);
                while (time != 0 && partie.getJoueurQuiJoue() == ESPION) {
                    sleep(1000);
                    time = time - 1000;
                    partie.setTime(time);
                }
            }
            // Si le joueur qui joue a le rôle d'agent, on lance le timer des agents.
            if (partie.getJoueurQuiJoue() == AGENT) {
                time = partie.getTimer().getTimerAgent();
                partie.setTime(time/1000);
                while (time != 0 && partie.getJoueurQuiJoue() == AGENT) {
                    sleep(1000);
                    time = time - 1000;
                    partie.setTime(time);
                }
            }
        }
        NotifierObservateurs();
    }

    /**
     * Retourne le temps en seconde à afficher.
     * @return Partie.time en seconde.
     */
    public int getTemps() {
        return (int)(partie.getTime()/1000);
    }

    // Fin méthodes

}
