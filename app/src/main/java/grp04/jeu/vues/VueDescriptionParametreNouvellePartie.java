package grp04.jeu.vues;

import grp04.jeu.Utils;
import grp04.jeu.modele.GestionnaireMenuNewGame;
import javafx.scene.control.Label;

/**
 * Classe utilisée pour afficher la description du paramètre survolé dans le menu
 * de création d'une nouvelle partie.
 */
public class VueDescriptionParametreNouvellePartie extends Label implements Observateur {

    private final GestionnaireMenuNewGame gestionnaireMenuNewGame;

    public VueDescriptionParametreNouvellePartie(GestionnaireMenuNewGame gestionnaireMenuNewGame) {
        super();
        this.gestionnaireMenuNewGame = gestionnaireMenuNewGame;
        gestionnaireMenuNewGame.ajouterObservateur(this);

        this.setText("");
        this.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        this.setPrefWidth(Utils.getInstance().getWindowWidth() * 0.4);
        this.setWrapText(true);

    }

    @Override
    public void reagir() {
        switch (gestionnaireMenuNewGame.getTypeBoutonIncrSurvole()) {
            case TAILLE -> {
                setText("Régler la taille du plateau.");
            }
            case NB_CARTES -> {
                setText("""
                        Choisir le nombre de cartes à deviner pour l'équipe BLEUE (qui ne débute pas).
                        L'équipe ROUGE (qui débute) doit deviner une carte de plus.""");
            }
            case NB_NOIRES -> {
                setText("Choisir le nombre de cartes noires présentes sur le plateau.");
            }
            case TYPE_TIMER -> {
                setText("""
                        Régler le type de minuteur.
                        Individuel : l'AGENT et les ESPIONS ont chacun leur minuteur.
                        Équipe : le temps est partagé par tous les membres de l'équipe.""");
            }
            case TIMER_ESPION_OU_EQUIPE_BLEU -> {
                switch (gestionnaireMenuNewGame.getTypeTimer()) {
                    case EQUIPE -> {
                        setText("Temps (en secondes) dont dispose l'équipe BLEUE pour jouer son tour.");
                    }
                    case INDIVIDUEL -> {
                        setText("Temps (en secondes) dont dispose l'ESPION pour donner son indice pendant son tour.");
                    }
                    case null, default -> {}

                }
            }
            case TIMER_AGENT_OU_EQUIPE_ROUGE -> {
                switch (gestionnaireMenuNewGame.getTypeTimer()) {
                    case EQUIPE -> {
                        setText("Temps (en secondes) dont dispose l'équipe ROUGE pour jouer son tour.");
                    }
                    case INDIVIDUEL -> {
                        setText("Temps (en secondes) dont disposent les AGENTS pour jouer leur tour.");
                    }
                    case null, default -> {}
                }
            }
            case THEME -> {
                setText("""
                        Choisir le thème des mots qui seront présents sur les cartes.
                        Thème par défault : 100 mots.
                        Random : séléctionne les mots parmis l'ensemble des mots des thèmes.""");
            }
            case null, default -> {}  // ne rien mettre
        }

    }
}
