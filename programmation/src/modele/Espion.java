package modele;

import java.util.ArrayList;

import controleur.Interaction;

public class Espion extends Personnage{

    private PlateauDeJeu plateau = getPlateau();
    private Pioche pioche = plateau.getPioche();

	private ArrayList<Quartier> main = new ArrayList<Quartier>();

	private String choixQuartier = "";
	private int choixJoueur = 0;
	private int compteur = 0;

	private Joueur joueur;

	public Espion() {
		super("Espion", 2, Caracteristiques.ESPION);
	}
	
	@Override
	public void utiliserPouvoir() {
		// Choix joueur
		for(int i = 0; i < plateau.getNombreJoueurs(); i++){
			if(plateau.getJoueur(i) == this.getJoueur()){
				continue;
			}
			System.out.println((i+1) + ". " + plateau.getJoueur(i));
		}

		System.out.print("Choisissez un joueur (le chiffre devant): ");
		choixJoueur = Interaction.lireUnEntier(1, plateau.getNombreJoueurs()) - 1;
		joueur = plateau.getJoueur(choixJoueur);

		//Choix type de quartier
		System.out.print("Quel type de quartier (Noble, Militaire, Religieux, Commercant ou Merveille) choisissez vous ? ");
        choixQuartier = Interaction.lireUneChaine();

		main = joueur.getMain();

		for(int i = 0; i < main.size(); i++){
			if(main.get(i).toString() == choixQuartier){
				compteur++;
			}
		}
		
		if(compteur == 0){
			return;
		}
		//Don de pièce et ajout de carte dans la main
		joueur.retirerPieces((joueur.nbPieces() < compteur?compteur - joueur.nbPieces():compteur));
		this.getJoueur().ajouterPieces((joueur.nbPieces() < compteur?compteur - joueur.nbPieces():compteur));
		for(int i = 0; i < compteur; i++){
			this.getJoueur().ajouterQuartierDansMain(pioche.piocher());
		}
        
	}
}
