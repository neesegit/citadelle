/*package test;

import modele.Caracteristiques;
import modele.Joueur;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;
import modele.Abbe;
import modele.Marchande;

public class TestAbbe {
        public static void main(String[] args) {
                TestAbbe test = new TestAbbe();
                // test.test1();
                // test.test2();
        }

        public void test1() {
                System.out.println("TEST DU CONSTRUCTEUR");
                Abbe abbe = new Abbe();
                Test.test(abbe.getNom().equals("Abbe"), "test du nom du personnage");
                Test.test(abbe.getRang() == 5, "test du rang du personnage");
                Test.test(abbe.getCaracteristiques().equals(Caracteristiques.ABBE),
                                "test des caracteristiques du personnage");
                Test.test(abbe.getJoueur() == null, "test de l'initialisation de la variable \"joueur\"");
                Test.test(abbe.getAssassine() == false, "test de l'initialisation de la variable \"assassine\"");
                Test.test(abbe.getVole() == false, "test de l'initialisation de la variable \"vole\"");
        }

        public void test2() {
                System.out.println(
                                "TEST DE LA PERCEPTION DE RESSOURCES SPECIFIQUES + PASSIF (sachant que l'abbe n'est le plus riche)");
                PlateauDeJeu plateau = new PlateauDeJeu();
                Joueur joueur = new Joueur("Billy");
                plateau.ajouterJoueur(joueur);
                Joueur joueur2 = new Joueur("Martin");
                plateau.ajouterJoueur(joueur2);
                Joueur joueur3 = new Joueur("Emma");
                plateau.ajouterJoueur(joueur3);
                Abbe abbe = new Abbe();
                Quartier quartier1 = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
                Quartier quartier2 = new Quartier("prison", Quartier.TYPE_QUARTIERS[1], 2);
                Quartier quartier3 = new Quartier("église", Quartier.TYPE_QUARTIERS[0], 2);
                abbe.setJoueur(joueur);
                abbe.ajouterPieces();
                Test.test(abbe.getJoueur().nbPieces() == 2, "test du nombre de pieces d'or avant perception");
                Test.test(plateau.getNombrePersonnages() == 3, "nombre de joueurs");
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
                Test.test(abbe.getJoueur().nbQuartiersDansMain() == 2,
                                "test du nombre de carte apres perception de ressources specifique avec 2 quartiers religieux");
                abbe.construire(quartier1);
                abbe.construire(quartier2);
                abbe.construire(quartier3);
                abbe.percevoirRessourcesSpecifiques();
                if (abbe.getJoueur().nbPieces() == 4) {
                        Test.test(abbe.getJoueur().nbPieces() == 4,
                                        "test du nombre de pieces d'or apres perception de ressources specifiques et abbe le plus riche avec 2 quartiers religieux");
                        Test.test(abbe.getJoueur().nbQuartiersDansMain() == 2,
                                        "test du nombre de carte apres perception de ressources specifique avec 2 quartiers religieux");
                }
                if (abbe.getJoueur().nbPieces() == 3) {
                        Test.test(abbe.getJoueur().nbPieces() == 3,
                                        "test du nombre de pieces d'or apres perception de ressources specifiques et abbe le plus riche avec 2 quartiers religieux");
                        Test.test(abbe.getJoueur().nbQuartiersDansMain() == 3,
                                        "test du nombre de carte apres perception de ressources specifique avec 2 quartiers religieux");
                }
                if (abbe.getJoueur().nbPieces() == 2) {
                        Test.test(abbe.getJoueur().nbPieces() == 2,
                                        "test du nombre de pieces d'or apres perception de ressources specifiques et abbe le plus riche avec 2 quartiers religieux");
                        Test.test(abbe.getJoueur().nbQuartiersDansMain() == 4,
                                        "test du nombre de carte apres perception de ressources specifique avec 2 quartiers religieux");
                }
                Test.test(abbe.getJoueur().nbPieces() == 0,
                                "test du nombre de pieces d'or apres perception de ressources specifiques et abbe le plus riche avec 2 quartiers religieux (n'as pas passe le test)");
                Test.test(abbe.getJoueur().nbQuartiersDansMain() == 0,
                                "test du nombre de carte apres perception de ressources specifique avec 2 quartiers religieux (n'as pas passe le test)");
                // pendant la preception de ressource spécifique il faut que le joueur avec le
                // plus d'or lui donne 1 piece d'or suaf si c'est l'abbe
                // tester une fois il prend une piece d'or
                // une fois il est le plus riche
        }

        public void test3() {
                System.out.println(
                                "TEST DE LA PERCEPTION DE RESSOURCES SPECIFIQUES + PASSIF (sachant que l'abbe n'est le plus riche)");
                PlateauDeJeu plateau = new PlateauDeJeu();
                Joueur joueur = new Joueur("Billy");
                plateau.ajouterJoueur(joueur);
                Joueur joueur2 = new Joueur("Martin");
                plateau.ajouterJoueur(joueur2);
                Joueur joueur3 = new Joueur("Emma");
                plateau.ajouterJoueur(joueur3);
                Marchande marchande = new Marchande();
                Abbe abbe = new Abbe();
                Quartier quartier1 = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
                Quartier quartier2 = new Quartier("prison", Quartier.TYPE_QUARTIERS[1], 2);
                Quartier quartier3 = new Quartier("église", Quartier.TYPE_QUARTIERS[0], 2);
                abbe.setJoueur(joueur);
                marchande.setJoueur(joueur2);
                abbe.ajouterPieces();
                marchande.ajouterPieces();
                marchande.ajouterPieces();
                Test.test(abbe.getJoueur().nbPieces() == 2, "test du nombre de pieces d'or avant perception");
                Test.test(marchande.getJoueur().nbPieces() == 4,
                                "test du nombre de pieces d'or avant perception, supremacie marchande");
                Test.test(plateau.getNombrePersonnages() == 3, "nombre de joueurs");
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
                Test.test(abbe.getJoueur().nbQuartiersDansMain() == 2,
                                "test du nombre de carte apres perception de ressources specifique avec 2 quartiers religieux");
                abbe.construire(quartier1);
                abbe.construire(quartier2);
                abbe.construire(quartier3);
                abbe.percevoirRessourcesSpecifiques();
                if (abbe.abbe.getJoueur().nbPieces() == 5) {
                        Test.test(abbe.getJoueur().nbPieces() == 5,
                                        "test du nombre de pieces d'or apres perception de ressources specifiques et abbe le plus riche avec 2 quartiers religieux");
                        Test.test(abbe.getJoueur().nbQuartiersDansMain() == 2,
                                        "test du nombre de pieces d'or apres perception de ressources specifique avec 2 quartiers religieux");
                }
                if (abbe.abbe.getJoueur().nbPieces() == 4) {
                        Test.test(abbe.getJoueur().nbPieces() == 4,
                                        "test du nombre de pieces d'or apres perception de ressources specifiques et abbe le plus riche avec 2 quartiers religieux");
                        Test.test(abbe.getJoueur().nbQuartiersDansMain() == 3,
                                        "test du nombre de pieces d'or apres perception de ressources specifique avec 2 quartiers religieux");
                }
                if (abbe.abbe.getJoueur().nbPieces() == 3) {
                        Test.test(abbe.getJoueur().nbPieces() == 3,
                                        "test du nombre de pieces d'or apres perception de ressources specifiques et abbe le plus riche avec 2 quartiers religieux");
                        Test.test(abbe.getJoueur().nbQuartiersDansMain() == 4,
                                        "test du nombre de pieces d'or apres perception de ressources specifique avec 2 quartiers religieux");
                }
                Test.test(abbe.getJoueur().nbPieces() == 0,
                                "test du nombre de pieces d'or apres perception de ressources specifiques et abbe le plus riche avec 2 quartiers religieux (n'as pas passe le test)");
                Test.test(abbe.getJoueur().nbQuartiersDansMain() == 0,
                                "test du nombre de pieces d'or apres perception de ressources specifique avec 2 quartiers religieux (n'as pas passe le test)");
                // pendant la preception de ressource spécifique il faut que le joueur avec le
                // plus d'or lui donne 1 piece d'or suaf si c'est l'abbe
                // tester une fois il prend une piece d'or
                // une fois il est le plus riche
        }
}
 */