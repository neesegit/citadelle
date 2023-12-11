package modele;

import java.util.Random;

import controleur.Interaction;

public class Diplomate extends Personnage{

    private String[] militaire = {"tour de guet","prison","caserne","forteresse"};

    private Quartier[] quartierJoueur;
    private Quartier[] quartierSecondJoueur;

    private Quartier quartierJoueurTemp;
    private Quartier quartierSecondJoueurTemp;

    private Personnage cible;

    private boolean choixON = false;

    Random random = new Random();

    public Diplomate() {
        super("Diplomate", 8, Caracteristiques.DIPLOMATE);
        this.quartierJoueur = getJoueur().getCite();
        
    }

    @Override
    public void percevoirRessourcesSpecifiques(){
        // 1 piece d'or pour chaque quartier noble dans sa cite
        for(int i = 0; i < militaire.length; i++){
            if (this.getJoueur().quartierPresentDansCite(militaire[i])) this.getJoueur().ajouterPieces(1);
        }
    }

    
    @Override
    public void utiliserPouvoir() {

        if(this.getJoueur().nbQuartiersDansCite() == 0) return;

        System.out.println("Voulez-vous echanger un de vos quartiers batis avec le quartier d'un autre joueur ? ");
        choixON = Interaction.lireOuiOuNon();

        if(choixON){
            //echange un de ses quartiers batis avec le quartier adverse
            System.out.println("Veuillez selectionner un joueur : ");
            for (int i = 0; i < getPlateau().getNombrePersonnages(); i++) System.out.println(i + 1 + " " + getPlateau().getPersonnage(i).getNom());// c'est le chiffre là

            System.out.println("Votre choix : ");
            int id = Interaction.lireUnEntier(1, getPlateau().getNombrePersonnages() + 1);
            cible = getPlateau().getPersonnage(id - 1);

            while(cible.getNom() == "Diplomate"){
                System.out.println("Vous ne pouvez pas choisir ce personnage");
                System.out.println("Votre choix : ");
                id = Interaction.lireUnEntier(1, getPlateau().getNombrePersonnages() + 1);
                cible = getPlateau().getPersonnage(id - 1);
            }

            quartierSecondJoueur = cible.getJoueur().getCite();

            System.out.println("Quel quartier voulez vous retirer de votre cité ?");
            for(int i = 0; i < this.quartierJoueur.length ;i++) System.out.println(i+1 + " " + this.quartierJoueur[i]);
            
            System.out.println("Votre choix : ");
            int idQuartier = Interaction.lireUnEntier(1, this.quartierJoueur.length)-1;

            System.out.println("Quel quartier voulez vous retirer de la cité de l'autre joueur ?");
            for(int i = 0; i < quartierSecondJoueur.length; i++) System.out.println(i+1 + " " + quartierSecondJoueur[i]);

            System.out.println("Votre choix : ");
            int idQuartierSecond = Interaction.lireUnEntier(1, quartierSecondJoueur.length)-1;

            quartierJoueurTemp = this.getJoueur().retirerQuartierDansCite(this.quartierJoueur[idQuartier].getNom());
            quartierSecondJoueurTemp = cible.getJoueur().retirerQuartierDansCite(quartierSecondJoueur[idQuartierSecond].getNom());

            this.getJoueur().ajouterQuartierDansCite(quartierSecondJoueurTemp);
            cible.getJoueur().ajouterQuartierDansCite(quartierJoueurTemp);
            
        }
        


    }

    @Override
    public void utiliserPouvoirAvatar() {

        if(random.nextBoolean()){
            //echange un de ses quartiers batis avec le quartier adverse
            
            int id = random.nextInt(1, getPlateau().getNombrePersonnages()+1);
            cible = getPlateau().getPersonnage(id - 1);

            while(cible.getNom() == "Diplomate"){
                System.out.println("Vous ne pouvez pas choisir ce personnage");
                id = random.nextInt(1, getPlateau().getNombrePersonnages()+1);
                cible = getPlateau().getPersonnage(id - 1);
            }

            quartierSecondJoueur = cible.getJoueur().getCite();

            int idQuartier = random.nextInt(this.quartierJoueur.length);

            int idQuartierSecond = random.nextInt(quartierSecondJoueur.length);

            quartierJoueurTemp = this.getJoueur().retirerQuartierDansCite(this.quartierJoueur[idQuartier].getNom());
            quartierSecondJoueurTemp = cible.getJoueur().retirerQuartierDansCite(quartierSecondJoueur[idQuartierSecond].getNom());

            this.getJoueur().ajouterQuartierDansCite(quartierSecondJoueurTemp);
            cible.getJoueur().ajouterQuartierDansCite(quartierJoueurTemp);
            
        }

    }
    
}
