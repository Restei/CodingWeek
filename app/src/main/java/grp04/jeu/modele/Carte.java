package grp04.jeu.modele;

import java.io.Serializable;

public class Carte extends SujetObserve implements Serializable {

    // Début propriétés

    private final TypeCarte type;
    private final String mot;
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

    public String getMot() {
        return mot;
    }

    public boolean getRevele(){
        return this.revele;
    }

    public void reveler(){
        this.revele = true;
    }

    /**
     * Fonction qui permet de changer le statue de la carte entre chaque tour.
     */
    public void switchRole() {
        this.espion = !this.espion;
    }

    /**
     * Fonction qui renvoie true si c'est un espion et faux sinon
     * @return boolean
     */
    public boolean getRole() {
        return this.espion;
    }



    // Fin méthodes

}
