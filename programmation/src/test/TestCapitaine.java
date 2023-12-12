/
package test;

import modele.Caracteristiques;
import modele.Eveque;
import modele.Joueur;
import modele.PlateauDeJeu;
import modele.Quartier;
import modele.Roi;

public class TestCapitaine {
    public static void main(String[] args) {
        TestCapitaine test = new TestCapitaine();
        //test.test1();
        //test.test2();
        //test.test3();
        test.test4();
        // test.test5();
    }

    public void test1() {
        System.out.println("TEST DU CONSTRUCTEUR");
        Capitaine capitaine = new Capitaine();
        Test.test(capitaine.getNom().equals("Capitaine"),
                "test du nom du personnage");
        Test.test(capitaine.getRang() == 8, "test du rang du personnage");
        Test.test(capitaine.getCaracteristiques().equals(Caracteristiques.CAPITAINE),
                "test des caracteristiques du personnage");
        Test.test(capitaine.getJoueur() == null,
                "test de l'initialisation de la variable \"joueur\"");
        Test.test(capitaine.getAssassine() == false,
                "test de l'initialisation de la variable \"assassine\"");
        Test.test(capitaine.getVole() == false,
                "test de l'initialisation de la variable \"vole\"");
    }

    public void test2() {
        System.out.println("TEST DE LA PERCEPTION DE RESSOURCE ET DU POUVOIR");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Joueur joueur = new Joueur("Billy");
        plateau.ajouterJoueur(joueur);
        Joueur joueur2 = new Joueur("Uge");
        plateau.ajouterJoueur(joueur2);
        Capitaine capitaine = new Capitaine();
        Roi roi = new Roi();
        capitaine.setJoueur(joueur);
        roi.setJoueur(joueur2);
        capitaine.setPlateau(plateau);
        roi.setPlateau(plateau);
        Test.test(plateau.getNombreJoueurs() == 2, "nombre de joueurs");
        capitaine.ajouterPieces();
        capitaine.ajouterPieces();
        Quartier quartier1 = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
        Quartier quartier2 = new Quartier("prison", Quartier.TYPE_QUARTIERS[1], 2);
        Quartier quartier3 = new Quartier("église", Quartier.TYPE_QUARTIERS[0], 2);
        Quartier quartier4 = new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5);
        Quartier quartier5 = new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3);
        roi.getJoueur().ajouterQuartierDansCite(quartier3);
        roi.getJoueur().ajouterQuartierDansCite(quartier4);
        roi.getJoueur().ajouterQuartierDansCite(quartier5);
        roi.getJoueur().ajouterQuartierDansCite(quartier1);
        roi.getJoueur().ajouterQuartierDansCite(quartier2);
        capitaine.getJoueur().ajouterQuartierDansCite(quartier2);
        Test.test(roi.getJoueur().nbQuartiersDansCite() == 5,
                "nombre de quartier chez le roi avant de commencer");
        Test.test(capitaine.getJoueur().nbQuartiersDansCite() == 1,
                "nombre de quartier chez la capitaine avant de commencer");
        Test.test(capitaine.getJoueur().nbPieces() == 4,
                "nombre de piece de la capitaine avant de commencer");
        capitaine.percevoirRessourcesSpecifiques();
        Test.test(capitaine.getJoueur().nbPieces() == 5,
                "nombre de piece de la capitaine apres perception des ressources");
        capitaine.utiliserPouvoir();
        if (capitaine.getJoueur().quartierPresentDansCite("temple")) {
            Test.test(capitaine.getJoueur().nbQuartiersDansCite() == 2,
                    "nombre de quartier chez la capitaine apres pouvoir");
            Test.test(capitaine.getJoueur().nbPieces() == 4,
                    "nombre de piece de la capitaine apres pouvoir (vole de 1)");
            Test.test(roi.getJoueur().nbQuartiersDansCite() == 4,
                    "nombre de quartier chez le roi apres vole");
            Test.test(roi.getJoueur().nbPieces() == 1,
                    "nombre de piece du roi apres pouvoir de la capitaine (vole de 1)");
        } else if (capitaine.getJoueur().quartierPresentDansCite("église")) {
            Test.test(capitaine.getJoueur().nbQuartiersDansCite() == 2,
                    "nombre de quartier chez la capitaine apres pouvoir");
            Test.test(capitaine.getJoueur().nbPieces() == 3,
                    "nombre de piece de la capitaine apres pouvoir (vole de 2)");
            Test.test(roi.getJoueur().nbQuartiersDansCite() == 4,
                    "nombre de quartier chez le roi apres vole");
            Test.test(roi.getJoueur().nbPieces() == 2,
                    "nombre de piece du roi apres pouvoir de la capitaine (vole de 2)");
        } else {
            Test.test(capitaine.getJoueur().nbQuartiersDansCite() == 2,
                    "nombre de quartier chez la capitaine apres pouvoir");
            Test.test(capitaine.getJoueur().nbPieces() == 2,
                    "nombre de piece de la capitaine apres pouvoir (vole de 3)");
            Test.test(roi.getJoueur().nbQuartiersDansCite() == 4,
                    "nombre de quartier chez le roi apres vole");
            Test.test(roi.getJoueur().nbPieces() == 3,
                    "nombre de piece du roi apres pouvoir de la capitaine (vole de 3)");
        } // quand on fait le test veriffier que le vole de palais et de la prison n'est
        // pas possible
    }

    public void test3() {
        System.out.println("TEST DU POUVOIR SANS ARGENT");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Joueur joueur = new Joueur("Billy");
        plateau.ajouterJoueur(joueur);
        Joueur joueur2 = new Joueur("Uge");
        plateau.ajouterJoueur(joueur2);
        Capitaine capitaine = new Capitaine();
        Roi roi = new Roi();
        capitaine.setJoueur(joueur);
        roi.setJoueur(joueur2);
        capitaine.setPlateau(plateau);
        roi.setPlateau(plateau);
        Test.test(plateau.getNombreJoueurs() == 2, "nombre de joueurs");
        Quartier quartier1 = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
        roi.getJoueur().ajouterQuartierDansCite(quartier1);
        Test.test(roi.getJoueur().nbQuartiersDansCite() == 1,
                "nombre de quartier chez le roi avant de commencer");
        Test.test(capitaine.getJoueur().nbQuartiersDansCite() == 0,
                "nombre de quartier chez la capitaine avant de commencer");
        Test.test(capitaine.getJoueur().nbPieces() == 0,
                "nombre de piece de la capitaine avant de commencer");
        capitaine.percevoirRessourcesSpecifiques();
        Test.test(capitaine.getJoueur().nbPieces() == 0,
                "nombre de piece de la capitaine apres perception des ressources");
        capitaine.utiliserPouvoir();
        Test.test(capitaine.getJoueur().nbQuartiersDansCite() == 0,
                "nombre de quartier chez la capitaine apres pouvoir sans or");
        Test.test(capitaine.getJoueur().nbPieces() == 0,
                "nombre de piece de la capitaine apres pouvoir sans or");
        Test.test(roi.getJoueur().nbQuartiersDansCite() == 1,
                "nombre de quartier chez le roi apres pouvoir capitaine sans or");
    }

    public void test4() {
        System.out.println("TEST DU POUVOIR COUNTER DE EVEQUE");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Joueur joueur = new Joueur("Billy");
        plateau.ajouterJoueur(joueur);
        Joueur joueur2 = new Joueur("Uge");
        plateau.ajouterJoueur(joueur2);
        Capitaine capitaine = new Capitaine();
        Eveque eveque = new Eveque();
        capitaine.setJoueur(joueur);
        eveque.setJoueur(joueur2);
        capitaine.setPlateau(plateau);
        eveque.setPlateau(plateau);
        Test.test(plateau.getNombreJoueurs() == 2, "nombre de joueurs");
        Quartier quartier1 = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
        eveque.getJoueur().ajouterQuartierDansCite(quartier1);
        Test.test(eveque.getJoueur().nbQuartiersDansCite() == 1,
                "nombre de quartier chez le eveque avant de commencer");
        Test.test(capitaine.getJoueur().nbQuartiersDansCite() == 0,
                "nombre de quartier chez la capitaine avant de commencer");
        capitaine.ajouterPieces();
        Test.test(capitaine.getJoueur().nbPieces() == 2,
                "nombre de piece de la capitaine avant de commencer");
        capitaine.percevoirRessourcesSpecifiques();
        Test.test(capitaine.getJoueur().nbPieces() == 2,
                "nombre de piece de la capitaine apres perception des ressources");
        capitaine.utiliserPouvoir();
        Test.test(capitaine.getJoueur().nbQuartiersDansCite() == 0,
                "nombre de quartier chez la capitaine counter par l'eveque");
        Test.test(capitaine.getJoueur().nbPieces() == 2,
                "nombre de piece de la capitaine apres pouvoir counter par l eveque");
        Test.test(eveque.getJoueur().nbQuartiersDansCite() == 1,
                "nombre de quartier chez l eveque apres pouvoir capitaine counter");
    }

    public void test5() {
        System.out.println("TEST DU POUVOIR SANS QUARTIER SUR CIBLE");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Joueur joueur = new Joueur("Billy");
        plateau.ajouterJoueur(joueur);
        Joueur joueur2 = new Joueur("Uge");
        plateau.ajouterJoueur(joueur2);
        Capitaine capitaine = new Capitaine();
        Roi roi = new Roi();
        capitaine.setJoueur(joueur);
        roi.setJoueur(joueur2);
        capitaine.setPlateau(plateau);
        roi.setPlateau(plateau);
        Test.test(plateau.getNombreJoueurs() == 2, "nombre de joueurs");
        Test.test(roi.getJoueur().nbQuartiersDansCite() == 1,
                "nombre de quartier chez le roi avant de commencer");
        Test.test(capitaine.getJoueur().nbQuartiersDansCite() == 0,
                "nombre de quartier chez la capitaine avant de commencer");
        Test.test(capitaine.getJoueur().nbPieces() == 0,
                "nombre de piece de la capitaine avant de commencer");
        capitaine.percevoirRessourcesSpecifiques();
        Test.test(capitaine.getJoueur().nbPieces() == 0,
                "nombre de piece de la capitaine apres perception des ressources");
        capitaine.getJoueur().ajouterPieces(5);
        capitaine.utiliserPouvoir();
        Test.test(capitaine.getJoueur().nbQuartiersDansCite() == 0,
                "nombre de quartier chez la capitaine apres pouvoir sans or");
        Test.test(capitaine.getJoueur().nbPieces() == 5,
                "nombre de piece de la capitaine apres pouvoir sans or");
        Test.test(roi.getJoueur().nbQuartiersDansCite() == 1,
                "nombre de quartier chez le roi apres pouvoir capitaine sans or");
    }
}