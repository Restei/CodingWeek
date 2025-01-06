package grp04.jeu;

import grp04.jeu.vues.VuePartie;
import javafx.application.Application;
import javafx.stage.Stage;


public class GridPaneExperiments extends Application  {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("LinguaCrypt");
        int height = 800;
        int width = 1200;
        primaryStage.setHeight(height);
        primaryStage.setWidth(width);
        //GestionnairePartie partie = new GestionnairePartie();
        //GestionnaireTemps temps = new GestionnaireTemps();
        //Grille grille = new Grille(int taille)
        
        VuePartie test = new VuePartie(/*partie, temps, grille*/);
        test.ShowGame(primaryStage, height, width);
    }

}