package grp04.jeu.vues;

import grp04.jeu.Utils;
import grp04.jeu.modele.GestionnaireMenuNewGame;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class BoutonIncr extends HBox implements Observateur {
    private final GestionnaireMenuNewGame gestionnaireMenuNewGame;
    private final Label labelElement;  // label entre les 2 boutons flèches
    private final Type type;


    /**
     * Types de BoutonIncr.
     */
    public enum Type {
        TAILLE,
        NB_CARTES,
        NB_NOIRES,
        TYPE_TIMER,
        TIMER_ESPION,
        TIMER_AGENT,
        THEME,
    }


    public BoutonIncr(GestionnaireMenuNewGame gestionnaireMenuNewGame, Type type) {
        this.gestionnaireMenuNewGame = gestionnaireMenuNewGame;
        gestionnaireMenuNewGame.ajouterObservateur(this);
        this.labelElement = new Label();
        this.type = type;

        setSpacing(10);

        Button prev = new Button();
        Image fleche = new Image("fleche.png", labelElement.getHeight(), labelElement.getHeight(), false, false);
        ImageView flecheg = new ImageView(fleche);
        flecheg.setFitHeight(15);
        flecheg.setFitWidth(15);
        prev.setGraphic(flecheg);

        Button next = new Button();
        ImageView fleched = new ImageView(fleche);
        fleched.setFitHeight(15);
        fleched.setFitWidth(15);
        fleched.setRotate(180);
        next.setGraphic(fleched);

        this.setAlignment(Pos.CENTER);
        this.labelElement.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));

        Label labelnom = new Label(getNomParametre(type));


        // gestion des évènements

        next.setOnMouseClicked(e -> {
            switch (type) {
                case TAILLE:
                    gestionnaireMenuNewGame.incrTaille();
                    break;
                case NB_CARTES:
                    gestionnaireMenuNewGame.incrNbCarte();
                    break;
                case NB_NOIRES:
                    gestionnaireMenuNewGame.incrNbCarteNoire();
                    break;
                case TYPE_TIMER:
                    gestionnaireMenuNewGame.switchType();
                    break;
                case TIMER_ESPION:
                    gestionnaireMenuNewGame.incrTimerEspionBleu();
                    break;
                case TIMER_AGENT:
                    gestionnaireMenuNewGame.incrTimerAgentRouge();
                    break;
                case THEME:
                    gestionnaireMenuNewGame.themeSuivant();
                    break;
            }
        });

        prev.setOnMouseClicked(e -> {
            switch (type) {
                case TAILLE:
                    gestionnaireMenuNewGame.decrTaille();
                    break;
                case NB_CARTES:
                    gestionnaireMenuNewGame.decrNbCarte();
                    break;
                case NB_NOIRES:
                    gestionnaireMenuNewGame.decrNbCarteNoire();
                    break;
                case TYPE_TIMER:
                    gestionnaireMenuNewGame.switchType();
                    break;
                case TIMER_ESPION:
                    gestionnaireMenuNewGame.decrTimerEspionBleu();
                    break;
                case TIMER_AGENT:
                    gestionnaireMenuNewGame.decrTimerAgentRouge();
                    break;
                case THEME:
                    gestionnaireMenuNewGame.themePrecedent();
                    break;
            }
        });

        this.setOnMouseEntered(e -> gestionnaireMenuNewGame.setTypeBoutonIncrSurvole(this.type));

        getChildren().addAll(labelnom, prev, this.labelElement, next);


    }

    public void reagir() {
        switch (this.type) {
            case TAILLE:
                Object taille = gestionnaireMenuNewGame.getTaille(); // cast car type inconnu a priori
                this.labelElement.setText("Taille : " + taille + " x " + taille);
                break;
            case NB_CARTES:
                this.labelElement.setText(gestionnaireMenuNewGame.getNbCarte());
                break;
            case NB_NOIRES:
                this.labelElement.setText(gestionnaireMenuNewGame.getNbCarteNoire());
                break;
            case TYPE_TIMER:
                this.labelElement.setText(gestionnaireMenuNewGame.getType());
                break;
            case TIMER_ESPION:
                this.labelElement.setText(gestionnaireMenuNewGame.getTimerEspionBleu());
                break;
            case TIMER_AGENT:
                this.labelElement.setText(gestionnaireMenuNewGame.getTimerAgentRouge());
                break;
            case THEME:
                this.labelElement.setText(gestionnaireMenuNewGame.getTheme());
                break;
        }

    }

    /**
     * Donne le nom du paramètre à afficher avant le système de réglage, en fonction du type du bouton.
     */
    private String getNomParametre(Type type) {
        return switch (this.type) {
            case TAILLE -> "Taille de la grille";
            case NB_CARTES -> "Nombre total de cartes";
            case NB_NOIRES -> "Nombre de cartes noires";
            case TYPE_TIMER -> "Minuteur";
            case TIMER_ESPION -> "Temps de jeu pour l'espion";
            case TIMER_AGENT -> "Temps de jeu pour l'agent";
            case THEME -> "Thème";
            default -> "MAUVAIS TYPE DE BOUTON";
        };

    }

}
