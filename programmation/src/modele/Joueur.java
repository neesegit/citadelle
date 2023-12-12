package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Joueur {

    private String nom;
    private int tresor;
    private Quartier[] cite;
    private int nbQuartier;
    private ArrayList<Quartier> main;
    private boolean possedeCouronne;

    private boolean bot;
    private static Random generateur = new Random();
    private boolean possedeCatacombes;


    public Personnage monPersonnage;

    private int truc = 0;

    public Joueur(String nom) {
        this.nom = nom;
        this.tresor = 0;
        this.nbQuartier = 0;
        this.possedeCouronne = false;
        this.cite = new Quartier[7];
        this.main = new ArrayList<Quartier>();
        this.monPersonnage = null;
        this.possedeCatacombes = false;
    }

    public String getNom() {
        return this.nom;
    }

    public int nbPieces() {
        return this.tresor;
    }

    public int nbQuartiersDansCite() {
        return this.nbQuartier;
    }

    public Quartier[] getCite() {
        return cite;
    }


    public ArrayList<Quartier> getMain(){

        return this.main;
    }

    public int nbQuartiersDansMain() {
        return this.main.size();
    }

    public boolean getPossedeCouronne() {
        return this.possedeCouronne;
    }

    public void setPossedeCouronne(boolean b) {
        this.possedeCouronne = b;
    }

    public Personnage getPersonnage() {
        return this.monPersonnage;
    }

    public static Random getGenerateur() {
        return generateur;
    }

    public void ajouterPieces(int plus) {
        if (plus < 0) {
            System.out.println("Le nombre choisis doit être positif");
            return;
        }
        this.tresor += plus;
    }

    public void retirerPieces(int moins) {
        if (moins < 0) {
            System.out.println("Le nombre choisis doit être positif");
            return;
        }
        if (this.tresor - moins < 0) {
            System.out.println("Le nombre choisis est trop grand");
            return;
        }
        this.tresor -= moins;
    }

    public void ajouterQuartierDansCite(Quartier quartier) {
        if (this.nbQuartier == this.cite.length) {
            System.out.println("trop de quartier");
            return;
        }

        if(this.getNom().equals("Navigatrice")){

            System.out.println("Vous n'avez pas le droit de construire");
            return;
        }
        this.cite[this.nbQuartier] = quartier;
        this.nbQuartier++;
    }

    public boolean quartierPresentDansCite(String nom) {

        for (int i = 0; i < this.nbQuartier; i++) {
            if (this.cite[i].getNom() == nom) {
                truc = i;
                return true;
            }
        }
        return false;

    }

    public Quartier retirerQuartierDansCite(String nom) {

        if (quartierPresentDansCite(nom)) {
            Quartier temp = null;
            temp = this.cite[truc];
            for (int i = truc; i < this.cite.length - 1; i++) {
                this.cite[i] = this.cite[i + 1];
            }
            this.nbQuartier--;
            return temp;
        }

        return null;
    }

    public void ajouterQuartierDansMain(Quartier nomQuartier) {
        this.main.add(nomQuartier);
    }

    public Quartier retirerQuartierDansMain() {
        if (this.nbQuartiersDansMain() == 0) {
            return null;
        }
        int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
        Quartier temp = this.main.get(numeroHasard);
        this.main.remove(numeroHasard);
        return temp;
    }

    public boolean aTroisQuartiersDuMemeType(PlateauDeJeu plateau) {
        Quartier[] quartiersDuJoueur = plateau.getQuartiersDuJoueur(this);
        Map<String, Integer> compteurQuartiers = new HashMap<>();
        for (Quartier quartier : plateau.getQuartiersDuJoueur(this)) {
            if (quartier != null) {
                compteurQuartiers.put(quartier.getType(), compteurQuartiers.getOrDefault(quartier.getType(), 0) + 1);
                if (compteurQuartiers.get(quartier.getType()) >= 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean possedeCatacombes() {
        return this.possedeCatacombes;
    }

    public void obtenirCatacombes() {
        this.possedeCatacombes = true;
    }

    /* Je comprends pas vraiment ce qu'il faut faire la
    public void marquerPointsCatacombes() {
        if (this.possedeCatacombes) {
        }
    }

    public void retirerCatacombesDeLaMain() {

    }

    public void ajouterCatacombesALaMain() {

    }
    */

    /* il manque peut être des trucs pour le donjons*/ 
    public boolean peutAffecterDonjon(Personnage personnage) {
        return personnage.getRang() < Quartier.RANG_POUVOIR_MAX;
    }


    public void reinitialiser() {
        this.tresor = 0;
        this.nbQuartier = 0;
        this.main.clear();
    }


    public void ajouterPoints(int pointsBasilique) {
    }

    public void setEffetCapitoleUtilise(boolean b) {
    }

    public boolean possedeDracoport() {
        return true;
    }

    public boolean aUtiliseEffetCapitole() {
        return true;

    }
}