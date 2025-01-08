package grp04.jeu.vues;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class VueMenuThemes extends VBox{

    public VueMenuThemes(ChargeurScene chargeurScene){
        Font font = Utils.getInstance().getFont(1);
        Font smallfont = Utils.getInstance().getFont(0);

        Button escape = new Button("Retour");
        HBox top = new HBox();
        Label blank = new Label("     ");
        Label title = new Label("Thèmes");
        title.setFont(font);
        blank.setFont(font);
        escape.setOnAction(event->chargeurScene.chargerMenuPrincipal());
        escape.setFont(smallfont);
        top.setAlignment(Pos.CENTER);
        top.setSpacing(Utils.getInstance().getWindowWidth()*0.25);
        top.getChildren().add(blank);
        top.getChildren().add(title);
        top.getChildren().add(escape);
        this.getChildren().add(top);
        this.setSpacing(Utils.getInstance().getWindowWidth()*0.1);;


    }


    
}
