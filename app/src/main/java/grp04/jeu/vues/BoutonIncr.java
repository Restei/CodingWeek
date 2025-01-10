package grp04.jeu.vues;

import grp04.jeu.Utils;
import grp04.jeu.modele.GestionnaireMenuNewGame;
import grp04.jeu.modele.MenuButton;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class BoutonIncr extends HBox implements Observateur {

    private final GestionnaireMenuNewGame gestionnaireMenuNewGame;
    private final Label labelNom;
    private final Label labelElement;  // label entre les 2 boutons flèches
    private final Type type;  // type du BoutonIncr

    /**
     * Types de BoutonIncr.
     */
    public enum Type {
        TAILLE,
        NB_CARTES,
        NB_NOIRES,
        TYPE_TIMER,
        TIMER_ESPION_OU_EQUIPE_BLEU,  // premier bouton timer
        TIMER_AGENT_OU_EQUIPE_ROUGE,  // second bouton timer
        THEME,
    }

    public BoutonIncr(GestionnaireMenuNewGame gestionnaireMenuNewGame, Type type) {
        this.gestionnaireMenuNewGame = gestionnaireMenuNewGame;
        gestionnaireMenuNewGame.ajouterObservateur(this);
        this.labelElement = new Label();
        this.labelNom = new Label();
        this.type = type;

        Region spacing = new Region();
        HBox.setHgrow(spacing, Priority.ALWAYS);

        setSpacing(10);

        MenuButton prev = new MenuButton();
        Image fleche = new Image("fleche.png", labelElement.getHeight(), labelElement.getHeight(), false, false);
        ImageView flecheg = new ImageView(fleche);
        flecheg.setFitHeight(15);
        flecheg.setFitWidth(15);
        prev.setGraphic(flecheg);

        MenuButton next = new MenuButton();
        ImageView fleched = new ImageView(fleche);
        fleched.setFitHeight(15);
        fleched.setFitWidth(15);
        fleched.setRotate(180);
        next.setGraphic(fleched);

        this.setAlignment(Pos.CENTER);
        this.labelElement.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        this.labelNom.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));

        // gestion des évènements

        // incrémentation
        next.onActionAndSound(e -> {
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
                case TIMER_ESPION_OU_EQUIPE_BLEU:
                    gestionnaireMenuNewGame.incrTimerEspionBleu();
                    break;
                case TIMER_AGENT_OU_EQUIPE_ROUGE:
                    gestionnaireMenuNewGame.incrTimerAgentRouge();
                    break;
                case THEME:
                    gestionnaireMenuNewGame.themeSuivant();
                    break;
            }
        });

        // décrémentation
        prev.onActionAndSound(e -> {
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
                case TIMER_ESPION_OU_EQUIPE_BLEU:
                    gestionnaireMenuNewGame.decrTimerEspionBleu();
                    break;
                case TIMER_AGENT_OU_EQUIPE_ROUGE:
                    gestionnaireMenuNewGame.decrTimerAgentRouge();
                    break;
                case THEME:
                    gestionnaireMenuNewGame.themePrecedent();
                    break;
            }
        });

        this.setOnMouseEntered(e -> gestionnaireMenuNewGame.setTypeBoutonIncrSurvole(this.type));

        getChildren().addAll(labelNom, spacing, prev, this.labelElement, next);


    }

    public void reagir() {

        // gestion du labelNom
        this.labelNom.setText(getNomParametre(type));

        // gestion du labelElement
        switch (this.type) {
            case TAILLE:
                Object taille = gestionnaireMenuNewGame.getTaille(); // cast car type inconnu a priori
                this.labelElement.setText(taille + " × " + taille);
                break;
            case NB_CARTES:
                this.labelElement.setText(Integer.toString(gestionnaireMenuNewGame.getNbCarte()));
                break;
            case NB_NOIRES:
                this.labelElement.setText(gestionnaireMenuNewGame.getNbCarteNoire());
                break;
            case TYPE_TIMER:
                String text = "";
                switch(gestionnaireMenuNewGame.getTypeTimer()) {
                    case EQUIPE -> text = "Équipe";
                    case INDIVIDUEL -> text = "Individuel";
                }
                this.labelElement.setText(text);
                break;
            case TIMER_ESPION_OU_EQUIPE_BLEU:
                this.labelElement.setText(gestionnaireMenuNewGame.getTimerEspionBleu());
                break;
            case TIMER_AGENT_OU_EQUIPE_ROUGE:
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
            case TAILLE -> "Taille plateau";
            case NB_CARTES -> "Nombre total de cartes";
            case NB_NOIRES -> "Nombre de cartes noires";
            case TYPE_TIMER -> "Minuteur";
            case TIMER_ESPION_OU_EQUIPE_BLEU -> {
                switch (gestionnaireMenuNewGame.getTypeTimer()) {
                    case INDIVIDUEL -> {
                        yield "Temps de jeu pour l'espion";
                    }
                    case EQUIPE -> {
                        yield "Temps de jeu équipe bleue";
                    }
                    case null, default -> {
                        yield "ERR IN BoutonIncr.getNomParametre()";
                    }
                }
            }
            case TIMER_AGENT_OU_EQUIPE_ROUGE -> {
                switch (gestionnaireMenuNewGame.getTypeTimer()) {
                    case INDIVIDUEL -> {
                        yield "Temps de jeu pour l'agent";
                    }
                    case EQUIPE -> {
                        yield "Temps de jeu équipe rouge";
                    }
                    case null, default -> {
                        yield "ERR IN BoutonIncr.getNomParametre()";
                    }
                }
            }
            case THEME -> "Thème";
            default -> "MAUVAIS TYPE DE BOUTON";
        };

    }

}
