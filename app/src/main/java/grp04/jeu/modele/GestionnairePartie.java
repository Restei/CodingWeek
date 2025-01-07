package grp04.jeu.modele;

import javafx.application.Platform;

import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import static grp04.jeu.modele.TypeCarte.*;
import static grp04.jeu.modele.TypeJoueur.*;
import static grp04.jeu.modele.TypeTimer.*;

// Classe permettent de gérer une partie.
public class GestionnairePartie extends SujetObserve {

    // Début propriétés

    private Partie partie;
    final AtomicInteger time = new AtomicInteger(0);

    // Fin propriétés


    // Début constructeurs

    public GestionnairePartie(Partie partie) {
        this.partie = partie;
    }

    // Fin constructeurs


    //Début méthodes

    /**
     * Permet d'actualiser la partie si la carte de coordonnée i,j est jouée.
     * @param i numéro de la ligne de la carte jouée.
     * @param j numéro de la colonne de la carte jouée.
     */
    public void jouer(int i, int j) {
        Carte carte = partie.getGrille().getCarte(i, j);
        TypeEquipe equipe = partie.getEquipeQuiJoue();
        int nbCarte;

        // Si les agents de l'équipe equipe trouve une carte noire.
        if (carte.getType() == NOIRE) {
            if (equipe == TypeEquipe.BLEU) {
                partie.setGagnant(TypeEquipe.ROUGE);
            } else {
                partie.setGagnant(TypeEquipe.BLEU);
            }
        }

        // Si les agents de l'équipe equipe trouve une carte civile.
        if (carte.getType() == CIVILE) {
            if (equipe == TypeEquipe.BLEU) {
                partie.setEquipeQuiJoue(TypeEquipe.ROUGE);
            } else {
                partie.setEquipeQuiJoue(TypeEquipe.BLEU);
            }
        }

        // Si les agents de l'équipe equipe trouve une carte rouge.
        if (carte.getType() == ROUGE) {
            if (equipe == TypeEquipe.ROUGE) {
                nbCarte = partie.getNbCarteRouge();
                partie.setNbCarteRouge(partie.getNbCarteRouge() - 1);
                if (nbCarte == 0) {
                    partie.setGagnant(TypeEquipe.ROUGE);
                }
            } else {
                partie.setEquipeQuiJoue(TypeEquipe.BLEU);
            }
        }

        // Si les agents de l'équipe equipe trouve une carte bleu.
        if (carte.getType() == BLEU) {
            if (equipe == TypeEquipe.BLEU) {
                nbCarte = partie.getNbCarteBleu();
                partie.setNbCarteBleu(partie.getNbCarteBleu() - 1);
                if (nbCarte == 0) {
                    partie.setGagnant(TypeEquipe.BLEU);
                }
            } else {
                partie.setEquipeQuiJoue(TypeEquipe.ROUGE);
            }
        }

        NotifierObservateurs();
    }

    /**
     * Lance un timer lorsqu'un joueur commence son tour de jeu.
     */
    public void lanceTimer() {
        TypeTimer type = partie.getTimer().getType();
        java.util.Timer timer = new java.util.Timer();

        // Si le jeu à un timer en mode individuelle.
        if (type == INDIVIDUEL) {
            // Si le joueur qui joue a un rôle d'espion, on lance le timer des espions.
            if (partie.getJoueurQuiJoue() == ESPION) {
                time.set(partie.getTimer().getTimerEspion()/1000);
                TimerTask taskEspion = new TimerTask() {
                    @Override
                    public void run() {
                        time.set(time.get()-1);
                        Platform.runLater(() -> NotifierObservateurs());
                        if (time.get() == 0 || partie.getJoueurQuiJoue() == AGENT) {
                            timer.cancel();
                        }
                    }
                };
                timer.schedule(taskEspion, 0, 1000);
            }
            // Si le joueur qui joue a le rôle d'agent, on lance le timer des agents.
            if (partie.getJoueurQuiJoue() == AGENT) {
                time.set(partie.getTimer().getTimerAgent()/1000);
                TimerTask taskAgent = new TimerTask() {
                    @Override
                    public void run() {
                        time.set(time.get()-1);
                        Platform.runLater(() -> NotifierObservateurs());
                        if (time.get() == 0 || partie.getJoueurQuiJoue() == ESPION) {
                            timer.cancel();
                        }
                    }
                };
                timer.schedule(taskAgent, 0, 1000);
            }
        }
    }

    /**
     * Permet de retourner l'équipe qui joue.
     * @return false si l'équipe est rouge, true si l'équipe est bleu.
     */
    public boolean getEquipe() {
        return partie.getEquipeQuiJoue() != TypeEquipe.ROUGE;
    }

    /**
     * Permet de retourner le rôle du joueur qui est entrain de jouer.
     * @return false si le joueur est espion, et true si le joueur est agent.
     */
    public boolean getRole() {
        return partie.getJoueurQuiJoue() != ESPION;
    }

    /**
     * Permet de retourner le nombre de cartes restantes de l'équipe rouge.
     * @return Partie.NbCarteRouge
     */
    public int getNbCarteRouge() {
        return partie.getNbCarteRouge();
    }

    /**
     * Prmet de retrouner le nombre de cartes restantes de l'équipe bleu.
     * @return Partie.NbCarteBleu
     */
    public int getNbCarteBleu() {
        return partie.getNbCarteBleu();
    }

    /**
     * Retourne le temps en seconde à afficher.
     * @return time
     */
    public int getTemps() {
        return this.time.get();
    }

    // Fin méthodes

}
