package grp04.jeu.vues;

import grp04.jeu.Utils;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class BoutonIncr extends HBox {
    private MenuNouvellePartie vue;
    private Label elem;
    private int limite;
    private int conf;
    private String nom;

    public BoutonIncr(MenuNouvellePartie vue,String nom,int initial_conf, int limite){
        this.vue = vue;
        this.limite = limite;
        this.conf = initial_conf;
        this.elem = new Label(Integer.toString(initial_conf));
        setSpacing(10);
        Button prev = new Button();
        Button next = new Button();
        Image fleche = new Image("fleche.png",elem.getHeight(),elem.getHeight(),false,false);
        ImageView flecheg = new ImageView(fleche);
        flecheg.setFitHeight(15);
        flecheg.setFitWidth(15);
        prev.setGraphic(flecheg);
        prev.setOnMouseClicked(e-> decr());

        ImageView fleched = new ImageView(fleche);
        fleched.setFitHeight(15);
        fleched.setFitWidth(15);
        fleched.setRotate(180);
        next.setGraphic(fleched);
        next.setOnMouseClicked(e-> incr());
        this.setAlignment(Pos.CENTER);
        this.elem.setFont(Utils.getInstance().getFont(2));

        Label labelnom = new Label(nom);
        labelnom.setFont(Utils.getInstance().getFont(2));

        getChildren().addAll(labelnom,prev,this.elem,next);

        //getChildren().set(3)
    }

    public void incr(){
        if (this.conf<this.limite) {
            this.conf++;
        }
        Label changement = new Label(Integer.toString(this.conf));
        changement.setFont(Utils.getInstance().getFont(2));
        getChildren().set(2,changement);
    }
    public void decr(){
        if (this.conf>1) {
            this.conf--;
        }
        Label changement = new Label(Integer.toString(this.conf));
        changement.setFont(Utils.getInstance().getFont(2));
        getChildren().set(2,changement);
    }

}
