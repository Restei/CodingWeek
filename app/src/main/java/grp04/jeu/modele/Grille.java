package grp04.jeu.modele;

import grp04.jeu.vues.Observateur;

import java.util.ArrayList;

public class Grille extends SujetObserve {
    Carte[][] grille;
    public Grille(int i,int j){
        super();
        this.grille = new Carte[i][j];
        for (int k=0;k<i;k++){
            for (int l=0;l<j;l++){
                this.grille[k][l]=new Carte();
            }
        }
    }
    public int size(){
        return grille.length;
    }

    public Carte get(int i,int j){
        return grille[i][j];
    }
}
