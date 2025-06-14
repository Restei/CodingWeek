package grp04.jeu.vues;
import grp04.jeu.modele.GestionnairePartie;

import javafx.scene.layout.GridPane;

public class VueGrille extends GridPane{
    private VueCarte[][] grille;


    public VueGrille(GestionnairePartie gestionnaire){
        this.setHgap(8);
        this.setVgap(8);
        this.grille = new VueCarte[gestionnaire.getPartie().getGrille().size()][gestionnaire.getPartie().getGrille().size()];
        for (int i =0;i<gestionnaire.getPartie().getGrille().size();i++) {
            for (int j = 0; j < gestionnaire.getPartie().getGrille().size(); j++) {
                VueCarte carte = (new VueCarte(gestionnaire,i,j));
                this.add(carte, i, j);
                grille[i][j]= carte;
            }
        }

    }
}
