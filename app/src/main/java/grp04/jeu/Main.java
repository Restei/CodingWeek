package grp04.jeu;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    private static Main mInstance;
    
    public static Main getInstance() {
        return mInstance;
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("LinguaCrypt");
        primaryStage.setResizable(false);
        primaryStage.setHeight(Utils.getInstance().getWindowHeight());
        primaryStage.setWidth(Utils.getInstance().getWindowWidth());
        Utils.getInstance().playMusic();

        ChargeurScene chargeurScene = new ChargeurScene(primaryStage);
        chargeurScene.chargerMenuPrincipal();
    }

}