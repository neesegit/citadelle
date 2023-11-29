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
                for(int i = 0; i < 2; i++){
                    ajouterPieces();
                }
                return;
            } else if(choix == "c"){
                for(int i = 0; i < 4; i++){
                    getJoueur().ajouterQuartierDansMain(pioche.piocher());
                }
                return;
            }
        }
	}
}
