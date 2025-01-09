package grp04.jeu.vues;

import grp04.jeu.Utils;
import grp04.jeu.modele.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuNouvellePartie extends VBox {

    public MenuNouvellePartie(GestionnaireMenuNewGame menu){
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

        BoutonIncr taille = new BoutonIncr(menu,"taille");
        BoutonIncr NbCartes = new BoutonIncr(menu,"NbCartes");
        BoutonIncr NbNoires = new BoutonIncr(menu,"NbNoires");
        BoutonIncr typetime = new BoutonIncr(menu,"typetime");
        BoutonIncr timerespion = new BoutonIncr(menu,"timer espion");
        BoutonIncr timeragent = new BoutonIncr(menu,"timer agent");
        BoutonIncr theme = new BoutonIncr(menu,"theme");

        //Ajout des paramètre à la vue

        getChildren().addAll(taille,NbCartes,NbNoires,typetime,timerespion,timeragent,theme);

        //Creation boutons retour et creer

        HBox controle = new HBox();
        Button retour = new Button("RETOUR");
        Button creer = new Button("CREER");
        retour.setFont(Utils.getInstance().getFont(2));
        retour.setOnMouseClicked(e-> menu.retourmenuprincipale());

        creer.setFont(Utils.getInstance().getFont(2));
        creer.setOnMouseClicked(e-> menu.creationpartie());
        creer.setDefaultButton(true);

        controle.getChildren().addAll(retour, creer);
        controle.setAlignment(Pos.CENTER);

        //Ajout des boutons de controle

        getChildren().add(controle);

    }
}
