package grp04.jeu.vues;

import grp04.jeu.modele.GestionnairePartie;
import javafx.scene.control.Label;

public class VueMotSelectionne extends Label implements Observateur {

    private final GestionnairePartie gestionnairePartie;

    public VueMotSelectionne(GestionnairePartie gestionnairePartie){
        super();
        this.gestionnairePartie = gestionnairePartie;
        gestionnairePartie.ajouterObservateur(this);
    }

    @Override
    public void reagir() {
        this.setText(gestionnairePartie.getPartie().getGrille().getCarteActuelle());
    }
}
