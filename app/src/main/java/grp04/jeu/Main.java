package grp04.jeu;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application  {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("LinguaCrypt");
        primaryStage.setResizable(false);
        primaryStage.setHeight(Utils.getInstance().getWindowHeight());
        primaryStage.setWidth(Utils.getInstance().getWindowWidth());

        ChargeurScene chargeurScene = new ChargeurScene(primaryStage);
        chargeurScene.chargerMenuPrincipal();
    }

}