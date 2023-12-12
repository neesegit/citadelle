package modele;

import controleur.Interaction;

public class Navigatrice extends Personnage {

    private PlateauDeJeu plateau;
    private Pioche pioche;

    public Navigatrice() {
        super("Navigatrice", 7, Caracteristiques.NAVIGATRICE);
        this.plateau = getPlateau();
        if (this.plateau == null) {
            plateau = new PlateauDeJeu();
        }
        this.pioche = this.plateau.getPioche();
    }

    @Override
    public void utiliserPouvoir() {
        while (true) {
            System.out.print("Prendre (p)ieces ou piochez (c)artes : ");
            String choix = Interaction.lireUneChaine();
            if (choix.equals("p")) {
                this.getJoueur().ajouterPieces(4);
                return;
            } else if (choix.equals("c")) {
                for (int i = 0; i < 4; i++) {
                    this.getJoueur().ajouterQuartierDansMain(pioche.piocher()); // this.getJoueur() ... ?
                }
                return;
            }
        }

    }

    @Override
    public void construire(Quartier nouveau) {
        System.out.println("En tant que navigatrice, vous appartenez aux mers, donc construire ne servirait à rien");

    }

    @Override
    public void utiliserPouvoirAvatar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'utiliserPouvoirAvatar'");
    }
}
