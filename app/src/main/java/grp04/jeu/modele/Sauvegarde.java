package grp04.jeu.modele;

import java.io.Serializable;

public class Sauvegarde implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Partie partie;
    private final Statistique statistique;

    public Sauvegarde(Partie partie, Statistique statistique){
        this.partie = partie;
        this.statistique=statistique;
    }

    public Partie dataPartie(){
        return this.partie;
    }
    
    public Statistique dataStatistique(){
        return this.statistique;
    }
    
}
