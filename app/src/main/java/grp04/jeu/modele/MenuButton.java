package grp04.jeu.modele;

import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MenuButton extends Button {

    private MediaPlayer player;
    private Media media;
    
    public MenuButton(){
        super();
        try {
            media = new Media(getClass().getResource("/click-104721.mp3").toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public MenuButton(String text){
        super(text);
        try {
            media = new Media(getClass().getResource("/click-104721.mp3").toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    public void onActionAndSound(EventHandler<ActionEvent> function){
        super.setOnAction(event -> {
            function.handle(event);
            this.player = new MediaPlayer(media);
            this.player.setVolume(0.7);
            player.play();
        });
    }

}
