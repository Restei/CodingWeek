package grp04.jeu.modele;

import java.io.Serializable;

public class Statistique implements Serializable {

    // Début Propriétés

    private int nbCarteCivileTrouveParRouge = 0;
    private int nbCarteCivileTrouveParBleu = 0;
    private int TempsTotalEspionRouge = 0;
    private int TempsTotalEspionBleu = 0;
    private int TempsTotalAgentRouge = 0;
    private int TempsTotalAgentBleu = 0;
    private int nbCarteBleuRestante;
    private int nbCarteRougeRestante;
    private int nbCarteRougeTrouveParBleu = 0;
    private int nbCarteBleuTrouveParRouge = 0;
    private int nbCarteAssassinTrouveParBleu = 0;
    private int nbCarteAssassinTrouveParRouge = 0;
    private int nbTourJoueParRouge = 0;
    private int nbTourJoueParBleu = 0;
    private TypeEquipe gagnant;

    // Fin propriétés

    // Début constructeurs

    public Statistique(int nbCarteRougeRestante, int nbCarteBleuRestante) {
        this.nbCarteRougeRestante = nbCarteRougeRestante;
        this.nbCarteBleuRestante = nbCarteBleuRestante;
    }

    // Fin constructeurs

    // Début méthodes

    public int getNbCarteCivileTrouve(TypeEquipe typeEquipe) {
        if (typeEquipe == TypeEquipe.ROUGE) {
            return nbCarteCivileTrouveParRouge;
        }
        return nbCarteCivileTrouveParBleu;
    }

    public void incrementNbCarteCivileTrouve(TypeEquipe typeEquipe) {
        if (typeEquipe == TypeEquipe.ROUGE) {
            nbCarteCivileTrouveParRouge++;
        } else {
            nbCarteCivileTrouveParBleu++;
        }
    }

    public int getTempsTotal(TypeEquipe typeEquipe, TypeJoueur typeJoueur) {
        if (typeEquipe == TypeEquipe.ROUGE) {
            if (typeJoueur == TypeJoueur.AGENT) {
                return TempsTotalAgentRouge;
            }
            return TempsTotalEspionRouge;
        }
        if (typeJoueur == TypeJoueur.AGENT) {
            return TempsTotalAgentBleu;
        }
        return TempsTotalEspionBleu;
    }

    public void incrTempsTotal(TypeEquipe typeEquipe, TypeJoueur typeJoueur,int temps) {
        if (typeEquipe == TypeEquipe.ROUGE) {
            if (typeJoueur == TypeJoueur.AGENT) {
                TempsTotalAgentRouge+= temps;
            }
            else {
                TempsTotalEspionRouge += temps;
            }
        }
        else {
            if (typeJoueur == TypeJoueur.AGENT) {
                TempsTotalAgentBleu += temps;
            } else {
                TempsTotalEspionBleu += temps;
            }
        }
    }

    public int getNbCarteRestante(TypeEquipe typeEquipe) {
        if (typeEquipe == TypeEquipe.ROUGE) {
            return nbCarteRougeRestante;
        }
        return nbCarteBleuRestante;
    }

    public void dencrementNbCarteRestante(TypeEquipe typeEquipe) {
        if (typeEquipe == TypeEquipe.ROUGE) {
            nbCarteRougeRestante--;
        } else {
            nbCarteBleuRestante--;
        }
    }

    public int getNbCarteBleuTrouveParRouge() {
        return nbCarteBleuTrouveParRouge;
    }

    public void incrementNbCarteBleuTrouveParRouge() {
        nbCarteBleuTrouveParRouge++;
    }

    public int getNbCarteRougeTrouveParBleu() {
        return nbCarteRougeTrouveParBleu;
    }

    public void incrementNbCarteRougeTrouveParBleu() {
        nbCarteRougeTrouveParBleu++;
    }

    public int getNbCarteAssassinTrouve(TypeEquipe typeEquipe) {
        if (typeEquipe == TypeEquipe.ROUGE) {
            return nbCarteAssassinTrouveParRouge;
        }
        return nbCarteAssassinTrouveParBleu;
    }

    public void incrementNbCarteAssassinTrouve(TypeEquipe typeEquipe) {
        if (typeEquipe == TypeEquipe.ROUGE) {
            nbCarteAssassinTrouveParRouge++;
        } else {
            nbCarteAssassinTrouveParBleu++;
        }
    }

    public void incrementNbTourJoue(TypeEquipe typeEquipe) {
        if (typeEquipe == TypeEquipe.ROUGE) {
            nbTourJoueParRouge++;
        } else {
            nbTourJoueParBleu++;
        }
    }

    public TypeEquipe getGagnant() {
        return gagnant;
    }

    public String getPerdant() {
        if (gagnant==TypeEquipe.BLEU) return "ROUGE";
        return "BLEUE";
    }


    public int getNbTourJoue(){
        return Math.max(this.nbTourJoueParBleu, this.nbTourJoueParRouge);
    }

    public void setGagnant(TypeEquipe gagnant) {
        this.gagnant = gagnant;
    }

    // Fin méthodes

}
