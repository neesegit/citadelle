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

    public abstract static class Personnage {

        private String nom;
        private int rang;
        private String caracteristiques;
        private Joueur joueur;
        private boolean assassine;
        private boolean vole;
        private PlateauDeJeu plateau;

        public Personnage(String nom, int rang, String caracteristiques) {
            this.nom = nom;
            this.rang = rang;
            this.caracteristiques = caracteristiques;
            this.joueur = null;
            this.assassine = false;
            this.vole = false;
        }

        public String getNom() {
            return this.nom;
        }
        public int getRang() {
            return this.rang;
        }
        public String getCaracteristiques() {
            return this.caracteristiques;
        }
        public Joueur getJoueur() {
            return this.joueur;
        }
        public boolean getAssassine() {
            return this.assassine;
        }
        public boolean getVole() {
            return this.vole;
        }

       /* public void setJoueur(Joueur j) {
            this.joueur = j;
            this.joueur.monPersonnage = this;
        }*/
        public void setVole() {
            this.vole = true;
        }
        public void setAssassine() {
            this.assassine = true;
        }

        public PlateauDeJeu getPlateau() {
            return this.plateau;
        }
        public void setPlateau(PlateauDeJeu p) {
            this.plateau = p;
        }

        public void ajouterPieces() {
            if(this.getJoueur() == null || this.getAssassine() == true) {
                return;
            }
            this.getJoueur().ajouterPieces(2);
        }
        public void ajouterQuartier(Quartier nouveau) {
            if(this.getJoueur() == null || this.getAssassine() == true) {
                return;
            }
            this.getJoueur().ajouterQuartierDansMain(nouveau);
        }

        public void construire(Quartier nouveau) {
            if(this.getJoueur() == null || this.getAssassine() == true) {
                return;
            }
            this.getJoueur().ajouterQuartierDansCite(nouveau);
        }

        public void percevoirRessourcesSpecifiques() {
            if(this.getJoueur() == null || this.getAssassine() == true) {
                return;
            }
            System.out.println("Aucune ressource spécifique");
        }

        public abstract void utiliserPouvoir();

        public void reinitialiser() {
            this.joueur = null;
            if(this.joueur.monPersonnage != null) {this.joueur.monPersonnage = null;}
            this.vole = false;
            this.assassine = false;
        }

    }
}