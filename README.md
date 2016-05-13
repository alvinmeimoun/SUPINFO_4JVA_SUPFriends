SUPINFO - 4JVA SUPFriends - Projet


Pré-requis :

Netbeans
JDK 8
Payara Server 4.1.1.162
MySQL



Installation : 

- Créer la resource jdbc nommée "jdbc/supfriends"
- Importer le jeu de données fourni dans le dossier Documents
- Compiler et déployer l'ear



WebServices : 

Les webservices respectent la documentation fournie dans le sujet

Il est également possible de les tester en important le collection POSTMAN fournie dans le dossier Documents


Le diagramme de base de données se situe dans le dossier Documents



Informations diverses : 

Deux utilisateurs sont disponibles dans le jeu de données fourni : antonin et alvin , le mot de passe pour les deux est test .

Sur certaines version de Glassfish ou Payara, certains problème peuvent apparaître, c'est pour celà qu'il est recommandé d'utiliser la version 4.1.1.162 de Payara Server (et en règle générale la dernière révision de la version 4.1.x). 
En cas de problèmes tels que "NullPointerException" ou "No [EntityType] was found for the key class...", supprimer le déploiement sur le serveur, stopper le serveur, nettoyer et compiler la solution, allumer le serveur et redéployer.