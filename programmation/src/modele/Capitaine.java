package modele;

import java.util.Random;

import controleur.Interaction;

public class Capitaine extends Personnage {

    private String[] militaire = { "tour de guet", "prison", "caserne", "forteresse" };

    private Quartier[] quartierSecondJoueur;
    private Quartier[] moinsEgaleDeTrois;

    private Quartier choixQuartier;

    private boolean choixON;
    private int vide;
    private Joueur cible;

    Random random = new Random();

    public Capitaine() {
        super("Capitaine", 8, Caracteristiques.CAPITAINE);
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        for (int i = 0; i < militaire.length; i++) {
            if (this.getJoueur().quartierPresentDansCite(militaire[i]))
                this.getJoueur().ajouterPieces(1);
        }
    }

    @Override
    public void utiliserPouvoir() {

        if (this.getJoueur().nbPieces() == 0) {
            System.out.println("Vous ne pouvez pas utiliser votre pouvoir");
            return;
        }

        System.out.println("Voulez-vous confisquer un quartier à un joueur ? ");
        choixON = Interaction.lireOuiOuNon();

        if (choixON) {
            // echange un de ses quartiers batis avec le quartier adverse
            System.out.println("Veuillez selectionner un joueur : ");
            for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
                System.out.println(i + 1 + " " + getPlateau().getJoueur(i).getNom() + " "
                        + getPlateau().getJoueur(i).getPersonnage().getNom() + " quartier"
                        + (getPlateau().getJoueur(i).nbQuartiersDansCite() > 1 ? "s" : "") + " dans la cite : "
                        + getPlateau().getJoueur(i).nbQuartiersDansCite());
            }
            boolean choixfait = false;
            int idQuartierSecond = 0;
            while (!choixfait) {
                System.out.println("Votre choix (autre que vous) : ");
                int id = Interaction.lireUnEntier(1, getPlateau().getNombreJoueurs() + 1);
                cible = getPlateau().getJoueur(id - 1);

                if (cible != this.getJoueur()) {
                    choixfait = true;
                }
            }

            if (cible.getPersonnage().getNom() == "Eveque") {
                System.out.println("Vous ne pouvez pas attaquer l'Eveque");
                return;
            }
            quartierSecondJoueur = cible.getCite();

            vide = 0;
            for (int i = 0; i < this.quartierSecondJoueur.length; i++) {
                if (quartierSecondJoueur[i] == null) {
                    vide++;
                }
            }
            if (vide == this.quartierSecondJoueur.length) {
                System.out.println("La cite de votre cible est vide vous ne pouvez donc pas faire d'echange");
                return;
            }
            System.out.println("Quel quartier voulez vous retirer de la cité de l'autre joueur ?");
            for (int i = 0; i < cible.nbQuartiersDansCite(); i++)
                System.out.println(i + 1 + " " + quartierSecondJoueur[i].getNom() + " cout de construction "
                        + quartierSecondJoueur[i].getCout());
            choixfait = false;
            while (!choixfait) {
                System.out.println("Votre choix (ne doit pas dépasser 3 de coup de construction): ");
                idQuartierSecond = Interaction.lireUnEntier(1, cible.nbQuartiersDansCite() + 1) - 1;
                if (quartierSecondJoueur[idQuartierSecond] != null
                        && quartierSecondJoueur[idQuartierSecond].getCout() <= 3) {
                    choixfait = true;
                }
            }
            if (this.getJoueur().nbPieces() == 0
                    && this.getJoueur().nbPieces() - quartierSecondJoueur[idQuartierSecond].getCout() >= 0) {
                System.out.println("Vous n'avez pas assez d'argent");
                return;
            }
            this.getJoueur().retirerPieces(quartierSecondJoueur[idQuartierSecond].getCout());
            cible.ajouterPieces(quartierSecondJoueur[idQuartierSecond].getCout());
            this.getJoueur().ajouterQuartierDansCite(
                    cible.retirerQuartierDansCite(quartierSecondJoueur[idQuartierSecond].getNom()));
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {

        if (this.getJoueur().nbPieces() == 0) {
            System.out.println("Vous ne pouvez pas utiliser votre pouvoir");
            return;
        }

        if (random.nextBoolean()) {

            int id = random.nextInt(getPlateau().getNombrePersonnages()) + 1;
            cible = getPlateau().getJoueur(id - 1);

            while (cible.getNom() == "Capitaine") {
                id = random.nextInt(1, getPlateau().getNombrePersonnages() + 1);
                cible = getPlateau().getJoueur(id - 1);
            }

            quartierSecondJoueur = cible.getCite();

            for (int i = 0; i < quartierSecondJoueur.length; i++) {
                if (quartierSecondJoueur[i].getCout() <= 3)
                    moinsEgaleDeTrois[i] = quartierSecondJoueur[i];
            }

            int idChoixQuartier = random.nextInt(1, moinsEgaleDeTrois.length);

            choixQuartier = moinsEgaleDeTrois[idChoixQuartier - 1];

            while (this.getJoueur().nbPieces() < choixQuartier.getCout()) {
                idChoixQuartier = random.nextInt(1, moinsEgaleDeTrois.length);

                choixQuartier = moinsEgaleDeTrois[idChoixQuartier - 1];
            }

            this.getJoueur().ajouterQuartierDansMain(choixQuartier);
            this.getJoueur().retirerPieces(choixQuartier.getCout());
            cible.ajouterPieces(choixQuartier.getCout());
        }
    }

}
