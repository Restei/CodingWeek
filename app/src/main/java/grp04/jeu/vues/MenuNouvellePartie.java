package grp04.jeu.vues;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import grp04.jeu.modele.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MenuNouvellePartie extends VBox implements Observateur {

    private final GestionnaireMenuNewGame gestionnaireMenuNewGame;
    private final Button buttonCreer;
    private Overlay overlay;


    public MenuNouvellePartie(ChargeurScene chargeurScene, GestionnaireMenuNewGame gestionnaireMenuNewGame, Overlay overlay) {

        this.gestionnaireMenuNewGame = gestionnaireMenuNewGame;
        this.gestionnaireMenuNewGame.ajouterObservateur(this);

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
        VBox.setVgrow(centerSpacing1, Priority.ALWAYS);
        this.getChildren().add(centerSpacing1);

        // Conteneur pour le conteneur des boutons et de la description
        HBox centerBox = new HBox();

        Region leftSpacing = new Region();
        HBox.setHgrow(leftSpacing, Priority.ALWAYS);
        centerBox.getChildren().add(leftSpacing);

        // Conteneur des BoutonIncr : paramètres modifiables
        VBox conteneurParams = new VBox();
        conteneurParams.setPrefWidth(Utils.getInstance().getWindowWidth() * 0.33);
        conteneurParams.setSpacing(20); // espacement inter-éléments
        BoutonIncr taille = new BoutonIncr(gestionnaireMenuNewGame, BoutonIncr.Type.TAILLE);
        BoutonIncr NbCartes = new BoutonIncr(gestionnaireMenuNewGame, BoutonIncr.Type.NB_CARTES);
        BoutonIncr NbNoires = new BoutonIncr(gestionnaireMenuNewGame, BoutonIncr.Type.NB_NOIRES);
        BoutonIncr typetime = new BoutonIncr(gestionnaireMenuNewGame, BoutonIncr.Type.TYPE_TIMER);
        BoutonIncr timerespion = new BoutonIncr(gestionnaireMenuNewGame, BoutonIncr.Type.TIMER_ESPION_OU_EQUIPE_BLEU);
        BoutonIncr timeragent = new BoutonIncr(gestionnaireMenuNewGame, BoutonIncr.Type.TIMER_AGENT_OU_EQUIPE_ROUGE);
        BoutonIncr theme = new BoutonIncr(gestionnaireMenuNewGame, BoutonIncr.Type.THEME);
        conteneurParams.getChildren().addAll(taille, NbCartes, NbNoires, typetime, timerespion, timeragent, theme);
        centerBox.getChildren().add(conteneurParams);

        // espace au milieu du conteneur central
        Region middleCenterSpacing = new Region();
        HBox.setHgrow(middleCenterSpacing, Priority.ALWAYS);
        centerBox.getChildren().add(middleCenterSpacing);

        // Conteneur de la description
        centerBox.getChildren().add(new VueDescriptionParametreNouvellePartie(gestionnaireMenuNewGame));

        Region rightSpacing = new Region();
        HBox.setHgrow(rightSpacing, Priority.ALWAYS);
        centerBox.getChildren().add(rightSpacing);

        this.getChildren().add(centerBox);

        // Espacement après centerBox
        Region centerSpacing2 = new Region();
        VBox.setVgrow(centerSpacing2, javafx.scene.layout.Priority.ALWAYS);
        this.getChildren().add(centerSpacing2);

        // Conteneur pour les boutons retour et creer
        HBox controlBox = new HBox();

        Region controlBoxLeftSpacing = new Region();
        HBox.setHgrow(controlBoxLeftSpacing, Priority.ALWAYS);

        // bouton pour revenir au menu
        Button retour = new Button("Retour");
        retour.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        retour.setOnMouseClicked(e -> gestionnaireMenuNewGame.retourmenuprincipale());

        Region controlBoxMiddleSpacing = new Region();
        HBox.setHgrow(controlBoxMiddleSpacing, Priority.ALWAYS);

        // bouton pour créer une nouvelle partie
        this.buttonCreer = new Button("Créer");
        this.buttonCreer.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        this.buttonCreer.setOnMouseClicked(e -> {
            if (gestionnaireMenuNewGame.creationPartieAvantCompletion()) {
                overlay.ajouterEtAfficherPopup(new PopupThemeComplete(overlay, gestionnaireMenuNewGame));
            }
        });
        this.buttonCreer.setDefaultButton(true);

        Region controlBoxRightSpacing = new Region();
        HBox.setHgrow(controlBoxRightSpacing, Priority.ALWAYS);

        controlBox.getChildren().addAll(controlBoxLeftSpacing, retour, controlBoxMiddleSpacing, this.buttonCreer, controlBoxRightSpacing);
        controlBox.setAlignment(Pos.CENTER);

        getChildren().add(controlBox);


        // espacement en bas de l'écran
        Region bottomSpacing = new Region();
        VBox.setVgrow(bottomSpacing, javafx.scene.layout.Priority.ALWAYS);
        this.getChildren().add(bottomSpacing);

    }

    @Override
    public void reagir() {
        // désactive le bouton si la config n'est pas valide
        buttonCreer.setDisable(!this.gestionnaireMenuNewGame.estUneConfigValide());
    }
}
