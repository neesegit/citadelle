package modele;

import java.util.Random;

import controleur.Interaction;

public class Empereur extends Personnage {

    private String[] noble = {"manoir","château","palais"};

    private PlateauDeJeu plateau;
    private Personnage cible;
    private String choix;

    private boolean choixBot = true;

    private Random random = new Random();

    public Empereur() {
        super("Empereur", 4, Caracteristiques.EMPEREUR);
        this.plateau = getPlateau();
        if(this.plateau == null) plateau = new PlateauDeJeu();
    }

    @Override
    public void percevoirRessourcesSpecifiques(){
        // 1 piece d'or pour chaque quartier noble dans sa cite
        for(int i = 0; i < noble.length; i++){
            if (this.getJoueur().quartierPresentDansCite(noble[i])) this.getJoueur().ajouterPieces(1);
        }
    }

    @Override
    public void utiliserPouvoir() {
        // Donne couronne contre 1 pièce ou 1 carte
        System.out.println("A quel personnage voulez-vous donner la couronne ?");
        for (int i = 0; i < getPlateau().getNombrePersonnages(); i++) {
            System.out.println(i + 1 + " " + getPlateau().getPersonnage(i).getNom() + " " + getJoueur().nbPieces());
        }
        System.out.println("Votre choix : ");
        int id = Interaction.lireUnEntier(1, getPlateau().getNombrePersonnages() + 1);
        cible = getPlateau().getPersonnage(id - 1);
        this.getJoueur().setPossedeCouronne(false);
        cible.getJoueur().setPossedeCouronne(true);

        System.out.print("Voulez vous une (p)ièce ou une (c)arte ? ");
        choix = Interaction.lireUneChaine();
        if(choix.equals("p")){
            this.getJoueur().ajouterPieces(1);
            cible.getJoueur().retirerPieces(1);
        } else if(choix.equals("c")){
            this.getJoueur().ajouterQuartierDansMain(cible.getJoueur().retirerQuartierDansMain());
        }

    }

    @Override
    public void utiliserPouvoirAvatar() {
        // pareil mais avec un bot

        while(choixBot){
            int id = random.nextInt(this.plateau.getNombrePersonnages())+1;
            cible = this.plateau.getPersonnage(id-1);
            if(cible.getNom() != "Empereur") choixBot = false;
        }

        this.getJoueur().setPossedeCouronne(false);
        cible.getJoueur().setPossedeCouronne(true);

        boolean choixPCBot = random.nextBoolean();
        if(choixPCBot){
            this.getJoueur().ajouterPieces(1);
            cible.getJoueur().retirerPieces(1);
        } else {
            this.getJoueur().ajouterQuartierDansMain(cible.getJoueur().retirerQuartierDansMain());
        }
    }
    
}
