package grp04.jeu.vues;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import grp04.jeu.modele.MenuButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class PopupCredits extends VBox{

    public PopupCredits(ChargeurScene chargeurScene){

        Font font = Utils.getInstance().getFont(Utils.FontType.HEADER);
        Font smallfont = Utils.getInstance().getFont(Utils.FontType.SMALL_FONT);

        Label title = new Label("Crédits");
        title.setFont(font);
        Label title2 = new Label("IronSteed Studios");
        title2.setFont(smallfont);

        HBox hbox=new HBox();
        VBox vBoxL = new VBox();
        VBox vBoxR = new VBox();
        Label left = new Label("Joseph Balsan\nClément Escude--Cotinat");
        Label right = new Label("Guillaume Hisleur\nRussel Tchiakaka");
        left.setFont(smallfont);
        right.setFont(smallfont);
        vBoxL.getChildren().add(left);
        vBoxR.getChildren().add(right);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(vBoxL);
        hbox.getChildren().add(vBoxR);
        hbox.setSpacing(Utils.getInstance().getWindowWidth()*0.075);
        Label music = new Label("Musiques :\nhttps://pixabay.com/users/alanajordan-25247407/\nhttps://pixabay.com/users/magiksolo-29264138/\nhttps://pixabay.com/");
        music.setTextAlignment(TextAlignment.CENTER);
        music.setFont(smallfont);
        MenuButton button = new MenuButton("Retour");
        button.onActionAndSound(event->chargeurScene.chargerMenuPrincipal());
        button.setFont(smallfont);


        
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(title);
        this.getChildren().add(title2);
        this.getChildren().add(hbox);
        this.getChildren().add(music);
        this.getChildren().add(button);
        this.setMaxWidth(Utils.getInstance().getWindowWidth()*0.6);
        this.setMaxHeight(Utils.getInstance().getWindowHeight()*0.7);
        this.setStyle("-fx-background-color:rgb(255, 255, 255)");
        this.setPadding(new Insets(Utils.getInstance().getWindowWidth()*0.05));
        this.setSpacing(Utils.getInstance().getWindowWidth()*0.02);
    }
    
}
