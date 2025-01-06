package grp04.jeu.modele;

public class Carte {

    // Début propriétés

    private TypeCarte type;
    private String mot;
    private boolean revele = false;

    // Fin propriétés


    // Début onstructeurs

    public Carte(TypeCarte type, String mot) {
        this.type = type;
        this.mot = mot;
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

    // Fin méthodes

}
