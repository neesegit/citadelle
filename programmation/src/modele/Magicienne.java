package modele;

import controleur.Interaction;
import java.util.ArrayList;

public class Magicienne extends Personnage{

    private boolean echange = false, choix = false;
    public Magicienne(){
        super("Magicienne",3, Caracteristiques.MAGICIENNE);
        ArrayList<Quartier> mainCopieMagicienne = new ArrayList<Quartier>();
        ArrayList<Quartier> mainCopieCible = new ArrayList<Quartier>();
    }

    @Override
    public void utiliserPouvoir() {
        if (getJoueur().getMain() == <>){ // acune idée de comment dire que la main est vide
            System.out.println("Vous n'avez pas de cartes donc vous ne pouvez rien faire");
        }else{
            while (!choix) {
                System.out.println("Vous pouvez echanger toutes vos cartes (écrire 'toutes'), juste certaines (écrire 'certaines') ou aucune (écrire 'aucune')");
                String reponse = Interaction.lireUneChaine();



                if (reponse == "toutes") {
                    choix = true;
                    System.out.println("Avec quel personnage voulez-vous echanger vos cartes ?");
                    for(int i = 0; i < getPlateau().getNombrePersonnages(); i++) {//TODO : faire que le joueur ne soit pas visible et aussi que les chiffres moches ne soit plus là
                        System.out.println(i+1 + " " + getPlateau().getPersonnage(i) + " " + getJoueur(i).nbQuartiersDansMain());//c'est le chiffre là
                    }
                    while (!echange) {
                        System.out.println("Votre choix : ");
                        int id = Interaction.lireUnEntier(1, getPlateau().getNombrePersonnages());
                        Personnage cible = getPlateau().getPersonnage(id - 1);
                        if (cible.getNom() == "Magicienne") {//je sais pas s'il y a des limite de rang a son pouvoir
                            System.out.println("Vous ne pouvez pas voler ce personnage");
                        } else {
                            echange = true;
                        }
                    }
                    //mtn il faut que je copie la main du joueur pour que ce soit la main de la magicienne
                    for (int i = 0; i < getJoueur().nbQuartiersDansMain(); i++){
                        String copie = getJoueur().getMain().get(i);// varaiable java le get// on choisi un a un les quartiers de la liste
                        mainCopieMagicienne.add(copie);// pour les copié dans la main copie
                    }

                    //donc on utilise la copie de magicienne et de la cible en attendant
                    // on vide a main de la magicienne et de la cible
                    //on redonne a la magicienne
                    //on redonne a la cible


                } else if (reponse == "certaines") {
                    //faire la même que pour toutes mais ici il peut choisir les quelles :)))))))))))
                    choix = true;
                } else if (reponse == "aucune") {
                    choix = true;
                } else {
                    System.out.println("Verifier que vous avez bien écris votre choix 'toutes' / 'certaines' / 'aucune'");
                }
            }
        }
    }
}