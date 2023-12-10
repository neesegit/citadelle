package modele;

public class Architecte extends Personnage {

    public Architecte() {
        super("Architecte", 6, Caracteristiques.ARCHITECTE);
    }

    @Override
    public void utiliserPouvoir() {
        getJoueur().ajouterQuartierDansMain(getPlateau().getPioche().piocher());
        getJoueur().ajouterQuartierDansMain(getPlateau().getPioche().piocher());
    }

    @Override
    public void utiliserPouvoirAvatar(){
        getJoueur().ajouterQuartierDansMain(getPlateau().getPioche().piocher());
        getJoueur().ajouterQuartierDansMain(getPlateau().getPioche().piocher());
    }
}
