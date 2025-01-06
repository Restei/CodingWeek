package grp04.jeu.vues;
import grp04.jeu.modele.Grille;
import grp04.jeu.modele.SujetObserve;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class VueGrille extends GridPane implements Observateur{
    private Grille grille;
    private VueCarte[][] agent;
    private VueCarte[][] espion;
    private int index;

    public VueGrille(Grille grille){
           this.grille = grille;
           grille.ajouterObservateur(this);
           this.index = 0;
           this.agent = new VueCarte[grille.size()][grille.size()];
           this.espion = new VueCarte[grille.size()][grille.size()];
           for (int i =0;i<grille.size();i++) {
               for (int j = 0; j < grille.size(); j++) {
                   VueCarte carte = (new VueCarte(grille.getCarte(i, j), false));
                   this.add(carte, i, j);
                   agent[i][j]= carte;
                   espion[i][j] = (new VueCarte(grille.getCarte(i, j), true));
               }
           }
    }

    public void reagir(){
        this.getChildren().clear();
        if (index==0){
            index=1;
            for (int i =0;i< agent.length;i++){
                for (int j =0;j<agent.length;j++){
                    this.add(espion[i][j],i,j);
                }
            }
        }
        else{
            index=0;
            for (int i =0;i< agent.length;i++){
                for (int j =0;j<agent.length;j++){
                    this.add(agent[i][j],i,j);
                }
            }

        }
    }
}
