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
    private GestionnaireMenuNewGame menu;
    private Label elem;
    private String champs;

    public BoutonIncr(GestionnaireMenuNewGame menu,String champs){
        this.menu = menu;
        menu.ajouterObservateur(this);
        this.elem = new Label();
        this.champs = champs;
        setSpacing(10);
        Button prev = new Button();
        Button next = new Button();
        Image fleche = new Image("fleche.png",elem.getHeight(),elem.getHeight(),false,false);
        ImageView flecheg = new ImageView(fleche);
        flecheg.setFitHeight(15);
        flecheg.setFitWidth(15);
        prev.setGraphic(flecheg);

        ImageView fleched = new ImageView(fleche);
        fleched.setFitHeight(15);
        fleched.setFitWidth(15);
        fleched.setRotate(180);
        next.setGraphic(fleched);
        this.setAlignment(Pos.CENTER);
        this.elem.setFont(Utils.getInstance().getFont(2));

        Label labelnom = new Label(champs);
        switch (champs){
            case "taille":
                this.elem.setText(menu.getTaille());
                break;
            case "NbCartes":
                this.elem.setText(menu.getNbCarte());
                break;
            case "NbNoires":
                this.elem.setText(menu.getNbCarteNoire());
                break;
            case "typetime":
                this.elem.setText(menu.getType());
                break;
            case "timer espion":
                this.elem.setText(menu.getTimerEspionBleu());
                break;
            case "timer agent":
                this.elem.setText(menu.getTimerAgentRouge());
                break;
            case "theme":
                this.elem.setText(menu.getTheme());
                break;
        }


        next.setOnMouseClicked(e-> {        switch (champs){
            case "taille":
                menu.incrTaille();
                break;
            case "NbCartes":
                menu.incrNbCarte();
                break;
            case "NbNoires":
                menu.incrNbCarteNoire();
                break;
            case "typetime":
                menu.switchType();
                break;
            case "timer espion":
                menu.incrTimerEspionBleu();
                break;
            case "timer agent":
                menu.incrTimerAgentRouge();
                break;
            case "theme":
                menu.themeSuivant();
                break;
        }});

        prev.setOnMouseClicked(e-> {
            switch (champs){
                case "taille":
                    menu.decrTaille();
                    break;
                case "NbCartes":
                    menu.decrNbCarte();
                    break;
                case "NbNoires":
                    menu.decrNbCarteNoire();
                    break;
                case "typetime":
                    menu.switchType();
                    break;
                case "timer espion":
                    menu.decrTimerEspionBleu();
                    break;
                case "timer agent":
                    menu.decrTimerAgentRouge();
                    break;
                case "theme":
                    menu.themePrecedent();
                    break;
            }});


        getChildren().addAll(labelnom,prev,this.elem,next);



    }
    public void reagir(){
        switch (this.champs){
            case "taille":
                this.elem.setText(menu.getTaille());
                break;
            case "NbCartes":
                this.elem.setText(menu.getNbCarte());
                break;
            case "NbNoires":
                this.elem.setText(menu.getNbCarteNoire());
                break;
            case "typetime":
                this.elem.setText(menu.getType());
                break;
            case "timer espion":
                this.elem.setText(menu.getTimerEspionBleu());
                break;
            case "timer agent":
                this.elem.setText(menu.getTimerAgentRouge());
                break;
            case "theme":
                this.elem.setText(menu.getTheme());
                break;
        }

    }

}
