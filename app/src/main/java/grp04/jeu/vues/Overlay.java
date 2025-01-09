package grp04.jeu.vues;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Overlay extends StackPane {

    /**
     * Crée un Overlay permettant d'afficher des popups supplémentaires devant un fond.
     */
    public Overlay(ChargeurScene chargeurScene) {
        super();
    }

    /**
     * Place une couche en fond de l'overlay, remplaçant éventuellement celle qu'il y avait avant.
     *
     * @param node couche affichée au fond de l'overlay
     */
    public void setFond(Node node) {
        if (this.getChildren().isEmpty()) {
            this.getChildren().add(node);
        } else {
            this.getChildren().set(0, node);
        }
    }

    private void ajouterEtAfficherCoucheIntermediaire() {
        final Canvas canvas = new Canvas(Utils.getInstance().getWindowWidth(), Utils.getInstance().getWindowHeight());
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.rgb(110, 105, 100, 0.7));
        graphicsContext.fillRect(0, 0, Utils.getInstance().getWindowWidth(), Utils.getInstance().getWindowHeight());
        this.getChildren().add(canvas);
    }

    /**
     * Ajoute une couche grise et un popup devant le fond.
     *
     * @param popup Node qui sera affiché, contenant le popup. Il faut éventuellement lui donner accès à l'instance
     *             d'overlay avant de l'ajouter.
     */
    public void ajouterEtAfficherPopup(Node popup) {
        this.ajouterEtAfficherCoucheIntermediaire();
        this.getChildren().add(popup);
    }

    /**
     * Ferme le dernier popup ajouté
     */
    public void fermerDernierPopup() {
        if (this.getChildren().size() >= 3) {
            this.getChildren().removeLast(); // ferme le popup
            this.getChildren().removeLast(); // ferme la couche grise
        } else {
            System.err.println("[Overlay.fermerDernierPopup()] Avertissement : tentative de suppression d'un popup inexistant");
        }
    }

}
