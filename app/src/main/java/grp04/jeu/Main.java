package grp04.jeu;

import grp04.jeu.modele.*;
import grp04.jeu.vues.VuePartie;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application  {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Grille g = new Grille(3);
        Partie partie = new Partie(g,null,10);
        g.setPartie(partie);
        GestionnairePartie gestionnairePartie = new GestionnairePartie(partie);
        partie.setGestionnaire(gestionnairePartie);
        g.insertCarte(new Carte(TypeCarte.ROUGE,"Tour",g),0,0);
        g.insertCarte(new Carte(TypeCarte.NOIRE,"Reine",g),1,0);
        g.insertCarte(new Carte(TypeCarte.BLEU,"Dame",g),2,0);
        g.insertCarte(new Carte(TypeCarte.CIVILE,"Cavalier",g),0,1);
        g.insertCarte(new Carte(TypeCarte.ROUGE,"Fou",g),1,1);
        g.insertCarte(new Carte(TypeCarte.NOIRE,"Roi",g),2,1);
        g.insertCarte(new Carte(TypeCarte.CIVILE,"Tour",g),0,2);
        g.insertCarte(new Carte(TypeCarte.BLEU,"Fou",g),1,2);
        g.insertCarte(new Carte(TypeCarte.ROUGE,"Cavalier",g),2,2);
        primaryStage.setTitle("LinguaCrypt");
        int height = 800;
        int width = 1200;
        primaryStage.setHeight(height);
        primaryStage.setWidth(width);
        //GestionnairePartie partie = new GestionnairePartie();
        //GestionnaireTemps temps = new GestionnaireTemps();
        //Grille grille = new Grille(int taille)

        VuePartie test = new VuePartie(/*partie, temps, grille*/);
        test.ShowGame(primaryStage, height, width,gestionnairePartie);
    }

}