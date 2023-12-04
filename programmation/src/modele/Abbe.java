package modele;

import controleur.Interaction;

public class Abbe extends Personnage {

    private PlateauDeJeu plateau = getPlateau();
    private Pioche pioche = plateau.getPioche();

    private boolean boucle;
    private String choix;

    private int quelJoueur = 0;
    private int[] nbPiecesPersonnage = new int[plateau.getNombreJoueurs()];
    private String[] religieux = {"temple", "église", "monastère", "cathédrale"};
    
    public Abbe(){
        super("Abbe", 5, Caracteristiques.ABBE);
    }

    @Override
    public void percevoirRessourcesSpecifiques(){

        // AJOUT DE PIECE ET / OU DE CARTE

        for(int i = 0; i < religieux.length; i++){
            boucle = true;
            if (this.getJoueur().quartierPresentDansCite(religieux[i])) {
                while(boucle){
                    System.out.print("Souhaitez vous récupérer une (p)ièce ou une (c)arte ? ");
                    choix = Interaction.lireUneChaine();
                    if(choix == "p"){
                        this.getJoueur().ajouterPieces(1);
                        boucle = false;
                    } else if(choix == "c"){
                        this.getJoueur().ajouterQuartierDansMain(pioche.piocher());
                        boucle = false;
                    }
                }    
            }
            
        }

        // LE PERSONNAGE LE PLUS RICHE DONNE 1 PIECE D OR A L ABBE

        for(int i = 0; i < plateau.getNombreJoueurs(); i++){
            // TODO voir le personnage avec le plus d'or
            nbPiecesPersonnage[i] = plateau.getJoueur(i).nbPieces();
        }
        for(int i = 0; i < plateau.getNombreJoueurs(); i++){
            if(nbPiecesPersonnage[quelJoueur] < nbPiecesPersonnage[i]){
                quelJoueur = i;
            }
        }
        plateau.getJoueur(quelJoueur).retirerPieces(1);
        this.getJoueur().ajouterPieces(1);

    }

    @Override
    public void utiliserPouvoir(){
        //rien à faire ici
    }
}
