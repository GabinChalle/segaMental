
# segaMental

SEGA_MENTAL

Informations sur l'application SEGA_MENTAL

Base de données : celle-ci est en MySQL, une fois le script exécuté dans votre SGBD préféré, il vous faudra modifier le fichier Web -> META-INF -> context.xml pour concorder avec le BDD_NAME, le LOGIN et le PWD de votre base.

Programme : 
bo (Buisness Object) comprend l'ensemble des classes nécessaire à l'application.

controller : comprend les différents controller pour affichez les informations de la page 

dal (Data Access Layer) comprend l'ensemble des classes implémentant l'interface IDAO  et IDAFactory, IUserDAO et le package JDBC permettant la gestion de la connexion et des requêtes  de selection, modification et supressesion  vers la base de données

filter comprend les normes de login, version et d'encoding de toutes les pages web générer par l'app

listener : class qui écoute certains événements dans l'application. Lorsque l'événement qu'il écoute se déclenche, il agit en fonction

model : les models sont utiliser pour communiquer entre les controllers et les pages webs

et dans web vous retrouverez les pages webs, le css, le dossier vendor...

L'Application

Vous pouvez vous connectez avec des identifiants ui sont présent dans la BDD ou créer votre compte, il est aussi posisble de se connecter dans la partie admin pour avoir accès à la liste des utilisateurs qui peuvent se connecter  avec le MDP : kze369 , il est possible de les modifiers, en ajouter et d'en supprimer

Lorsqu'un utilisateur c'est connecter, il est possible d'afficher les scores ou alors de jouer. Lorsque le jeu est fini, vous avez vos scores d'afficher et la possibilité de recommencer une partie. 