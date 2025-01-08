package grp04.jeu.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Theme implements Serializable {

    // Début propriétés

    private List<String> mots = new ArrayList<>();
    private String name;
    private int id;
    private static int lastId = 0;

    // Fin propriétés

    // Début constructeurs

    public Theme(String name) {
        this.name = name;
        id = lastId++;
    }

    // Fin constructeurs

    // Début méthodes

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMots() {
        return mots;
    }

    public void addMot(String mot) {
        if (!mots.contains(mot)) {
            mots.add(mot);
        }
    }

    public void removeMot(String mot) {
        mots.remove(mot);
    }

    public boolean estDansMots(String mot) {
        return mots.contains(mot);
    }

    // Fin méthodes

}
