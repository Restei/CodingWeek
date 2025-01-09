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

    }

    @Override
    public void reagir() {
        switch (gestionnaireMenuNewGame.getTypeBoutonIncrSurvole()) {
            case TAILLE -> {
                setText("Régler la taille du plateau.");
            }
            case NB_CARTES -> {
                setText("Choisir le nombre de cartes à deviner pour l'équipe qui ne débute pas.\n" +
                        "L'équipe qui débute doit deviner une carte de plus.");
            }
            case NB_NOIRES -> {
                setText("Choisir le nombre de cartes noires présentes sur le plateau.");
            }
            case TYPE_TIMER -> {
                setText("Régler le type de minuteur.\n" +
                        "Individuel : ...\n" +
                        "Équipe : l²mao");
            }
            case TIMER_ESPION -> {
                setText("Temps ");
            }
            case TIMER_AGENT -> {
                setText("bla");
            }
            case THEME -> {
                setText("Choisir le thème des mots qui seront présents sur les cartes.");
            }
            case null, default -> {}  // ne rien mettre
        }

    }
}
