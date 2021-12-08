## Description :

Un virement est un transfert d'argent d'un compte emetteur vers un compte bénéficiaire ...

**Besoin métier :**

Ajouter un nouveau usecase versement. Le Versement est un dépôt d'agent sur un compte donné .

Le versement est une opération trés utile lors des transfert de cash .

Imaginez que vous allez à une agence avec un montant de 1000DH et que vous transferez ça en spécifiant le RIB souhaité .

L'identifiant fonctionnel d'un compte dans ce cas préçis est le RIB .


## Assignement :

* Le code présente des anomalies de qualité (bonnes pratiques , abstraction , lisibilité ...) et des bugs .
  * localiser le maximum
  * Essayer d'améliorer la qualité du code .
  * Essayer de résoudre les bugs détectés.
* Implementer le use case `Versement`
* Ajouter des tests unitaires.
* **Nice to have** : Ajouter une couche de sécurité

## How to use
To build the projet you will need :
* Java 11+
* Maven

Build command :
```
mvn clean install
```

Run command :
```
./mvnw spring-boot:run 
## or use any prefered method (IDE , java -jar , docker .....)
```

## How to submit
* Fork the project into your personal gitlab space .
* Do the stuff .
* Send us the link .


##My Notes:

I -Notes-

1-Bonnes Pratiques:
code ilisibles, peu de commentaire [densite de commentaire 25-35%]
nommage des variable mal fait
Appliquer les outils auss +
https://perso.liris.cnrs.fr/pierre-antoine.champin/enseignement/algo/cours/algo/bonnes_pratiques.html

2-







Ginfo2 Drive:
https://drive.google.com/drive/u/0/folders/1Uapg11Ind2Mk99Z94-_g0B3lfMX-UkO6
secure app after :
https://developer.okta.com/blog/2018/07/30/10-ways-to-secure-spring-boot
security as I saw :
https://spring.io/guides/gs/securing-web/

Best One:
https://www.javainuse.com/spring/boot-jwt

try to use JWT , because il ne faut pas que tout le monde soit capable d'envoyer une Post Request



Abstraction: en utilisant le principe de inversion de Dependances :
Dependency inversion principle , le D de SOLID {

Single responsibility principle (responsabilité unique)

Open/closed principle (ouvert/fermé)

Liskov substitution principle (substitution de Liskov)

Interface segregation principle (ségrégation des interfaces)}

https://openclassrooms.com/fr/courses/4504771-simplifiez-le-developpement-dapplications-java-avec-spring/4757789-ajouter-de-labstraction

1-classe/module de haut niveau ne doit pas dependre de class/module de bas niveau
il faut que class dependre d'abstraction/interface
2-les abstraction ne doivent pas dependre des details ,
mais les details doivent dependre des abstraction

Injection de dependance

cad , il faut separer le contrat que le module realise , de l'implementation


DAO:
https://gayerie.dev/epsi-b3-orm/spring_framework/spring_dao.html

Unit Tests:
https://www.baeldung.com/spring-boot-testing
https://www.tutorialspoint.com/spring_boot/spring_boot_unit_test_cases.htm

1-Analyse du code:

23bug

18(enumpb)


Gestion des exception dans le contrller : il y a plusieurs methodes , mais il ne faut pas que l'app crash  !!
Il ya vraiment trops de recherche dedant que j'ai realiseeee,
mais je vais citer les etapes pour bien resoudre le probleme :
1. throw TransactionException() et throw en generale va aretter le projet de s'executer en generale , ce qui n'est pas acceptee
2. J'ai handler l'exception en mettant un try catch , comme vous pouver le voir dans le cas de "Montant minimal de virement non atteint"
3. J'ai utiliser la force et la flexibilite qui est deja fournis avec ControllerAdvice(deja existant) que je touve mieux(meme cote formite/monotomie/style programmation )
4.
* J'ai utiliser la creation de class pour chaque type d'Exeption(Transactional par exemple) , mais c'est pas conseillee (si on a trop !)
* mais puisque c'est pas pratique , j'ai analyser plus ce que j'ai sur @Advice , et la fonction qui handle l'exeption , et J'ai pu extraire le bon message

Analyse Statique:
SpotBugs
PMD


