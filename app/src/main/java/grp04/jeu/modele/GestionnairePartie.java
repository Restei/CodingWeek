package grp04.jeu.modele;

import javafx.scene.control.Alert;

import static grp04.jeu.modele.TypeCarte.*;
import static grp04.jeu.modele.TypeJoueur.*;

// Classe permettent de gérer une partie.
public class GestionnairePartie extends SujetObserve {

    // Début propriétés

    private Partie partie;

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
        partie.switchRole();
        NotifierObservateurs();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("Passez à: " + " " + partie.getJoueurQuiJoue() + " " + partie.getEquipeQuiJoue() + " "  );

        alert.showAndWait();
        NotifierObservateurs();
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

    public Partie getPartie(){
        return this.partie;
    }
    // Fin méthodes

}
