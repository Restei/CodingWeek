package grp04.jeu.vues;

import grp04.jeu.Utils;
import grp04.jeu.modele.GestionnairePartie;
import grp04.jeu.modele.MenuButton;
import grp04.jeu.modele.Partie;
import grp04.jeu.modele.TypeEquipe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PopupChangerJoueur extends VBox{

    public PopupChangerJoueur(Overlay overlay, GestionnairePartie gestionnairePartie){

        Font font = Utils.getInstance().getFont(Utils.FontType.HEADER);
        Font smallfont = Utils.getInstance().getFont(Utils.FontType.SMALL_FONT);

        Partie partie = gestionnairePartie.getPartie();

        Label top = new Label("Fin de tour");
        top.setFont(font);

        String couleurEquipe = "";
        if (partie.getEquipeQuiJoue() == TypeEquipe.ROUGE) {
            couleurEquipe = "ROUGE";
        } else if (partie.getEquipeQuiJoue() == TypeEquipe.BLEU) {
            couleurEquipe = "BLEUE";
        } else {
            System.err.println("[PopupChangerJoueur] mauvais type d'équipe");
            couleurEquipe = "BROKEN";
        }
        Label body = new Label("Passez à l'"+partie.getJoueurQuiJoue()+" de l'équipe "+ couleurEquipe+".");
        body.setFont(smallfont);


        MenuButton reprendre = new MenuButton("Reprendre");
        reprendre.setStyle(Utils.getInstance().getMainMenuButtonColor());
        reprendre.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        reprendre.onActionAndSound(event -> {
            gestionnairePartie.lancerTimerIfEquipeEtEspion();
            overlay.fermerDernierPopup();
        });
        reprendre.setDefaultButton(true);


        this.getChildren().add(top);
        this.getChildren().add(body);
        this.getChildren().add(reprendre);

        this.setAlignment(Pos.CENTER);
        this.setSpacing(Utils.getInstance().getWindowWidth()*0.05);
        this.setMaxSize(Utils.getInstance().getWindowWidth()*0.6, Utils.getInstance().getWindowHeight()*0.4);
        this.setStyle("-fx-background-color:rgb(255, 255, 255)");
        this.setPadding(new Insets(Utils.getInstance().getWindowWidth()*0.05));
    }
    
}
