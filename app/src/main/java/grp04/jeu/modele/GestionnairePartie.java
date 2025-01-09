package grp04.jeu.modele;

import javafx.application.Platform;

import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import static grp04.jeu.modele.TypeCarte.*;
import static grp04.jeu.modele.TypeJoueur.*;
import static grp04.jeu.modele.TypeTimer.*;
import static java.lang.System.exit;

// Classe permettent de gérer une partie déjà créer.
public class GestionnairePartie extends SujetObserve {

    // Début propriétés

    private final Partie partie;
    private final Statistique statistique;
    // time permet au timer de communiquer le temps à afficher à VueChrono.
    public final AtomicInteger time = new AtomicInteger(0);
    private int sauvTime;

    // Fin propriétés


    // Début constructeurs

    public GestionnairePartie(Partie partie, Statistique statistique) {
        this.partie = partie;
        this.statistique = statistique;
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
        carte.reveler();
        NotifierObservateurs();
        // Si les agents de l'équipe trouve une carte noire.
        if (carte.getType() == NOIRE) {
            if (equipe == TypeEquipe.BLEU) {
                partie.setGagnant(TypeEquipe.ROUGE);
            } else {
                partie.setGagnant(TypeEquipe.BLEU);
            }
            switchRole();
        }
        else if (carte.getType() == ROUGE){
            partie.setNbCarteRouge(partie.getNbCarteRouge() - 1);
            if (partie.getNbCarteRouge()==0) {
                partie.setGagnant(TypeEquipe.ROUGE);
            }
            if (equipe == TypeEquipe.BLEU){
                switchRole();
            }
        }
        else if (carte.getType() == BLEU){
           partie.setNbCarteBleu(partie.getNbCarteBleu()-1);
           if (partie.getNbCarteBleu()==0){
               partie.setGagnant(TypeEquipe.BLEU);
           }
           if (equipe == TypeEquipe.ROUGE){
               switchRole();
           }
        }
        // Si les agents de l'équipe trouve une carte civile.
        else {
            switchRole();
        }
    }

    public void switchRole(){
        if (partie.getTimer().getType() == EQUIPE && partie.getJoueurQuiJoue() == ESPION && time.get() <= 0) {
            partie.switchRole();
        }
        partie.switchRole();
        NotifierObservateurs();
        time.set(0);
        NotifierObservateurs();
        lanceTimer();
    }

    /**
     * Lance un timer lorsqu'un joueur commence son tour de jeu.
     */
    public void lanceTimer() {
        boolean chargerTimer = time.get() <= 0;
        TypeTimer type = partie.getTimer().getType();
        java.util.Timer timer = new java.util.Timer();
        TimerTask taskTimer = new TimerTask() {
            @Override
            public void run() {
                time.set(time.get()-1);
                Platform.runLater(() -> NotifierObservateurs());
                if (time.get() < 0) {
                    timer.cancel();
                }
                if (time.get() == 0) {
                    Platform.runLater(() -> switchRole());
                    timer.cancel();
                }
            }
        };

        // Si le jeu à un timer en mode individuelle.
        if (type == INDIVIDUEL) {
            time.set(partie.getTimer().getTimerJoueur(partie.getJoueurQuiJoue())+1);
        }
        // Si le jeu à un timer en mode equipe
        else {
            if (time.get() <= 0 || partie.getJoueurQuiJoue() == ESPION) {
                time.set(partie.getTimer().getTimerEquipe(partie.getEquipeQuiJoue()) + 1);
            }
        }
        if (chargerTimer) {
            timer.schedule(taskTimer, 0, 1000);
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
     * Permet de retrouner le nombre de cartes restantes de l'équipe bleu.
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
        if (sauvTime == 0) {
            return this.time.get();
        }
        return sauvTime;
    }

    public Partie getPartie(){
        return this.partie;
    }

    /**
     * Permet de mettre en pause le chrono.
     */
    public void pauseChrono() {
        sauvTime = time.get();
        time.set(-1);
    }

    /**
     * Permet de relancer le chrono.
     */
    public void reprendreChrono() {
        time.set(sauvTime);
        sauvTime = 0;
        java.util.Timer timer = new java.util.Timer();
        TimerTask taskTimer = new TimerTask() {
            @Override
            public void run() {
                time.set(time.get()-1);
                Platform.runLater(() -> NotifierObservateurs());
                if (time.get() < 0) {
                    timer.cancel();
                }
                if (time.get() == 0) {
                    Platform.runLater(() -> switchRole());
                    timer.cancel();
                }
            }
        };
        timer.schedule(taskTimer, 0, 1000);
    }

    public void sauvegarderPartie(String nomSauvegarde) {
        GestionnaireSauvegarde.sauvegarder(nomSauvegarde, partie, statistique);
    }

    public void debutPartie() {
        lanceTimer();
    }

    // Fin méthodes

}
