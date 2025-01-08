package grp04.jeu.modele;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GestionnaireSauvegarde {

    private GestionnaireSauvegarde(){}

    public static ArrayList<String> initialisation(){
        ArrayList<String> liste = new ArrayList<String>();
        File dossierSaves = new File("sauvegardes/");

        if (!dossierSaves.exists() || !dossierSaves.isDirectory()) {
            System.err.println("Le dossier 'sauvegardes/' est introuvable !");
            return liste;
        }

        File[] fichiers = dossierSaves.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".sav")
        );

        if (fichiers == null) return liste;

        for (File fichier : fichiers){
            liste.add(fichier.getName());
        }
        return liste;
    
    }


    public static void supprimerSauvegarde(String name){
        File dossierSaves = new File("sauvegardes/");

        if (!dossierSaves.exists() || !dossierSaves.isDirectory()) {
            System.err.println("Le dossier 'sauvegardes/' est introuvable !");
            return;
        }

        File[] fichiers = dossierSaves.listFiles((dir, names) ->
                names.toLowerCase().endsWith(".sav")
        );

        if (fichiers == null) return;

        for (File fichier : fichiers){
            if (fichier.getName().equals(name)){
                fichier.delete();
            }
        }
    }


    public static void sauvegarder(String name,Partie partie/*, Statistique statistique */){
        Sauvegarde sauvegarde= new Sauvegarde(partie/* ,statistique */ );
        try {
            FileOutputStream fileOut = new FileOutputStream("sauvegardes/"+name+".sav");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(sauvegarde );
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    

}