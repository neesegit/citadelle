package modele;

import java.util.Random;

import controleur.Interaction;

public class Cardinal extends Personnage {

    private int compteur = 0, choixJoueur;
    private boolean DejaConstruitUnTruc = false, cibleVerrouille = false;
    private PlateauDeJeu plateau;
    private Pioche pioche;

    Random random = new Random();

    public Cardinal() {
        super("Cardinal", 5, Caracteristiques.CARDINAL);
        this.plateau = getPlateau();
        if (this.plateau == null) {
            plateau = new PlateauDeJeu();
        }
        this.pioche = this.plateau.getPioche();
    }

    @Override
    public void construire(Quartier nouveau) {
        if(this.getJoueur().isBot()){
            if (!DejaConstruitUnTruc) {
                if (this.getJoueur() == null || this.getAssassine() == true) {
                    return;
                }
                if (this.getJoueur().nbPieces() < nouveau.coutConstruction) {
                    while (!cibleVerrouille) {
                        choixJoueur = random.nextInt(1, getPlateau().getNombreJoueurs() + 1);
                        if (getJoueur() != getPlateau().getJoueur(choixJoueur - 1)) {
                            cibleVerrouille = true;
                        }
                    }
                    int aBesoin = nouveau.coutConstruction - this.getJoueur().nbPieces();
                    if (aBesoin > getPlateau().getJoueur(choixJoueur - 1).nbPieces()) {
                        System.out.println("Le joueur selectionné n'as pas assez d'or !");
                        return;
                    }
                    if (this.getJoueur().nbQuartiersDansMain() < aBesoin) {
                        System.out.println("Vous n'avez pas assez de carte dans la main pour faire l'echange");
                        return;
                    }
                    getPlateau().getJoueur(choixJoueur - 1).retirerPieces(aBesoin);
                    this.getJoueur().ajouterPieces(aBesoin);
                    for (int i = 0; i < aBesoin; i++) {
                        Quartier transfert = this.getJoueur().retirerQuartierDansMain();
                        getPlateau().getJoueur(choixJoueur - 1).ajouterQuartierDansMain(transfert);
                    }
                }
                this.getJoueur().ajouterQuartierDansCite(nouveau);
                this.getJoueur().retirerPieces(nouveau.coutConstruction);
                DejaConstruitUnTruc = true;
            }
            System.out.println("Vous construit un quartier dans ce tour");
        } else {
            if (!DejaConstruitUnTruc) {
                if (this.getJoueur() == null || this.getAssassine() == true) {
                    return;
                }
                if (this.getJoueur().nbPieces() < nouveau.coutConstruction) {
                    for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
                        System.out.println(i + 1 + " " + getPlateau().getJoueur(i).getNom() + " ("
                                + getPlateau().getJoueur(i).getPersonnage().getNom() + " "
                                + getPlateau().getJoueur(i).nbPieces() + " or"
                                + (getPlateau().getJoueur(i).nbPieces() > 1 ? "s" : "") + ")");
                    }
                    while (!cibleVerrouille) {
                        System.out.print("Choisissez un joueur autre que vous (le chiffre devant): ");
                        choixJoueur = Interaction.lireUnEntier(1, getPlateau().getNombreJoueurs() + 1);
                        if (getJoueur() != getPlateau().getJoueur(choixJoueur - 1)) {
                            cibleVerrouille = true;
                        }
                    }
                    int aBesoin = nouveau.coutConstruction - this.getJoueur().nbPieces();
                    System.out.println(getPlateau().getJoueur(choixJoueur - 1).nbPieces());
                    System.out.println(aBesoin);
                    if (aBesoin > getPlateau().getJoueur(choixJoueur - 1).nbPieces()) {
                        System.out.println("Le joueur selectionné n'as pas assez d'or !");
                        return;
                    }
                    if (this.getJoueur().nbQuartiersDansMain() < aBesoin) {
                        System.out.println("Vous n'avez pas assez de carte dans la main pour faire l'echange");
                        return;
                    }
                    getPlateau().getJoueur(choixJoueur - 1).retirerPieces(aBesoin);
                    this.getJoueur().ajouterPieces(aBesoin);
                    for (int i = 0; i < aBesoin; i++) {
                        Quartier transfert = this.getJoueur().retirerQuartierDansMain();
                        getPlateau().getJoueur(choixJoueur - 1).ajouterQuartierDansMain(transfert);
                    }
                }
                this.getJoueur().ajouterQuartierDansCite(nouveau);
                this.getJoueur().retirerPieces(nouveau.coutConstruction);
                DejaConstruitUnTruc = true;
            }
            System.out.println("Vous construit un quartier dans ce tour");
        }
    }

    @Override
    public void percevoirRessourcesSpecifiques() {

        if (getJoueur().quartierPresentDansCite("temple")) {
            compteur++;
        }
        if (getJoueur().quartierPresentDansCite("église")) {
            compteur++;
        }
        if (getJoueur().quartierPresentDansCite("monastère")) {
            compteur++;
        }
        if (getJoueur().quartierPresentDansCite("cathédrale")) {
            compteur++;
        }
        for (int i = 0; i < compteur; i++) {
            this.getJoueur().ajouterQuartierDansMain(getPlateau().getPioche().piocher());
        }
        System.out.println("Piocher " + compteur + " carte" + (compteur > 1 ? "s" : ""));
    }

    @Override
    public void utiliserPouvoir() {
        System.out.println("Ce personnage n'a pas de pouvoir particulier");
    }

    @Override
    public void utiliserPouvoirAvatar() {}
}
