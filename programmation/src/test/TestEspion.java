package test;

import java.util.ArrayList;

import modele.Caracteristiques;
import modele.Espion;
import modele.Joueur;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;
import modele.Roi;

public class TestEspion {
    public static void main(String[] args) {
        TestNavigatrice test = new TestNavigatrice();
        test.test1();
        test.test2();
    }

    public void test1() {
        System.out.println("TEST DU CONSTRUCTEUR");
        Espion espion = new Espion();
        Test.test(espion.getNom().equals("Espion"), "test du nom du personnage");
        Test.test(espion.getRang() == 2, "test du rang du personnage");
        Test.test(espion.getCaracteristiques().equals(Caracteristiques.ESPION),
                "test des caracteristiques du personnage");
        Test.test(espion.getJoueur() == null, "test de l'initialisation de la variable \"joueur\"");
        Test.test(espion.getAssassine() == false, "test de l'initialisation de la variable \"assassine\"");
        Test.test(espion.getVole() == false, "test de l'initialisation de la variable \"vole\"");
    }

    public void test2() {
        System.out.println("TEST DU POUVOIR ESPION");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Roi roi = new Roi();
        Espion espion = new Espion();
        Joueur joueur1 = new Joueur("Billy");
        plateau.ajouterJoueur(joueur1);
        Joueur joueur2 = new Joueur("Martin");
        plateau.ajouterJoueur(joueur2);
        roi.setJoueur(joueur1);
        espion.setJoueur(joueur2);

        // creation d'une pioche:
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
        q = new Quartier("catheedrale", Quartier.TYPE_QUARTIERS[0], 5);
        pioche.ajouter(q);
        q = new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3);
        pioche.ajouter(q);
        q = new Quartier("manoir", Quartier.TYPE_QUARTIERS[2], 3);
        pioche.ajouter(q);
        q = new Quartier("hotel de ville", Quartier.TYPE_QUARTIERS[3], 15);
        pioche.ajouter(q);
        q = new Quartier("bibliotheque", Quartier.TYPE_QUARTIERS[4], 6, "Si vous choisissez...");
        pioche.ajouter(q);
        pioche.melanger();

        // on distribue les cartes aux joueurs:
        joueur1.ajouterQuartierDansMain(pioche.piocher());
        joueur1.ajouterQuartierDansMain(pioche.piocher());
        joueur1.ajouterQuartierDansMain(pioche.piocher());
        joueur2.ajouterQuartierDansMain(pioche.piocher());
        joueur2.ajouterQuartierDansMain(pioche.piocher());
        joueur2.ajouterQuartierDansMain(pioche.piocher());
        joueur1.ajouterQuartierDansMain(pioche.piocher());
        joueur1.ajouterQuartierDansMain(pioche.piocher());

        // on affiche la main de chaque joueur:
        System.out.print("Main du Roi (" + roi.getJoueur().getNom() + "): ");
        ArrayList<Quartier> mainRoi = roi.getJoueur().getMain();
        for (int i = 0; i < mainRoi.size(); i++)
            System.out.print(mainRoi.get(i).getNom() + ", ");
        System.out.println("");
        System.out.print("Main de l'espion (" + espion.getJoueur().getNom() + "): ");
        ArrayList<Quartier> mainAssassin = espion.getJoueur().getMain();
        for (int i = 0; i < mainAssassin.size(); i++)
            System.out.print(mainAssassin.get(i).getNom() + ", ");
        System.out.println("");

        // on recupere la taille de la pioche:
        roi.ajouterPieces();
        espion.ajouterPieces();
        Test.test(espion.getJoueur().nbPieces() == 2, "test du nombre de pieces d'or avant pouvoir");
        Test.test(espion.getJoueur().nbQuartiersDansMain() == 3, "test du nombre de carte avant pouvoir");
        espion.utiliserPouvoir();

        Test.test(espion.getJoueur().nbPieces() == 4, "test du nombre de pieces d'or recuperer du pouvoir");
        Test.test(roi.getJoueur().nbPieces() == 0, "test du nombre de pieces du roi");
        // attention ça dépend du nombre de quartier de ce type a changer pour voir si
        // les cas marche
        Test.test(espion.getJoueur().nbQuartiersDansMain() == 5, "test du nombre de carte apres pouvoir");
    }
}
