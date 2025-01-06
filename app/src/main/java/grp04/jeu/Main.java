package grp04.jeu;

import grp04.jeu.modele.Grille;
import grp04.jeu.vues.VueGrille;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        Label vuePartie = new Label("hello");
        Grille g = new Grille(3,3);
        g.get(0,0).setMot("Tour");

        g.get(1,0).setMot("Fou");
        g.get(2,0).setMot("Reine");
        g.get(0,1).setMot("Roi");
        g.get(1,1).setMot("Cavalier");
        g.get(0,0).setCouleur("B");
        g.get(1,0).setCouleur("N");
        g.get(2,0).setCouleur("W");
        g.get(0,1).setCouleur("R");
        g.get(1,1).setCouleur("B");

        VueGrille vg  = new VueGrille(g);
        //Partie partie = new Partie();
        //GestionnairePartie gestionnairePartie = new GestionnairePartie(partie);
        //VuePartie vuePartie = new VuePartie();

        BorderPane root = new BorderPane();
        root.setCenter(vg);

        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setTitle("LinguaCrypt : VuePartie");
        stage.setResizable(false);
        stage.show();
    }
}

