package modele;

import controleur.Interaction;

public class Assassin extends Personnage {

	private boolean assassine = false;

	public Assassin() {
		super("Assassin", 1, Caracteristiques.ASSASSIN);
	}

	@Override
	public void utiliserPouvoir() {
		System.out.println("Quel personnage voulez-vous assassiner ? ");
		for (int i = 0; i < getPlateau().getNombrePersonnages(); i++) {
			System.out.println(i + 1 + " " + getPlateau().getPersonnage(i).getNom());
		}
		while (!assassine) {
			System.out.print("Votre choix : ");
			int id = Interaction.lireUnEntier(1, getPlateau().getNombrePersonnages());
			Personnage cible = getPlateau().getPersonnage(id - 1);
			if (cible.getNom() == "Assassin") {
				System.out.println("Vous ne pouvez pas vous assassiner.");
			} else {
				assassine = true;
				cible.setAssassine();
			}
		}
	}

}
