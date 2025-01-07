package grp04.jeu;

import grp04.jeu.modele.*;
import grp04.jeu.vues.VuePartie;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // grille temporaire
        Grille grille = new Grille(3);
        grille.insertCarte(new Carte(TypeCarte.ROUGE, "Tour", grille), 0, 0);
        grille.insertCarte(new Carte(TypeCarte.NOIRE, "Reine", grille), 1, 0);
        grille.insertCarte(new Carte(TypeCarte.BLEU, "Dame", grille), 2, 0);
        grille.insertCarte(new Carte(TypeCarte.CIVILE, "Cavalier", grille), 0, 1);
        grille.insertCarte(new Carte(TypeCarte.ROUGE, "Fou", grille), 1, 1);
        grille.insertCarte(new Carte(TypeCarte.NOIRE, "Roi", grille), 2, 1);
        grille.insertCarte(new Carte(TypeCarte.CIVILE, "Tour", grille), 0, 2);
        grille.insertCarte(new Carte(TypeCarte.BLEU, "Fou", grille), 1, 2);
        grille.insertCarte(new Carte(TypeCarte.ROUGE, "Cavalier", grille), 2, 2);

        Partie partie = new Partie(grille, null, 10);


        GestionnairePartie gestionnairePartie = new GestionnairePartie(partie);
        partie.setGestionnaire(gestionnairePartie);

        primaryStage.setTitle("LinguaCrypt");
        int height = Utils.getInstance().getWindowHeight();
        int width = Utils.getInstance().getWindowWidth();
        primaryStage.setHeight(height);
        primaryStage.setWidth(width);

        VuePartie vuePartie = new VuePartie(gestionnairePartie);
        primaryStage.setScene(new Scene(vuePartie));
        primaryStage.show();

    }

}