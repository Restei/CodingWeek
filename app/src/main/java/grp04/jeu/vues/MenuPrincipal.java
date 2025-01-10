package grp04.jeu.vues;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import grp04.jeu.modele.MenuButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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


        MenuButton newGame = new MenuButton("Nouvelle partie");
        newGame.onActionAndSound(e -> chargeurScene.menuNouvellePartie());
        newGame.setFont(headerFont);
        newGame.setStyle(Utils.getInstance().getMainMenuButtonColor());
        newGame.setPrefWidth(Utils.getInstance().getWindowWidth()*0.5);

        MenuButton loadGame = new MenuButton("Charger partie");
        loadGame.onActionAndSound(event -> chargeurScene.menuSauvegarde());
        loadGame.setFont(headerFont);
        loadGame.setStyle(Utils.getInstance().getMainMenuButtonColor());
        loadGame.setPrefWidth(Utils.getInstance().getWindowWidth()*0.5);

        MenuButton addWord = new MenuButton("Accéder à la liste de mots");
        addWord.onActionAndSound(event -> chargeurScene.menuListeMots());
        addWord.setFont(headerFont);
        addWord.setStyle(Utils.getInstance().getMainMenuButtonColor());
        addWord.setPrefWidth(Utils.getInstance().getWindowWidth()*0.5);

        MenuButton credits = new MenuButton("Crédits");
        credits.onActionAndSound(event -> overlay.ajouterEtAfficherPopup(new PopupCredits(chargeurScene)));
        credits.setFont(headerFont);
        credits.setStyle(Utils.getInstance().getMainMenuButtonColor());
        credits.setPrefWidth(Utils.getInstance().getWindowWidth()*0.5);

        MenuButton quit = new MenuButton("Quitter");
        quit.setFont(headerFont);
        quit.onActionAndSound(new EventHandler<ActionEvent>() {
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
