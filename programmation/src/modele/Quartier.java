package modele;

public class Quartier {
	
	private String nom;
	private String type;
	private int coutConstruction;
	private String caracteristiques;
	public static final String[] TYPE_QUARTIERS = {"RELIGIEUX", "MILITAIRE", "NOBLE", "COMMERCANT", "MERVEILLE"};
	
	public Quartier() {
		this.nom = "";
		this.type = "";
		this.coutConstruction = 0;
		this.caracteristiques = "";
	}
	
	public Quartier(String nom, String type, int cout) {
		this(nom, type, cout, "");
	}
	
	public Quartier(String nom, String type, int cout, String caracteristiques) {
		setNom(nom);
		setType(type);
		setCout(cout);
		setCaracteristiques(caracteristiques);
	}
	
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		int i = 0;
		while(type != TYPE_QUARTIERS[i]) {
			i++;
			if(i == TYPE_QUARTIERS.length) {
				this.type = "";
				return;
			}
		}
		this.type = type;
	}
	public int getCout() {
		return this.coutConstruction;
	}
	public void setCout(int cout) {
		this.coutConstruction = (cout<1 || cout>6?0:cout);
	}
	public String getCaracteristiques() {
		return this.caracteristiques;
	}
	public void setCaracteristiques(String caracteristiques) {
		this.caracteristiques = caracteristiques;
	}
	
	

}
