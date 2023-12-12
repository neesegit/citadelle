package modele;

public class Marchande extends Personnage {
    private int compteur = 0;

    public Marchande() {
        super("Marchande", 6, Caracteristiques.MARCHANDE);
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        if (getJoueur().quartierPresentDansCite("taverne")) {
            compteur++;
        }
        if (getJoueur().quartierPresentDansCite("échoppe")) {
            compteur++;
        }
        if (getJoueur().quartierPresentDansCite("marché")) {
            compteur++;
        }
        if (getJoueur().quartierPresentDansCite("comptoir")) {
            compteur++;
        }
        if (getJoueur().quartierPresentDansCite("port")) {
            compteur++;
        }
        if (getJoueur().quartierPresentDansCite("hôtel de ville")) {
            compteur++;
        }
        this.getJoueur().ajouterPieces(compteur);
        System.out.println("Ajouter " + compteur + " pièce" + (compteur > 1 ? "s" : ""));
    }

    @Override
    public void utiliserPouvoir() {
        this.getJoueur().ajouterPieces(1);
        System.out.println("Ajouter 1 pièce");
    }

    @Override
    public void utiliserPouvoirAvatar() {
        this.getJoueur().ajouterPieces(1);
        System.out.println("Ajouter 1 pièce");
    }
}
