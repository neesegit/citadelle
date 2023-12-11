/
  package test;
  
  import modele.Assassin;
  import modele.Caracteristiques;
  import modele.Joueur;
  import modele.PlateauDeJeu;
  import modele.Quartier;
  import modele.Roi;
  
  public class TestNegociant {
  public static void main(String[] args) {
  TestNegociant test = new TestNegociant();
  // test.test1();
  // test.test2();
  }
  
  public void test1() {
  System.out.println("TEST DU CONSTRUCTEUR");
  Negociant negociant = new Negociant();
  Test.test(negociant.getNom().equals("Negociant"),
  "test du nom du personnage");
  Test.test(negociant.getRang() == 6, "test du rang du personnage");
  Test.test(negociant.getCaracteristiques().equals(Caracteristiques.NEGOCIANT),
  "test des caracteristiques du personnage");
  Test.test(negociant.getJoueur() == null,
  "test de l'initialisation de la variable \"joueur\"");
  Test.test(negociant.getAssassine() == false,
  "test de l'initialisation de la variable \"assassine\"");
  Test.test(negociant.getVole() == false,
  "test de l'initialisation de la variable \"vole\"");
  }
  
  public void test2() {
  System.out.println("TEST PERCEPTION DES RESSOURCES ET POUVOIR (PASSIF)");
  PlateauDeJeu plateau = new PlateauDeJeu();
  Joueur joueur = new Joueur("Billy");
  plateau.ajouterJoueur(joueur);
  Joueur joueur2 = new Joueur("Martin");
  plateau.ajouterJoueur(joueur2);
  Joueur joueur3 = new Joueur("Emma");
  plateau.ajouterJoueur(joueur3);
  Negociant negociant = new Negociant();
  negociant.setJoueur(joueur);
  Quartier quartier = new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5);
  Quartier quartier2 = new Quartier("echoppe", Quartier.TYPE_QUARTIERS[3], 2);
  Quartier quartier3 = new Quartier("echoppe", Quartier.TYPE_QUARTIERS[3], 2);
  Quartier quartier4 = new Quartier("taverne", Quartier.TYPE_QUARTIERS[3], 1);
  Quartier quartier5 = new Quartier("cathedrale", Quartier.TYPE_QUARTIERS[0],
  5);
  negociant.getJoueur().ajouterPieces(15);
  Test.test(negociant.getJoueur().nbQuartiersDansCite() == 0,
  "Verification nombre de quartier dans la cité");
  negociant.construire(quartier2);
  negociant.construire(quartier);
  negociant.construire(quartier3);
  negociant.construire(quartier5);
  negociant.construire(quartier4);
  Test.test(negociant.getJoueur().nbQuartiersDansCite() == 4,
  "Verification du passif (reussi a construire tout sauf la cathedrale)");
  }
  }
 /