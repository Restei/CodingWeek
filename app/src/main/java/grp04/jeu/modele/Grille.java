package grp04.jeu.modele;

public class Grille extends SujetObserve{

    // Début propriétés

    private Carte[][] grille;
    // Fin propriétés


    // Début constructeurs


    public Grille(int n) {

        this.grille = new Carte[n][n];
    }

    // Fin constructeurs


    // Début méthodes

    public void insertCarte(Carte carte, int x, int y) {
        grille[x][y] = carte;
    }

    public Carte getCarte(int x, int y) {
        return grille[x][y];
    }

    public void switchRole(){
        for (int i =0;i<grille.length;i++){
            for (int j = 0;j< grille.length;j++){
                grille[i][j].switchRole();
            }
        }
    }
    public int size(){
        return grille.length;
    }

    // Fin méthodes

}
