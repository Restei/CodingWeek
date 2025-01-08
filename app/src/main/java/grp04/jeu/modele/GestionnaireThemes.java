package grp04.jeu.modele;

import java.io.File;
import java.util.ArrayList;

public class GestionnaireThemes {

    private GestionnaireThemes(){}
    
    public static ArrayList<String> themes(){
            ArrayList<String> liste = new ArrayList<String>();
            File dossierSaves = new File("themes/");
    
            if (!dossierSaves.exists() || !dossierSaves.isDirectory()) {
                System.err.println("Le dossier 'themes/' est introuvable !");
                return liste;
            }
    
            File[] fichiers = dossierSaves.listFiles((dir, name) ->
                    name.toLowerCase().endsWith("")
            );
    
            if (fichiers == null) return liste;
    
            for (File fichier : fichiers){
                liste.add(fichier.getName());
            }
            return liste;
        
        }

}
