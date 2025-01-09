package grp04.jeu.modele;

import grp04.jeu.ChargeurScene;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireMenuNewGame extends SujetObserve{

    // Début propriétés

    ChargeurScene chargeurScene;
    private int taille;
    private int nbCarte;
    private int nbCarteNoire;
    private TypeTimer type;
    private int timerEspionBleu;
    private int timerAgentRouge;
    private int indiceTheme;
    private List<String> mots = new ArrayList<>();

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
        indiceTheme = 0;
    }

    // Fin constructeurs

    // Début méthodes

    public void creationpartie() {
        Partie partie = CreateurPartie.createurPartie(taille, nbCarte, nbCarteNoire, type, timerEspionBleu, timerAgentRouge, mots);
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
            timerAgentRouge = 90;
            timerEspionBleu = 90;
        } else {
            type = TypeTimer.INDIVIDUEL;
            timerAgentRouge = 60;
            timerEspionBleu = 30;
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

    public void themeSuivant() {
        List<String> listeTheme = GestionnaireThemes.themes();
        if (indiceTheme < listeTheme.size()-1) {
            indiceTheme++;
        } else {
            indiceTheme = 0;
        }
        if (indiceTheme < listeTheme.size()-1 && indiceTheme > 0) {
            mots = GestionnaireThemes.mots(listeTheme.get(indiceTheme));
        }
        NotifierObservateurs();
    }

    public void themePrecedent() {
        List<String> listeTheme = GestionnaireThemes.themes();
        if (indiceTheme > 0) {
            indiceTheme--;
        } else {
            indiceTheme = listeTheme.size()-1;
        }
        if (indiceTheme < listeTheme.size()-1 && indiceTheme > 0) {
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

    // Fin méthodes

}
