package modele;

import controleur.Interaction;

public class Navigatrice extends Personnage{

    private PlateauDeJeu plateau = getPlateau();
    private Pioche pioche = plateau.getPioche();

	public Navigatrice() {
		super("Navigatrice", 7, Caracteristiques.NAVIGATRICE);
	}
	
	@Override
	public void utiliserPouvoir() {
        while(true){
            System.out.print("Prendre (p)ieces ou piochez (c)artes : ");
            String choix = Interaction.lireUneChaine();
            if(choix == "p"){
                this.getJoueur().ajouterPieces(4);
                return;
            } else if(choix == "c"){
                for(int i = 0; i < 4; i++){
                    this.getJoueur().ajouterQuartierDansMain(pioche.piocher()); //this.getJoueur() ... ?
                }
                return;
            }
        }
	}

    @Override
    public void utiliserPouvoirAvatar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'utiliserPouvoirAvatar'");
    }
}
