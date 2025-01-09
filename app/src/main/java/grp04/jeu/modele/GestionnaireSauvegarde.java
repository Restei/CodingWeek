package grp04.jeu.modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GestionnaireSauvegarde {

    public static String gameName;
    
        private GestionnaireSauvegarde(){}

    /**
     * Permet de récupérer les différents sauvegardes de parties sauvegardées dans le dossier sauvegardes.
     * @return une liste des noms des sauvegardes
     */
    public static ArrayList<String> initialisation(){
            ArrayList<String> liste = new ArrayList<>();
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
                System.err.println("Le dossier 'sauvegarde/' est introuvable !");
                return;
            }
    
            File[] fichiers = dossierSaves.listFiles((dir, names) ->
                    names.toLowerCase().endsWith(".sav")
            );
    
            if (fichiers == null) return;
    
            for (File fichier : fichiers){
                if (fichier.getName().equals(name)){
                    if (!fichier.delete()) {
                        System.out.println("Erreur de suppression du fichier: " + name);
                    }
                }
            }
        }

    /**
     * Permet de sauvegarder une partie
     * @param name nom de la sauvegarde
     * @param partie partie à sauvegarder
     * @param statistique statistique associée à la partie
     */
    public static void sauvegarder(String name, Partie partie, Statistique statistique){
            Sauvegarde sauvegarde = new Sauvegarde(partie, statistique);
            File dossierSaves = new File("sauvegardes/");
            if (!dossierSaves.exists() || !dossierSaves.isDirectory()) {
                if (!dossierSaves.mkdir()) {
                    System.err.println("Le dossier 'sauvegardes/' est introuvable, et n'a pas pu être créé !");
                }
            }
            try {
                FileOutputStream fileOut = new FileOutputStream("sauvegardes/"+name+".sav");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(sauvegarde);
                out.close();
                fileOut.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }

    /**
     * Permet de charger une sauvegarde
     * @param name nom de la sauvegarde
     * @return la partie sauvegarder
     * @throws Exception si la partie n'est pas chargée
     */
    public static Sauvegarde charger(String name) throws Exception{
            try {
                FileInputStream fileIn = new FileInputStream("sauvegardes/"+name);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Sauvegarde sauvegarde = (Sauvegarde) in.readObject();
                in.close();
                fileIn.close();
                gameName = name;
            return sauvegarde;
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.err.println("Sauvegarde non trouvée");
            c.printStackTrace();
        }
        System.err.println("N'arrive pas à charger une partie");
        return null;
    }

}