package modele;

public class Eveque extends Personnage {
    private int compteur = 0;

    public Eveque() {
        super("Eveque", 5, Caracteristiques.EVEQUE);
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        if (this.getJoueur().quartierPresentDansCite("temple")) {
            compteur++;
        }
        if (this.getJoueur().quartierPresentDansCite("église")) {
            compteur++;
        }
        if (this.getJoueur().quartierPresentDansCite("monastère")) {
            compteur++;
        }
        if (this.getJoueur().quartierPresentDansCite("cathédrale")) {
            compteur++;
        }
        this.getJoueur().ajouterPieces(compteur);
        System.out.println("Ajouter " + compteur + " pièce" + (compteur > 1 ? "s" : ""));
    }

    @Override
    public void utiliserPouvoir() {
        // attention pas dans son pouvoir mais dans les personnage de rang 8 il est
        // protéger
    }

    @Override
    public void utiliserPouvoirAvatar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'utiliserPouvoirAvatar'");
    }
}