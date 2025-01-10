package grp04.jeu.modele;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import grp04.jeu.vues.Overlay;
import grp04.jeu.vues.PopupChangerJoueur;

import static grp04.jeu.modele.TypeCarte.*;
import static grp04.jeu.modele.TypeJoueur.*;
import static grp04.jeu.modele.TypeTimer.*;

// Classe permettent de gérer une partie déjà créer.
public class GestionnairePartie extends SujetObserve {

    // Début propriétés

    private final Partie partie;
    private final Statistique statistique;
    // time permet au timer de communiquer le temps à afficher à VueChrono.
    private final AtomicInteger time = new AtomicInteger(0);
    private int sauvTime;
    private final Overlay overlay;

    // Fin propriétés


    // Début constructeurs

    public GestionnairePartie(Partie partie, Statistique statistique, Overlay overlay) {
        this.partie = partie;
        this.statistique = statistique;
        this.overlay = overlay;
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
            statistique.incrementNbCarteAssassinTrouve(equipe);
            if (equipe == TypeEquipe.BLEU) {
                partie.setGagnant(TypeEquipe.ROUGE);
                statistique.setGagnant(TypeEquipe.ROUGE);
            } else {
                partie.setGagnant(TypeEquipe.BLEU);
                statistique.setGagnant(TypeEquipe.BLEU);
            }
            try {
                Media media = new Media(getClass().getResource("/marcha-funebre-8-bits-260615.mp3").toURI().toString());
                MediaPlayer player = new MediaPlayer(media);
                player.play();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            switchRole();
        }
        else if (carte.getType() == TypeCarte.ROUGE){
            statistique.dencrementNbCarteRestante(TypeEquipe.ROUGE);
            partie.setNbCarteRouge(partie.getNbCarteRouge() - 1);
            if (partie.getNbCarteRouge()==0) {
                partie.setGagnant(TypeEquipe.ROUGE);
                statistique.setGagnant(TypeEquipe.ROUGE);
                try {
                    Media media = new Media(getClass().getResource("/tada-234709.mp3").toURI().toString());
                    MediaPlayer player = new MediaPlayer(media);
                    player.play();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            if (equipe == TypeEquipe.BLEU){
                statistique.incrementNbCarteRougeTrouveParBleu();
                switchRole();
            }
        }
        else if (carte.getType() == TypeCarte.BLEU){
           statistique.dencrementNbCarteRestante(TypeEquipe.BLEU);
           partie.setNbCarteBleu(partie.getNbCarteBleu()-1);
           if (partie.getNbCarteBleu()==0){
               partie.setGagnant(TypeEquipe.BLEU);
               statistique.setGagnant(TypeEquipe.BLEU);
               try {
                   Media media = new Media(getClass().getResource("/tada-234709.mp3").toURI().toString());
                   MediaPlayer player = new MediaPlayer(media);
                   player.play();
               } catch (URISyntaxException e) {
                   e.printStackTrace();
               }
           }
           if (equipe == TypeEquipe.ROUGE){
               statistique.incrementNbCarteBleuTrouveParRouge();
               switchRole();
           }
        }
        // Si les agents de l'équipe trouve une carte civile.
        else {
            statistique.incrementNbCarteCivileTrouve(equipe);
            switchRole();
        }
    }

    /**
     * Permet de changer les attributs de partie en fonction de la nouvelle situation.
     */
    public void switchRole(){
        int total_time;
        if (partie.getTimer().getType() == INDIVIDUEL) total_time= partie.getTimer().getTimerJoueur(partie.getJoueurQuiJoue());
        else total_time=partie.getTimer().getTimerEquipe(partie.getEquipeQuiJoue());
        statistique.incrTempsTotal(partie.getEquipeQuiJoue(), partie.getJoueurQuiJoue(),total_time- time.get());
        if (partie.getJoueurQuiJoue() == AGENT) {
            statistique.incrementNbTourJoue(partie.getEquipeQuiJoue());
        }
        if (partie.getTimer().getType() == EQUIPE && partie.getJoueurQuiJoue() == ESPION && time.get() <= 0) {
            partie.switchRole();
        }
        partie.switchRole();
        if (partie.getTimer().getType() == INDIVIDUEL || partie.getJoueurQuiJoue() == ESPION) {
            time.set(-1);
        }
        if (partie.getTimer().getType() == EQUIPE && partie.getJoueurQuiJoue() == AGENT) {
            pauseChrono();
        }
        PopupChangerJoueur popupChangerJoueur = new PopupChangerJoueur(overlay, this);
        overlay.ajouterEtAfficherPopup(popupChangerJoueur);
        NotifierObservateurs();
    }

    /**
     * Lance un timer lorsqu'un joueur commence son tour de jeu.
     */
    public void lanceTimer() {
        boolean chargerTimer = time.get() <= 0;
        TypeTimer type = partie.getTimer().getType();
        java.util.Timer timer = new java.util.Timer(true);
        TimerTask taskTimer = new TimerTask() {
            @Override
            public void run() {
                time.set(time.get()-1);
                Platform.runLater(() -> NotifierObservateurs());
                if (time.get() < 0) {
                    timer.cancel();
                }
                if (time.get() <= 10 && time.get()>0){
                    Platform.runLater(() -> {
                        try {
                            Media media = new Media(getClass().getResource("/tic-tac-81751.mp3").toURI().toString());
                            MediaPlayer mediaPlayer = new MediaPlayer(media);
                            mediaPlayer.setStopTime(new Duration(1000));
                            mediaPlayer.play();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    });
                }
                if (time.get() == 0) {
                    Platform.runLater(() -> {
                        try {
                            Media media = new Media(getClass().getResource("/analog-timer-74998.mp3").toURI().toString());
                            MediaPlayer mediaPlayer = new MediaPlayer(media);
                            mediaPlayer.setStartTime(new Duration(14000));
                            mediaPlayer.setStopTime(new Duration(16000));
                            mediaPlayer.play();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    });
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
                time.set(partie.getTimer().getTimerEquipe(partie.getEquipeQuiJoue())+1);
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
            if (time.get() < 0) {
                if (partie.getTimer().getType()==INDIVIDUEL){
                    if (partie.getJoueurQuiJoue()==AGENT){
                        return partie.getTimer().getTimerJoueur(AGENT);
                    }
                    else{
                        return partie.getTimer().getTimerJoueur(ESPION);
                    }
                }
                else{
                    if (partie.getEquipeQuiJoue() == TypeEquipe.ROUGE){
                        return partie.getTimer().getTimerEquipe(TypeEquipe.ROUGE);
                    }
                    else{
                        return partie.getTimer().getTimerEquipe(TypeEquipe.BLEU);
                    }
                }
            }
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
        java.util.Timer timer = new java.util.Timer(true);
        TimerTask taskTimer = new TimerTask() {
            @Override
            public void run() {
                time.set(time.get()-1);
                Platform.runLater(() -> NotifierObservateurs());
                if (time.get() < 0) {
                    timer.cancel();
                }
                if (time.get() <= 10 && time.get()>0){
                    Platform.runLater(() -> {
                        try {
                            Media media = new Media(getClass().getResource("/tic-tac-81751.mp3").toURI().toString());
                            MediaPlayer mediaPlayer = new MediaPlayer(media);
                            mediaPlayer.setStopTime(new Duration(1000));
                            mediaPlayer.play();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    });
                }
                if (time.get() == 0) {
                    Platform.runLater(() -> {
                        try {
                            Media media = new Media(getClass().getResource("/analog-timer-74998.mp3").toURI().toString());
                            MediaPlayer mediaPlayer = new MediaPlayer(media);
                            mediaPlayer.setStartTime(new Duration(14000));
                            mediaPlayer.setStopTime(new Duration(16000));
                            mediaPlayer.play();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    });
                    Platform.runLater(() -> switchRole());
                    timer.cancel();
                }
            }
        };
        timer.schedule(taskTimer, 0, 1000);
    }

    /**
     * Permet de lancer un nouveau timer si c'est au tour de    l'espion de l'équipe adverse, ou de seulement relancer le chrono si le joueur suivant est un agent, en mode timer équipe.
     */
    public void lancerTimerIfEquipeEtEspion() {
        if (partie.getTimer().getType() == EQUIPE && partie.getJoueurQuiJoue() == AGENT) {
            reprendreChrono();
        } else {
            lanceTimer();
        }
    }

    public Statistique getStatistique(){
        return this.statistique;
    }

    public void sauvegarderPartie(String nomSauvegarde) {
        GestionnaireSauvegarde.sauvegarder(nomSauvegarde, partie, statistique);
    }

    public void debutPartie() {
        lanceTimer();
    }

    // Fin méthodes

}
