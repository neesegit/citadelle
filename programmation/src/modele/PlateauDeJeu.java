package modele;

public class PlateauDeJeu {

    private Personnage[] listePersonnage;
    private Joueur[] listeJoueurs;
    private Pioche pioche;
    private int nombrePersonnages;
    private int nombreJoueurs;

    public PlateauDeJeu() {

        this.listeJoueurs = new Joueur[9];
        this.listePersonnage = new Personnage[17];
        this.pioche = new Pioche();
        this.nombrePersonnages = 0;
        this.nombreJoueurs = 0;

    }
    public void permuterJoueur(int indiceJoueurDernierEmplacementDuTableau,int indiceJoueurDissident){
        Joueur joueurDernierEmplacementDuTableau=this.listeJoueurs[indiceJoueurDernierEmplacementDuTableau];
        Joueur joueurDissident=this.listeJoueurs[indiceJoueurDissident];
        this.listeJoueurs[indiceJoueurDernierEmplacementDuTableau]=joueurDissident;
        this.listeJoueurs[indiceJoueurDissident]=joueurDernierEmplacementDuTableau;
    }
    public Quartier[] getQuartiersDuJoueur(Joueur joueur) {
        for (Joueur j : listeJoueurs) {
            if (j.equals(joueur)) {
                return j.getCite();
            }
        }
        return null;
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
    
    public void setPioche(Pioche pioche){
        this.pioche = pioche;
    }

    public Personnage getPersonnage(int i) {

        if (i < 0 || i > this.listePersonnage.length - 1) {
            return null;
        }

        return this.listePersonnage[i];
    }

    public Joueur getJoueur(int i) {

        if (i < 0 || i > this.listeJoueurs.length - 1) {
            return null;
        }

        return this.listeJoueurs[i];
    }

    public void ajouterPersonnage(Personnage p) {
        if (p == null || nombrePersonnages == this.listePersonnage.length) {
            return;
        }

        this.listePersonnage[nombrePersonnages] = p;
        p.setPlateau(this);
        nombrePersonnages++;
    }

    public void ajouterJoueur(Joueur j) {
        if (j == null || nombreJoueurs == this.listeJoueurs.length) {
            return;
        }

        this.listeJoueurs[nombreJoueurs] = j;
        nombreJoueurs++;
    }

}