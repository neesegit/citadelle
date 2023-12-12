
  package test;
  
  import java.util.ArrayList;
  
  import modele.Assassin;
  import modele.Joueur;
  import modele.Marchande;
  import modele.Pioche;
  import modele.PlateauDeJeu;
  import modele.Quartier;
  import modele.Roi;
  import modele.*;
  
  public class TestVoyante {
  public static void main(String[] args) {
  TestVoyante test = new TestVoyante();
  // test.test1();
  // test.test2();
  }
  
  public void test1() {
  System.out.println("TEST DU CONSTRUCTEUR");
  PlateauDeJeu plateau = new PlateauDeJeu();
  Roi roi = new Roi();
  plateau.ajouterPersonnage(roi);
  Marchande marchande = new Marchande();
  plateau.ajouterPersonnage(marchande);
  Assassin assassin = new Assassin();
  plateau.ajouterPersonnage(assassin);
  Voyante voyante = new Voyante();
  plateau.ajouterPersonnage(voyante);
  Test.test(plateau.getNombrePersonnages() == 4, "nombre de personnages");
  Test.test(plateau.getPersonnage(3) == voyante,
  "recuperation du personnage de la voyante");
  Test.test(plateau.getPersonnage(3).getRang() == 3,
  "rang de la voyante");
  
  }
  
  public void test2() {
  System.out.println("TEST DU POUVOIR DE LA MAGICIENNE");
  PlateauDeJeu plateau = new PlateauDeJeu();
  
  // creation de quatre personnages
  Roi roi = new Roi();
  plateau.ajouterPersonnage(roi);
  Assassin assassin = new Assassin();
  plateau.ajouterPersonnage(assassin);
  Marchande marchande = new Marchande();
  plateau.ajouterPersonnage(marchande);
  Voyante voyante = new Voyante();
  plateau.ajouterPersonnage(voyante);
  
  // creation de quatre joueurs
  Joueur joueur1 = new Joueur("Milou");
  plateau.ajouterJoueur(joueur1);
  Joueur joueur2 = new Joueur("Billy");
  plateau.ajouterJoueur(joueur2);
  Joueur joueur3 = new Joueur("Belle");
  plateau.ajouterJoueur(joueur3);
  Joueur joueur4 = new Joueur("Philipe");
  plateau.ajouterJoueur(joueur4);
  
  // on associe les personnages aux joueurs
  roi.setJoueur(joueur1);
  assassin.setJoueur(joueur2);
  voyante.setJoueur(joueur3);
  marchande.setJoueur(joueur4);
  
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
  q = new Quartier("�choppe", Quartier.TYPE_QUARTIERS[3], 2);
  pioche.ajouter(q);
  q = new Quartier("basilique", Quartier.TYPE_QUARTIERS[4], 4,
  "A la fin de la partie, ...");
  pioche.ajouter(q);
  q = new Quartier("cath�drale", Quartier.TYPE_QUARTIERS[0], 5);
  pioche.ajouter(q);
  q = new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3);
  pioche.ajouter(q);
  q = new Quartier("manoir", Quartier.TYPE_QUARTIERS[2], 3);
  pioche.ajouter(q);
  q = new Quartier("h�tel de ville", Quartier.TYPE_QUARTIERS[3], 15);
  pioche.ajouter(q);
  q = new Quartier("biblioth�que", Quartier.TYPE_QUARTIERS[4], 6,
  "Si vous choisissez...");
  pioche.ajouter(q);
  pioche.melanger();
  
  // on distribue les cartes aux joueurs:
  joueur1.ajouterQuartierDansMain(pioche.piocher());
  joueur1.ajouterQuartierDansMain(pioche.piocher());
  joueur1.ajouterQuartierDansMain(pioche.piocher());
  joueur2.ajouterQuartierDansMain(pioche.piocher());
  joueur2.ajouterQuartierDansMain(pioche.piocher());
  joueur2.ajouterQuartierDansMain(pioche.piocher());
  joueur3.ajouterQuartierDansMain(pioche.piocher());
  joueur3.ajouterQuartierDansMain(pioche.piocher());
  
  // on affiche la main de chaque joueur:
  System.out.print("Main du Roi (" + roi.getJoueur().getNom() + "): ");
  ArrayList<Quartier> mainRoi = roi.getJoueur().getMain();
  for (int i = 0; i < mainRoi.size(); i++)
  System.out.print(mainRoi.get(i).getNom() + ", ");
  System.out.println("");
  System.out.print("Main de l'assassin (" + assassin.getJoueur().getNom() +
  "): ");
  ArrayList<Quartier> mainAssassin = assassin.getJoueur().getMain();
  for (int i = 0; i < mainAssassin.size(); i++)
  System.out.print(mainAssassin.get(i).getNom() + ", ");
  System.out.println("");
  System.out.print("Main de la magicienne (" + voyante.getJoueur().getNom() +
  "): ");
  ArrayList<Quartier> mainVoyante = voyante.getJoueur().getMain();
  for (int i = 0; i < mainVoyante.size(); i++)
  System.out.print(mainVoyante.get(i).getNom() + ", ");
  System.out.println("");
  System.out.print("Main de la marchande (" + marchande.getJoueur().getNom() +
  "): ");
  ArrayList<Quartier> mainMarchande = marchande.getJoueur().getMain();
  for (int i = 0; i < mainMarchande.size(); i++)
  System.out.print(mainMarchande.get(i).getNom() + ", ");
  System.out.println("");
  
  // utiliser le pouvoir de la voyante :
  voyante.utiliserPouvoir();
  
  // on reaffiche la main de chaque joueur:
  System.out.print("Main du Roi (" + roi.getJoueur().getNom() + "): ");
  for (int i = 0; i < mainRoi.size(); i++)
  System.out.print(mainRoi.get(i).getNom() + ", ");
  System.out.println("");
  System.out.print("Main de l'assassin (" + assassin.getJoueur().getNom() +
  "): ");
  for (int i = 0; i < mainAssassin.size(); i++)
  System.out.print(mainAssassin.get(i).getNom() + ", ");
  System.out.println("");
  System.out.print("Main de la magicienne (" + voyante.getJoueur().getNom() +
  "): ");
  mainVoyante = voyante.getJoueur().getMain();
  for (int i = 0; i < mainVoyante.size(); i++)
  System.out.print(mainVoyante.get(i).getNom() + ", ");
  System.out.println("");
  System.out.print("Main de la marchande (" + marchande.getJoueur().getNom() +
  "): ");
  mainMarchande = marchande.getJoueur().getMain();
  for (int i = 0; i < mainMarchande.size(); i++)
  System.out.print(mainMarchande.get(i).getNom() + ", ");
  System.out.println("");
  
  Test.test(marchande.getJoueur().nbQuartiersDansMain() == 0,
  "On verifie que chacun a bin le même nombre carte à la fin");
  Test.test(roi.getJoueur().nbQuartiersDansMain() == 3,
  "On verifie que chacun a bin le même nombre carte à la fin");
  Test.test(assassin.getJoueur().nbQuartiersDansMain() == 3,
  "On verifie que chacun a bin le même nombre carte à la fin");
  Test.test(voyante.getJoueur().nbQuartiersDansMain() == 2,
  "On verifie que chacun a bin le même nombre carte à la fin");
  Quartier quartier1 = new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3);
  Quartier quartier2 = new Quartier("manoir", Quartier.TYPE_QUARTIERS[2], 3);
  Quartier quartier3 = new Quartier("prison", Quartier.TYPE_QUARTIERS[1], 2);
  voyante.getJoueur().ajouterPieces(8);
  voyante.construire(quartier1);
  voyante.construire(quartier2);
  voyante.construire(quartier3);
  Test.test(voyante.getJoueur().nbQuartiersDansCite() == 2,
  "Verifiez que deux construction par tour");
  }
  }