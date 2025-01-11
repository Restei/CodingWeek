# Fonctionnalités de l'application

## Menu d'accueil

Permet de naviguer entre les différents menus :
- Menu de création d'une partie
- Menu des sauvegardes
- Menu des thèmes

## Menu de création d'une partie

Permet créer une partie selon différents critères :
- Taille de la grille : entre 2x2 et 10x10
- Nombre de cartes (correspond au nombre de cartes de l'équipe bleue) : le nombre total de cartes non neutres (bleues, rouges, et assassin) doit être inférieur à la taille de la grille
- Nombre de cartes assassin : doit respecter le critère ci-dessus
- Type de timer
- Timer de l'espion ou de l'équipe bleue
- Timer de l'agent ou de l'équipe rouge
- Thème

Le menu empêche la création d'une partie invalide.

## Menu des sauvegardes

Permet de charger ou supprimer une sauvegarde. Il s'agit des sauvegardes de parties. Les sauvegardes sont sauvegardées dans le dossier `sauvegardes/`.

## Menu des thèmes

Permet de créer, modifier et supprimer des thèmes. Ici, il n'est pas possible d'accéder au thème par défault qui est hardcodé.
Les thèmes sont sauvegardés dans le dossier `themes/`. Lors de la création, si le dossier n'existe pas, il est créé.

## Génération d'une partie

Permet de jouer à LinguaCrypt. Le jeu comporte un timer. Il est possible de mettre le jeu en pause.

## Overlay pause

- Possibilité de reprendre le jeu
- Possibilité de sauvegarder la partie : il faut pour cela associer un mon à la sauvegarde.
Si le dossier `sauvegardes/` n'existe pas, il est créé
- Possibilité de revenir au menu d'accueil
