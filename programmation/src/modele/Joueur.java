package modele;

import java.util.ArrayList;
import java.util.Random;

public class Joueur {

    private String nom;
    private int tresor;
    private Quartier[] cite;
    private int nbQuartier;
    private ArrayList<Quartier> main;
    private boolean possedeCouronne;
    private boolean bot;

    public Personnage monPersonnage;

    private int truc = 0;
    Random generateur = new Random();

    public Joueur(String nom) {
        this.nom = nom;
        this.tresor = 0;
        this.nbQuartier = 0;
        this.possedeCouronne = false;
        this.cite = new Quartier[8];
        this.main = new ArrayList<Quartier>();
        this.monPersonnage = null;
    }

    public boolean isBot(){
        return this.bot ? true : false;
    }
    public void setType(boolean type){
        this.bot=type;
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
        return this.cite;
    }
    public ArrayList<Quartier> getMain(){
        return this.main;
    }
    public int nbQuartiersDansMain(){
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

    public void ajouterPieces(int plus) {
        if(plus < 0) {
            System.out.println("Le nombre choisis doit être positif");
            return;
        }
        this.tresor += plus;
    }
    public void retirerPieces(int moins) {
        if(moins < 0) {
            System.out.println("Le nombre choisis doit être positif");
            return;
        }
        if(this.tresor-moins < 0) {
            System.out.println("Le nombre choisis est trop grand");
            return;
        }
        this.tresor -= moins;
    }

    public void ajouterQuartierDansCite(Quartier quartier) {
        if(this.nbQuartier == this.cite.length) {
            System.out.println("trop de quartier");
            return;
        }
        if(this.getNom() == "Navigatrice"){
            System.out.println("Vous n'avez pas le droit de construire");
            return;
        }
        this.cite[this.nbQuartier] = quartier;
        this.nbQuartier++;
    }
    public boolean quartierPresentDansCite(String nom) {

        for(int i = 0; i<this.nbQuartier; i++) {
            if(this.cite[i].getNom() == nom) {
                truc = i;
                return true;
            }
        }
        return false;

    }
    public Quartier retirerQuartierDansCite(String nom) {

        if(quartierPresentDansCite(nom)) {
            Quartier temp = null;
            temp = this.cite[truc];
            for(int i = truc; i<this.cite.length-1;i++) {
                this.cite[i] = this.cite[i+1] ;
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
        if(this.nbQuartiersDansMain() == 0) {
            return null;
        }
        int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
        Quartier temp = this.main.get(numeroHasard);
        this.main.remove(numeroHasard);
        return temp;
    }

    public void reinitialiser() {
        this.tresor = 0;
        this.nbQuartier = 0;
        this.main.clear();
    }
}