package grp04.jeu.modele;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionnaireThemes {

    private GestionnaireThemes(){}
    
    public static ArrayList<String> themes(){
            ArrayList<String> liste = new ArrayList<String>();
            File dossierSaves = new File("themes/");
    
            if (!dossierSaves.exists() || !dossierSaves.isDirectory()) {
                if (!dossierSaves.mkdir()) {
                    System.err.println("Le dossier 'themes/' est introuvable, et n'a pas pu être créé !");
                    return liste;
                }
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

        public static void ajouterMot(String name, String theme){
            try {
                name = name.toUpperCase();
                BufferedWriter writer = new BufferedWriter(new FileWriter("themes/"+theme, true));
                writer.append(name);
                writer.append('\n');
                writer.close();

            } catch (IOException i) {
                i.printStackTrace();
            }
        }

        public static ArrayList<String> mots(String theme){
            ArrayList<String> liste = new ArrayList<String>();
            File themeFile = new File("themes/"+theme);
    
            if (!themeFile.exists()) {
                System.err.println("Le fichier 'themes/'"+theme+" est introuvable !");
                return liste;
            }
            Scanner myReader;
            try {
                myReader = new Scanner(themeFile);
                while (myReader.hasNextLine()) {
                    liste.add(myReader.nextLine());
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return liste;
        }

}
