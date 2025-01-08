package grp04.jeu;

import grp04.jeu.modele.*;

// AKA GM

import grp04.jeu.vues.MenuPrincipal;
import grp04.jeu.vues.Overlay;
import grp04.jeu.vues.VueMenuSauvegarde;
import grp04.jeu.vues.VuePartie;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChargeurScene {

    private final Stage primaryStage;
    
    public ChargeurScene(Stage primStage){
        this.primaryStage=primStage;
    }

    public void montrerScene(Scene scene){
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void chargerMenuPrincipal(){
        Overlay overlay = new Overlay(this);
        MenuPrincipal menuPrincipal = new MenuPrincipal(this, overlay);
        overlay.setFond(menuPrincipal);
        Scene scene = new Scene(overlay);
        montrerScene(scene);
    }

    // démonstration
    public void pseudoGame(){
        Grille g = new Grille(3);
        Timer timer = new Timer(TypeTimer.INDIVIDUEL,10000,10000);
        Partie partie = new Partie(g,timer,10);
        GestionnairePartie gestionnairePartie = new GestionnairePartie(partie, null);
        g.insertCarte(new Carte(TypeCarte.ROUGE,"Tour"),0,0);
        g.insertCarte(new Carte(TypeCarte.NOIRE,"Reine"),1,0);
        g.insertCarte(new Carte(TypeCarte.BLEU,"Dame"),2,0);
        g.insertCarte(new Carte(TypeCarte.CIVILE,"Cavalier"),0,1);
        g.insertCarte(new Carte(TypeCarte.ROUGE,"Fou"),1,1);
        g.insertCarte(new Carte(TypeCarte.NOIRE,"Roi"),2,1);
        g.insertCarte(new Carte(TypeCarte.CIVILE,"Tour"),0,2);
        g.insertCarte(new Carte(TypeCarte.BLEU,"Fou"),1,2);
        g.insertCarte(new Carte(TypeCarte.ROUGE,"Cavalier"),2,2);
        
        Overlay overlay = new Overlay(this);
        VuePartie vuePartie = new VuePartie(gestionnairePartie, overlay);
        overlay.setFond(vuePartie);
        Scene scene = new Scene(overlay);

        montrerScene(scene);
    }

    public void chargerNouvellePartie(Partie partie, Statistique statistique){
        GestionnairePartie gestionnairePartie = new GestionnairePartie(partie, statistique);

        VuePartie vuePartie = new VuePartie(gestionnairePartie);
        Scene scene = new Scene(vuePartie);

        montrerScene(scene);
    }

    /*public void menuNouvellePartie(){
        VueMenuNouvellePartie vueMenuNouvellePartie = new VueMenuNouvellePartie();
        Scene scene = new Scene(vueMenuNouvellePartie);
        montrerScene(scene);
    }

    
    public void menuListeMots(){
        VueMenuListeMots vueMenuListeMots = new VueMenuListeMots();
        Scene scene = new Scene(vueMenuListeMots);
        montrerScene(scene);
    }*/
    
    public void menuSauvegarde(){
        VueMenuSauvegarde vueMenuSauvegarde = new VueMenuSauvegarde(this);
        Scene scene = new Scene(vueMenuSauvegarde);
        montrerScene(scene);
    }

    public void lancerPatie(Partie partie/*, Statistique statistique */){
        
    }



}
