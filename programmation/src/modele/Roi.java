package modele;

public class Roi extends Personnage {

	private int compteur = 0;
	
	public Roi() {
		super("Roi",4,Caracteristiques.ROI);
		
	}

	@Override
	public void utiliserPouvoir() {
		System.out.println("Je prends la couronne");
		this.getJoueur().setPossedeCouronne(true);	
	}
	
	public void percevoirRessourcesSpecifiques() {
		for(int i = 0; i<this.getJoueur().nbQuartiersDansCite();i++) {
			if(this.getJoueur().quartierPresentDansCite("noble")) {
				compteur++;
			}
		}
		this.getJoueur().ajouterPieces(compteur);
		System.out.println("Ajouter "+compteur+" pièce"+(compteur>1?"s":""));
	}

}
