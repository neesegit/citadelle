package modele;

import java.util.Random;

import controleur.Interaction;

public class Voleur extends Personnage {

	private boolean vole = false;

	Random random = new Random();

	public Voleur() {
		super("Voleur", 2, Caracteristiques.VOLEUR);
	}

	@Override
	public void utiliserPouvoir() {
		System.out.println("Quel personnage voulez-vous volez ? ");
		for (int i = 0; i < getPlateau().getNombrePersonnages(); i++) {
			System.out.println(i + 1 + " " + getPlateau().getPersonnage(i).getNom() + " ("
					+ getPlateau().getJoueur(i).getNom() + ")");
		}
		while (!vole) {
			System.out.print("Votre choix : ");
			int id = Interaction.lireUnEntier(1, getPlateau().getNombrePersonnages() + 1);
			Personnage cible = getPlateau().getPersonnage(id - 1);
			if (cible.getNom() == "Voleur" || cible.getRang() == 1) {
				System.out.println("Vous ne pouvez pas voler ce personnage.");
			} else {
				vole = true;
				cible.setVole();
				getJoueur().ajouterPieces(2);
			}
		}
	}

	@Override
	public void utiliserPouvoirAvatar() {
		while (!vole) {
			int id = random.nextInt(1, getPlateau().getNombrePersonnages())-1;
			Personnage cible = getPlateau().getPersonnage(id);
			if (cible.getNom() == "Voleur" || cible.getRang() == 1) {
				System.out.println("Vous ne pouvez pas voler ce personnage.");
			} else {
				vole = true;
				cible.setVole();
				getJoueur().ajouterPieces(2);
			}
		}	
	}

}
