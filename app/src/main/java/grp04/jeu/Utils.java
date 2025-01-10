package grp04.jeu;

import java.net.URISyntaxException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

public class Utils {

    private static Utils instance;

    private int windowWidth = 1200;
    private int windowHeight = 720;
    private Font title = Font.font("Courier New", 50);
    private Font header = Font.font("Courier New", 30);
    private Font smallfont = Font.font("Courier New", 20);
    private Font minifont = Font.font("Courier New",10);
    private int currentSong = 0;
    private Media media;
    private  static MediaPlayer mediaPlayer;

    private String mainMenuButtonColor = "-fx-background-color:rgb(109, 236, 126)";

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    private Utils() {
    }

    public static Utils getInstance() {
        if (instance == null) {
            return new Utils();
        } else {
            return instance;
        }
    }

    public enum FontType {
        TITLE, // 3
        HEADER,  // 1
        SMALL_FONT,  // 2
        MINI_FONT //4
    }

    /** Renvoie une police
     * @param font type de police demandé
     */
    public Font getFont(FontType font) {
        return switch (font) {
            case FontType.HEADER -> this.header;
            case SMALL_FONT -> this.smallfont;
            case TITLE -> this.title;
            case MINI_FONT -> this.minifont;
            default -> {
                System.err.println("[Utils.getfont()] attention : police chargée par défaut");
                yield this.smallfont;
            }
        };

    }

    public String getMainMenuButtonColor() {
        return mainMenuButtonColor;
    }

    public void playMusic(){
        if (currentSong==0){
            currentSong=1;
            try {
                media = new Media(getClass().getResource("/secret-double-agent-spy-248968.mp3").toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                mediaPlayer.setVolume(0.9);
                mediaPlayer.setOnEndOfMedia(new Runnable(){

                    @Override
                    public void run() {
                        playMusic();
                    }
                    
                });
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        else if (currentSong==1){
            currentSong=2;
            try {
                media = new Media(getClass().getResource("/small_crimes-261103.mp3").toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                mediaPlayer.setVolume(0.9);
                mediaPlayer.setOnEndOfMedia(new Runnable(){

                    @Override
                    public void run() {
                        playMusic();
                    }
                    
                });
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        else{
            currentSong=0;
            try {
                media = new Media(getClass().getResource("/spy-always-dies-along-93740.mp3").toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                mediaPlayer.setVolume(1);
                mediaPlayer.setOnEndOfMedia(new Runnable(){

                    @Override
                    public void run() {
                        playMusic();
                    }
                    
                });
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
