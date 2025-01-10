package grp04.jeu.vues;

import grp04.jeu.ChargeurScene;
import grp04.jeu.Utils;
import grp04.jeu.modele.GestionnairePartie;
import grp04.jeu.modele.Statistique;
import grp04.jeu.modele.TypeEquipe;
import grp04.jeu.modele.TypeJoueur;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Popup affiché en fin de partie.
 */
public class PopupStatistique extends VBox {

    private final GestionnairePartie gestionnairePartie;

    public PopupStatistique(GestionnairePartie gestionnairePartie, ChargeurScene chargeurScene, Overlay overlay) {
        this.gestionnairePartie = gestionnairePartie;
        this.setStyle("-fx-background-color: #FFFFFF;");
        this.setMaxSize(Utils.getInstance().getWindowWidth() * 0.8, Utils.getInstance().getWindowHeight() * 0.8);
        this.setSpacing(30);
        this.setAlignment(Pos.CENTER);

        Statistique statistique = gestionnairePartie.getStatistique();
        this.gestionnairePartie.pauseChrono();

        //Initalisation des partie de la vue

        String equipeGagnante = "";
        if (statistique.getGagnant() == TypeEquipe.ROUGE) {
            equipeGagnante = "ROUGE";
        } else if (statistique.getGagnant() == TypeEquipe.BLEU) {
            equipeGagnante = "BLEUE";
        } else {
            System.err.println("[PopupStatistique] mauvais type d'équipe");
            equipeGagnante = "BROKEN";
        }

        Label Titre = new Label("Victoire de l'équipe " + equipeGagnante + " !");
        Titre.setFont(Utils.getInstance().getFont(Utils.FontType.TITLE));

        Label NumeroTour = new Label();
        int nbToursJoues = statistique.getNbTourJoue();
        if (nbToursJoues == 1) {
            NumeroTour.setText("1 tour joué.");
        } else {
            NumeroTour.setText(nbToursJoues + " tours joués.");
        }
        NumeroTour.setFont(Utils.getInstance().getFont(Utils.FontType.HEADER));

        Label DefaiteAssassin = new Label("");
        DefaiteAssassin.setFont(Utils.getInstance().getFont(Utils.FontType.HEADER));

        if (statistique.getNbCarteAssassinTrouve(TypeEquipe.BLEU) + statistique.getNbCarteAssassinTrouve(TypeEquipe.ROUGE) > 0) {
            DefaiteAssassin.setText("L'équipe " + statistique.getPerdant() + " a pioché une carte assassin.");
        }

        // données centrales
        HBox Milieu = new HBox();

        // données
        VBox listedescription = new VBox();
        listedescription.setAlignment(Pos.CENTER_LEFT);
        VBox listebleu = new VBox();
        listebleu.setAlignment(Pos.CENTER);
        VBox listerouge = new VBox();
        listerouge.setAlignment(Pos.CENTER);

        Label equipe = new Label("Équipe : ");
        Label civile = new Label("Cartes civiles piochées : ");
        Label TempsEspion = new Label("Temps total utilisé par l'ESPION : ");
        Label TempsAgent = new Label("Temps total utilisé par les AGENTS : ");
        Label CarteRestante = new Label("Cartes restantes : ");
        Label CarteAdverse = new Label("Cartes de l'équipe adverse piochées : ");

        Label equipeBleu = new Label("BLEU");
        Label nbCivileBleu = new Label(formatCarteLabel(statistique.getNbCarteCivileTrouve(TypeEquipe.BLEU)));
        Label TempsTotalEspionBleu = new Label(statistique.getTempsTotal(TypeEquipe.BLEU, TypeJoueur.ESPION) + " s");
        Label TempsTotalAgentBleu = new Label(statistique.getTempsTotal(TypeEquipe.BLEU, TypeJoueur.AGENT) + " s");
        Label nbCarteBleuRestante = new Label(formatCarteLabel(statistique.getNbCarteRestante(TypeEquipe.BLEU)));
        Label nbCarteRougeTrouveParBleu = new Label(formatCarteLabel(statistique.getNbCarteRougeTrouveParBleu()));

        Label equipeRouge = new Label("ROUGE");
        Label nbCivileRouge = new Label(formatCarteLabel(statistique.getNbCarteCivileTrouve(TypeEquipe.ROUGE)));
        Label TempsTotalEspionRouge = new Label(statistique.getTempsTotal(TypeEquipe.ROUGE, TypeJoueur.ESPION) + " s");
        Label TempsTotalAgentRouge = new Label(statistique.getTempsTotal(TypeEquipe.ROUGE, TypeJoueur.AGENT) + " s");
        Label nbCarteRougeRestante = new Label(formatCarteLabel(statistique.getNbCarteRestante(TypeEquipe.ROUGE)));
        Label nbCarteBleuTrouveParRouge = new Label(formatCarteLabel(statistique.getNbCarteBleuTrouveParRouge()));

        // tous les labels utilisés
        Label[] allLabel = {equipe, civile, TempsEspion, TempsAgent, CarteRestante, CarteAdverse, equipeBleu, nbCivileBleu, TempsTotalEspionBleu, TempsTotalAgentBleu, nbCarteBleuRestante, nbCarteRougeTrouveParBleu, equipeRouge, nbCivileRouge, TempsTotalEspionRouge, TempsTotalAgentRouge, nbCarteRougeRestante, nbCarteBleuTrouveParRouge};

        for (Label label : allLabel) {
            label.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        }

        listedescription.getChildren().addAll(equipe, CarteRestante, civile, TempsEspion, TempsAgent, CarteAdverse);
        listebleu.getChildren().addAll(equipeBleu, nbCarteBleuRestante, nbCivileBleu, TempsTotalEspionBleu, TempsTotalAgentBleu, nbCarteRougeTrouveParBleu);
        listerouge.getChildren().addAll(equipeRouge, nbCarteRougeRestante, nbCivileRouge, TempsTotalEspionRouge, TempsTotalAgentRouge, nbCarteBleuTrouveParRouge);

        // espacements
        Region spacingMilieu1 = new Region();
        Region spacingMilieu2 = new Region();
        Region spacingMilieu3 = new Region();
        Region spacingMilieu4 = new Region();

        HBox.setHgrow(spacingMilieu1, Priority.ALWAYS);
        HBox.setHgrow(spacingMilieu2, Priority.ALWAYS);
        HBox.setHgrow(spacingMilieu3, Priority.ALWAYS);
        HBox.setHgrow(spacingMilieu4, Priority.ALWAYS);


        //Initialisation milieu
        Milieu.getChildren().addAll(spacingMilieu1, listedescription, spacingMilieu2, listebleu, spacingMilieu3, listerouge, spacingMilieu4);

        //Initialisation Retour

        Button Retour = new Button("Retour au menu");
        Retour.setFont(Utils.getInstance().getFont(Utils.FontType.HEADER));
        Retour.setOnMouseClicked(e -> {
            chargeurScene.chargerMenuPrincipal();
        });
        //Initialisation PopUp

        getChildren().addAll(Titre, NumeroTour, DefaiteAssassin, Milieu, Retour);


    }


    /**
     * Formate un libellé indiquant le nombre de cartes, en gérant le singulier et le pluriel.
     *
     * @param nbCartes Le nombre de cartes à afficher.
     * @return Une chaîne de caractères représentant le nombre de cartes,
     * avec "carte" au singulier si {@code nbCartes} vaut 1,
     * sinon "cartes" au pluriel.
     */
    private String formatCarteLabel(int nbCartes) {
        return nbCartes + (nbCartes == 1 ? " carte" : " cartes");
    }


}
