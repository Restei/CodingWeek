package grp04.jeu.modele;

import grp04.jeu.ChargeurScene;
import grp04.jeu.vues.BoutonIncr;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireMenuNewGame extends SujetObserve {

    private BoutonIncr.Type typeBoutonIncrSurvole;  // type du dernier BoutonIncr survolé par la souris

    // Début propriétés

    ChargeurScene chargeurScene;
    private int taille;
    /**
     * Nombre de cartes de l'équipe qui ne commence pas (équipe bleue).
     */
    private int nbCarte;
    private int nbCarteNoire;
    private TypeTimer typeTimer;
    private int timerEspionBleu;
    private int timerAgentRouge;
    private int indiceTheme;
    private List<String> mots = new ArrayList<>();

    private final int pasVariationTemps = 10;  // pas pour le réglage des paramètres de temps
    private final int tailleMaxGrille = 10; // taille maximum du côté du plateau
    private final int tailleMinGrille = 2; // taille minmum du côté du plateau

    // Fin propriétés

    // Début constructeurs

    public GestionnaireMenuNewGame(ChargeurScene chargeurscene) {
        this.chargeurScene = chargeurscene;
        this.taille = 3;
        this.nbCarte = 2;
        this.nbCarteNoire = 2;
        this.typeTimer = TypeTimer.INDIVIDUEL;
        this.timerAgentRouge = 60;
        this.timerEspionBleu = 30;
        indiceTheme = 0;
    }

    // Fin constructeurs

    // Début méthodes

    public void creationpartie() {
        Partie partie = CreateurPartie.createurPartie(taille, nbCarte, nbCarteNoire, typeTimer, timerEspionBleu, timerAgentRouge, mots);
        Statistique statistique = new Statistique(partie.getNbCarteRouge(), partie.getNbCarteBleu());
        chargeurScene.chargerNouvellePartie(partie, statistique);
    }

    public void retourmenuprincipale() {
        chargeurScene.chargerMenuPrincipal();
    }

    public void incrTaille() {
        if (taille < tailleMaxGrille) {
            taille++;
        }
        NotifierObservateurs();
    }

    public void decrTaille() {
        if (taille > tailleMinGrille) {
            taille--;
        }
        NotifierObservateurs();
    }

    public void incrNbCarte() {
        nbCarte++;
        NotifierObservateurs();
    }

    public void decrNbCarte() {
        if (nbCarte > 1) {
            nbCarte--;
        }
        NotifierObservateurs();
    }

    public void incrNbCarteNoire() {
        nbCarteNoire++;
        NotifierObservateurs();
    }

    public void decrNbCarteNoire() {
        if (nbCarteNoire > 0) {
            nbCarteNoire--;
        }
        NotifierObservateurs();
    }

    public void incrTimerEspionBleu() {
        timerEspionBleu += pasVariationTemps;
        NotifierObservateurs();
    }

    public void decrTimerEspionBleu() {
        if (timerEspionBleu > pasVariationTemps) {
            timerEspionBleu -= pasVariationTemps;
        }
        NotifierObservateurs();
    }

    public void incrTimerAgentRouge() {
        timerAgentRouge += pasVariationTemps;
        NotifierObservateurs();
    }

    public void decrTimerAgentRouge() {
        if (timerAgentRouge > pasVariationTemps) {
            timerAgentRouge -= pasVariationTemps;
        }
        NotifierObservateurs();
    }

    public void switchType() {
        // TODO éventuellement calculer le temps total dynamiquement
        if (typeTimer == TypeTimer.INDIVIDUEL) {
            typeTimer = TypeTimer.EQUIPE;
            timerAgentRouge = 90;
            timerEspionBleu = 90;
        } else {
            typeTimer = TypeTimer.INDIVIDUEL;
            timerAgentRouge = 60;
            timerEspionBleu = 30;
        }
        NotifierObservateurs();
    }

    public String getTaille() {
        return Integer.toString(taille);
    }

    public int getNbCarte() {
        return nbCarte;
    }

    public String getNbCarteNoire() {
        return Integer.toString(nbCarteNoire);
    }

    public TypeTimer getTypeTimer() {
        return typeTimer;
    }

    public void themeSuivant() {
        List<String> listeTheme = GestionnaireThemes.themes();
        if (indiceTheme < listeTheme.size() - 1) {
            indiceTheme++;
        } else {
            indiceTheme = 0;
        }
        if (indiceTheme < listeTheme.size() - 1 && indiceTheme > 0) {
            mots = GestionnaireThemes.mots(listeTheme.get(indiceTheme));
        }
        NotifierObservateurs();
    }

    public void themePrecedent() {
        List<String> listeTheme = GestionnaireThemes.themes();
        if (indiceTheme > 0) {
            indiceTheme--;
        } else {
            indiceTheme = listeTheme.size() - 1;
        }
        if (indiceTheme < listeTheme.size() - 1 && indiceTheme > 0) {
            mots = GestionnaireThemes.mots(listeTheme.get(indiceTheme));
        }
        NotifierObservateurs();
    }

    public String getTheme() {
        List<String> listeTheme = GestionnaireThemes.themes();
        if (listeTheme.isEmpty()) {
            return "Theme de démo";
        }
        return listeTheme.get(indiceTheme);
    }

    public String getTimerEspionBleu() {
        return Integer.toString(timerEspionBleu);
    }

    public String getTimerAgentRouge() {
        return Integer.toString(timerAgentRouge);
    }

    public BoutonIncr.Type getTypeBoutonIncrSurvole() {
        return typeBoutonIncrSurvole;
    }

    public void setTypeBoutonIncrSurvole(BoutonIncr.Type typeBoutonIncrSurvole) {
        this.typeBoutonIncrSurvole = typeBoutonIncrSurvole;
        NotifierObservateurs();
    }

    /**
     * Indique si les valeurs fournies permettent de créer une partie valide.
     *
     * @param taille             taille du côté de la grille
     * @param nbCartesEquipeBleu nombre de cartes à deviner pour l'équipe qui ne commence pas
     * @param nbCartesNoires     nombre de cartes noires sur le plateau
     * @return true si les paramètres permettent de créer une partie valide
     */
    public boolean estUneConfigValide(int taille, int nbCartesEquipeBleu, int nbCartesNoires) {
        if (taille <= 0 || taille > tailleMaxGrille) {
            return false;
        }
        int nbCartesEquipeRouge = nbCartesEquipeBleu + 1;
        return (taille * taille >= nbCartesEquipeBleu + nbCartesEquipeRouge + nbCartesNoires);
    }

    /**
     * Indique si la configuration actuelle permet de créer une partie valide.
     */
    public boolean estUneConfigValide() {
        return estUneConfigValide(this.taille, this.nbCarte, this.nbCarteNoire);
    }

    // Fin méthodes

}
