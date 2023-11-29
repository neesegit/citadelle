package modele;

public class Eveque extends Personnage {
    private int compteur = 0;

    public Eveque() {
        super("Eveque", 5, Caracteristiques.EVEQUE);
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
        this.getJoueur().ajouterPieces(compteur);
        System.out.println("Ajouter " + compteur + " pièce" + (compteur > 1 ? "s" : ""));
    }

    @Override
    public void utiliserPouvoir() {
        // attention pas dans son pouvoir mais dans les personnage de rang 8 il est
        // protéger
    }

    @Override
    public void utiliserPouvoirAvatar(){
        
    }
}