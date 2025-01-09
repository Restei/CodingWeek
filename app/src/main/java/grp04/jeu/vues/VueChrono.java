package grp04.jeu.vues;

import grp04.jeu.modele.GestionnairePartie;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;

// HBox contenant l'image d'un sablier et le temps restant à jouer
public class VueChrono extends HBox implements Observateur {

    private final GestionnairePartie gestionnairePartie;
    private final Label labelTemps;

    public VueChrono(GestionnairePartie gestionnairePartie) {
        super();
        this.gestionnairePartie = gestionnairePartie;
        this.gestionnairePartie.ajouterObservateur(this);
        this.labelTemps = new Label();

        Image chronoImage = new Image("chrono.png");
        new ImageView(chronoImage);
        this.getChildren().add(new ImageView(chronoImage));
        this.getChildren().add(this.labelTemps);

    }

    @Override
    public void reagir() {
        int tempsRestant = gestionnairePartie.getTemps();

        // temps restant en s
        int minutesRestantes = tempsRestant / 60;
        int secondesRestantes = tempsRestant % 60;
        this.labelTemps.setText(minutesRestantes + ":" + String.format("%02d", secondesRestantes));
    }
}
