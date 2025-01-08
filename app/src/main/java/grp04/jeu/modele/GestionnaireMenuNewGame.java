package grp04.jeu.modele;

import grp04.jeu.ChargeurScene;

public class GestionnaireMenuNewGame extends SujetObserve{

    // Début propriétés

    ChargeurScene chargeurScene;
    int taille;
    int nbCarte;
    int nbCarteNoire;
    TypeTimer type;
    int timerEspionBleu;
    int timerAgentRouge;
    Theme theme = null;

    // Fin propriétés

    // Début constructeurs

    public GestionnaireMenuNewGame() {}

    // Fin constructeurs

    // Début méthodes

    public void creationpartie() {
        Partie partie = CreateurPartie.createurPartie(taille, nbCarte, nbCarteNoire, type, timerEspionBleu, timerAgentRouge, theme);
        Statistique statistique = new Statistique(partie.getNbCarteRouge(), partie.getNbCarteBleu());
        chargeurScene.chargerNouvellePartie(partie, statistique);
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
    }

    public int getTaille() {
        return taille;
    }

    public int getNbCarte() {
        return nbCarte;
    }

    public int getNbCarteNoire() {
        return nbCarteNoire;
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

    public int getTimerEspionBleu() {
        return timerEspionBleu;
    }

    public int getTimerAgentRouge() {
        return timerAgentRouge;
    }

    // Fin méthodes

}
