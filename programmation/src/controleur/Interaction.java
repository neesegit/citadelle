package controleur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interaction {
	private static Scanner sc = new Scanner(System.in);

	public static int lireUnEntier() {
		int i = 0;
		boolean continu = true;
		do {
			try {
				i = sc.nextInt();
				continu = false;
			} catch (InputMismatchException e) {
				System.out.print("Veuillez rentrer un chiffre : ");
				sc.next(); // passe l'entier pour eviter de boucler
			}
		} while (continu);
		return i;
	}

	// renvoie un entier lu au clavier compris dans l'intervalle
	// [borneMin, borneMax[
	public static int lireUnEntier(int borneMin, int borneMax) {
		int i = 0;
		boolean saisieValide = false;

		while (!saisieValide) {
			// System.out.print("Entrez un entier dans l'intervalle ["+ borneMin +",
			// "+borneMax+"[ : ");
			if (sc.hasNextInt()) {
				i = sc.nextInt();
				if (i >= borneMin && i <= borneMax) {
					saisieValide = true;
				} else {
					System.out.println("L'entier doit être dans l'intervalle [" + borneMin + ", " + borneMax +"].");
				}
			} else {
				System.out.println("Veuillez entrer un entier valide.");
			}
		}
		return i;
	}

	// lit les reponses "oui", "non", "o" ou "n" et renvoie un booleen
	public static boolean lireOuiOuNon() {
		boolean retour = false;
		boolean saisieValide = false;

		while (!saisieValide) {
			// System.out.print("Veuillez répondre par '(o)ui' ou '(n)on' : ");
			String reponse = sc.next().toLowerCase();
			if (reponse.equals("oui") || reponse.equals("o")) {
				retour = true;
				saisieValide = true;
			} else if (reponse.equals("non") || reponse.equals("n")) {
				retour = false;
				saisieValide = true;
			} else {
				System.out.println("Veuillez répondre par '(o)ui' ou '(n)on'.");
			}
		}

		return retour;
	}

	// renvoie une chaine de caractere lue au clavier:
	public static String lireUneChaine() {
		String retour = "";
		retour = sc.next();

		return retour;
	}

}