package grp04.jeu.modele;

import grp04.jeu.vues.Observateur;
import java.util.ArrayList;

// Classe abstraite que les objets pouvant être observés doivent étendre.
public abstract class SujetObserve {
    private final ArrayList<Observateur> observateurs;

    public SujetObserve() {
        observateurs = new ArrayList<>();
    }

    public void NotifierObservateurs() {
        for (Observateur obs : observateurs) {
            obs.reagir();
        }
    }
    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    }
}
