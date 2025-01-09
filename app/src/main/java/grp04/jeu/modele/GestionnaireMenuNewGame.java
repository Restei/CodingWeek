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
    TypeTimer type;
    int timerEspionBleu;
    int timerAgentRouge;
    Theme theme;

    // Fin propriétés

    // Début constructeurs

    public GestionnaireMenuNewGame(ChargeurScene chargeurscene) {
        this.chargeurScene = chargeurscene;
        this.taille = 3;
        this.nbCarte = 2;
        this.nbCarteNoire = 2;
        this.type = TypeTimer.INDIVIDUEL;
        this.timerAgentRouge = 60;
        this.timerEspionBleu = 30;
        this.theme = null;
    }

    // Fin constructeurs

    // Début méthodes

    public void creationpartie() {
        Partie partie = CreateurPartie.createurPartie(taille, nbCarte, nbCarteNoire, type, timerEspionBleu, timerAgentRouge, theme);
        Statistique statistique = new Statistique(partie.getNbCarteRouge(), partie.getNbCarteBleu());
        chargeurScene.chargerNouvellePartie(partie,statistique);
    }

    public void retourmenuprincipale(){
        chargeurScene.chargerMenuPrincipal();
    }

    public void incrTaille() {
        taille++;
        NotifierObservateurs();
    }

    public void decrTaille() {
        if (taille > 0) {
            taille--;
        }
        NotifierObservateurs();
    }

    public void incrNbCarte() {
        nbCarte++;
        NotifierObservateurs();
    }

    public void decrNbCarte() {
        if (taille > 0) {
            nbCarte--;
        }
        NotifierObservateurs();
    }

    public void incrNbCarteNoire() {
        nbCarteNoire++;
        NotifierObservateurs();
    }

    public void decrNbCarteNoire() {
        if (taille > 0) {
            nbCarteNoire--;
        }
        NotifierObservateurs();
    }

    public void incrTimerEspionBleu() {
        timerEspionBleu++;
        NotifierObservateurs();
    }

    public void decrTimerEspionBleu() {
        if (timerEspionBleu > 0) {
            timerEspionBleu--;
        }
        NotifierObservateurs();
    }

    public void incrTimerAgentRouge() {
        timerAgentRouge++;
        NotifierObservateurs();
    }

    public void decrTimerAgentRouge() {
        if (timerAgentRouge > 0) {
            timerAgentRouge--;
        }
        NotifierObservateurs();
    }

    public void switchType() {
        if (type == TypeTimer.INDIVIDUEL) {
            type = TypeTimer.EQUIPE;
        } else {
            type = TypeTimer.INDIVIDUEL;
        }
        NotifierObservateurs();
    }

    public String getTaille() {
        return Integer.toString(taille);
    }

    public String getNbCarte() {
        return Integer.toString(nbCarte);
    }

    public String getNbCarteNoire() {
        return Integer.toString(nbCarteNoire);
    }

    public String getType() {
        if (type == TypeTimer.INDIVIDUEL) {
            return "Individuel";
        }
        return "Equipe";
    }

    public void themeSuivant() {}

    public void themePrecedent() {}

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
