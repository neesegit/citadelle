package modele;

import java.util.Random;

import controleur.Interaction;

public class Cardinal extends Personnage{

    private int compteurQuartierReligieux = 0;
    private String[] religieux = {"temple", "église", "monastère", "cathédrale"};
    
    private Joueur monJoueur = this.getJoueur();

    private boolean choixON;
    private int choixNbPiece = 0;
    private boolean echange = false;

    private boolean bot = false;
    private boolean choixBot = true;

    private Pioche pioche;
    private PlateauDeJeu plateau;

    private Personnage cible;

    private Random random = new Random();

    public Cardinal(){
        super("Cardinal", 5, Caracteristiques.CARDINAL);
        this.plateau = getPlateau();
        if(this.plateau == null){
            plateau = new PlateauDeJeu();
        } 
        this.pioche = this.plateau.getPioche(); 
    }

    @Override
    public void percevoirRessourcesSpecifiques(){
        // ~ Ajout de 1 pièce par quartier religieux présent dans la cité
        //Ajout de 1 quartier
        for(int i = 0; i < religieux.length; i++){
            if(monJoueur.quartierPresentDansCite(religieux[i])) compteurQuartierReligieux++;
        }
        //monJoueur.ajouterPieces(compteurQuartierReligieux);
        this.getJoueur().ajouterQuartierDansMain(pioche.piocher());
    }

    @Override
    public void construire(Quartier nouveau){
        if(this.getDejaConstruit()){
            if(this.getJoueur() == null || this.getAssassine() == true) return;
            if(this.getJoueur().nbPieces() < nouveau.coutConstruction){
                if(bot){
                    while(choixBot){
                        int id = random.nextInt(this.plateau.getNombrePersonnages())+1;
                        cible = this.plateau.getPersonnage(id-1);
                        if(cible.getNom() != "Cardinal") choixBot = false;
                    }
                    choixNbPiece = random.nextInt(cible.getJoueur().nbPieces());
                    for(int i = 0; i < choixNbPiece; i++) cible.getJoueur().ajouterQuartierDansMain(this.getJoueur().retirerQuartierDansMain());
                    this.getJoueur().ajouterPieces(choixNbPiece);
                } else {
                    System.out.print("Voulez vous prendre une ou plusieurs pièces à un autre joueur en échange de cartes ? ");
                    choixON = Interaction.lireOuiOuNon();
                    if(choixON){
                        System.out.println("A quel personnage voulez-vous prendre des pièces ?");
                        for (int i = 0; i < getPlateau().getNombrePersonnages(); i++) {
                            System.out.println(i + 1 + " " + getPlateau().getPersonnage(i).getNom() + " " + getJoueur().nbPieces());
                        }
                        while (!echange) {
                            System.out.println("Votre choix : ");
                            int id = Interaction.lireUnEntier(1, getPlateau().getNombrePersonnages() + 1);
                            cible = getPlateau().getPersonnage(id - 1);
                            if (cible.getNom() == "Cardinal") {// je sais pas s'il y a des limite de rang a son pouvoir
                                System.out.println("Vous ne pouvez pas choisir ce personnage");
                            } else {
                                echange = true;
                            }
                        }
                        System.out.print("Combien de pieces voulez vous de "+cible.getNom()+" ? (" + cible.getJoueur().nbPieces()+" pieces) ");
                        choixNbPiece = Interaction.lireUnEntier(0, cible.getJoueur().nbPieces());
                        for(int i = 0; i < choixNbPiece; i++) cible.getJoueur().ajouterQuartierDansMain(this.getJoueur().retirerQuartierDansMain());
                        this.getJoueur().ajouterPieces(choixNbPiece);

                    } else {
                        System.out.println("Vous n'avez pas d'or pour construire ce quartier");
                        return;
                    }
                }
            }
            this.getJoueur().ajouterQuartierDansCite(nouveau);
            this.setDejaConstruit(false);
        }
        System.out.println("Vous avez construit un quartier dans ce tour");
        
    }


    @Override
    public void utiliserPouvoir(){}

    @Override
    public void utiliserPouvoirAvatar(){bot = true;}
}
