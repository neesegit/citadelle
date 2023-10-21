import java.util.ArrayList;
import java.util.Random;

public class Joueur {
    private String nom;
    private int tresor, nbQuartier;
    private Boolean possedeCouronne;
    private Quartier[] cite;
    private ArrayList<Quartier> main;
    private int spot = 0;
    Random generateur = new Random();

    public String getNom(){
        return this.nom;
    }

    public int nbPieces(){
        return this.tresor;
    }

    public int nbQuartiersDansCite(){
        return this.nbQuartier;
    }

    public Quartier[] getCite(){
        return this.cite;
    }

    public ArrayList<Quartier> getMain() {
        return main;
    }

    public int nbQuartiersDansMain(){
        return this.main.size();
    }

    public Boolean getPossedeCouronne() {
        return possedeCouronne;
    }

    public void setPossedeCouronne(Boolean possedeCouronne) {
        this.possedeCouronne = possedeCouronne;
    }

    //on definie les données du joueur par défault
    public Joueur(String nom){
        this.nom = nom;
        this.nbQuartier = 0;
        this.cite = new Quartier[8];
        this.possedeCouronne = false;
        this.main = new ArrayList<Quartier>();
    }

    //on verifie bien que le joueur a 0 piece d'or mini
    public void ajouterPieces(int nbPieces){
        if(nbPieces>=0){
            this.tresor += nbPieces;
        }
    }

    // il faut mini pour acheter et que la soustraction ne te mène pas dans le négatif
    public void retirerPieces(int nbPieces){
        if(nbPieces>0){
            if (this.tresor - nbPieces>=0){
                this.tresor -= nbPieces;
            }
        }
    }

    //dea
    public void ajouterQuartierDansCite(Quartier quartier){
        if(this.nbQuartier < this.cite.length){
            this.cite[this.nbQuartier] = quartier;
            this.nbQuartier++;
        }
    }

    public boolean quartierPresentDansCite(String nom){
        for(int i = 0; i<this.nbQuartier; i++) {
            if(this.cite[i].getNom() == nom) {
                spot = i;
                return true;
            }
        }
        return false;
    }

    public Quartier retirerQuartierDansCite(String nom){
        // si le quartier est dans la cite
        if (quartierPresentDansCite(nom)){
            Quartier retour = null;
            retour = this.cite[spot];
            // alors à partir de sa position je redef le [] pour l'effacer
            for (int i = spot; i<this.cite.length-1; i++){
                this.cite[i] = this.cite[i+1];
            }
            this.nbQuartier--;
            return retour;
        }
        return null;
    }

    public void ajouterQuartierDansMain(Quartier quartier){

        this.main.add(quartier);
    }

    public Quartier retirerQuartierDansMain(){
        if(this.nbQuartiersDansMain() == 0) {
            return null;
        }
        int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
        Quartier temp = this.main.get(numeroHasard);
        this.main.remove(numeroHasard);
        return temp;
    }

    public void reinitialiser(){
        this.tresor = 0;
        this.nbQuartier = 0;
        this.main.clear();
    }
}
