package grp04.jeu.vues;
import grp04.jeu.modele.GestionnairePartie;

import grp04.jeu.modele.Grille;
import grp04.jeu.modele.SujetObserve;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class VueGrille extends GridPane{
    private GestionnairePartie gestionnaire;
    private VueCarte[][] grille;


    public VueGrille(GestionnairePartie gestionnaire){
           this.gestionnaire = gestionnaire;
           this.grille = new VueCarte[gestionnaire.getPartie().getGrille().size()][gestionnaire.getPartie().getGrille().size()];
           for (int i =0;i<gestionnaire.getPartie().getGrille().size();i++) {
               for (int j = 0; j < gestionnaire.getPartie().getGrille().size(); j++) {
                   VueCarte carte = (new VueCarte(this.gestionnaire,i,j));
                   this.add(carte, i, j);
                   grille[i][j]= carte;
               }
           }

    }
}
