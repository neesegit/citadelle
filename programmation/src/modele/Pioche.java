package modele;

import java.util.ArrayList;
import java.util.Random;

public class Pioche {
	
	private ArrayList<Quartier> liste;
	
	private Random generateur = new Random();
	
	public Pioche() {
		
		this.liste = new ArrayList<Quartier>();
		
	}
	
	public Quartier piocher() {
		
		if(this.liste.size() == 0) {
			return null;
		}
		
		Quartier piocher = this.liste.get(0);
		this.liste.remove(0);
		
		return piocher;
	}
	
	public void ajouter(Quartier nouveau) {
		this.liste.add(nouveau);
	}
	
	public int nombreElements() {
		return this.liste.size();
	}
	
	public void melanger() {
		int i = generateur.nextInt(this.liste.size()-1);
		int j = generateur.nextInt(this.liste.size()-1);
		
		Quartier melange = this.liste.get(i);
		
		this.liste.set(i, this.liste.get(j));
		this.liste.set(j, melange);
	}

}
