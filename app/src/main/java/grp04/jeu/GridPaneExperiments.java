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
        //GestionnairePartie gest = new GestionnairePartie();
        
        VuePartie test = new VuePartie(/*gest*/);
        test.ShowGame(primaryStage, height, width);
    }

}