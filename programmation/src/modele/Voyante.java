package modele;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;

public class Voyante extends Personnage {

    private ArrayList<Joueur> joueur = new ArrayList<Joueur>();

    private Personnage cible;

    private int permisDeConstruire = 2;
    private boolean choixON = false;


    Random random = new Random();

    public Voyante() {
        super("Voyante", 3, Caracteristiques.VOYANTE);
    }


    @Override
    public void construire(Quartier nouveau){
        if(this.getJoueur().isBot()){
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
                this.getJoueur().retirerPieces(nouveau.coutConstruction);
                permisDeConstruire--;
                if(permisDeConstruire > 0) choixON = random.nextBoolean();
                if(choixON){
                    nouveau = this.getJoueur().retirerQuartierDansMain();
                    choixON = false;
                }
            }    
        } else {
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
                this.getJoueur().retirerPieces(nouveau.coutConstruction);
                permisDeConstruire--;
                if(permisDeConstruire > 0){
                    System.out.println("Voulez vous construire à nouveau (reste "+permisDeConstruire+" nombre de construction) ? ");
                    System.out.print("Veuillez répondre par oui ou par non : ");
                    choixON = Interaction.lireOuiOuNon();
                }
                if(choixON){
                    nouveau = this.getJoueur().retirerQuartierDansMain();
                    choixON = false;
                }
            }
        }
    }






    @Override
    public void utiliserPouvoir() {
        
        
        ArrayList<Quartier> CopieCartesSelectionne = new ArrayList<Quartier>();
        for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
            if (getPlateau().getJoueur(i).nbQuartiersDansMain() != 0) {
                Quartier CarteDelete = getPlateau().getJoueur(i).retirerQuartierDansMain();
                CopieCartesSelectionne.add(CarteDelete);
                joueur.add(getPlateau().getJoueur(i));
            }
        }



        
        for(int i = 0; i < joueur.size(); i++){
            this.getJoueur().ajouterQuartierDansMain(CopieCartesSelectionne.get(i));
        }
        while(joueur.size() != 0){
            
            System.out.println("Vous devez redonner une carte quartier de votre choix a chaque joueur qui vous a donné une carte");

            System.out.println("Avec quel personnage voulez-vous donner votre carte ?");
            for(int i = 0; i < joueur.size(); i++){
                System.out.println(i+1 + " " + joueur.get(i).getPersonnage().getNom());
            }

            System.out.print("Votre choix : ");
            int id = Interaction.lireUnEntier(1, joueur.size());
            cible = getPlateau().getPersonnage(id-1);

            System.out.println("Choisissez un quartier à donner : ");
            for(int i = 0; i < CopieCartesSelectionne.size(); i++){
                System.out.println(i+1 + " " + CopieCartesSelectionne.get(i).getNom());
            }

            System.out.print("Votre choix : ");
            int idQuartier = Interaction.lireUnEntier(1, CopieCartesSelectionne.size());
            cible.getJoueur().ajouterQuartierDansMain(CopieCartesSelectionne.get(idQuartier-1));

            CopieCartesSelectionne.remove(idQuartier-1);
            joueur.remove(id-1);
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {
        ArrayList<Quartier> CopieCartesSelectionne = new ArrayList<Quartier>();
        for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
            if (getPlateau().getJoueur(i).nbQuartiersDansMain() != 0) {
                Quartier CarteDelete = getPlateau().getJoueur(i).retirerQuartierDansMain();
                CopieCartesSelectionne.add(CarteDelete);
                joueur.add(getPlateau().getJoueur(i));
            }
        }



        
        for(int i = 0; i < joueur.size(); i++){
            this.getJoueur().ajouterQuartierDansMain(CopieCartesSelectionne.get(i));
        }
        while(joueur.size() != 0){
            
            int id = random.nextInt(1, joueur.size());
            cible = getPlateau().getPersonnage(id-1);

            int idQuartier = random.nextInt(1, CopieCartesSelectionne.size());
            cible.getJoueur().ajouterQuartierDansMain(CopieCartesSelectionne.get(idQuartier-1));

            CopieCartesSelectionne.remove(idQuartier-1);
            joueur.remove(id-1);
        }
    }
}
