package modele;

import java.util.ArrayList;

import controleur.Interaction;

public class Voyante extends Personnage {

    private boolean choix = false;

    public Voyante() {
        super("Voyante", 3, Caracteristiques.VOYANTE);
    }

    @Override
    public void utiliserPouvoir() {
        if (getJoueur().getMain().isEmpty()) {
            System.out.println("Vous n'avez pas de cartes donc vous ne pouvez rien faire");
        } else {
            // faire une copie de la carte aléatoire des joueurs
            ArrayList<Quartier> CopieCartesSelectionne = new ArrayList<Quartier>();
            for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
                if (getPlateau().getJoueur(i).nbQuartiersDansMain() != 0) {
                    Quartier CarteDelete = getPlateau().getJoueur(i).retirerQuartierDansMain();
                    CopieCartesSelectionne.add(CarteDelete);
                }
            }
            int NbCarteDonné = CopieCartesSelectionne.size();
            for (int i = 0; i < NbCarteDonné; i++) {
                getJoueur().ajouterQuartierDansMain(CopieCartesSelectionne.get(i));
            }
            /* 
            for (int i; i < NbCarteDonné; i++) {
                while (!choix) {
                    System.out.println(
                            "Vous devez redonner une carte quartier de votre choix a chaque personne qui vous a donné une carte");
                    System.out.println("Avec quel personnage voulez-vous donner votre cartes ?");
                    for (int i = 0; i < getPlateau().getNombrePersonnages(); i++) {
                        System.out.println(i + 1 + " " + getPlateau().getPersonnage(i).getNom());
                    }
                    while (!echange) {

                    }
                }
            }
            */

            // on redistribue une carte a chaque joueur que a participer
        }
    }
}
