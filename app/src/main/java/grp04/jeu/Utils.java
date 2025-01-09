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

    public enum FontType {
        TITLE, // 3
        HEADER,  // 1
        SMALL_FONT  // 2
    }

    /** Renvoie une police
     * @param font type de police demandé
     */
    public Font getFont(FontType font) {
        return switch (font) {
            case FontType.HEADER -> this.header;
            case SMALL_FONT -> this.smallfont;
            case TITLE -> this.title;
            default -> {
                System.err.println("[Utils.getfont()] attention : police chargée par défaut");
                yield this.smallfont;
            }
        };

    }

    public String getMainMenuButtonColor() {
        return mainMenuButtonColor;
    }
}
