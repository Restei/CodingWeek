package grp04.jeu.vues;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import grp04.jeu.modele.GestionnaireMenuNewGame;
import grp04.jeu.modele.GestionnaireThemes;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PopupThemeComplete extends VBox {

    public PopupThemeComplete(Overlay overlay, GestionnaireMenuNewGame gestionnaireMenuNewGame) {

        VBox vboxLabel = new VBox();
        Label label1 = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        label1.setFont(Utils.getInstance().getFont(Utils.FontType.HEADER));
        label2.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        label3.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        label1.setText("Attention : le thème sélectionné n'a pas suffisamment de mots.");
        label2.setText("Il sera complété avec des mots du thème par défault.");
        label3.setText("Voulez-vous continuer ?");

        vboxLabel.getChildren().addAll(label1, label2);
        vboxLabel.setAlignment(Pos.CENTER);
        vboxLabel.setSpacing(Utils.getInstance().getWindowWidth() * 0.005);

        HBox hbox = new HBox();

        Button no = new Button("Non");
        no.setStyle(Utils.getInstance().getMainMenuButtonColor());
        no.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        no.setOnAction(event -> {
            overlay.fermerDernierPopup();
        });
        no.setDefaultButton(true);

        Button yes = new Button("Oui");
        yes.setStyle(Utils.getInstance().getMainMenuButtonColor());
        yes.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        yes.setOnAction(event -> {
            gestionnaireMenuNewGame.creationPartie();
        });

        hbox.getChildren().add(no);
        hbox.getChildren().add(yes);
        hbox.setSpacing(Utils.getInstance().getWindowWidth() * 0.05);
        hbox.setAlignment(Pos.CENTER);

        VBox vboxBouton = new VBox();
        vboxBouton.getChildren().addAll(label3, hbox);
        vboxBouton.setAlignment(Pos.CENTER);
        vboxBouton.setSpacing(Utils.getInstance().getWindowWidth() * 0.015);

        this.getChildren().addAll(vboxLabel, vboxBouton);

        this.setAlignment(Pos.CENTER);
        this.setSpacing(Utils.getInstance().getWindowWidth() * 0.05);
        this.setMaxSize(Utils.getInstance().getWindowWidth() * 0.9, Utils.getInstance().getWindowHeight() * 0.2);
        this.setStyle("-fx-background-color:rgb(255, 255, 255)");
        this.setPadding(new Insets(Utils.getInstance().getWindowWidth() * 0.05));
    }

}
