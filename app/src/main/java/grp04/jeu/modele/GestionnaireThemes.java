package grp04.jeu.modele;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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

    /**
     * Permet de sélectionner tous les mots de tous les thèmes
     * @return une liste contenant tous les mots de tout les thèmes
     */
    public static ArrayList<String> aleatoire() {
            ArrayList<String> listeMots = new ArrayList<>();
            for (String theme : themes()) {
                for (String mot : mots(theme)) {
                    if (!listeMots.contains(mot)) {
                        listeMots.add(mot);
                    }
                }
            }
            return listeMots;
        }

        public static List<String> motsParDefault(int n) {
            List<String> mots = Arrays.asList(
                    "ABEILLE", "ABRICOT", "ACTEUR", "AIGLE", "ALERTE",
                    "AMAZONE", "AMOUR", "ANGLE", "ANIMAL", "ANTIQUE",
                    "ARBRE", "ARC", "ARGENT", "ARMÉE", "ART",
                    "ATLANTIDE", "AVENTURE", "BALEINE", "BANANE", "BARRIÈRE",
                    "BATEAU", "BIÈRE", "BISON", "BLÉ", "BOMBE",
                    "BONBON", "BOUCLIER", "BOULANGER", "BOUTON", "BRUME",
                    "BULLE", "CACTUS", "CAMÉLÉON", "CANYON", "CAPITAINE",
                    "CAROTTE", "CAVALIER", "CENTAURE", "CHÂTEAU", "CHEVAL",
                    "CHOCOLAT", "CLÉ", "CŒUR", "COMÈTE", "CORBEAU",
                    "COTON", "CRAYON", "CROIX", "DÉSERT", "DIAMANT",
                    "DRAGON", "ÉCOLE", "ÉCRAN", "ÉCUREUIL", "ÉLÉPHANT",
                    "ÉTOILE", "FÉE", "FEU", "FLÈCHE", "FLEUR",
                    "FORÊT", "FOURMI", "FUSÉE", "GALAXIE", "GÂTEAU",
                    "GÉNIE", "GLACE", "GRENOUILLE", "GRIFFE", "GUERRIER",
                    "HARMONIE", "HÉROS", "HIBOU", "HORLOGE", "IGLOO",
                    "ÎLE", "JARDIN", "JUNGLE", "KOALA", "LAC",
                    "LANTERNE", "LICORNE", "LION", "LUNE", "MIROIR",
                    "MONTAGNE", "NINJA", "NUAGE", "OCÉAN", "OR",
                    "OURS", "PAPILLON", "PARFUM", "PHARE", "PIRATE",
                    "PLUME", "ROBOT", "ROCHE", "SABLE", "SERPENT"
            );
            Collections.shuffle(mots);
            return mots.subList(0, n);
        }

    public static List<String> motsParDefault(int n, List<String> mots) {
        List<String> motsParDefault = Arrays.asList(
                "ABEILLE", "ABRICOT", "ACTEUR", "AIGLE", "ALERTE",
                "AMAZONE", "AMOUR", "ANGLE", "ANIMAL", "ANTIQUE",
                "ARBRE", "ARC", "ARGENT", "ARMÉE", "ART",
                "ATLANTIDE", "AVENTURE", "BALEINE", "BANANE", "BARRIÈRE",
                "BATEAU", "BIÈRE", "BISON", "BLÉ", "BOMBE",
                "BONBON", "BOUCLIER", "BOULANGER", "BOUTON", "BRUME",
                "BULLE", "CACTUS", "CAMÉLÉON", "CANYON", "CAPITAINE",
                "CAROTTE", "CAVALIER", "CENTAURE", "CHÂTEAU", "CHEVAL",
                "CHOCOLAT", "CLÉ", "CŒUR", "COMÈTE", "CORBEAU",
                "COTON", "CRAYON", "CROIX", "DÉSERT", "DIAMANT",
                "DRAGON", "ÉCOLE", "ÉCRAN", "ÉCUREUIL", "ÉLÉPHANT",
                "ÉTOILE", "FÉE", "FEU", "FLÈCHE", "FLEUR",
                "FORÊT", "FOURMI", "FUSÉE", "GALAXIE", "GÂTEAU",
                "GÉNIE", "GLACE", "GRENOUILLE", "GRIFFE", "GUERRIER",
                "HARMONIE", "HÉROS", "HIBOU", "HORLOGE", "IGLOO",
                "ÎLE", "JARDIN", "JUNGLE", "KOALA", "LAC",
                "LANTERNE", "LICORNE", "LION", "LUNE", "MIROIR",
                "MONTAGNE", "NINJA", "NUAGE", "OCÉAN", "OR",
                "OURS", "PAPILLON", "PARFUM", "PHARE", "PIRATE",
                "PLUME", "ROBOT", "ROCHE", "SABLE", "SERPENT"
        );
        Collections.shuffle(motsParDefault);
        List<String> motsRetournes = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < motsParDefault.size() && j < n) {
            if (!mots.contains(motsParDefault.get(i))) {
                motsRetournes.add(motsParDefault.get(i));
                j++;
            }
            i++;
        }
        return motsRetournes;
    }

}
