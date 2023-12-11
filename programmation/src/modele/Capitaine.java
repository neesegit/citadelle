package modele;

import java.util.Random;

import controleur.Interaction;

public class Capitaine extends Personnage{

    private String[] militaire = {"tour de guet","prison","caserne","forteresse"};

    private Quartier[] quartierSecondJoueur;
    private Quartier[] moinsEgaleDeTrois;

    private Quartier choixQuartier;

    private boolean choixON;
    private Personnage cible;

    Random random = new Random();

    public Capitaine() {
        super("Capitaine", 8, Caracteristiques.CAPITAINE);
    }

    @Override
    public void percevoirRessourcesSpecifiques(){
        for(int i = 0; i < militaire.length; i++){
            if (this.getJoueur().quartierPresentDansCite(militaire[i])) this.getJoueur().ajouterPieces(1);
        }
    }

    @Override
    public void utiliserPouvoir() {

        if(this.getJoueur().nbPieces() == 0){
            System.out.println("Vous ne pouvez pas utiliser votre pouvoir");
            return;
        }

        System.out.println("Voulez-vous confisquer un quartier à un joueur ? ");
        choixON = Interaction.lireOuiOuNon();

        if(choixON){
            
            System.out.println("Veuillez selectionner un joueur : ");
            for (int i = 0; i < getPlateau().getNombrePersonnages(); i++) System.out.println(i + 1 + " " + getPlateau().getPersonnage(i).getNom());// c'est le chiffre là

            System.out.println("Votre choix : ");
            int id = Interaction.lireUnEntier(1, getPlateau().getNombrePersonnages() + 1);
            cible = getPlateau().getPersonnage(id - 1);

            while(cible.getNom() == "Capitaine"){
                System.out.println("Vous ne pouvez pas choisir ce personnage");
                System.out.println("Votre choix : ");
                id = Interaction.lireUnEntier(1, getPlateau().getNombrePersonnages() + 1);
                cible = getPlateau().getPersonnage(id - 1);
            }

            quartierSecondJoueur = cible.getJoueur().getCite();

            for(int i = 0; i < quartierSecondJoueur.length; i++){
                if(quartierSecondJoueur[i].getCout() <= 3) moinsEgaleDeTrois[i] = quartierSecondJoueur[i];
            }

            System.out.println("Veuillez selectionner un quartier : ");
            for(int i = 0; i < moinsEgaleDeTrois.length; i++) System.out.println(i + 1 + " " + moinsEgaleDeTrois[i]);

            System.out.println("Votre choix : ");
            int idChoixQuartier = Interaction.lireUnEntier(1, moinsEgaleDeTrois.length);

            choixQuartier = moinsEgaleDeTrois[idChoixQuartier-1];

            while(this.getJoueur().nbPieces() < choixQuartier.getCout()){
                System.out.println("Vous n'avez pas asser d'argent pour acheter ce quartier");
                System.out.println("Votre choix : ");
                idChoixQuartier = Interaction.lireUnEntier(1, moinsEgaleDeTrois.length);

                choixQuartier = moinsEgaleDeTrois[idChoixQuartier-1];
            }

            this.getJoueur().ajouterQuartierDansMain(choixQuartier);
            this.getJoueur().retirerPieces(choixQuartier.getCout());
            cible.getJoueur().ajouterPieces(choixQuartier.getCout());
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {

        if(this.getJoueur().nbPieces() == 0){
            System.out.println("Vous ne pouvez pas utiliser votre pouvoir");
            return;
        }

        if(random.nextBoolean()){
            
            
            int id = random.nextInt(getPlateau().getNombrePersonnages())+1;
            cible = getPlateau().getPersonnage(id - 1);

            while(cible.getNom() == "Capitaine"){
                id = random.nextInt(1, getPlateau().getNombrePersonnages()+1);
                cible = getPlateau().getPersonnage(id - 1);
            }

            quartierSecondJoueur = cible.getJoueur().getCite();

            for(int i = 0; i < quartierSecondJoueur.length; i++){
                if(quartierSecondJoueur[i].getCout() <= 3) moinsEgaleDeTrois[i] = quartierSecondJoueur[i];
            }

            int idChoixQuartier = random.nextInt(1, moinsEgaleDeTrois.length);

            choixQuartier = moinsEgaleDeTrois[idChoixQuartier-1];

            while(this.getJoueur().nbPieces() < choixQuartier.getCout()){
                idChoixQuartier = random.nextInt(1, moinsEgaleDeTrois.length);

                choixQuartier = moinsEgaleDeTrois[idChoixQuartier-1];
            }

            this.getJoueur().ajouterQuartierDansMain(choixQuartier);
            this.getJoueur().retirerPieces(choixQuartier.getCout());
            cible.getJoueur().ajouterPieces(choixQuartier.getCout());
        }
    }
    
}
