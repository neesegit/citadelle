package modele;

import controleur.Interaction;
import java.util.ArrayList;

public class Magicienne extends Personnage {

    private boolean echange = false, choix = false;
    private Personnage cible;

    public Magicienne() {
        super("Magicienne", 3, Caracteristiques.MAGICIENNE);
    }

    @Override
    public void utiliserPouvoir() {
        if (getJoueur().getMain().isEmpty()) { // acune idée de comment dire que la main est vide, je crois que ça
                                               // marche ça
            System.out.println("Vous n'avez pas de cartes donc vous ne pouvez rien faire");
        } else {
            while (!choix) {
                System.out.println("Voulez-vous echanger vos cartes avec celles d’un autre joueur ?");
                boolean reponse = Interaction.lireOuiOuNon();
                if (reponse) {
                    choix = true;
                    System.out.println("Avec quel personnage voulez-vous echanger vos cartes ?");
                    for (int i = 0; i < getPlateau().getNombrePersonnages(); i++) {// TODO : faire que le joueur ne soit
                                                                                   // pas visible et aussi que les
                                                                                   // chiffres moches ne soit plus là
                        System.out.println(i + 1 + " " + getPlateau().getPersonnage(i).getNom() + " "
                                + getJoueur().nbQuartiersDansMain());// c'est le chiffre là
                    }
                    while (!echange) {
                        System.out.println("Votre choix : ");
                        int id = Interaction.lireUnEntier(1, getPlateau().getNombrePersonnages() + 1);
                        cible = getPlateau().getPersonnage(id - 1);
                        if (cible.getNom() == "Magicienne") {// je sais pas s'il y a des limite de rang a son pouvoir
                            System.out.println("Vous ne pouvez pas choisir ce personnage");
                        } else {
                            echange = true;
                        }
                    }
                    // mtn il faut que je copie la main du joueur et de la magicienne
                    ArrayList<Quartier> mainCopieMagicienne = (ArrayList<Quartier>) getJoueur().getMain().clone();
                    ArrayList<Quartier> joueurMainCopie = (ArrayList<Quartier>) cible.getJoueur().getMain().clone();
                    // on vide a main de la magicienne et de la cible
                    for (int i = 0; i <= getJoueur().nbQuartiersDansMain(); i++) {
                        getJoueur().retirerQuartierDansMain();
                    }
                    for (int i = 0; i <= cible.getJoueur().nbQuartiersDansMain() + 1; i++) {
                        cible.getJoueur().retirerQuartierDansMain();
                    }
                    // on réatribut les mains
                    for (int i = 0; i < mainCopieMagicienne.size(); i++) {
                        cible.getJoueur().ajouterQuartierDansMain(mainCopieMagicienne.get(i));
                    }
                    for (int i = 0; i < joueurMainCopie.size(); i++) {
                        getJoueur().ajouterQuartierDansMain(joueurMainCopie.get(i));
                    }
                } else {
                    choix = true;
                    System.out.println("Combien de cartes voulez vous échanger ? \n Le chiffre doit être inférieur à "
                            + getJoueur().nbQuartiersDansMain() + 1);
                    int reponseNbCarte = Interaction.lireUnEntier();
                    if (reponseNbCarte == getJoueur().nbQuartiersDansMain()) {
                        // comme c'est son nombre de carte on bazarde tout
                        // on vide a main de la magicienne
                        for (int i = 0; i < reponseNbCarte; i++) { // mais du coup il choisi pas là c'est du random
                            getPlateau().getPioche().ajouter(getJoueur().retirerQuartierDansMain());
                        }
                        // on réatribut les mains
                        for (int i = 0; i < reponseNbCarte; i++) {
                            getJoueur().ajouterQuartierDansMain(getPlateau().getPioche().piocher());
                        }
                    } else {
                        // ici il choisi
                        ArrayList<Quartier> mainCopieMagicienne = getJoueur().getMain();// main de la
                        // créer la copie avec nouvelle carte
                        for (int i = 0; i < reponseNbCarte; i++) {
                            System.out.println(
                                    mainCopieMagicienne + "\nQuelle carte voulez vous échanger (indiquez son rang)");
                            int index = Interaction.lireUnEntier();
                            getPlateau().getPioche().ajouter(mainCopieMagicienne.get(index));
                            mainCopieMagicienne.remove(index);
                        }
                        for (int i = 0; i < reponseNbCarte; i++) {
                            mainCopieMagicienne.add(getPlateau().getPioche().piocher());
                        }
                        // vide la main
                        for (int i = 0; i < mainCopieMagicienne.size(); i++) {
                            getJoueur().retirerQuartierDansMain();
                        }
                        // remplie la main
                        for (int i = 0; i < mainCopieMagicienne.size(); i++) {
                            getJoueur().ajouterQuartierDansMain(mainCopieMagicienne.get(i));
                        }

                    }
                }
            }
        }
    }
}