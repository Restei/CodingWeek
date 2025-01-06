package grp04.jeu.vues;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;

// HBox contenant l'image d'un sablier et le temps restant à jouer
public class VueChrono extends HBox implements Observateur {

    private GestionnaireTemps gestionnaireTemps;
    private Label labelTemps;

    public VueChrono(GestionnaireTemps gestionnaireTemps) {
        super();
        this.gestionnaireTemps = gestionnaireTemps;
        this.labelTemps = new Label("0:00");

        Image chronoImage = new Image("chrono.png");
        new ImageView(chronoImage);
        this.getChildren().add(new ImageView(chronoImage));
        this.getChildren().add(this.labelTemps);

    }

    @Override
    public void reagir() {
        int tempsRestant = gestionnaireTemps.getTemps(); // temps restant en s
        int minutesRestantes = tempsRestant / 60;
        int secondesRestantes = tempsRestant % 60;
        this.labelTemps.setText(minutesRestantes + ":" + secondesRestantes);
    }
}
