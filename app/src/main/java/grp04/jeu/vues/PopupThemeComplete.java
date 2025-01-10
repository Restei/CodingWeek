package grp04.jeu.vues;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PopupThemeComplete extends VBox {

    public PopupThemeComplete(Overlay overlay, ChargeurScene chargeurScene) {
        Label label = new Label();
        label.setFont(Utils.getInstance().getFont(Utils.FontType.HEADER));
        label.setText("Attention : le thème sélectionner n'a pas suffisamment de mots.\nIl sera complété avec des mots du thème par défault.\nVoulez-vous continuer ?");
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

        hbox.getChildren().add(no);
        hbox.getChildren().add(yes);
        hbox.setSpacing(Utils.getInstance().getWindowWidth() * 0.05);
        hbox.setAlignment(Pos.CENTER);

        this.getChildren().add(label);
        this.getChildren().add(hbox);

        this.setAlignment(Pos.CENTER);
        this.setSpacing(Utils.getInstance().getWindowWidth() * 0.05);
        this.setMaxSize(Utils.getInstance().getWindowWidth() * 0.75, Utils.getInstance().getWindowHeight() * 0.2);
        this.setStyle("-fx-background-color:rgb(255, 255, 255)");
        this.setPadding(new Insets(Utils.getInstance().getWindowWidth() * 0.05));
    }

}
