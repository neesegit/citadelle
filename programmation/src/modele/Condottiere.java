package modele;

import controleur.Interaction;

public class Condottiere extends Personnage {
    private int compteur = 0;
    private Personnage cible;
    private int quartierADetruire;

    public Condottiere() {
        super("Condottiere", 8, Caracteristiques.EVEQUE);
    }

    @Override
    public void utiliserPouvoir() {
        System.out.println("voulez vous utiliser votre pouvoir de destruction ?");
        boolean reponse = Interaction.lireOuiOuNon();
        if (reponse) {
            boolean choix = false;
            while (!choix) {
                System.out.println("Voici la liste des joueurs et du contenu de leur cité : ");
                for (int i = 0; i < getPlateau().getNombrePersonnages(); i++) {
                    System.out.println(i + 1 + " " + getPlateau().getPersonnage(i).getNom() + ": ");
                    for (int a = 0; a < getPlateau().getJoueur(i).nbQuartiersDansCite(); a++) {
                        System.out.println((a + 1) + " " + getPlateau().getJoueur(i).getCite()[a].getNom() + " ( coût "
                                + (getPlateau().getJoueur(i).getCite()[a].coutConstruction - 1) + " ) ,");
                    }
                    System.out.println("\n");
                }
                System.out.println("Pour information vous avez " + getJoueur().nbPieces() + " pièce"
                        + (getJoueur().nbPieces() > 1 ? "s" : "") + " dans votre trésor");
                System.out.println("Quel joueur choisissez-vous? (0 pour ne rien faire) : ");
                int id = Interaction.lireUnEntier(0, getPlateau().getNombrePersonnages() + 1);
                if (id == 0) {
                    choix = true;
                } else {
                    cible = getPlateau().getPersonnage(id - 1);
                    System.out.println("Quel quartier choisissez_vous ? ");
                    quartierADetruire = Interaction.lireUnEntier(1, cible.getJoueur().nbQuartiersDansCite() + 1);
                    if (cible.getJoueur().getCite()[quartierADetruire - 1].coutConstruction - 1 > getJoueur()
                            .nbPieces()) {
                        System.out.println("Votre trésor n’est pas suffisant");
                    } else {
                        choix = true;
                        cible.getJoueur()
                                .retirerQuartierDansCite(cible.getJoueur().getCite()[quartierADetruire - 1].getNom());
                        getJoueur()
                                .retirerPieces(cible.getJoueur().getCite()[quartierADetruire - 1].coutConstruction - 1);
                    }
                }
            }
        }
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        if (getJoueur().quartierPresentDansCite("tour de guet")) {
            compteur++;
        }
        if (getJoueur().quartierPresentDansCite("prison")) {
            compteur++;
        }
        if (getJoueur().quartierPresentDansCite("caserne")) {
            compteur++;
        }
        if (getJoueur().quartierPresentDansCite("forteresse")) {
            compteur++;
        }
        this.getJoueur().ajouterPieces(compteur);
        System.out.println("Ajouter " + compteur + " pièce" + (compteur > 1 ? "s" : ""));
    }
}