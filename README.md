# Contacts
Application android pour d'une liste de contact


# 2 Étapes

## 2.1 Examinez le code fourni

Examinez attentivement le code fourni, et comprenez-en la structure. Toutes les classes nécessaires sont déjà
présentes, mais aucune méthode, sauf une, n’est déjà implémentée.
Il ne devrait pas être nécessaire de créer aucune nouvelle classe, mais vous devrez modifier celles déjà
présentes. En particulier, vous devrez implémenter des nouvelles méthodes qui ne sont pas déjà présentes
dans les squelettes de classe fournie. (Même s’il ne devrait pas être nécessaire de créer de nouvelles classes, il
vous est permis de le faire si vous le désirez. Par contre, n’enlevez aucune classe parmi celles déjà fournies.)
Pour que le squelette de code compile, il fallait obligatoirement que le code contienne certaines méthodes
afin de respecter les interfaces : Celles-ci sont donc vide et ils vous faudra les modifier. Il en est de même
pour certains constructeurs, il vous est d’ailleurs permis d’en modifier la signature si vous le voulez. Enfin,
vous pouvez aussi ajouter des interfaces et/ou des superclasses à toute les classe avec le squelette, mais vous
ne pouvez pas en enlever.
1Les fichiers xml de layout sont déjà fournis : Bien que certaines modifications devront y être apportées pour
accomplir les tâches ci-dessous, il ne devait pas être nécessaire d’y apporter des modifications substantielles.
Notez la présence, dans le dossier "drawable", de plusieurs icones que vous pourrez utiliser dans votre
implantation pour les différents boutons et menus.

## 2.2 Implémentez les menus (10 points)

```diff
+La sélection du filtre pour favoris dans l’Activity principale est implémentée avec un menu BottomNavigationView.
+Le bouton pour créer un nouveau contact, quant à lui, est implémenté à l’aide d’un option menu régulier. Il en
+est de même pour le bouton "sauvegarder le contact" de l’écran d’édition. Implémentez ces menus !
+À ce point, il n’est pas nécesaire de lier une action particulière à chacun des items de menus, mais tous
+les boutons devraient être présents. 
Vous devriez déjà implémenter, par contre, le code permettant d’ouvrir
l’activité d’édition de contact lors d’un clic sur le bouton "nouveau contact".
```

## 2.3 Ajoutez les icônes, les "hints" et les formats de champs à l’écran d’édition de contact (5 points)
L’Activity d’édition de contact n’a pas les pictogrammes ni les "hints" accompagnant les différents champs.
De plus, les champs n’ont aucun type spécifié. Modifiez le layout pour les ajouter.

## 2.4 Implémentez la classe Contact (10 points)

La classe contact représente un contact dans le carnet d’adresse.
Elle doit stocker les informations suivantes : Son prénom, son nom, son adresse courriel, son numéro de
téléphone, un numéro d’identification unique, et une mention si le contact est favori ou non.
Il n’est pas nécessaire d’effectuer aucune validation sur les informations fournies à la classe. Ainsi, aucune
erreur particulière n’a à être provoquée si un numéro téléphone ou une adresse courriel invalide est fournie,
ni si le numéro de série n’est pas unique. Il est aussi permis de stocker le numéro de téléphone comme une
simple String.

## 2.5 Utilisez les Data Bindings pour lier ensemble la classe Contact et l’Activity d’édition de contact (15 points)
Utilisez le système de Data Binding d’Android pour lier ensemble la classe Contact et l’Activity d’édition
de contact.
Assurez-vous que cette liaison est bidirectionnelle !

## 2.6 Implémentez la classe DBHelper (20 points)

La classe DBHelper permet de construire, gêrer et accéder à la base de donnée de contacts. Construisez-la
selon le modèle montré pendant les périodes de démonstrations !
Le choix des méthodes spécifiques avec lesquelles vous interfacerez la base de donnée est à votre choix, mais
devrait évidemment être adaptée aux modes d’accès dont vous aurez besoin pour un carnet de contact.
À noter que lorsque la liste de contact doit être filtrée, ce filtre doit être implémenté à même la requête à la
base de donnée, et non pas à posteriori.
Afin de rendre votre travail plus facile, il sera accepté, dans le présent devoir, une implémentation avec les
caractéristiques suivante :
— Une fonction qui retourne toute la liste de contacts peut retourner cette liste sous forme d’une liste
Java standard : Il n’est pas nécessaire de retourner un Cursor avec lequel vous interfaceriez les
résultats de la requête au point d’utilisation.
— Le classement, par ordre alphabétique, de vos contacts, n’a pas besoin d’être gêré par la base de
donnée elle-même : elle peut donc stocker et retourner les contacts dans le désordre, et ce n’est qu’au
point d’utilisation que ceux-ci seront classés par ordre alphabétique.
— Il n’est pas nécessaire de faire une implémentation "gracieuse" de onUpgrade. Simplement détruire
la base de donnée et la recréer est suffisant pour le présent travail.

## 2.7 Implémentez la liste de contact de l’Activity principale (15 points) 
Implémentez les classes ContactRecyclerAdapter, ContactSwipeCallback et
ContactViewHolder et liez-les au RecyclerView de l’Activity principale pour rendre fonction-
nelle la liste de contacts.
Utilisez les outils de Data Bindings pour lier les différents contacts à chaque item de la liste.
À ce point, les actions de glissement, et de clics (autant court que long) sur les contacts devraient être
implémentées, mais ne provoquer aucune action particulière pour le moment. Aucune action n’est associée à
l’action de "move" sur la liste : ce comportement doit donc être désactivé.
Le filtre pour contact favoris devrais aussi être fonctionnel.
La méthode ContactSwipeCallback.onChildDraw() est déjà implémentée, et il n’est pas néces-
saire d’y toucher. C’est cette méthode qui permet d’afficher le logo "poubelle" en dessous d’un contact
lorsqu’on le glisse.

## 2.8 Complétez l’implémentation des actions (15 points)
Complétez maintenant l’implémentation des actions sur les différents contacts : appel, ajout, édition et
destruction, autant dans l’activité principale que dans l’activité d’édition de contact.
À ce point, l’état de la base de donnée devrait bien être à jour avec les actions réalisées par l’usager, et la liste
affichée devrais être cohérent avec le contenu de la base de donnée ; même lorsque celle-ci est modifiée par
l’usager.

## 2.9 Préservation de l’état (10 points)
Votre application doit gêrer correctement la situation lorsque l’écran est basculé. Cela signifie que son état
doit être préservé même à travers une rotation d’écran.
Similairement, l’état de l’Activity principale doit être préservé pour être restauré au retour d’une autre
Activity, comme par exemple après avoir complété un appel téléphonique ou avoir été à l’écran d’édition de
contact.

## 2.10 Bonus : Recherche de contact (10 points)
Ajoutez une fonction de recherche à l’Activity principale : Lorsque celle-ci est activée, une barre de recherche
apparaît en haut de l’écran ; et lorsque l’usager y tape, la liste de contact est automatiquement filtrée pour ne
maintenir que les contacts dont le nom ou le prénom contient la requête.
La (ou les) portions de leur nom qui correspond à la requête doit, de plus, être souligné.
