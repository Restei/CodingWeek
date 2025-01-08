package grp04.jeu;

import javafx.scene.text.Font;

public class Utils {

    private static Utils instance;

    private int windowWidth = 1200;
    private int windowHeight = 720;
    private Font font = Font.font("Courier New", 30);
    private Font smallfont = Font.font("Courier New", 20);

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

    public Font getFont(int font) {
        /* renvoie un font voulue
        * argument : int font
        * font = 1 => font
        * font = 2 => smallfont   */
        Font valeur;
        switch(font){
            case 1:
                valeur = this.font;
                break;
            default:
                valeur = this.smallfont;
        }
        return valeur;
    }

    public String getMainMenuButtonColor() {
        return mainMenuButtonColor;
    }
}
