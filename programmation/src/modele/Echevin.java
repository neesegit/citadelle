package modele;

import java.util.ArrayList;

import controleur.Interaction;

public class Echevin extends Personnage{

    private int idChoix1 = 0;
    private int idChoix2 = 0;
    private int idChoix3 = 0;

    private int vraiChoix = 0;

    private ArrayList<Joueur> tableauJoueurs = new ArrayList<Joueur>();

    public Echevin() {
        super("Echevin", 1, Caracteristiques.ECHEVIN);
    }

    @Override
    public void utiliserPouvoir() {

        for(int i = 0; i < getPlateau().getNombreJoueurs(); i++){
            tableauJoueurs.add(getPlateau().getJoueur(i));
        }

        for(int i = 0; i<tableauJoueurs.size(); i++){
            if(tableauJoueurs.get(i).getPersonnage().getNom() == "Echevin"){
                tableauJoueurs.remove(i);
                break;
            }
        }

        System.out.println("Veuillez choisir sur quel personnage vous voulez mettre vos jetons face caché");
        for(int i = 0; i<tableauJoueurs.size(); i++) System.out.println(i+1 + " " + tableauJoueurs.get(i).getPersonnage().getNom());

        System.out.print("Votre choix 1 :");
        idChoix1 = Interaction.lireUnEntier(1, tableauJoueurs.size())-1;
        while(idChoix1 == idChoix2){
            System.out.print("Votre choix 2 :");
            idChoix2 = Interaction.lireUnEntier(1, tableauJoueurs.size())-1;
            if(idChoix2 == idChoix1){
                System.out.println("Vous ne pouvez pas choisir le même personnage que précédemment");
            }
        }
        while(idChoix3 == idChoix2 || idChoix3 == idChoix1){
            System.out.print("Votre choix 3 :");
            idChoix3 = Interaction.lireUnEntier(1, tableauJoueurs.size())-1;
            if(idChoix3 == idChoix1 || idChoix3 == idChoix2){
                System.out.println("Vous ne pouvez pas choisir le même personnage que précédemment");
            }
        }

        System.out.println("Quel choix est il le vrai ?");
        System.out.println("1 Choix1");
        System.out.println("2 Choix2");
        System.out.println("3 Choix3");

        System.out.print("Votre choix : ");
        vraiChoix = Interaction.lireUnEntier(1, 3);

    }


    @Override
    public void utiliserPouvoirAvatar() {}



    //dans tour de jeu, vérifier après avoir construit un batiment si le
    //joueur à un jeton 


    public void revelerJeton() {

    }
    
}
