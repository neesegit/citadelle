/
package test;

import modele.Architecte;
import modele.Caracteristiques;
import modele.Joueur;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;

public class TestArchiviste {
    public static void main(String[] args) {
        TestArchiviste test = new TestArchiviste();
        // test.test1();
        // test.test2();
    }

    public void test1() {
        System.out.println("TEST DU CONSTRUCTEUR");
        Archiviste archiviste = new Archiviste();
        Test.test(archiviste.getNom().equals("Archiviste"),
                "test du nom du personnage");
        Test.test(archiviste.getRang() == 7, "test du rang du personnage");
        Test.test(archiviste.getCaracteristiques().equals(Caracteristiques.ARCHIVISTE),
                "test des caract�ristiques du personnage");
        Test.test(archiviste.getJoueur() == null,
                "test de l'initialisation de la variable \"joueur\"");
        Test.test(archiviste.getAssassine() == false,
                "test de l'initialisation de la variable \"assassine\"");
        Test.test(archiviste.getVole() == false,
                "test de l'initialisation de la variable \"vole\"");
    }

  public void test2() {
  System.out.println("TEST DU POUVOIR ET DE SA CAPACITE A CONSTRUIRE PLUS");
  PlateauDeJeu plateau = new PlateauDeJeu();
  Joueur joueur = new Joueur("Billy");
  plateau.ajouterJoueur(joueur);
  Archiviste archiviste = new Archiviste();
  archiviste.setJoueur(joueur);
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
  q = new Quartier("basilique", Quartier.TYPE_QUARTIERS[4], 4,
  "A la fin de la partie, ...");
  pioche.ajouter(q);
  q = new Quartier("cathedrale", Quartier.TYPE_QUARTIERS[0], 5);
  pioche.ajouter(q);
  q = new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3);
  pioche.ajouter(q);
  q = new Quartier("manoir", Quartier.TYPE_QUARTIERS[2], 3);
  pioche.ajouter(q);
  q = new Quartier("hotel de ville", Quartier.TYPE_QUARTIERS[3], 15);
  pioche.ajouter(q);
  q = new Quartier("bibliotheque", Quartier.TYPE_QUARTIERS[4], 6,
  "Si vous choisissez...");
  pioche.ajouter(q);
  pioche.melanger();
  Test.test(archiviste.getJoueur().nbQuartiersDansMain() == 0,
  "Test du nombre de carte dans la main");
  archiviste.utiliserPouvoir();
  // verifier que j'ai bien le choix entre 7 carte quartier
  Test.test(archiviste.getJoueur().nbQuartiersDansMain() == 1,
  "Test du nombre de carte dans la main");
  Quartier quartier1 = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
  Quartier quartier3 = new Quartier("église", Quartier.TYPE_QUARTIERS[0], 2);
  archiviste.ajouterPieces();
  archiviste.ajouterPieces();
  archiviste.construire(quartier1);
  archiviste.construire(quartier3);
  Test.test(archiviste.getJoueur().nbQuartiersDansCite() == 2,
  "Test de la double construction");// Overwrite la
  // onction
  // construire
  }
    // le fait qu'il n'y ai pas assez de pioche n'arrivera pas du fait que la
    pioche
    // ce recycle
}/