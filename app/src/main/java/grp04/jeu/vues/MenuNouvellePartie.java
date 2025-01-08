package grp04.jeu.vues;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import grp04.jeu.modele.CreateurPartie;
import grp04.jeu.modele.Partie;
import grp04.jeu.modele.Theme;
import grp04.jeu.modele.TypeTimer;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuNouvellePartie extends VBox {

    private ChargeurScene chargeurScene;

    public MenuNouvellePartie(ChargeurScene chargeurScene){
        this.chargeurScene = chargeurScene;

        //Taille, Nb Carte, Nb noires , TypeTimer type , Timer Espion, Timer agent, Theme
        setSpacing(10);

        //Titre
        Label titre = new Label("New Game");
        titre.setFont(Utils.getInstance().getFont(3));

        HBox titrebox = new HBox();
        titrebox.setAlignment(Pos.CENTER);
        titrebox.getChildren().add(titre);

        this.getChildren().add(titrebox);

        //Paramètres modifiable

        BoutonIncr taille = new BoutonIncr(this,"taille :",3,10);
        BoutonIncr NbCartes = new BoutonIncr(this,"NbCartes :",2,49);
        BoutonIncr NbNoires = new BoutonIncr(this,"NbNoires :",2,97);
        BoutonIncr typetime = new BoutonIncr(this,"Type de timer :",1,2);
        BoutonIncr timerespion = new BoutonIncr(this,"temps espion :",30,300);
        BoutonIncr timeragent = new BoutonIncr(this,"temps agent :",60,300);
        BoutonIncr theme = new BoutonIncr(this,"theme :" ,0,1000);

        //Ajout des paramètre à la vue

        getChildren().addAll(taille,NbCartes,NbNoires,typetime,timerespion,timeragent,theme);

        //Creation boutons retour et creer

        HBox controle = new HBox();
        Button retour = new Button("RETOUR");
        Button creer = new Button("CREER");
        retour.setFont(Utils.getInstance().getFont(2));
        retour.setOnMouseClicked(e-> chargeurScene.chargerMenuPrincipal());

        creer.setFont(Utils.getInstance().getFont(2));
        creer.setOnMouseClicked(e-> {
            Theme themes = new Theme("test");
            themes.addMot("a");
            themes.addMot("h");
            themes.addMot("i");
            themes.addMot("b");
            themes.addMot("c");
            themes.addMot("d");
            themes.addMot("e");
            themes.addMot("f");
            themes.addMot("g");
            Partie partie = CreateurPartie.createurPartie(3,2,2, TypeTimer.INDIVIDUEL,30,30,themes);
            chargeurScene.pseudoGame(partie);

        });

        controle.getChildren().addAll(retour, creer);
        controle.setAlignment(Pos.CENTER);

        //Ajout des boutons de controle

        getChildren().add(controle);

    }
}
