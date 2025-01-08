package grp04.jeu.vues;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import grp04.jeu.modele.GestionnairePartie;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PopupMenuPause extends VBox {

    private final GestionnairePartie gestionnairePartie;
    private final Overlay overlay;

    private final ChargeurScene chargeurScene;

    public PopupMenuPause(GestionnairePartie gestionnairePartie, ChargeurScene chargeurScene, Overlay overlay) {

        this.overlay = overlay;
        this.gestionnairePartie = gestionnairePartie;
        this.chargeurScene = chargeurScene;

        this.setStyle("-fx-background-color: #FFFFFF;");
        this.setMaxSize(Utils.getInstance().getWindowWidth() * 0.8, Utils.getInstance().getWindowHeight() * 0.8);
        this.setSpacing(40);
        this.setAlignment(Pos.CENTER);

        Label titre = new Label("Pause");
        titre.setFont(Utils.getInstance().getFont(1)); // grand texte

        Label space = new Label(" ");  // ugly

        Button reprendre = new Button("Reprendre");
        reprendre.setStyle(Utils.getInstance().getMainMenuButtonColor());
        reprendre.setFont(Utils.getInstance().getFont(2));
        reprendre.setOnAction(event -> {
            this.overlay.fermerDernierPopup();
            this.gestionnairePartie.reprendreChrono();
        });

        Button sauvegarder = new Button("Sauvegarder");
        sauvegarder.setStyle(Utils.getInstance().getMainMenuButtonColor());
        sauvegarder.setFont(Utils.getInstance().getFont(2));

        Button quitter = new Button("Menu Principal");
        quitter.setStyle(Utils.getInstance().getMainMenuButtonColor());
        quitter.setFont(Utils.getInstance().getFont(2));
        quitter.setOnAction(event -> {
            // TODO demander avant de quitter si pas sauvegardé
            this.chargeurScene.chargerMenuPrincipal();

        });


        this.getChildren().addAll(titre, space, reprendre, sauvegarder, quitter);
    }


}
