package modele;

import java.util.Random;

import controleur.Interaction;

public class Diplomate extends Personnage {

    private String[] militaire = { "tour de guet", "prison", "caserne", "forteresse" };
    private int vide;

    private Quartier[] quartierJoueur;
    private Quartier[] quartierSecondJoueur;

    private Quartier quartierJoueurTemp;
    private Quartier quartierSecondJoueurTemp;

    private Joueur cible;

    private boolean choixON = false;

    Random random = new Random();

    public Diplomate() {
        super("Diplomate", 8, Caracteristiques.DIPLOMATE);
        this.quartierJoueur = getJoueur().getCite();

    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        // 1 piece d'or pour chaque quartier noble dans sa cite
        for (int i = 0; i < militaire.length; i++) {
            if (this.getJoueur().quartierPresentDansCite(militaire[i]))
                this.getJoueur().ajouterPieces(1);
        }
    }

    @Override
    public void utiliserPouvoir() {
        this.quartierJoueur = this.getJoueur().getCite();
        System.out.println("Voulez-vous echanger un de vos quartiers batis avec le quartier d'un autre joueur ? ");
        choixON = Interaction.lireOuiOuNon();

        if (choixON) {
            // echange un de ses quartiers batis avec le quartier adverse
            System.out.println("Veuillez selectionner un joueur : ");
            for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
                System.out.println(i + 1 + " " + getPlateau().getJoueur(i).getNom() + " "
                        + getPlateau().getJoueur(i).getPersonnage().getNom() + " quartier"
                        + (getPlateau().getJoueur(i).nbQuartiersDansCite() > 1 ? "s"
                                : "" + " dans la cite : " + getPlateau().getJoueur(i).nbQuartiersDansCite()));
            }
            boolean choixfait = false;
            int idQuartierSecond = 0, idQuartier = 0;
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
            for (int i = 0; i < this.quartierJoueur.length; i++) {
                if (quartierJoueur[i] == null) {
                    vide++;
                }
            }
            if (vide == this.quartierJoueur.length) {
                System.out.println("Votre cite est vide vous ne pouvez donc pas faire d'echange");
                return;
            }
            quartierSecondJoueur = cible.getCite();
            System.out.println("Quel quartier voulez vous retirer de votre cité ?");
            for (int i = 0; i < this.getJoueur().nbQuartiersDansCite(); i++)
                System.out.println(i + 1 + " "
                        + this.quartierJoueur[i].getNom());
            choixfait = false;
            while (!choixfait) {
                System.out.println("Votre choix : ");
                idQuartier = Interaction.lireUnEntier(1, this.getJoueur().nbQuartiersDansCite() + 1) - 1;
                if (quartierJoueur[idQuartier] != null) {
                    choixfait = true;
                }
            }
            vide = 0;
            for (int i = 0; i < this.quartierSecondJoueur.length; i++) {
                if (quartierSecondJoueur[i] == null) {
                    vide++;
                }
            }
            if (vide == this.quartierSecondJoueur.length) {
                System.out.println("La cit de votre cible est vide vous ne pouvez donc pas faire d'echange");
                return;
            }
            System.out.println("Quel quartier voulez vous retirer de la cité de l'autre joueur ?");
            for (int i = 0; i < cible.nbQuartiersDansCite(); i++)
                System.out.println(i + 1 + " " + this.quartierSecondJoueur[i].getNom());
            choixfait = false;
            while (!choixfait) {
                System.out.println("Votre choix (non null): ");
                idQuartierSecond = Interaction.lireUnEntier(1, cible.nbQuartiersDansCite() + 1) - 1;
                if (quartierSecondJoueur[idQuartierSecond] != null) {
                    choixfait = true;
                }
            }
            if (this.getJoueur().nbPieces() == 0) {
                if (quartierSecondJoueur[idQuartierSecond].getCout() - quartierJoueur[idQuartier].getCout() == 0) {
                    this.getJoueur().ajouterQuartierDansCite(
                            cible.retirerQuartierDansCite(quartierSecondJoueur[idQuartierSecond].getNom()));
                    cible.ajouterQuartier(
                            this.getJoueur().retirerQuartierDansCite(this.quartierJoueur[idQuartier].getNom()));
                }
                return;
            }
            if (quartierSecondJoueur[idQuartierSecond].getCout() - quartierJoueur[idQuartier].getCout() > 0) {
                this.getJoueur().retirerPieces(
                        quartierSecondJoueur[idQuartierSecond].getCout() - quartierJoueur[idQuartier].getCout());
                cible.ajouterPieces();
            }
            this.getJoueur().ajouterQuartierDansCite(
                    cible.retirerQuartierDansCite(quartierSecondJoueur[idQuartierSecond].getNom()));
            cible.ajouterQuartier(
                    this.getJoueur().retirerQuartierDansCite(this.quartierJoueur[idQuartier].getNom()));
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {

        if (random.nextBoolean()) {
            // echange un de ses quartiers batis avec le quartier adverse

            int id = random.nextInt(getPlateau().getNombrePersonnages()) + 1;
            cible = getPlateau().getJoueur(id - 1);

            while (cible.getNom() == "Diplomate") {
                System.out.println("Vous ne pouvez pas choisir ce personnage");
                id = random.nextInt(getPlateau().getNombrePersonnages()) + 1;
                cible = getPlateau().getJoueur(id - 1);
            }

            quartierSecondJoueur = cible.getCite();

            int idQuartier = random.nextInt(this.quartierJoueur.length);

            int idQuartierSecond = random.nextInt(quartierSecondJoueur.length);

            quartierJoueurTemp = this.getJoueur().retirerQuartierDansCite(this.quartierJoueur[idQuartier].getNom());
            quartierSecondJoueurTemp = cible
                    .retirerQuartierDansCite(quartierSecondJoueur[idQuartierSecond].getNom());

            this.getJoueur().ajouterQuartierDansCite(quartierSecondJoueurTemp);
            cible.ajouterQuartierDansCite(quartierJoueurTemp);
        }

    }

}
