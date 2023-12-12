package test;

import modele.*;
import modele.Joueur;
import modele.PlateauDeJeu;
import modele.Quartier;

public class TestAlchimiste {
    public static void main(String[] args) {
        TestAlchimiste test = new TestAlchimiste();
        // test.test1();
        // test.test2();
        // test.test3();
    }

    public void test1() {
        System.out.println("TEST DU CONSTRUCTEUR");
        Alchimiste alchimiste = new Alchimiste();
        Test.test(alchimiste.getNom().equals("Alchimiste"), "test du nom du personnage");
        Test.test(alchimiste.getRang() == 6, "test du rang du personnage");
        Test.test(alchimiste.getCaracteristiques().equals(Caracteristiques.ALCHIMISTE),
                "test des caracteristiques du personnage");
        Test.test(alchimiste.getJoueur() == null, "test de l'initialisation de la variable \"joueur\"");
        Test.test(alchimiste.getAssassine() == false, "test de l'initialisation de la variable \"assassine\"");
        Test.test(alchimiste.getVole() == false, "test de l'initialisation de la variable \"vole\"");
    }

    public void test2() {
        System.out.print("TEST DU PASSIF / POUVOIR REMBOURSE L'OR ( sauf pour la Forge et Bailli )");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Joueur joueur = new Joueur("Billy");
        plateau.ajouterJoueur(joueur);
        Alchimiste alchimiste = new Alchimiste();
        Quartier quartier2 = new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 3);
        alchimiste.setJoueur(joueur);
        alchimiste.ajouterPieces();
        alchimiste.ajouterPieces();
        Test.test(alchimiste.getJoueur().nbPieces() == 4, "test du nombre de pieces d'or avant la construction");
        alchimiste.construire(quartier2);
        Test.test(alchimiste.getJoueur().nbPieces() == 1, "test du nombre de pieces d'or après la construction");
        alchimiste.utiliserPouvoir();
        // il faudrait que le pouvoir ne marche que si il construit un quartier aussi
        Test.test(alchimiste.getJoueur().nbPieces() == 4, "test du nombre de pieces d'or après le pouvoir");
    }

    public void test3() {
        System.out.print("TEST DU PASSIF / POUVOIR REMBOURSE L'OR ( sauf pour la Forge et Bailli )");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Joueur joueur = new Joueur("Billy");
        plateau.ajouterJoueur(joueur);
        Alchimiste alchimiste = new Alchimiste();
        Quartier quartier2 = new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 3);
        Quartier quartier1 = new Quartier("prison", Quartier.TYPE_QUARTIERS[1], 2);
        alchimiste.setJoueur(joueur);
        alchimiste.ajouterPieces();
        alchimiste.ajouterPieces();
        Test.test(alchimiste.getJoueur().nbPieces() == 4, "test du nombre de pieces d'or avant la construction");
        alchimiste.utiliserPouvoir();
        // le pouvoir ne doit pas s'utiliser du coup;
        Test.test(alchimiste.getJoueur().nbPieces() == 4, "test du nombre de pieces d'or après le pouvoir");
        alchimiste.construire(quartier2);
        Test.test(alchimiste.getJoueur().nbPieces() == 1, "test du nombre de pieces d'or après la construction");
        alchimiste.utiliserPouvoir();
        // il faudrait que le pouvoir ne marche que si il construit un quartier aussi
        Test.test(alchimiste.getJoueur().nbPieces() == 4, "test du nombre de pieces d'or après le pouvoir");
        alchimiste.utiliserPouvoir();
        Test.test(alchimiste.getJoueur().nbPieces() == 4, "test de la double utulisation du pouvoir");
        // le pouvoir ne peut être utiliser qu'une seul fois
    }
}
