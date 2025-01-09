package grp04.jeu.vues;

import grp04.jeu.Utils;
import grp04.jeu.modele.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MenuNouvellePartie extends VBox {

    public MenuNouvellePartie(GestionnaireMenuNewGame gestionnaireMenuNewGame) {
        //Contenu du menu : Taille, Nb Carte, Nb noires , TypeTimer type , Timer Espion, Timer agent, Theme

        this.setSpacing(10);

        // Espacement avant le titre
        Region topSpacing = new Region();
        VBox.setVgrow(topSpacing, javafx.scene.layout.Priority.ALWAYS);
        this.getChildren().add(topSpacing);

        // Titre
        Label titre = new Label("New Game");
        titre.setFont(Utils.getInstance().getFont(Utils.FontType.TITLE));
        HBox titrebox = new HBox();
        titrebox.setAlignment(Pos.CENTER);
        titrebox.getChildren().add(titre);
        this.getChildren().add(titrebox);

        // Espacement après le titre
        Region centerSpacing1 = new Region();
        VBox.setVgrow(centerSpacing1, javafx.scene.layout.Priority.ALWAYS);
        this.getChildren().add(centerSpacing1);

        // Conteneur pour le conteneur des boutons et de la description
        HBox centerBox = new HBox();

        // Conteneur des BoutonIncr : paramètres modifiables
        VBox conteneurParams = new VBox();
        conteneurParams.setSpacing(20); // espacement inter-éléments
        BoutonIncr taille = new BoutonIncr(gestionnaireMenuNewGame, BoutonIncr.Type.TAILLE);
        BoutonIncr NbCartes = new BoutonIncr(gestionnaireMenuNewGame, BoutonIncr.Type.NB_CARTES);
        BoutonIncr NbNoires = new BoutonIncr(gestionnaireMenuNewGame, BoutonIncr.Type.NB_NOIRES);
        BoutonIncr typetime = new BoutonIncr(gestionnaireMenuNewGame, BoutonIncr.Type.TYPE_TIMER);
        BoutonIncr timerespion = new BoutonIncr(gestionnaireMenuNewGame, BoutonIncr.Type.TIMER_ESPION);
        BoutonIncr timeragent = new BoutonIncr(gestionnaireMenuNewGame, BoutonIncr.Type.TIMER_AGENT);
        BoutonIncr theme = new BoutonIncr(gestionnaireMenuNewGame, BoutonIncr.Type.THEME);
        conteneurParams.getChildren().addAll(taille, NbCartes, NbNoires, typetime, timerespion, timeragent, theme);
        centerBox.getChildren().add(conteneurParams);

        // Conteneur de la description
        centerBox.getChildren().add(new VueDescriptionParametreNouvellePartie(gestionnaireMenuNewGame));

        this.getChildren().add(centerBox);


        // Espacement après centerBox
        Region centerSpacing2 = new Region();
        VBox.setVgrow(centerSpacing2, javafx.scene.layout.Priority.ALWAYS);
        this.getChildren().add(centerSpacing2);

        // Conteneur pour les boutons retour et creer
        HBox controlBox = new HBox();
        Button retour = new Button("RETOUR");
        Button creer = new Button("CREER");
        retour.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        retour.setOnMouseClicked(e -> gestionnaireMenuNewGame.retourmenuprincipale());

        creer.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        creer.setOnMouseClicked(e -> gestionnaireMenuNewGame.creationpartie());

        controlBox.getChildren().addAll(retour, creer);
        controlBox.setAlignment(Pos.CENTER);

        //Ajout des boutons de contrôle

        getChildren().add(controlBox);



        Region bottomSpacing = new Region();
        VBox.setVgrow(bottomSpacing, javafx.scene.layout.Priority.ALWAYS);
        this.getChildren().add(bottomSpacing);

    }
}
