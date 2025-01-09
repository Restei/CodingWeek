package grp04.jeu.modele;

import grp04.jeu.ChargeurScene;
import grp04.jeu.vues.BoutonIncr;

public class GestionnaireMenuNewGame extends SujetObserve {

    private BoutonIncr.Type typeBoutonIncrSurvole;  // type du dernier BoutonIncr survolé par la souris

    // Début propriétés

    ChargeurScene chargeurScene;
    int taille;
    int nbCarte;
    int nbCarteNoire;
    TypeTimer typeTimer;
    int timerEspionBleu;
    int timerAgentRouge;
    Theme theme;

    private int pasVariationTemps = 10;  // pas pour le réglage des paramètres de temps

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
        this.theme = null;
    }

    // Fin constructeurs

    // Début méthodes

    public void creationpartie() {
        Partie partie = CreateurPartie.createurPartie(taille, nbCarte, nbCarteNoire, typeTimer, timerEspionBleu, timerAgentRouge, theme);
        Statistique statistique = new Statistique(partie.getNbCarteRouge(), partie.getNbCarteBleu());
        chargeurScene.chargerNouvellePartie(partie, statistique);
    }

    public void retourmenuprincipale() {
        chargeurScene.chargerMenuPrincipal();
    }

    public void incrTaille() {
        if (taille < 10) {
            taille++;
        }
        NotifierObservateurs();
    }

    public void decrTaille() {
        if (taille > 0) {
            taille--;
        }
        NotifierObservateurs();
    }

    public void incrNbCarte() {
        if (true) { // TODO Condition pertinente
            nbCarte++;
        }
        NotifierObservateurs();
    }

    public void decrNbCarte() {
        if (nbCarte > 0) {
            nbCarte--;
        }
        NotifierObservateurs();
    }

    public void incrNbCarteNoire() {
        if (true) {
            nbCarteNoire++;
        }
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
    }

    public void themePrecedent() {
    }

    public String getTheme() {
        if (theme == null) {
            return "Theme de démo";
        }
        return "A faire"; // TODO
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

    // Fin méthodes

}
