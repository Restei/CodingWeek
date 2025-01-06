package grp04.jeu;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class GridPaneExperiments extends Application  {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("LinguaCrypt");
        int height = 800;
        int width = 1200;
        primaryStage.setHeight(height);
        primaryStage.setWidth(width);
        
        Font font_title = Font.font("Courier New", 40);

        Text Title = new Text(0,width*0.1, "Welcome !");
        Title.setWrappingWidth(width-10);
        Title.setTextAlignment(TextAlignment.CENTER);
        Title.setFont(font_title);

        Group firstGroup = new Group(Title);
        Scene firstScene = new Scene(firstGroup, Color.WHITE);
        primaryStage.setScene(firstScene);
        primaryStage.show();
    }

}