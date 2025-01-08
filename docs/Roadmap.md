# Roadmap

## Lundi 6

### Fait : (affichage de la vue partie)
 - Guillaume : layout de l'interface d'une partie (VuePartie)
 - Russel : grille centrale et cartes sur le plateau (vueGrille, vueCarte)
 - Clément : Observateur et SujetObserve, VueChrono
 - Joseph : Partie, GestionnairePartie, Grille, Carte, GestionnaireTimer, Timer

### À faire ensuite
- Corriger bugs liés à `sleep()` dans Timer
- Vérifier que VueChrono fonctionne et l'ajouter à VuePartie
- Assigner des actions aux boutons de vuePartie
- Implémenter les tours de jeu
- Tests unitaires de tout sauf Grille

## Mardi 7
- Résolution du bug lié à `sleep()` dans Timer : Joseph
- Mise en place des différentes fonctionnalités du timer : afficher un timer en haut à gauche, initialiser le timer en fonction des joueurs (espion ou agent), et des équipes (bleu ou rouge) : Joseph
- Implémentation des tours de jeu : Russel
- Carte sur les côté pour afficher les score des équipes : Russel
- Création du menu d'accueil : les boutons ne sont pas actifs sauf le bouon quitter : Guillaume
- Implémentation de la classe ChangeurScene qui permet de naviguer entre les différents menus : Guillaume et Clément
- Remise sous forme vue controleur de VuePartie : Clément
- Remise sous forme vue controleur de VueCarteRestante : Russel

### À faire ensuite
- Attacher une action lorsqu'on appuie sur les boutons du menu
- Faire un affichage lors de la victoire d'une équipe
- Implémentation de la class Overlay
- Tests unitaires de tout
- Création de la partie (grille de cartes)

## Mercredi 8
- Réalisation des overlay (permet de mettre de façon générique un overlay) : Clément
- Réalisation de la fonctionnalité permettant de créer une partie : Joseph et Russel
- Mettre des actions sur les boutons du menu principal : Guillaume et Russel
- Réalisation des sauvegardes : Guillaume
- Réalisation de test unitaire de Grille et Theme : Joseph

### À faire ensuite
- Faire un affichage lors de la victoire d'une équipe
- Faire en sorte que le tour du joueur s'arrête si le timer tombe à 0
- Ajouter la partie modification et sélection des thèmes
- Finir les tests unitaires
- Lancer une partie chargée lorsqu'on appuie sur "charger"