package grp04.jeu.modele;

public class Carte extends SujetObserve {

    // Début propriétés

    private TypeCarte type;
    private String mot;
    private boolean revele;
    private boolean espion;

    // Fin propriétés


    // Début Constructeurs

    public Carte(TypeCarte type, String mot) {
        super();
        this.type = type;
        this.mot = mot;
        this.espion = true;
        this.revele = false;
    }

    // Fin constructeurs


    // Début méthodes

    public TypeCarte getType() {
        return type;
    }

    public void setType(TypeCarte type) {
        this.type = type;
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public boolean getRevele(){
        return this.revele;
    }

    public void reveler(){
        this.revele = true;
    }

    public void switchRole() {
        this.espion = !this.espion;
    }

    public boolean getRole() {
    //fonction qui renvoie true si c'est un espion et faux sinon

        return this.espion;
    }

    // Fin méthodes

}
