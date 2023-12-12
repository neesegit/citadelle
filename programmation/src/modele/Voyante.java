package modele;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;

public class Voyante extends Personnage {

    private ArrayList<Joueur> joueur = new ArrayList<Joueur>();

    private Joueur cible;

    private int permisDeConstruire = 2;
    private boolean choixON = false;

    Random random = new Random();

    public Voyante() {
        super("Voyante", 3, Caracteristiques.VOYANTE);
    }

    @Override
    public void construire(Quartier nouveau) {
        if (this.getJoueur().isBot()) {
            while (permisDeConstruire > 0) {
                if (this.getJoueur() == null || this.getAssassine() == true) {
                    return;
                }
                if (this.getJoueur().nbPieces() < nouveau.coutConstruction) {
                    System.out.println("Vous n'avez pas  d'or pour construire ce quartier");
                    permisDeConstruire--;
                    continue;
                }
                this.getJoueur().ajouterQuartierDansCite(nouveau);
                this.getJoueur().retirerPieces(nouveau.coutConstruction);
                permisDeConstruire--;
                if (permisDeConstruire > 0)
                    choixON = random.nextBoolean();
                if (choixON) {
                    nouveau = this.getJoueur().retirerQuartierDansMain();
                    choixON = false;
                }
            }
        } else {
            while (permisDeConstruire > 0) {
                if (this.getJoueur() == null || this.getAssassine() == true) {
                    return;
                }
                if (this.getJoueur().nbPieces() < nouveau.coutConstruction) {
                    System.out.println("Vous n'avez pas  d'or pour construire ce quartier");
                    permisDeConstruire--;
                    continue;
                }
                this.getJoueur().ajouterQuartierDansCite(nouveau);
                this.getJoueur().retirerPieces(nouveau.coutConstruction);
                permisDeConstruire--;
                if (permisDeConstruire > 0) {
                    System.out.println("Voulez vous construire à nouveau (reste " + permisDeConstruire
                            + " nombre de construction) ? ");
                    System.out.print("Veuillez répondre par oui ou par non : ");
                    choixON = Interaction.lireOuiOuNon();
                }
                if (choixON) {
                    nouveau = this.getJoueur().retirerQuartierDansMain();
                    choixON = false;
                }
            }
        }
    }

    @Override
    public void utiliserPouvoir() {
        ArrayList<Quartier> CopieCartesSelectionne = new ArrayList<Quartier>();
        for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
            if (getPlateau().getJoueur(i).nbQuartiersDansMain() != 0
                    && getPlateau().getJoueur(i).getPersonnage().getNom() != "Voyante") {
                Quartier CarteDelete = getPlateau().getJoueur(i).retirerQuartierDansMain();
                CopieCartesSelectionne.add(CarteDelete);
                joueur.add(getPlateau().getJoueur(i));
            }
        }
        for (int a = 0; a < joueur.size(); a++) {
            this.getJoueur().ajouterQuartierDansMain(CopieCartesSelectionne.get(a));
            System.out.println("carte recupere : " + CopieCartesSelectionne.get(a).getNom());
        }
        while (joueur.size() != 0) {
            System.out.println(
                    "Vous devez redonner une carte quartier de votre choix a chaque joueur qui vous a donné une carte, c'est a dire "
                            + joueur.size() + " carte" + (joueur.size() > 1 ? "s" : ""));

            System.out.println("Avec quel personnage voulez-vous donner votre carte ?");
            for (int b = 0; b < joueur.size(); b++) {
                System.out.println(b + 1 + " " + joueur.get(b).getPersonnage().getNom());
            }

            System.out.print("Votre choix : ");
            int id = Interaction.lireUnEntier(1, joueur.size() + 1) - 1;
            cible = joueur.get(id);

            System.out.println("Choisissez un quartier à donner : ");
            for (int c = 0; c < this.getJoueur().nbQuartiersDansMain(); c++) {
                System.out.println(c + 1 + " " + this.getJoueur().getMain().get(c).getNom());
            }

            System.out.print("Votre choix : ");
            int idQuartier = Interaction.lireUnEntier(1, this.getJoueur().nbQuartiersDansMain() + 1) - 1;
            cible.ajouterQuartierDansMain(this.getJoueur().getMain().get(idQuartier));
            this.getJoueur().getMain().remove(idQuartier);
            joueur.remove(id);
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {
        ArrayList<Quartier> CopieCartesSelectionne = new ArrayList<Quartier>();
        for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
            if (getPlateau().getJoueur(i).nbQuartiersDansMain() != 0) {
                Quartier CarteDelete = getPlateau().getJoueur(i).retirerQuartierDansMain();
                CopieCartesSelectionne.add(CarteDelete);
                joueur.add(getPlateau().getJoueur(i));
            }
        }

        for (int i = 0; i < joueur.size(); i++) {
            this.getJoueur().ajouterQuartierDansMain(CopieCartesSelectionne.get(i));
        }
        while (joueur.size() != 0) {

            int id = random.nextInt(1, joueur.size());
            cible = getPlateau().getJoueur(id - 1);

            int idQuartier = random.nextInt(1, CopieCartesSelectionne.size());
            cible.ajouterQuartierDansMain(CopieCartesSelectionne.get(idQuartier - 1));

            CopieCartesSelectionne.remove(idQuartier - 1);
            joueur.remove(id - 1);
        }
    }
}
