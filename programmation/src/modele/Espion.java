package modele;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;

public class Espion extends Personnage {

	private PlateauDeJeu plateau;
	private Pioche pioche;

	private ArrayList<Quartier> main = new ArrayList<Quartier>();

	private String choixQuartier = "";
	private int choixJoueur = 0;
	private int compteur = 0;
	private boolean cibleVerrouille = false;
	private Joueur joueur;

	Random random = new Random();

	public Espion() {
		super("Espion", 2, Caracteristiques.ESPION);
		this.plateau = getPlateau();
		if (this.plateau == null) {
			plateau = new PlateauDeJeu();
		}
		this.pioche = this.plateau.getPioche();
	}

	@Override
	public void utiliserPouvoir() {
		// Choix joueur
		for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
			if (plateau.getJoueur(i) == this.getJoueur()) {
				continue;
			}
			System.out.println((i + 1) + ". " + plateau.getJoueur(i));
		}
		for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
			System.out.println(i + 1 + " " + getPlateau().getJoueur(i).getNom() + " ("
					+ getPlateau().getJoueur(i).getPersonnage().getNom() + ")");
		}

		while (!cibleVerrouille) {
			System.out.print("Choisissez un joueur autre que vous (le chiffre devant): ");
			choixJoueur = Interaction.lireUnEntier(1, getPlateau().getNombreJoueurs() + 1);
			if (getJoueur() != getPlateau().getJoueur(choixJoueur - 1)) {
				cibleVerrouille = true;
			}
		}

		joueur = getPlateau().getJoueur(choixJoueur - 1);

		// Choix type de quartier
		cibleVerrouille = false;
		while (!cibleVerrouille) {// fais une fois la boucle a vide ??
			System.out.print(
					"Quel type de quartier ecrire le nom (NOBLE, MILITAIRE, RELIGIEUX, COMMERCANT ou MERVEILLE) choisissez vous ? ");
			choixQuartier = Interaction.lireUneChaine();
			if (choixQuartier.equals("NOBLE") || choixQuartier.equals("MILITAIRE") || choixQuartier.equals("RELIGIEUX")
					|| choixQuartier.equals("COMMERCANT") || choixQuartier.equals("MERVEILLE")) {
				cibleVerrouille = true;
			}
		}

		main = joueur.getMain();
		for (int i = 0; i < main.size(); i++) {
			if (main.get(i).getType().equals(choixQuartier)) {
				compteur++;
			}
		}

		if (compteur == 0) {
			return;
		}
		// Don de pièce et ajout de carte dans la main
		joueur.retirerPieces((joueur.nbPieces() < compteur ? compteur - joueur.nbPieces() : compteur));
		this.getJoueur().ajouterPieces((joueur.nbPieces() < compteur ? compteur - joueur.nbPieces() : compteur));
		for (int i = 0; i < compteur; i++) {
			this.getJoueur().ajouterQuartierDansMain(pioche.piocher());
		}

	}

	@Override
	public void utiliserPouvoirAvatar() {
		
		while (!cibleVerrouille) {
			choixJoueur = random.nextInt(1, getPlateau().getNombreJoueurs());
			if (getJoueur() != getPlateau().getJoueur(choixJoueur)) {
				cibleVerrouille = true;
			}
		}

		joueur = getPlateau().getJoueur(choixJoueur);

		// Choix type de quartier
		int choixQuartierBot = random.nextInt(1, 5);

		if(choixQuartierBot == 1){
			choixQuartier = "NOBLE";
		}
		if(choixQuartierBot == 2){
			choixQuartier = "MILITIARE";
		}
		if(choixQuartierBot == 3){
			choixQuartier = "RELIGIEUX";
		}
		if(choixQuartierBot == 4){
			choixQuartier = "COMMERCANT";
		}
		if(choixQuartierBot == 5){
			choixQuartier = "MERVEILLE";
		}

		main = joueur.getMain();
		for (int i = 0; i < main.size(); i++) {
			if (main.get(i).getType().equals(choixQuartier)) {
				compteur++;
			}
		}

		if (compteur == 0) {
			return;
		}
		// Don de pièce et ajout de carte dans la main
		joueur.retirerPieces((joueur.nbPieces() < compteur ? compteur - joueur.nbPieces() : compteur));
		this.getJoueur().ajouterPieces((joueur.nbPieces() < compteur ? compteur - joueur.nbPieces() : compteur));
		for (int i = 0; i < compteur; i++) {
			this.getJoueur().ajouterQuartierDansMain(pioche.piocher());
		}
	}
}

