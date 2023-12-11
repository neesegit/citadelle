package modele;

import java.util.ArrayList;

import controleur.Interaction;

public class Archiviste extends Personnage {

    private PlateauDeJeu plateau;
    private Pioche pioche;
    private Quartier[] choixTemp;

    private int permisDeConstruire = 2;
    private boolean choixON = false;

    private int choixCarte = 0;

    public Archiviste() {
        super("Archiviste", 7, Caracteristiques.ARCHIVISTE);
        this.plateau = getPlateau();
        if(this.plateau == null){
            plateau = new PlateauDeJeu();
        } 
        this.pioche = this.plateau.getPioche();
        this.choixTemp = new Quartier[7];
    }

    @Override
    public void percevoirRessourcesSpecifiques(){
        System.out.println("Liste des cartes :");
        for(int i = 0; i < 7; i++){
            choixTemp[i] = pioche.piocher();
            System.out.println(i+1 + ". "+choixTemp[i].getNom());
        }
        
        System.out.print("Choisissez une carte : ");
        choixCarte = Interaction.lireUnEntier(1, 7);
        this.ajouterQuartier(choixTemp[choixCarte-1]);
    }

    @Override
    public void construire(Quartier nouveau){
        while(permisDeConstruire > 0){
            if (this.getJoueur() == null || this.getAssassine() == true) {
                return;
            }
            if (this.getJoueur().nbPieces() < nouveau.coutConstruction) {
                System.out.println("Vous n'avez pas  d'or pour construire ce quartier");
                permisDeConstruire--;
                continue;
            }
            this.getJoueur().ajouterQuartierDansCite(nouveau);
            permisDeConstruire--;
            if(permisDeConstruire > 0){
                System.out.println("Voulez vous construire à nouveau (reste "+permisDeConstruire+" nombre de construction) ? ");
                System.out.print("Veuillez répondre par oui ou par non : ");
                choixON = Interaction.lireOuiOuNon();
            }
            if(choixON){
                System.out.println("Veuillez choisir un quartier parmis votre main :");
                System.out.println(this.getJoueur().getMain().toString());
                nouveau = this.getJoueur().retirerQuartierDansMain();
            }
        }
    }

    //TODO faire la partie du bot

    @Override
    public void utiliserPouvoir() {}

    @Override
    public void utiliserPouvoirAvatar() {}


    
}
