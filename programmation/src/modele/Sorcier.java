package modele;

import java.util.Random;

import controleur.Interaction;

public class Sorcier extends Personnage{

    private int idJoueur = 0;
    private Joueur joueur;

    private int idCarte = 0;
    private Quartier quartier;

    private boolean choix;

    Random random = new Random();

    public Sorcier() {
        super("Sorcier", 3, Caracteristiques.SORCIER);
    }

    @Override
    public void utiliserPouvoir() {

        System.out.println("De quel joueur souhaitez vous choisir la carte ?");
        for(int i = 0; i<getPlateau().getNombreJoueurs();i++){
            System.out.println(i+1 + " " + getPlateau().getJoueur(i).getNom());
        }

        System.out.print("Votre choix : ");
        idJoueur = Interaction.lireUnEntier(1, getPlateau().getNombreJoueurs())-1;

        joueur = getPlateau().getJoueur(idJoueur);

        System.out.println("Quel carte souhaitez vous choisir ?");
        for(int i = 0; i<joueur.getMain().size();i++){
            System.out.println(i+1 + " " + joueur.getMain().get(i).getNom());
        }

        System.out.print("Votre choix : ");
        idCarte = Interaction.lireUnEntier(1, joueur.getMain().size());

        quartier = joueur.getMain().get(idCarte);

        System.out.println("Souhaitez vous prendre la carte (oui) ou la construire (non)?");
        choix = Interaction.lireOuiOuNon();

        if(choix){
            //prendre la carte
            this.getJoueur().ajouterQuartierDansMain(quartier);
        } else {
            //construire la carte
            if (this.getJoueur() == null || this.getAssassine() == true) {
                return;
            }
            if (this.getJoueur().nbPieces() < quartier.coutConstruction) {
                System.out.println("Vous n'avez pas  d'or pour construire ce quartier");
                return;
            }
            this.getJoueur().ajouterQuartierDansCite(quartier);
            this.getJoueur().retirerPieces(quartier.coutConstruction);
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {

        idJoueur = random.nextInt(1, getPlateau().getNombreJoueurs())-1;

        joueur = getPlateau().getJoueur(idJoueur);

        idCarte = random.nextInt(1, joueur.getMain().size());

        quartier = joueur.getMain().get(idCarte);

        choix = random.nextBoolean();

        if(choix){
            //prendre la carte
            this.getJoueur().ajouterQuartierDansMain(quartier);
        } else {
            //construire la carte
            if (this.getJoueur() == null || this.getAssassine() == true) {
                return;
            }
            if (this.getJoueur().nbPieces() < quartier.coutConstruction) {
                System.out.println("Vous n'avez pas  d'or pour construire ce quartier");
                return;
            }
            this.getJoueur().ajouterQuartierDansCite(quartier);
            this.getJoueur().retirerPieces(quartier.coutConstruction);
        }

    }
    
}
