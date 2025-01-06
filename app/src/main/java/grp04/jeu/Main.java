package grp04.jeu;

import grp04.jeu.modele.Carte;
import grp04.jeu.modele.Grille;
import grp04.jeu.modele.TypeCarte;
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


        g.insertCarte(new Carte(TypeCarte.ROUGE,"Tour"),0,0);
        g.insertCarte(new Carte(TypeCarte.NOIRE,"Reine"),1,0);
        g.insertCarte(new Carte(TypeCarte.BLEU,"Dame"),2,0);
        g.insertCarte(new Carte(TypeCarte.CIVILE,"Cavalier"),0,1);
        g.insertCarte(new Carte(TypeCarte.ROUGE,"Fou"),1,1);
        g.insertCarte(new Carte(TypeCarte.NOIRE,"Roi"),2,1);
        g.insertCarte(new Carte(TypeCarte.CIVILE,"Tour"),0,2);
        g.insertCarte(new Carte(TypeCarte.BLEU,"Fou"),1,2);
        g.insertCarte(new Carte(TypeCarte.ROUGE,"Cavalier"),2,2);

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

