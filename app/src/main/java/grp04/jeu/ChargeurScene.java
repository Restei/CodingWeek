package grp04.jeu;

import grp04.jeu.modele.Carte;
import grp04.jeu.modele.GestionnairePartie;
import grp04.jeu.modele.Grille;
import grp04.jeu.modele.Partie;
import grp04.jeu.modele.TypeCarte;

// AKA GM

import grp04.jeu.vues.MenuPrincipal;
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
        MenuPrincipal mainMenu = new MenuPrincipal();
        Scene scene = new Scene(mainMenu);
        montrerScene(scene);
    }

    public void pseudoGame(){
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
    }
    
    public void menuSauvegarde(){
        VueMenuSauvegarde vueMenuSauvegarde = new VueMenuSauvegarde();
        Scene scene = new Scene(vueMenuSauvegarde);
        montrerScene(scene);
    }*/

}
