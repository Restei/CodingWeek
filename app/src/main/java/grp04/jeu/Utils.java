package grp04.jeu;

import javafx.scene.text.Font;

public class Utils {

    private static Utils instance;

    private int windowWidth = 1200;
    private int windowHeight = 720;
    private Font title = Font.font("Courier New", 50);
    private Font header = Font.font("Courier New", 30);
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
        Font valeur = null;
        switch(font){
            case 1:
                valeur = this.header;
                break;
            case 2:
                valeur = this.smallfont;
                break;
            case 3:
                valeur = this.title;
                break;

        }
        return valeur;
    }

    public String getMainMenuButtonColor() {
        return mainMenuButtonColor;
    }
}
