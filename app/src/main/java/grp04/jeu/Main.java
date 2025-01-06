package grp04.jeu;

import grp04.jeu.modele.*;
import grp04.jeu.vues.VueChrono;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        Timer timer = new Timer(TypeTimer.INDIVIDUEL, 50000, 50000);
        Partie partie = new Partie(null, timer, 0);
        GestionnaireTemps gestionnaireTemps = new GestionnaireTemps(partie);
        VueChrono vueChrono = new VueChrono(gestionnaireTemps);

        BorderPane root = new BorderPane();
        root.setCenter(vueChrono);

        gestionnaireTemps.lanceTimer();
        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setTitle("LinguaCrypt : VuePartie");
        stage.setResizable(false);
        stage.show();
    }
}
