package grp04.jeu.vues;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import grp04.jeu.modele.GestionnairePartie;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PopupMenuPause extends VBox {

    private final GestionnairePartie gestionnairePartie;
    private final Overlay overlay;
    private TextField nomSauvegarde;

    public PopupMenuPause(GestionnairePartie gestionnairePartie, ChargeurScene chargeurScene, Overlay overlay) {

        this.overlay = overlay;
        this.gestionnairePartie = gestionnairePartie;
        this.setStyle("-fx-background-color: #FFFFFF;");
        this.setMaxSize(Utils.getInstance().getWindowWidth() * 0.8, Utils.getInstance().getWindowHeight() * 0.8);
        this.setSpacing(40);
        this.setAlignment(Pos.CENTER);

        Label titre = new Label("Pause");
        titre.setFont(Utils.getInstance().getFont(Utils.FontType.HEADER)); // grand texte

        Label space = new Label(" ");  // ugly

        Button reprendre = new Button("Reprendre");
        reprendre.setStyle(Utils.getInstance().getMainMenuButtonColor());
        reprendre.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        reprendre.setOnAction(event -> {
            this.overlay.fermerDernierPopup();
            this.gestionnairePartie.reprendreChrono();
        });

        this.nomSauvegarde = new TextField();
        nomSauvegarde.setPromptText("Nom de la sauvegarde");
        nomSauvegarde.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        nomSauvegarde.setMaxWidth(Utils.getInstance().getWindowWidth() * 0.5);

        Button sauvegarder = new Button("Sauvegarder");
        sauvegarder.setStyle(Utils.getInstance().getMainMenuButtonColor());
        sauvegarder.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        sauvegarder.setOnAction(event -> {
            if (!nomSauvegarde.getText().isEmpty()) {
                gestionnairePartie.sauvegarderPartie(nomSauvegarde.getText());
                PopupSauvegarde popupSauvegarde = new PopupSauvegarde(overlay);
                overlay.ajouterEtAfficherPopup(popupSauvegarde);
            }
        });
        sauvegarder.setDefaultButton(true);

        Button quitter = new Button("Menu Principal");
        quitter.setStyle(Utils.getInstance().getMainMenuButtonColor());
        quitter.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        quitter.setOnAction(event -> {
            PopupQuitter popupQuitter = new PopupQuitter(overlay, chargeurScene, 1);
            overlay.ajouterEtAfficherPopup(popupQuitter);
        });


        this.getChildren().addAll(titre, space, reprendre, nomSauvegarde, sauvegarder, quitter);
    }


}
