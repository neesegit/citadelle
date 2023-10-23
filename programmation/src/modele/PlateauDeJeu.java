package modele;

public class PlateauDeJeu {

	private Personnage[] listePersonnage;
	private Joueur[] listeJoueurs;
	private Pioche pioche;
	private int nombrePersonnages;
	private int nombreJoueurs;
	private int compteur = 0;
	
	public PlateauDeJeu() {
		
		this.listeJoueurs = new Joueur[9];
		this.listePersonnage = new Personnage[9];
		this.pioche = new Pioche();
		this.nombrePersonnages = 0;
		this.nombreJoueurs =  0;
		
	}
	
	public int getNombrePersonnages() {
		return this.nombrePersonnages;
	}
	public int getNombreJoueurs() {
		return this.nombreJoueurs;
	}
	public Pioche getPioche() {
		return this.pioche;
	}
	public Personnage getPersonnage(int i) {
		
		if(i<0 || i>this.listePersonnage.length-1) {
			return null;
		}
		
		return this.listePersonnage[i];
	}
	public Joueur getJoueur(int i) {
		
		if(i<0 || i>this.listeJoueurs.length-1) {
			return null;
		}
		
		return this.listeJoueurs[i];
	}
	
	public void ajouterPersonnage(Personnage p) {
		if(p == null || compteur == this.listePersonnage.length) {
			return;
		}
		
		this.listePersonnage[compteur] = p;
		compteur++;
	}
	public void ajouterJoueur(Joueur j) {
		if(j == null || compteur == this.listePersonnage.length) {
			return;
		}
		
		this.listeJoueurs[compteur] = j;
		compteur++;
	}
	
}