package test;

import modele.Assassin;
import modele.Caracteristiques;
import modele.Joueur;
import modele.PlateauDeJeu;
import modele.Quartier;
import modele.Roi;
import modele.*;

public class TestPatricien {
    public static void main(String[] args) {
        TestPatricien test = new TestPatricien();
        // test.test1();
        // test.test2();
        // test.test3();
        // test.test4();
    }

    public void test1() {
        System.out.println("TEST DU CONSTRUCTEUR");
        Patricien patricien = new Patricien();
        Test.test(patricien.getNom().equals("Patricien"), "test du nom du personnage");
        Test.test(patricien.getRang() == 4, "test du rang du personnage");
        Test.test(patricien.getCaracteristiques().equals(Caracteristiques.PATRICIEN),
                "test des caracteristiques du personnage");
        Test.test(patricien.getJoueur() == null, "test de l'initialisation de la variable \"joueur\"");
        Test.test(patricien.getAssassine() == false, "test de l'initialisation de la variable \"assassine\"");
        Test.test(patricien.getVole() == false, "test de l'initialisation de la variable \"vole\"");
    }

    public void test2() {
        System.out.println("TEST PERCEPTION DES RESSOURCES ET POUVOIR");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Joueur joueur = new Joueur("Billy");
        plateau.ajouterJoueur(joueur);
        Joueur joueur2 = new Joueur("Martin");
        plateau.ajouterJoueur(joueur2);
        Patricien patricien = new Patricien();
        Roi roi = new Roi();
        patricien.setJoueur(joueur);
        roi.setJoueur(joueur2);
        Quartier quartier = new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5);
        Quartier quartier2 = new Quartier("manoir", Quartier.TYPE_QUARTIERS[2], 3);
        patricien.getJoueur().ajouterQuartierDansCite(quartier);
        patricien.getJoueur().ajouterQuartierDansCite(quartier2);
        Test.test(patricien.getJoueur().nbPieces() == 0, "Nombre de piece avant perception");
        patricien.percevoirRessourcesSpecifiques();
        Test.test(patricien.getJoueur().nbPieces() == 2, "Nombre de piece apres ressource specifique");
        roi.getJoueur().setPossedeCouronne(true);
        patricien.utiliserPouvoir(); // ad vitam eternam j'usqu'au prochain patricien
        Test.test(patricien.getJoueur().getPossedeCouronne() == true,
                "Verifier que la couronne soit bien sur l'assassin");
    } // peut tre creer une nouvelle variable qui fait durer dans le temps ajoute
      // cette partie et tester en jeu

    public void test3() {
        System.out.println("TEST PERCEPTION DES RESSOURCES ET POUVOIR AVEC ASSASSINAT");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Joueur joueur = new Joueur("Billy");
        plateau.ajouterJoueur(joueur);
        Joueur joueur2 = new Joueur("Martin");
        plateau.ajouterJoueur(joueur2);
        Patricien patricien = new Patricien();
        Roi roi = new Roi();
        patricien.setJoueur(joueur);
        roi.setJoueur(joueur2);
        roi.getJoueur().setPossedeCouronne(true);
        patricien.setAssassine();
        patricien.utiliserPouvoir(); // que pour ce tour là
        Test.test(patricien.getJoueur().getPossedeCouronne() == true,
                "Verifier que la couronne soit bien sur l'assassin");
    } // a verifier en jeu

    public void test4() {
        System.out.println("TEST PERCEPTION DES RESSOURCES ET POUVOIR AVEC ENSORCELLER");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Joueur joueur = new Joueur("Billy");
        plateau.ajouterJoueur(joueur);
        Joueur joueur2 = new Joueur("Martin");
        plateau.ajouterJoueur(joueur2);
        Patricien patricien = new Patricien();
        Roi roi = new Roi();
        patricien.setJoueur(joueur);
        roi.setJoueur(joueur2);
        roi.getJoueur().setPossedeCouronne(true);
        //patricien.setEnsorcelle(); // connait pas encore la fonction
        patricien.utiliserPouvoir(); // que pour ce tour là
        Test.test(patricien.getJoueur().getPossedeCouronne() == true,
                "Verifier que la couronne soit bien sur l'assassin");
    }
}
