package grp04.jeu;

public class Utils {

    private static Utils instance;

    private int windowWidth = 1200;
    private int windowHeight = 800;

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

}
