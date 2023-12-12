package modele;

import java.util.Random;

import controleur.Interaction;

public class Empereur extends Personnage {

    private String[] noble = { "manoir", "château", "palais" };

    private PlateauDeJeu plateau;
    private Joueur cible;
    private String choix;

    private boolean choixBot = true;

    private Random random = new Random();

    public Empereur() {
        super("Empereur", 4, Caracteristiques.EMPEREUR);
        this.plateau = getPlateau();
        if (this.plateau == null)
            plateau = new PlateauDeJeu();
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        // 1 piece d'or pour chaque quartier noble dans sa cite
        for (int i = 0; i < noble.length; i++) {
            if (this.getJoueur().quartierPresentDansCite(noble[i]))
                this.getJoueur().ajouterPieces(1);
        }
    }

    @Override
    public void utiliserPouvoir() {
        // Donne couronne contre 1 pièce ou 1 carte
        System.out.println("A quel personnage voulez-vous donner la couronne ?");
        for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
            System.out.println(i + 1 + " " + getPlateau().getJoueur(i).getNom() + " ("
                    + getPlateau().getJoueur(i).getPersonnage().getNom() + ") piece"
                    + (getPlateau().getJoueur(i).nbPieces() > 1 ? "s" : "") + " :"
                    + getPlateau().getJoueur(i).nbPieces() + " nombre de carte"
                    + (getPlateau().getJoueur(i).nbQuartiersDansMain() > 1 ? "s" : "") + " :"
                    + getPlateau().getJoueur(i).nbQuartiersDansMain());
        }
        boolean pasmoi = true;
        while (pasmoi) {
            System.out.println("Votre choix (autre que vous et qui n'as pas deja la courronne): ");
            int id = Interaction.lireUnEntier(1, getPlateau().getNombreJoueurs() + 1);
            cible = getPlateau().getJoueur(id - 1);
            if (cible != this.getJoueur() && !(cible.getPossedeCouronne())) {
                pasmoi = false;
            }
        }
        for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
            if (getPlateau().getJoueur(i).getPossedeCouronne()) {
                getPlateau().getJoueur(i).setPossedeCouronne(false);
            }
        }
        cible.setPossedeCouronne(true);
        boolean reponse = false;
        while (!reponse) {
            System.out.print("Voulez vous une (p)ièce ou une (c)arte ? ");
            choix = Interaction.lireUneChaine();
            if (choix.equals("p")) {
                reponse = true;
                if (cible.nbPieces() > 0) {
                    this.getJoueur().ajouterPieces(1);
                    cible.retirerPieces(1);
                } else {
                    System.out.println("La cible n'a pas de piece");
                }
            } else if (choix.equals("c")) {
                reponse = true;
                if (cible.nbQuartiersDansMain() > 0) {
                    this.getJoueur().ajouterQuartierDansMain(cible.retirerQuartierDansMain());
                } else {
                    System.out.println("La cible n'a pas de carte quartier");
                }
            }
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {
        // pareil mais avec un bot

        while (choixBot) {
            int id = random.nextInt(this.plateau.getNombrePersonnages()) + 1;
            cible = this.plateau.getJoueur(id - 1);
            if (cible.getPersonnage().getNom() != "Empereur")
                choixBot = false;
        }

        this.getJoueur().setPossedeCouronne(false);
        cible.setPossedeCouronne(true);

        boolean choixPCBot = random.nextBoolean();
        if (choixPCBot) {
            this.getJoueur().ajouterPieces(1);
            cible.retirerPieces(1);
        } else {
            this.getJoueur().ajouterQuartierDansMain(cible.retirerQuartierDansMain());
        }
    }
}
