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

public class PopupStatistique extends VBox  {
    
    private final GestionnairePartie gestionnairePartie;

    public PopupStatistique(GestionnairePartie gestionnairePartie, ChargeurScene chargeurScene, Overlay overlay){
        this.gestionnairePartie= gestionnairePartie;
        this.setStyle("-fx-background-color: #FFFFFF;");
        this.setMaxSize(Utils.getInstance().getWindowWidth() * 0.8, Utils.getInstance().getWindowHeight() * 0.8);
        this.setSpacing(40);
        this.setAlignment(Pos.CENTER);

        Statistique statistique = gestionnairePartie.getStatistique();
        this.gestionnairePartie.pauseChrono();
        //Initalisation des partie de la vue

        Label Titre = new Label("Victoire de l'équipe :" + statistique.getGagnant());
        Titre.setFont(Utils.getInstance().getFont(Utils.FontType.TITLE));

        Label NumeroTour = new Label("Nombre de tour joué :" + Integer.toString(statistique.getNbTourJoue()));
        NumeroTour.setFont(Utils.getInstance().getFont(Utils.FontType.HEADER));

        Label DefaiteAssassin = new Label("");
        DefaiteAssassin.setFont(Utils.getInstance().getFont(Utils.FontType.HEADER));

        if (statistique.getNbCarteAssassinTrouve(TypeEquipe.BLEU)+ statistique.getNbCarteAssassinTrouve(TypeEquipe.ROUGE)>0){
            DefaiteAssassin.setText("L'équipe " + statistique.getPerdant() + " a pioché une carte assassin");
        }

        HBox Milieu = new HBox();


        //Initialisation listes

        Region regiongauche = new Region();
        Region regiondroite = new Region();

        HBox.setHgrow(regiongauche, Priority.ALWAYS);
        HBox.setHgrow(regiondroite, Priority.ALWAYS);

        VBox listedescription = new VBox();
        VBox listebleu = new VBox();
        VBox listerouge = new VBox();

        Label equipe = new Label("Equipe : ");
        Label civile = new Label("Nombre de cartes civiles pioché : ");
        Label TempsEspion = new Label("temps total utilisés par l'espion : ");
        Label TempsAgent = new Label("temps total utilisés par l'agent : ");
        Label CarteRestante = new Label("Nombre de carte restante : ");
        Label CarteAdverse = new Label("Nombre de cartes de l'équipe adverse pioché : ");

        equipe.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        civile.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        TempsEspion.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        TempsAgent.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        CarteRestante.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        CarteAdverse.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));

        Label equipeBleu = new Label("Bleu");
        Label nbCivileBleu = new Label(Integer.toString(statistique.getNbCarteCivileTrouve(TypeEquipe.BLEU)));
        Label TempsTotalEspionBleu = new Label(Integer.toString(statistique.getTempsTotal(TypeEquipe.BLEU, TypeJoueur.ESPION)));
        Label TempsTotalAgentBleu = new Label(  Integer.toString(statistique.getTempsTotal(TypeEquipe.BLEU, TypeJoueur.AGENT)));
        Label nbCarteBleuRestante = new Label(Integer.toString(statistique.getNbCarteRestante(TypeEquipe.BLEU)));
        Label nbCarteRougeTrouveParBleu = new Label(Integer.toString(statistique.getNbCarteRougeTrouveParBleu()));

        equipeBleu.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        nbCivileBleu.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        TempsTotalEspionBleu.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        TempsTotalAgentBleu.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        nbCarteBleuRestante.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        nbCarteRougeTrouveParBleu.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));

        Label equipeRouge = new Label("Rouge");
        Label nbCivileRouge = new Label(Integer.toString(statistique.getNbCarteCivileTrouve(TypeEquipe.ROUGE)));
        Label TempsTotalEspionRouge = new Label(Integer.toString(statistique.getTempsTotal(TypeEquipe.ROUGE, TypeJoueur.ESPION)));
        Label TempsTotalAgentRouge = new Label(Integer.toString(statistique.getTempsTotal(TypeEquipe.ROUGE, TypeJoueur.AGENT)));
        Label nbCarteRougeRestante = new Label(Integer.toString(statistique.getNbCarteRestante(TypeEquipe.ROUGE)));
        Label nbCarteBleuTrouveParRouge = new Label(Integer.toString(statistique.getNbCarteBleuTrouveParRouge()));

        equipeRouge.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        nbCivileRouge.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        TempsTotalEspionRouge.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        TempsTotalAgentRouge.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        nbCarteRougeRestante.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));
        nbCarteBleuTrouveParRouge.setFont(Utils.getInstance().getFont(Utils.FontType.SMALL_FONT));

        listedescription.getChildren().addAll(equipe,CarteRestante,civile,TempsEspion,TempsAgent,CarteAdverse);
        listebleu.getChildren().addAll(equipeBleu,nbCarteBleuRestante,nbCivileBleu,TempsTotalEspionBleu,TempsTotalAgentBleu,nbCarteRougeTrouveParBleu);
        listerouge.getChildren().addAll(equipeRouge,nbCarteRougeRestante,nbCivileRouge,TempsTotalEspionRouge,TempsTotalAgentRouge,nbCarteBleuTrouveParRouge);

        //Initialisation milieu

        Milieu.getChildren().addAll(listedescription,listebleu,regiongauche,regiondroite,listerouge);

        //Initialisation Retour

        Button Retour = new Button("RETOUR");
        Retour.setFont(Utils.getInstance().getFont(Utils.FontType.HEADER));
        Retour.setOnMouseClicked(e-> { chargeurScene.chargerMenuPrincipal();
        });
        //Initialisation PopUp

        getChildren().addAll(Titre,NumeroTour,DefaiteAssassin,Milieu,Retour);


    }

}
