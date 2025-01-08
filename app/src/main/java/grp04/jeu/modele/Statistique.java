package grp04.jeu.modele;

public class Statistique {

    // Début Propriétés

    int nbCarteCivileTrouveParRouge = 0;
    int nbCarteCivileTrouveParBleu = 0;
    int TempsTotalEspionRouge = 0;
    int TempsTotalEspionBleu = 0;
    int TempsTotalAgentRouge = 0;
    int TempsTotalAgentBleu = 0;
    int nbCarteBleuRestante;
    int nbCarteRougeRestante;
    int nbCarteRougeTrouveParBleu = 0;
    int nbCarteBleuTrouveParRouge = 0;
    int nbCarteAssassinTrouveParBleu = 0;
    int nbCarteAssassinTrouveParRouge = 0;
    int nbTourJoueParRouge = 0;
    int nbTourJoueParBleu = 0;

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

    public void setTempsTotal(TypeEquipe typeEquipe, TypeJoueur typeJoueur, int tempsTotal) {
        if (typeEquipe == TypeEquipe.ROUGE) {
            if (typeJoueur == TypeJoueur.AGENT) {
                TempsTotalAgentRouge = tempsTotal;
            }
            TempsTotalEspionRouge = tempsTotal;
        }
        if (typeJoueur == TypeJoueur.AGENT) {
            TempsTotalAgentBleu = tempsTotal;
        }
        TempsTotalEspionBleu = tempsTotal;
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

    public int getNbTourJoue(TypeEquipe typeEquipe) {
        if (typeEquipe == TypeEquipe.ROUGE) {
            return nbTourJoueParRouge;
        }
        return nbTourJoueParBleu;
    }

    public void incrementNbTourJoue(TypeEquipe typeEquipe) {
        if (typeEquipe == TypeEquipe.ROUGE) {
            nbTourJoueParRouge++;
        } else {
            nbTourJoueParBleu++;
        }
    }

    // Fin méthodes

}
