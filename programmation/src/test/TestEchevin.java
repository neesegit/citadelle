package test;

import modele.Caracteristiques;
import modele.Espion;
import modele.Joueur;
import modele.Magicienne;
import modele.PlateauDeJeu;
import modele.Quartier;
import modele.Roi;
import modele.*;

public class TestEchevin {
    public static void main(String[] args) {
        TestEchevin test = new TestEchevin();
        // test.test1(); initialisation
        // test.test2(); utilisation typique du pouvoir
        // test.test3(); utilisation avec deja le quartier dans la cite
    }

    public void test1() {
        System.out.println("TEST DU CONSTRUCTEUR");
        Echevin echevin = new Echevin();
        Test.test(echevin.getNom().equals("Echevin"), "test du nom du personnage");
        Test.test(echevin.getRang() == 1, "test du rang du personnage");
        Test.test(echevin.getCaracteristiques().equals(Caracteristiques.ECHEVIN),
                "test des caracteristiques du personnage");
        Test.test(echevin.getJoueur() == null, "test de l'initialisation de la variable \"joueur\"");
        Test.test(echevin.getAssassine() == false, "test de l'initialisation de la variable \"assassine\"");
        Test.test(echevin.getVole() == false, "test de l'initialisation de la variable \"vole\"");
    }

    public void test2() {
        System.out.println("TEST DU POUVOIR ESCHEVIN");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Roi roi = new Roi();
        Espion espion = new Espion();
        Magicienne magicienne = new Magicienne();
        Echevin echevin = new Echevin();
        espion.setPlateau(plateau);
        roi.setPlateau(plateau);
        magicienne.setPlateau(plateau);
        echevin.setPlateau(plateau);
        Joueur joueur1 = new Joueur("Billy");
        plateau.ajouterJoueur(joueur1);
        Joueur joueur2 = new Joueur("Martin");
        plateau.ajouterJoueur(joueur2);
        Joueur joueur3 = new Joueur("Kevin");
        plateau.ajouterJoueur(joueur3);
        Joueur joueur4 = new Joueur("Emma");
        plateau.ajouterJoueur(joueur4);
        roi.setJoueur(joueur1);
        espion.setJoueur(joueur2);
        magicienne.setJoueur(joueur3);
        echevin.setJoueur(joueur4);
        Quartier quartier = new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5);
        Test.test(echevin.getJoueur().nbQuartiersDansCite() == 0, "Verification que la cite de l'echevin est vide");
        Test.test(roi.getJoueur().nbQuartiersDansCite() == 0, "Verification que la cite du roi est vide");
        echevin.utiliserPouvoir();
        roi.getJoueur().ajouterPieces(5);
        echevin.getJoueur().ajouterPieces(5);
        Test.test(echevin.getJoueur().nbPieces() == 5, "On verifie le tresor de base");
        Test.test(roi.getJoueur().nbPieces() == 5, "On verifie le tresor de base");
        roi.construire(quartier);// faire en sorte que ce soit lui qui reçoive le jeton de taxe
        Test.test(echevin.getJoueur().nbPieces() == 5, "On verifie que l'argent n'as pas était utilisé");
        Test.test(roi.getJoueur().nbPieces() == 5, "On verifie que l'argent n'as pas était utilisé");
        Test.test(roi.getDejaConstruit(), "le permis de construire a était utilisé");
        Test.test(echevin.getJoueur().nbQuartiersDansCite() == 1,
                "Verification que la cite de l'echevin a pris le quartier");
        Test.test(roi.getJoueur().nbQuartiersDansCite() == 0, "Verification que la cite du roi reste vide");
    }

    public void test3() {
        System.out.println("TEST DU POUVOIR ESCHEVIN AVEC MEME QUARTIER");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Roi roi = new Roi();
        Espion espion = new Espion();
        Magicienne magicienne = new Magicienne();
        Echevin echevin = new Echevin();
        espion.setPlateau(plateau);
        roi.setPlateau(plateau);
        magicienne.setPlateau(plateau);
        echevin.setPlateau(plateau);
        Joueur joueur1 = new Joueur("Billy");
        plateau.ajouterJoueur(joueur1);
        Joueur joueur2 = new Joueur("Martin");
        plateau.ajouterJoueur(joueur2);
        Joueur joueur3 = new Joueur("Kevin");
        plateau.ajouterJoueur(joueur3);
        Joueur joueur4 = new Joueur("Emma");
        plateau.ajouterJoueur(joueur4);
        roi.setJoueur(joueur1);
        espion.setJoueur(joueur2);
        magicienne.setJoueur(joueur3);
        echevin.setJoueur(joueur4);
        Quartier quartier = new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5);
        Test.test(echevin.getJoueur().nbQuartiersDansCite() == 0, "Verification que la cite de l'echevin est vide");
        Test.test(roi.getJoueur().nbQuartiersDansCite() == 0, "Verification que la cite du roi est vide");
        echevin.ajouterQuartier(quartier);
        echevin.utiliserPouvoir();
        roi.getJoueur().ajouterPieces(5);
        echevin.getJoueur().ajouterPieces(5);
        Test.test(echevin.getJoueur().nbPieces() == 5, "On verifie le tresor de base");
        Test.test(roi.getJoueur().nbPieces() == 5, "On verifie le tresor de base");
        roi.construire(quartier);// faire en sorte que ce soit lui qui reçoive le jeton de taxe
        Test.test(echevin.getJoueur().nbPieces() == 5, "On verifie que l'argent n'as pas était utilisé");
        Test.test(roi.getJoueur().nbPieces() == 0, "On verifie que l'argent as était utilisé");
        Test.test(roi.getDejaConstruit(), "le permis de construire a était utilisé");
        Test.test(echevin.getJoueur().nbQuartiersDansCite() == 1,
                "Verification que la cite de l'echevin n'a pas pris le quartier");
        Test.test(roi.getJoueur().nbQuartiersDansCite() == 1, "Verification que la cite du roi a réussi a construire");
    }
}