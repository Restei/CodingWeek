package grp04.jeu;

import grp04.jeu.modele.*;

// AKA GM

import grp04.jeu.vues.MenuPrincipal;
import grp04.jeu.vues.Overlay;
import grp04.jeu.vues.VueMenuSauvegarde;
import grp04.jeu.vues.MenuNouvellePartie;
import grp04.jeu.vues.VueMenuThemes;
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
        Overlay overlay = new Overlay();
        MenuPrincipal menuPrincipal = new MenuPrincipal(this, overlay);
        overlay.setFond(menuPrincipal);
        Scene scene = new Scene(overlay);
        montrerScene(scene);
    }

    public void chargerNouvellePartie(Partie partie, Statistique statistique){
        Overlay overlay = new Overlay();
        GestionnairePartie gestionnairePartie = new GestionnairePartie(partie, statistique, overlay);
        gestionnairePartie.debutPartie();
        VuePartie vuePartie = new VuePartie(gestionnairePartie,this,overlay);
        overlay.setFond(vuePartie);

        Scene scene = new Scene(overlay);
        montrerScene(scene);
    }

    public void menuNouvellePartie(){
        Overlay overlay = new Overlay();
        GestionnaireMenuNewGame gestionnaire = new GestionnaireMenuNewGame(this);
        MenuNouvellePartie vueMenuNouvellePartie = new MenuNouvellePartie(this, gestionnaire, overlay);
        gestionnaire.NotifierObservateurs(); // sert à pré-charger l'affichage de vueNouvellePartie
        overlay.setFond(vueMenuNouvellePartie);

        Scene scene = new Scene(overlay);
        montrerScene(scene);
    }

    
    public void menuListeMots(){
        VueMenuThemes vueMenuThemes = new VueMenuThemes(this);
        Scene scene = new Scene(vueMenuThemes);
        montrerScene(scene);
    }
    
    public void menuSauvegarde(){
        VueMenuSauvegarde vueMenuSauvegarde = new VueMenuSauvegarde(this);
        Scene scene = new Scene(vueMenuSauvegarde);
        montrerScene(scene);
    }

    public void lancerPatie(Partie partie, Statistique statistique){
        
    }

}
