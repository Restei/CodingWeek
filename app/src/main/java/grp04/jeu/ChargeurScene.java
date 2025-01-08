package grp04.jeu;

import grp04.jeu.modele.Carte;
import grp04.jeu.modele.GestionnairePartie;
import grp04.jeu.modele.Grille;
import grp04.jeu.modele.Partie;
import grp04.jeu.modele.Timer;
import grp04.jeu.modele.TypeCarte;
import grp04.jeu.modele.TypeTimer;

// AKA GM

import grp04.jeu.vues.MenuPrincipal;
import grp04.jeu.vues.MenuNouvellePartie;
import grp04.jeu.vues.VuePartie;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChargeurScene {

    private Stage primaryStage;
    
    public ChargeurScene(Stage primStage){
        this.primaryStage=primStage;
    }

    public void montrerScene(Scene scene){
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void chargerMenuPrincipal(){
        MenuPrincipal mainMenu = new MenuPrincipal(this);
        Scene scene = new Scene(mainMenu);
        montrerScene(scene);
    }

    public void pseudoGame(Partie partie){
        Grille g = new Grille(3);
        Timer timer = new Timer(TypeTimer.INDIVIDUEL,10000,10000);
        Partie partiee = new Partie(g,timer,10);
        //GestionnairePartie gestionnairePartie = new GestionnairePartie(partiee);

        g.insertCarte(new Carte(TypeCarte.ROUGE,"Tour"),0,0);
        g.insertCarte(new Carte(TypeCarte.NOIRE,"Reine"),1,0);
        g.insertCarte(new Carte(TypeCarte.BLEU,"Dame"),2,0);
        g.insertCarte(new Carte(TypeCarte.CIVILE,"Cavalier"),0,1);
        g.insertCarte(new Carte(TypeCarte.ROUGE,"Fou"),1,1);
        g.insertCarte(new Carte(TypeCarte.NOIRE,"Roi"),2,1);
        g.insertCarte(new Carte(TypeCarte.CIVILE,"Tour"),0,2);
        g.insertCarte(new Carte(TypeCarte.BLEU,"Fou"),1,2);
        g.insertCarte(new Carte(TypeCarte.ROUGE,"Cavalier"),2,2);

        GestionnairePartie gestionnairePartie = new GestionnairePartie(partie);
        VuePartie vuePartie = new VuePartie(gestionnairePartie);
        Scene scene = new Scene(vuePartie);

        montrerScene(scene);
    }

    public void menuNouvellePartie(){
        MenuNouvellePartie vueMenuNouvellePartie = new MenuNouvellePartie(this);
        Scene scene = new Scene(vueMenuNouvellePartie);
        montrerScene(scene);
    }
/*
    
    public void menuListeMots(){
        VueMenuListeMots vueMenuListeMots = new VueMenuListeMots();
        Scene scene = new Scene(vueMenuListeMots);
        montrerScene(scene);
    }
    
    public void menuSauvegarde(){
        VueMenuSauvegarde vueMenuSauvegarde = new VueMenuSauvegarde();
        Scene scene = new Scene(vueMenuSauvegarde);
        montrerScene(scene);
    }*/

}
