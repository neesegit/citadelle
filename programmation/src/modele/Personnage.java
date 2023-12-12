package modele;

public abstract class Personnage {

    private String nom;
    private int rang;
    private String caracteristiques;
    private Joueur joueur;
    private boolean assassine;
    private boolean vole;
    private PlateauDeJeu plateau;
    private boolean DejaConstruitUnTruc;
    private boolean jetonEchevinVrai;
    private boolean jetonEchevinFaux;

    public Personnage(String nom, int rang, String caracteristiques) {
        this.nom = nom;
        this.rang = rang;
        this.caracteristiques = caracteristiques;
        this.joueur = null;
        this.assassine = false;
        this.vole = false;
        this.DejaConstruitUnTruc = true;
        this.jetonEchevinVrai = false;
        this.jetonEchevinFaux = false;
        // il y a un perso qui peut construire deux fois attention a changer pendant le
        // pouvoir.
    }

    public boolean getJetonEchevinVrai() {
        return this.jetonEchevinVrai;
    }

    public void setJetonEchevinVrai(boolean etatVrai) {
        this.jetonEchevinVrai = etatVrai;
    }

    public boolean getJetonEchevinFaux() {
        return this.jetonEchevinFaux;
    }

    public void setJetonEchevinFaux(boolean etatFaux) {
        this.jetonEchevinFaux = etatFaux;
    }

    public boolean getDejaConstruit() {
        return this.DejaConstruitUnTruc;
    }

    public void setDejaConstruit(boolean construit){
        this.DejaConstruitUnTruc = construit;
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

    public void setJoueur(Joueur j) {
        this.joueur = j;
        if (this.joueur != null) {
            this.joueur.monPersonnage = this;
        }
    }

    public void setVole() {
        this.vole = true;
        getJoueur().retirerPieces(2);
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
        if (this.getJoueur() == null || this.getAssassine() == true) {
            return;
        }
        this.getJoueur().ajouterPieces(2);
    }

    public void ajouterQuartier(Quartier nouveau) {
        if (this.getJoueur() == null || this.getAssassine() == true) {
            return;
        }
        this.getJoueur().ajouterQuartierDansMain(nouveau);
    }

    public void construire(Quartier nouveau) {
        if (DejaConstruitUnTruc) {
            if (this.getJoueur() == null || this.getAssassine() == true) {
                return;
            }
            if (this.getJoueur().nbPieces() < nouveau.coutConstruction) {
                System.out.println("Vous n'avez pas  d'or pour construire ce quartier");
                return;
            }
            this.getJoueur().ajouterQuartierDansCite(nouveau);
            DejaConstruitUnTruc = false;
        }
        System.out.println("Vous construit un quartier dans ce tour");
    }

    public void percevoirRessourcesSpecifiques() {
        if (this.getJoueur() == null || this.getAssassine() == true) {
            return;
        }
        System.out.println("Aucune ressource spécifique");
    }

    public abstract void utiliserPouvoir();
    public abstract void utiliserPouvoirAvatar();

    public void reinitialiser() {
        if (this.joueur.monPersonnage != null) {
            this.joueur.monPersonnage = null;
        }
        this.vole = false;
        this.assassine = false;
        this.joueur = null;
    }

}
