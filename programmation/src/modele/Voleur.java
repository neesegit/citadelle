package modele;

import controleur.Interaction;

public class Voleur extends Personnage {
	
	private boolean vole = false;
	
	public Voleur() {
		super("Voleur",2,Caracteristiques.VOLEUR);
	}
	
	@Override
	public void utiliserPouvoir() {
		System.out.println("Quel personnage voulez-vous volez ? ");
		for(int i = 0; i < getPlateau().getNombrePersonnages(); i++) { //TODO : Faire qu'un message apparaisse s'il se cible lui même et enlever les chiffres degeu si possible
			System.out.println(i+1 + " " + getPlateau().getPersonnage(i));
		}
		while(!vole) {
			System.out.print("Votre choix : ");
			int id = Interaction.lireUnEntier(1,getPlateau().getNombrePersonnages());
			Personnage cible = getPlateau().getPersonnage(id-1);
			if(cible.getNom() == "Voleur" || cible.getRang()==1) {
				System.out.println("Vous ne pouvez pas voler ce personnage.");
			} else {
				vole = true;
				cible.setVole();
				getJoueur().ajouterPieces(2);
			}
		}
	}

}
