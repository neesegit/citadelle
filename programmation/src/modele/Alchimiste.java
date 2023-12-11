package modele;

public class Alchimiste extends Personnage {

    public Alchimiste() {
        super("Alchimiste", 6, Caracteristiques.ALCHIMISTE);
    }

    @Override
    public void construire(Quartier nouveau) {
        if (this.getDejaConstruit()) {
            if (this.getJoueur() == null || this.getAssassine() == true) {
                return;
            }
            if (this.getJoueur().nbPieces() < nouveau.coutConstruction) {
                System.out.println("Vous n'avez pas  d'or pour construire ce quartier");
                return;
            }
            this.getJoueur().ajouterQuartierDansCite(nouveau);
            this.setDejaConstruit(false);
        }
        System.out.println("Vous construit un quartier dans ce tour");
    }

    @Override
    public void utiliserPouvoir() {
    }

    @Override
    public void utiliserPouvoirAvatar() {
    }

}