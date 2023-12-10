package test;

import modele.Assassin;
import modele.Caracteristiques;
import modele.Joueur;
import modele.PlateauDeJeu;
import modele.Quartier;
import modele.Roi;

public class TestEmpereur {
    public static void main(String[] args) {
        TestEmpereur test = new TestEmpereur();
        // test.test1();
        // test.test2();
        // test.test3();
        // test.test4();
    }

    public void test1() {
        System.out.println("TEST DU CONSTRUCTEUR");
        Empereur empereur = new Empereur();
        Test.test(empereur.getNom().equals("Empereur"), "test du nom du personnage");
        Test.test(empereur.getRang() == 4, "test du rang du personnage");
        Test.test(empereur.getCaracteristiques().equals(Caracteristiques.EMPEREUR),
                "test des caracteristiques du personnage");
        Test.test(empereur.getJoueur() == null, "test de l'initialisation de la variable \"joueur\"");
        Test.test(empereur.getAssassine() == false, "test de l'initialisation de la variable \"assassine\"");
        Test.test(empereur.getVole() == false, "test de l'initialisation de la variable \"vole\"");
    }

    public void test2() {
        System.out.println("TEST PERCEPTION DES RESSOURCES ET POUVOIR");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Joueur joueur = new Joueur("Billy");
        plateau.ajouterJoueur(joueur);
        Joueur joueur2 = new Joueur("Martin");
        plateau.ajouterJoueur(joueur2);
        Joueur joueur3 = new Joueur("Emma");
        plateau.ajouterJoueur(joueur3);
        Empereur empereur = new Empereur();
        Roi roi = new Roi();
        Assassin assassin = new Assassin();
        empereur.setJoueur(joueur);
        roi.setJoueur(joueur2);
        assassin.setJoueur(joueur3);
        Quartier quartier = new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5);
        Quartier quartier2 = new Quartier("manoir", Quartier.TYPE_QUARTIERS[2], 3);
        empereur.getJoueur().ajouterQuartierDansCite(quartier);
        empereur.getJoueur().ajouterQuartierDansCite(quartier2);
        Test.test(empereur.getJoueur().nbPieces() == 0, "Nombre de piece avant perception");
        empereur.percevoirRessourcesSpecifiques();
        Test.test(empereur.getJoueur().nbPieces() == 2, "Nombre de piece apres ressource specifique");
        roi.getJoueur().setPossedeCouronne(true);
        assassin.ajouterPieces();
        assassin.getJoueur().ajouterQuartierDansMain(quartier2);
        empereur.utiliserPouvoir();
        Test.test(assassin.getJoueur().getPossedeCouronne() == true,
                "Verifier que la couronne soit bien sur l'assassin");
        Test.test(empereur.getJoueur().nbPieces() == 3, "Test l'assassin donne de l'or");
        Test.test(empereur.getJoueur().nbQuartiersDansMain() == 1, "Test l'assassin donne une carte");
    }

    public void test3() {
        System.out.println("TEST PERCEPTION DES RESSOURCES ET POUVOIR SUR UNE PERSONNE QUI N AS NI OR NI CARTE");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Joueur joueur = new Joueur("Billy");
        plateau.ajouterJoueur(joueur);
        Joueur joueur2 = new Joueur("Martin");
        plateau.ajouterJoueur(joueur2);
        Joueur joueur3 = new Joueur("Emma");
        plateau.ajouterJoueur(joueur3);
        Empereur empereur = new Empereur();
        Roi roi = new Roi();
        Assassin assassin = new Assassin();
        empereur.setJoueur(joueur);
        roi.setJoueur(joueur2);
        assassin.setJoueur(joueur3);
        roi.getJoueur().setPossedeCouronne(true);
        empereur.utiliserPouvoir();
        Test.test(assassin.getJoueur().getPossedeCouronne() == true,
                "Verifier que la couronne soit bien sur l'assassin");
        Test.test(empereur.getJoueur().nbPieces() == 0, "Test l'assassin donne de l'or");
        Test.test(empereur.getJoueur().nbQuartiersDansMain() == 0, "Test l'assassin donne une carte");
    }

    public void test4() {
        System.out.println("TEST PERCEPTION DES RESSOURCES ET POUVOIR APRES ASSASSINAT");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Joueur joueur = new Joueur("Billy");
        plateau.ajouterJoueur(joueur);
        Joueur joueur2 = new Joueur("Martin");
        plateau.ajouterJoueur(joueur2);
        Joueur joueur3 = new Joueur("Emma");
        plateau.ajouterJoueur(joueur3);
        Empereur empereur = new Empereur();
        Roi roi = new Roi();
        Assassin assassin = new Assassin();
        empereur.setJoueur(joueur);
        roi.setJoueur(joueur2);
        assassin.setJoueur(joueur3);
        roi.getJoueur().setPossedeCouronne(true);
        empereur.setAssassine();
        empereur.utiliserPouvoir();
        Test.test(assassin.getJoueur().getPossedeCouronne() == true,
                "Verifier que la couronne soit bien sur l'assassin");
        Test.test(empereur.getJoueur().nbPieces() == 0, "Test l'assassin donne de l'or");
        Test.test(empereur.getJoueur().nbQuartiersDansMain() == 0, "Test l'assassin donne une carte");
    } // je sais pas comment ça fonctionne voir avec la fonction de mattieu
}
