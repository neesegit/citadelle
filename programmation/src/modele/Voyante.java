package modele;

import java.util.ArrayList;
import java.util.Random;

public class Voyante extends Personnage {

    private int placementCarte;

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
                Random random = new Random();
                placementCarte = random.nextInt(getPlateau().getJoueur(i).nbQuartiersDansMain() + 1);
                // la il faut que je copie la placement de ma carte dans la copie que je
                // l'ajoute quoi
                getPlateau().getPersonnage(i);
            }
            // on supprie la carte de la main des joueurs

            // on lajoute a la voyante

            // on redistribue une carte a chaque joueur que a participer
        }
    }
}
