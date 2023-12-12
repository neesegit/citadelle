package test;

import modele.Caracteristiques;
import modele.Diplomate;
import modele.Eveque;
import modele.Joueur;
import modele.PlateauDeJeu;
import modele.Quartier;
import modele.Roi;

public class TestDiplomate {
        public static void main(String[] args) {
                TestDiplomate test = new TestDiplomate();
                // test.test1();
                // test.test2();
                // test.test3();
                // test.test4();
                test.test5();
        }

        public void test1() {
                System.out.println("TEST DU CONSTRUCTEUR");
                Diplomate diplomate = new Diplomate();
                Test.test(diplomate.getNom().equals("Diplomate"),
                                "test du nom du personnage");
                Test.test(diplomate.getRang() == 8, "test du rang du personnage");
                Test.test(diplomate.getCaracteristiques().equals(Caracteristiques.DIPLOMATE),
                                "test des caracteristiques du personnage");
                Test.test(diplomate.getJoueur() == null,
                                "test de l'initialisation de la variable \"joueur\"");
                Test.test(diplomate.getAssassine() == false,
                                "test de l'initialisation de la variable \"assassine\"");
                Test.test(diplomate.getVole() == false,
                                "test de l'initialisation de la variable \"vole\"");
        }

        public void test2() {
                System.out.println("TEST DE LA PERCEPTION DE RESSOURCE ET DU POUVOIR");
                PlateauDeJeu plateau = new PlateauDeJeu();
                Joueur joueur = new Joueur("Billy");
                plateau.ajouterJoueur(joueur);
                Joueur joueur2 = new Joueur("Uge");
                plateau.ajouterJoueur(joueur2);
                Diplomate diplomate = new Diplomate();
                Roi roi = new Roi();
                diplomate.setJoueur(joueur);
                roi.setJoueur(joueur2);
                diplomate.setPlateau(plateau);
                roi.setPlateau(plateau);
                Test.test(plateau.getNombreJoueurs() == 2, "nombre de joueurs");
                diplomate.ajouterPieces();
                diplomate.ajouterPieces();
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
                diplomate.getJoueur().ajouterQuartierDansCite(quartier5);
                Test.test(roi.getJoueur().nbQuartiersDansCite() == 5,
                                "nombre de quartier chez le roi avant de commencer");
                Test.test(diplomate.getJoueur().nbQuartiersDansCite() == 1,
                                "nombre de quartier chez le diplomate avant de commencer");
                Test.test(diplomate.getJoueur().nbPieces() == 4,
                                "nombre de piece de le diplomate avant de commencer");
                diplomate.percevoirRessourcesSpecifiques();
                Test.test(diplomate.getJoueur().nbPieces() == 5,
                                "nombre de piece de le diplomate apres perception des ressources");
                diplomate.utiliserPouvoir();
                if (diplomate.getJoueur().quartierPresentDansCite("temple")) {
                        Test.test(diplomate.getJoueur().nbQuartiersDansCite() == 1,
                                        "nombre de quartier chez le diplomate apres pouvoir");
                        Test.test(diplomate.getJoueur().nbPieces() == 5,
                                        "nombre de piece du diplomate apres pouvoir (vole d'un 1)");
                        Test.test(roi.getJoueur().nbQuartiersDansCite() == 5,
                                        "nombre de quartier chez le roi apres l'echange");
                        Test.test(roi.getJoueur().nbPieces() == 0,
                                        "nombre de piece du roi apres pouvoir de l'echange'");
                } else if (diplomate.getJoueur().quartierPresentDansCite("prison")) {
                        Test.test(diplomate.getJoueur().nbQuartiersDansCite() == 1,
                                        "nombre de quartier chez le diplomate apres pouvoir");
                        Test.test(diplomate.getJoueur().nbPieces() == 5,
                                        "nombre de piece du diplomate apres pouvoir (vole d'un 2)");
                        Test.test(roi.getJoueur().nbQuartiersDansCite() == 5,
                                        "nombre de quartier chez le roi apres l'echage");
                        Test.test(roi.getJoueur().nbPieces() == 0,
                                        "nombre de piece du roi apres pouvoir du diplomate");
                } else if (diplomate.getJoueur().quartierPresentDansCite("église")) {
                        Test.test(diplomate.getJoueur().nbQuartiersDansCite() == 1,
                                        "nombre de quartier chez le diplomate apres pouvoir");
                        Test.test(diplomate.getJoueur().nbPieces() == 5,
                                        "nombre de piece du diplomate apres pouvoir (vole d'un 2)");
                        Test.test(roi.getJoueur().nbQuartiersDansCite() == 5,
                                        "nombre de quartier chez le roi apres l'echage");
                        Test.test(roi.getJoueur().nbPieces() == 0,
                                        "nombre de piece du roi apres pouvoir du diplomate");
                } else {
                        Test.test(diplomate.getJoueur().nbQuartiersDansCite() == 1,
                                        "nombre de quartier chez le diplomate apres pouvoir");
                        Test.test(diplomate.getJoueur().nbPieces() == 3,
                                        "nombre de piece du diplomate apres pouvoir echange d'un 3 contre un 5");
                        Test.test(roi.getJoueur().nbQuartiersDansCite() == 5,
                                        "nombre de quartier chez le roi apres vole");
                        Test.test(roi.getJoueur().nbPieces() == 2,
                                        "nombre de piece du roi apres pouvoir du diplomate");
                } // quand on fait le test veriffier que le vole de la caserne n'est
                  // pas possible enfin l'echange ne doit pas seffectuer car ca créerais des
                  // doublons
        }

        public void test3() {
                System.out.println("TEST DU POUVOIR SANS ARGENT");
                PlateauDeJeu plateau = new PlateauDeJeu();
                Joueur joueur = new Joueur("Billy");
                plateau.ajouterJoueur(joueur);
                Joueur joueur2 = new Joueur("Uge");
                plateau.ajouterJoueur(joueur2);
                Diplomate diplomate = new Diplomate();
                Roi roi = new Roi();
                diplomate.setJoueur(joueur);
                roi.setJoueur(joueur2);
                diplomate.setPlateau(plateau);
                roi.setPlateau(plateau);
                Test.test(plateau.getNombreJoueurs() == 2, "nombre de joueurs");
                Quartier quartier1 = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
                Quartier quartier5 = new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3);
                roi.getJoueur().ajouterQuartierDansCite(quartier5);
                diplomate.getJoueur().ajouterQuartierDansCite(quartier1);
                Test.test(roi.getJoueur().nbQuartiersDansCite() == 1,
                                "nombre de quartier chez le roi avant de commencer");
                Test.test(diplomate.getJoueur().nbQuartiersDansCite() == 1,
                                "nombre de quartier chez le diplomate avant de commencer");
                Test.test(diplomate.getJoueur().nbPieces() == 0,
                                "nombre de piece du diplomate avant de commencer");
                diplomate.percevoirRessourcesSpecifiques();
                Test.test(diplomate.getJoueur().nbPieces() == 0,
                                "nombre de piece de la capitaine apres perception des ressources");
                diplomate.utiliserPouvoir();
                // verifier que je peux bien ne pas faire d'echange due à la différence de prix
                Test.test(diplomate.getJoueur().nbQuartiersDansCite() == 1,
                                "nombre de quartier chez le diplomate apres pouvoir sans or");
                Test.test(diplomate.getJoueur().nbPieces() == 0,
                                "nombre de piece du diplomate apres pouvoir sans or");
                Test.test(roi.getJoueur().nbQuartiersDansCite() == 1,
                                "nombre de quartier chez le roi apres pouvoir diplomate sans or");
        }

        public void test4() {
                System.out.println("TEST DU POUVOIR COUNTER DE EVEQUE");
                PlateauDeJeu plateau = new PlateauDeJeu();
                Joueur joueur = new Joueur("Billy");
                plateau.ajouterJoueur(joueur);
                Joueur joueur2 = new Joueur("Uge");
                plateau.ajouterJoueur(joueur2);
                Diplomate diplomate = new Diplomate();
                Eveque eveque = new Eveque();
                diplomate.setJoueur(joueur);
                eveque.setJoueur(joueur2);
                eveque.setPlateau(plateau);
                diplomate.setPlateau(plateau);
                Test.test(plateau.getNombreJoueurs() == 2, "nombre de joueurs");
                Quartier quartier1 = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
                eveque.getJoueur().ajouterQuartierDansCite(quartier1);
                Quartier quartier5 = new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3);
                diplomate.getJoueur().ajouterQuartierDansCite(quartier5);
                Test.test(eveque.getJoueur().nbQuartiersDansCite() == 1,
                                "nombre de quartier chez le eveque avant de commencer");
                Test.test(diplomate.getJoueur().nbQuartiersDansCite() == 1,
                                "nombre de quartier chez le diplomate avant de commencer");
                diplomate.ajouterPieces();
                Test.test(diplomate.getJoueur().nbPieces() == 2,
                                "nombre de piece du diplomate avant de commencer");
                diplomate.utiliserPouvoir();
                Test.test(diplomate.getJoueur().nbQuartiersDansCite() == 1,
                                "nombre de quartier chez le diplomate counter par l'eveque");
                Test.test(diplomate.getJoueur().nbPieces() == 2,
                                "nombre de piece du diplomate apres pouvoir counter par l eveque");
                Test.test(eveque.getJoueur().nbQuartiersDansCite() == 1,
                                "nombre de quartier chez l eveque apres pouvoir diplomate counter");
        }

        public void test5() {
                System.out.println("TEST DU POUVOIR SANS QUARTIER");
                PlateauDeJeu plateau = new PlateauDeJeu();
                Joueur joueur = new Joueur("Billy");
                plateau.ajouterJoueur(joueur);
                Joueur joueur2 = new Joueur("Uge");
                plateau.ajouterJoueur(joueur2);
                Diplomate diplomate = new Diplomate();
                Roi roi = new Roi();
                diplomate.setJoueur(joueur);
                roi.setJoueur(joueur2);
                roi.setPlateau(plateau);
                diplomate.setPlateau(plateau);
                Test.test(plateau.getNombreJoueurs() == 2, "nombre de joueurs");
                Quartier quartier5 = new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3);
                roi.getJoueur().ajouterQuartierDansCite(quartier5);
                diplomate.ajouterPieces();
                Test.test(roi.getJoueur().nbQuartiersDansCite() == 1,
                                "nombre de quartier chez le roi avant de commencer");
                Test.test(diplomate.getJoueur().nbQuartiersDansCite() == 0,
                                "nombre de quartier chez le diplomate avant de commencer");
                Test.test(diplomate.getJoueur().nbPieces() == 2,
                                "nombre de piece du diplomate avant de commencer");
                diplomate.percevoirRessourcesSpecifiques();
                Test.test(diplomate.getJoueur().nbPieces() == 2,
                                "nombre de piece de la capitaine apres perception des ressources");
                diplomate.utiliserPouvoir();
                // verifier que je ne peux pas utiliser le pouvoir
                Test.test(diplomate.getJoueur().nbQuartiersDansCite() == 0,
                                "nombre de quartier chez le diplomate apres pouvoir sans quartier");
                Test.test(diplomate.getJoueur().nbPieces() == 2,
                                "nombre de piece du diplomate apres pouvoir sans quartier");
                Test.test(roi.getJoueur().nbQuartiersDansCite() == 1,
                                "nombre de quartier chez le roi apres pouvoir diplomate sans quartier");
        }
}
