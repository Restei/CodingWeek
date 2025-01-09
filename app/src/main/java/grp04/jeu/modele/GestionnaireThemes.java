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

    /**
     * Permet de retoruner la liste des thèmes sauvegarder dans le dossier themes
     * @return la liste des noms des thèmes
     */
    public static ArrayList<String> themes(){
            ArrayList<String> liste = new ArrayList<>();
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

    /**
     * Permet d'ajouter un mot dans un thème
     * @param name mot à ajouter
     * @param theme nom du thème
     */
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

    /**
     * Permet de retourner l'ensemble des mots d'un thème
     * @param theme nom du thème
     * @return sa liste de mots
     */
    public static ArrayList<String> mots(String theme){
            ArrayList<String> liste = new ArrayList<>();
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
