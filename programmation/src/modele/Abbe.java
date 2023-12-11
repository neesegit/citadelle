package modele;

import controleur.Interaction;

public class Abbe extends Personnage {

    private PlateauDeJeu plateau = getPlateau();
    private Pioche pioche = plateau.getPioche();

    private boolean boucle;
    private String choix;

    private int quelJoueur = 0;
    private int pasPlusRiche = 0;
    private int[] nbPiecesPersonnage = new int[plateau.getNombreJoueurs()];
    private String[] religieux = {"temple", "église", "monastère", "cathédrale"};
    
    private Joueur joueur;

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
            nbPiecesPersonnage[i] = plateau.getJoueur(i).nbPieces();
            if(nbPiecesPersonnage[i] == 0) pasPlusRiche++;
        }
        if(pasPlusRiche == plateau.getNombreJoueurs()) return;
        for(int i = 0; i < plateau.getNombreJoueurs(); i++){
            if(nbPiecesPersonnage[quelJoueur] < nbPiecesPersonnage[i]){
                quelJoueur = i;
            }
        }
        joueur = plateau.getJoueur(quelJoueur);
        joueur.retirerPieces(1);
        this.getJoueur().ajouterPieces((joueur.nbPieces() == 0?0:1));

    }

    @Override
    public void utiliserPouvoir(){
        //rien à faire ici
    }

    @Override
    public void utiliserPouvoirAvatar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'utiliserPouvoirAvatar'");
    }
}
