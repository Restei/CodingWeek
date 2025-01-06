package grp04.jeu.modele;

public class Carte extends SujetObserve{
    private boolean reveal;
    private String couleur;
    private String mot;
    public Carte(){
        super();
        this.reveal = false;
        this.couleur = "R";
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setMot(String mot){
        this.mot=mot;
    }
    public String getMot(){
        return this.mot;
    }
    public String Getcolor(){
        return couleur;
    }
    public boolean revealed(){
        return this.reveal;
    }
}
