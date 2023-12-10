package modele;

public class Roi extends Personnage {
    private int compteur = 0;

    public Roi() {
        super("Roi", 4, Caracteristiques.ROI);

    }

    @Override
    public void utiliserPouvoir() {
        System.out.println("Je prends la couronne");
        if (getJoueur() != null) {
            this.getJoueur().setPossedeCouronne(true);
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {
        System.out.println("Je prends la couronne");
        if (getJoueur() != null) {
            this.getJoueur().setPossedeCouronne(true);
        }
    }

    public void percevoirRessourcesSpecifiques() {
        if (this.getJoueur() == null) {
            System.out.println("Ce joueur n'est pas roi");
            return;
        }
        // il y a déja une boucle for dans quartierPresentDansCite
        // le nom des quartiers c'est pas le type attention donc on fait un ou avec les
        // trois quartiers noble
        if (this.getJoueur().quartierPresentDansCite("manoir")) {
            compteur++;
        }
        if (this.getJoueur().quartierPresentDansCite("château")) {
            compteur++;
        }
        if (this.getJoueur().quartierPresentDansCite("palais")) {
            compteur++;
        }
        this.getJoueur().ajouterPieces(compteur);
        System.out.println("Ajouter " + compteur + " pièce" + (compteur > 1 ? "s" : ""));
    }
}