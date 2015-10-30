# BlogMonopage

### Thibaut VIROLLE & Paul-Henri GUENARD - ESIEA 5A
---
###Description

Notre projet consiste en la création d'un blog sur une page unique qui n'est jamais rafraîchie intégralement.
Il a été réalisé dans le cadre du cours de Programmation Web de 5ème année de l'ESIEA.

###Technologies

**Angular.js**

AngularJS est un framework JavaScript open-source créé en 2009 puis repris l’année suivante par Google. Cela facilite et standardise le développement d’applications web coté client tel que notre blog grâce au support et à l’adaptabilité qu’offre une telle entreprise.
Le framework favorise la création d'éléments visuels d’où en résulte une navigation fluide et rapide, ce qui correspond parfaitement au cahier des charges. En effet il est important pour l’utilisateur d'accéder rapidement aux articles afin qu’il consulte l'actualité sur notre blog et non sur une autre.
L’explication de cette rapidité est donné par les benchmarks car AngularJS ne nécessite aucune dépendance et se suffit à lui-même.

<table>
    <thead>
        <tr>
            <th>Framework</th>
            <th align="center">Size</th>
            <th align="center">Size with required dependencies</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>AngularJS</td>
            <td align="right">39.5 kb</td>
            <td align="right">39.5 kb</td>
        </tr>
        <tr>
            <td>Backbone</td>
            <td align="right">6.5 kb</td>
            <td align="right">43.5 kb (jQuery & Underscore)</td>
        </tr>
        <tr>
            <td>Ember</td>
            <td align="right">90 kb</td>
            <td align="right">136.2 kb (jQuery & Handlebars)</td>
        </tr>
    </tbody>
</table>

Autre point important, AngularJS est basé sur le principe du Model View Controler (MVC) mais il peut être aussi considéré comme un Model View ViewModel (MVVM) grâce au data-binding. C'est-à-dire qu’une synchronisation se fait en temps réel entre entre la vue et le modèle.


**Spring**

Spring est un conteneur dit « léger », c'est-à-dire une infrastructure similaire à un serveur d'application J2EE. Il prend en charge la création d'objets et la mise en relation d'objets par l'intermédiaire d'un fichier de configuration qui décrit les objets à fabriquer et les relations de dépendances entre ces objets.

L'avantage par rapport aux serveurs d'application est qu'avec Spring nos classes n'ont pas besoin d'implémenter une interface pour être prises en charge par le framework. C'est pour cela que Spring est qualifié de léger et qu'il correspond donc parfaitement à un blog comme le notre.

###Fonctionnalités

Le blog "Actus by ESIEA" a pour but de poster des articles sur différents sujets d'actualité.

Il est possible de :

* se connecter à l'aide de son compte Google
* poster des articles
* poster des commentaires
* ajouter de nouveaux contributeurs au blog

Notre blog propose deux types de contributeurs :

* Un type Administrateur qui peut de poster des articles et des commentaires.
* Un type Utilisateur qui peut consulter les articles et poster des commentaires.
