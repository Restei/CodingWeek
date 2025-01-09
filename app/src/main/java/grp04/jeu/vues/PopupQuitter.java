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

public class PopupQuitter extends VBox {

    public PopupQuitter(Overlay overlay, ChargeurScene chargeurScene, int sceneAQuitter){
    
        Label label = new Label();
        label.setFont(Utils.getInstance().getFont(Utils.FontType.TITLE));
        switch (sceneAQuitter) {
            case 0:
                label.setText("Voulez-vous vraiment quitter le jeu ?");
                break;
            case 1:
                label.setText("Voulez-vous vraiment quitter la partie ?");
                break;
        
            default:
                System.err.println("Erreur : Ne reconnaît pas le menu à quitter");
                break;
        }

        HBox hbox = new HBox();
        
        Button no = new Button("Non");
        no.setStyle(Utils.getInstance().getMainMenuButtonColor());
        no.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        no.setOnAction(event -> {
            overlay.fermerDernierPopup();
        });
        
        Button yes = new Button("Oui");
        yes.setStyle(Utils.getInstance().getMainMenuButtonColor());
        yes.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        yes.setOnAction(event -> {
            switch (sceneAQuitter) {
                case 0:
                    Platform.exit();
                    break;
                case 1:
                    chargeurScene.chargerMenuPrincipal();
                    break;
            
                default:
                    overlay.fermerDernierPopup();
                    break;
            }
        });

        hbox.getChildren().add(no);
        hbox.getChildren().add(yes);
        hbox.setSpacing(Utils.getInstance().getWindowWidth()*0.05);
        hbox.setAlignment(Pos.CENTER);
        
        this.getChildren().add(label);
        this.getChildren().add(hbox);

        this.setAlignment(Pos.CENTER);
        this.setSpacing(Utils.getInstance().getWindowWidth()*0.05);
        this.setMaxSize(Utils.getInstance().getWindowWidth()*0.55, Utils.getInstance().getWindowHeight()*0.2);
        this.setStyle("-fx-background-color:rgb(255, 255, 255)");
        this.setPadding(new Insets(Utils.getInstance().getWindowWidth()*0.05));
    }
}
