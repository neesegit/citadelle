package modele;

import controleur.Interaction;

public class Espion extends Personnage{

    private PlateauDeJeu plateau = getPlateau();
    private Pioche pioche = plateau.getPioche();

	public Espion() {
		super("Espion", 2, Caracteristiques.ESPION);
	}
	
	@Override
	public void utiliserPouvoir() {
        System.out.print("Quel type de quartier (Noble, Militaire, Religieux, Commercant ou Merveille) choisissez vous ? ");
        String choix = Interaction.lireUneChaine();

        //TODO faire la classe
        
	}
}
