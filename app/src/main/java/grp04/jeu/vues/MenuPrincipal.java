package grp04.jeu.vues;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MenuPrincipal extends VBox {

    public MenuPrincipal(ChargeurScene chargeurScene, Overlay overlay) {
        Font headerFont = Utils.getInstance().getFont(Utils.FontType.HEADER);
        Font titleFont = Utils.getInstance().getFont(Utils.FontType.TITLE);


        // Espacement avant le titre
        Region topSpacing = new Region();
        VBox.setVgrow(topSpacing, javafx.scene.layout.Priority.ALWAYS);

        Label titre = new Label("LinguaCrypt");
        titre.setFont(titleFont);

        // Espacement avant les boutons
        Region middleSpacing = new Region();
        VBox.setVgrow(middleSpacing, javafx.scene.layout.Priority.ALWAYS);


        Button newGame = new Button("Nouvelle partie");
        newGame.setOnAction(event -> chargeurScene.menuNouvellePartie());
        newGame.setFont(headerFont);
        newGame.setStyle(Utils.getInstance().getMainMenuButtonColor());
        newGame.setPrefWidth(Utils.getInstance().getWindowWidth()*0.5);

        Button loadGame = new Button("Charger partie");
        loadGame.setOnAction(event -> chargeurScene.menuSauvegarde());
        loadGame.setFont(headerFont);
        loadGame.setStyle(Utils.getInstance().getMainMenuButtonColor());
        loadGame.setPrefWidth(Utils.getInstance().getWindowWidth()*0.5);

        Button addWord = new Button("Accéder à la liste de mots");
        addWord.setOnAction(event -> chargeurScene.menuListeMots());
        addWord.setFont(headerFont);
        addWord.setStyle(Utils.getInstance().getMainMenuButtonColor());
        addWord.setPrefWidth(Utils.getInstance().getWindowWidth()*0.5);

        Button credits = new Button("Crédits");
        credits.setOnAction(event -> overlay.ajouterEtAfficherPopup(new PopupCredits(chargeurScene)));
        credits.setFont(headerFont);
        credits.setStyle(Utils.getInstance().getMainMenuButtonColor());
        credits.setPrefWidth(Utils.getInstance().getWindowWidth()*0.5);

        Button quit = new Button("Quitter");
        quit.setOnAction(event -> Platform.exit());
        quit.setFont(headerFont);
        quit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                PopupQuitter popupQuitter = new PopupQuitter(overlay, chargeurScene, 0);
                overlay.ajouterEtAfficherPopup(popupQuitter);
            }
        });
        //Font font = Font.font("Courier New", 28);
        //quit.setFont(font);
        quit.setFont(headerFont);
        quit.setStyle(Utils.getInstance().getMainMenuButtonColor());
        quit.setPrefWidth(Utils.getInstance().getWindowWidth()*0.5);

        // Espacement après les boutons
        Region bottomSpacing = new Region();
        VBox.setVgrow(bottomSpacing, javafx.scene.layout.Priority.ALWAYS);

        this.getChildren().addAll(topSpacing, titre, middleSpacing, newGame, loadGame, addWord, credits, quit, bottomSpacing);

        this.setSpacing(20); // Espacement entre les éléments
        this.setAlignment(Pos.CENTER);
    }
}
