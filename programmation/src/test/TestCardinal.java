package test;

import modele.Caracteristiques;
import modele.Cardinal;
import modele.Joueur;
import modele.Magicienne;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;
import modele.Roi;

public class TestCardinal {
    public static void main(String[] args) {
        TestCardinal test = new TestCardinal();
        // test.test1();
        // test.test2();
        // test.test3();
    }

    public void test1() {
        System.out.println("TEST DU CONSTRUCTEUR");
        Cardinal cardinal = new Cardinal();
        Test.test(cardinal.getNom().equals("Cardinal"), "test du nom du personnage");
        Test.test(cardinal.getRang() == 5, "test du rang du personnage");
        Test.test(cardinal.getCaracteristiques().equals(Caracteristiques.CARDINAL),
                "test des caracteristiques du personnage");
        Test.test(cardinal.getJoueur() == null, "test de l'initialisation de la variable \"joueur\"");
        Test.test(cardinal.getAssassine() == false, "test de l'initialisation de la variable \"assassine\"");
        Test.test(cardinal.getVole() == false, "test de l'initialisation de la variable \"vole\"");
    }

    public void test2() {
        System.out.println("TEST POUVOIR ET PERCEPTION DE RESSOURCE (A ASSEZ POUR PAYER LE QUARTIER)");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Joueur joueur = new Joueur("Billy");
        plateau.ajouterJoueur(joueur);
        Joueur joueur2 = new Joueur("Martin");
        plateau.ajouterJoueur(joueur2);
        Joueur joueur3 = new Joueur("Emma");
        plateau.ajouterJoueur(joueur3);
        Cardinal cardinal = new Cardinal();
        Quartier quartier1 = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
        Quartier quartier3 = new Quartier("église", Quartier.TYPE_QUARTIERS[0], 2);
        cardinal.setJoueur(joueur);
        Magicienne magicienne = new Magicienne();
        Roi roi = new Roi();
        magicienne.setJoueur(joueur2);
        roi.setJoueur(joueur3);
        Test.test(plateau.getNombrePersonnages() == 3, "nombre de joueurs");
        Pioche pioche = plateau.getPioche();
        Quartier q = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
        pioche.ajouter(q);
        q = new Quartier("prison", Quartier.TYPE_QUARTIERS[1], 2);
        pioche.ajouter(q);
        q = new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5);
        pioche.ajouter(q);
        q = new Quartier("taverne", Quartier.TYPE_QUARTIERS[3], 1);
        pioche.ajouter(q);
        q = new Quartier("echoppe", Quartier.TYPE_QUARTIERS[3], 2);
        pioche.ajouter(q);
        q = new Quartier("basilique", Quartier.TYPE_QUARTIERS[4], 4, "A la fin de la partie, ...");
        pioche.ajouter(q);
        q = new Quartier("cathedrale", Quartier.TYPE_QUARTIERS[0], 5);
        pioche.ajouter(q);
        q = new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3);
        pioche.ajouter(q);
        q = new Quartier("manoir", Quartier.TYPE_QUARTIERS[2], 3);
        pioche.ajouter(q);
        q = new Quartier("hetel de ville", Quartier.TYPE_QUARTIERS[3], 15);
        pioche.ajouter(q);
        q = new Quartier("bibliotheque", Quartier.TYPE_QUARTIERS[4], 6, "Si vous choisissez...");
        pioche.ajouter(q);
        pioche.melanger();
        joueur.ajouterQuartierDansMain(pioche.piocher());
        joueur.ajouterQuartierDansMain(pioche.piocher());
        joueur2.ajouterQuartierDansMain(pioche.piocher());
        joueur2.ajouterQuartierDansMain(pioche.piocher());
        joueur3.ajouterQuartierDansMain(pioche.piocher());
        joueur3.ajouterQuartierDansMain(pioche.piocher());
        Test.test(cardinal.getJoueur().nbQuartiersDansMain() == 2, "Test du nombre de carte dans la main");
        cardinal.getJoueur().ajouterQuartierDansCite(quartier3);
        cardinal.percevoirRessourcesSpecifiques();
        Test.test(cardinal.getJoueur().nbQuartiersDansMain() == 3, "Test du nombre de carte dans la main ");
        cardinal.ajouterPieces();
        cardinal.construire(quartier1);
        // tj si le pouvoir est dans le overwrite de construire
        Test.test(cardinal.getJoueur().nbPieces() == 0,
                "test du nombre de piece normalement comme il utilise les peice des autre bah baam 0");
        Test.test(cardinal.getJoueur().nbQuartiersDansMain() == 3,
                "Test du nombre de caarte dans la main (trade ces 2 cartes contre 2 pieces)");
    }

    public void test3() {
        System.out.println("TEST DE LA PERCEPTION DE RESSOURCE PLUS POUVOIR (PAS DE PIECE)");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Joueur joueur = new Joueur("Billy");
        plateau.ajouterJoueur(joueur);
        Joueur joueur2 = new Joueur("Martin");
        plateau.ajouterJoueur(joueur2);
        Joueur joueur3 = new Joueur("Emma");
        plateau.ajouterJoueur(joueur3);
        Cardinal cardinal = new Cardinal();
        Quartier quartier1 = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
        Quartier quartier3 = new Quartier("église", Quartier.TYPE_QUARTIERS[0], 2);
        cardinal.setJoueur(joueur);
        Magicienne magicienne = new Magicienne();
        Roi roi = new Roi();
        magicienne.setJoueur(joueur2);
        roi.setJoueur(joueur3);
        Test.test(plateau.getNombrePersonnages() == 3, "nombre de joueurs");
        Pioche pioche = plateau.getPioche();
        Quartier q = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
        pioche.ajouter(q);
        q = new Quartier("prison", Quartier.TYPE_QUARTIERS[1], 2);
        pioche.ajouter(q);
        q = new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5);
        pioche.ajouter(q);
        q = new Quartier("taverne", Quartier.TYPE_QUARTIERS[3], 1);
        pioche.ajouter(q);
        q = new Quartier("echoppe", Quartier.TYPE_QUARTIERS[3], 2);
        pioche.ajouter(q);
        q = new Quartier("basilique", Quartier.TYPE_QUARTIERS[4], 4, "A la fin de la partie, ...");
        pioche.ajouter(q);
        q = new Quartier("cathedrale", Quartier.TYPE_QUARTIERS[0], 5);
        pioche.ajouter(q);
        q = new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3);
        pioche.ajouter(q);
        q = new Quartier("manoir", Quartier.TYPE_QUARTIERS[2], 3);
        pioche.ajouter(q);
        q = new Quartier("hetel de ville", Quartier.TYPE_QUARTIERS[3], 15);
        pioche.ajouter(q);
        q = new Quartier("bibliotheque", Quartier.TYPE_QUARTIERS[4], 6, "Si vous choisissez...");
        pioche.ajouter(q);
        pioche.melanger();
        joueur.ajouterQuartierDansMain(pioche.piocher());
        joueur.ajouterQuartierDansMain(pioche.piocher());
        joueur2.ajouterQuartierDansMain(pioche.piocher());
        joueur2.ajouterQuartierDansMain(pioche.piocher());
        joueur3.ajouterQuartierDansMain(pioche.piocher());
        joueur3.ajouterQuartierDansMain(pioche.piocher());
        Test.test(cardinal.getJoueur().nbQuartiersDansMain() == 2, "Test du nombre de carte dans la main");
        cardinal.getJoueur().ajouterQuartierDansCite(quartier3);
        cardinal.percevoirRessourcesSpecifiques();
        Test.test(cardinal.getJoueur().nbQuartiersDansMain() == 3, "Test du nombre de carte dans la main ");
        cardinal.construire(quartier1);
        // je sais pas si il overwrite construire du coup mais c'est ce qui est le plus
        // logique pour moi
        Test.test(cardinal.getJoueur().nbPieces() == 0,
                "test du nombre de piece normalement comme il utilise les peice des autre bah baam 0");
        Test.test(cardinal.getJoueur().nbQuartiersDansMain() == 1,
                "Test du nombre de caarte dans la main (trade ces 2 cartes contre 2 pieces)");
    }
}