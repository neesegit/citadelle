package test;

import modele.Caracteristiques;
import modele.Joueur;
import modele.Quartier;
import modele.Navigatrice;
import modele.PlateauDeJeu;

public class TestNavigatrice {
    public static void main(String[] args) {
        TestNavigatrice test = new TestNavigatrice();
        // test.test1();
        test.test2();
    }

    public void test1() {
        System.out.println("TEST DU CONSTRUCTEUR");
        Navigatrice navigatrice = new Navigatrice();
        Test.test(navigatrice.getNom().equals("Navigatrice"), "test du nom du personnage");
        Test.test(navigatrice.getRang() == 7, "test du rang du personnage");
        Test.test(navigatrice.getCaracteristiques().equals(Caracteristiques.NAVIGATRICE),
                "test des caracteristiques du personnage");
        Test.test(navigatrice.getJoueur() == null, "test de l'initialisation de la variable \"joueur\"");
        Test.test(navigatrice.getAssassine() == false, "test de l'initialisation de la variable \"assassine\"");
        Test.test(navigatrice.getVole() == false, "test de l'initialisation de la variable \"vole\"");
    }

    public void test2() {
        System.out.println("TEST DU POUVOIR DU Navigatrice");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Navigatrice navigatrice = new Navigatrice();
        navigatrice.setPlateau(plateau);
        Joueur joueur1 = new Joueur("Billy");
        plateau.ajouterJoueur(joueur1);
        navigatrice.setJoueur(joueur1);
        System.out.println(navigatrice.getJoueur());
        Test.test(navigatrice.getJoueur().nbQuartiersDansMain() == 0,
                "test du nombre de carte avant le pouvoir");
        navigatrice.utiliserPouvoir();
        Test.test(navigatrice.getJoueur().nbPieces() == 4, "test du choix +4 or");
        Test.test(navigatrice.getJoueur().nbQuartiersDansMain() == 4, "test du choix +4 carte");
        Quartier quartier1 = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
        navigatrice.ajouterPieces();
        navigatrice.getJoueur().ajouterQuartierDansMain(quartier1);
        Test.test(navigatrice.getJoueur().nbQuartiersDansCite() == 0, "test initialisation de la cite vide");
        // je suis pas sur car là ça construit direct mais peut etre que l'interdiction
        // vas être au dessus
        navigatrice.construire(quartier1);
        Test.test(navigatrice.getJoueur().nbQuartiersDansCite() == 0, "test la cite elle doit reste vide");
    }
}