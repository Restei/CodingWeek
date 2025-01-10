package grp04.jeu.vues;

import grp04.jeu.Utils;
import grp04.jeu.modele.MenuButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PopupSauvegarde extends VBox{

    public PopupSauvegarde(Overlay overlay){

        Label label = new Label("Partie sauvegardée avec succès !");
        label.setFont(Utils.getInstance().getFont(Utils.FontType.HEADER));

        
        MenuButton reprendre = new MenuButton("Ok");
        reprendre.setStyle(Utils.getInstance().getMainMenuButtonColor());
        reprendre.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        reprendre.onActionAndSound(event -> {
            overlay.fermerDernierPopup();
        });
        reprendre.setDefaultButton(true);

        
        this.getChildren().add(label);
        this.getChildren().add(reprendre);

        this.setAlignment(Pos.CENTER);
        this.setSpacing(Utils.getInstance().getWindowWidth()*0.05);
        this.setMaxSize(Utils.getInstance().getWindowWidth()*0.7, Utils.getInstance().getWindowHeight()*0.2);
        this.setStyle("-fx-background-color:rgb(255, 255, 255)");
        this.setPadding(new Insets(Utils.getInstance().getWindowWidth()*0.05));
    }

}
