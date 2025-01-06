package grp04.jeu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        Label vuePartie = new Label("hello");

        //Partie partie = new Partie();
        //GestionnairePartie gestionnairePartie = new GestionnairePartie(partie);
        //VuePartie vuePartie = new VuePartie();

        BorderPane root = new BorderPane();
        root.setCenter(vuePartie);

        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setTitle("LinguaCrypt : VuePartie");
        stage.setResizable(false);
        stage.show();
    }
}
