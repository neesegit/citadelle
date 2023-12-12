package modele;

import controleur.Interaction;

public class Abbe extends Personnage {

    private PlateauDeJeu plateau;
    private Pioche pioche;

    private boolean boucle;
    private String choix;

    private int quelJoueur = 0;
    private int pasPlusRiche = 0;
    private int[] nbPiecesPersonnage;
    private String[] religieux = { "temple", "église", "monastère", "cathédrale" };

    private Joueur joueur;

    public Abbe() {
        super("Abbe", 5, Caracteristiques.ABBE);
        this.plateau = getPlateau();
        if (this.plateau == null) {
            plateau = new PlateauDeJeu();
        }
        this.pioche = this.plateau.getPioche();
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        nbPiecesPersonnage = new int[plateau.getNombreJoueurs()];
        // AJOUT DE PIECE ET / OU DE CARTE

        for (int i = 0; i < religieux.length; i++) {
            boucle = true;
            if (this.getJoueur().quartierPresentDansCite(religieux[i])) {
                System.out.println("Pour le quartier " + religieux[i] + " dans votre cite, vous gagnez un choix :");
                while (boucle) {
                    System.out.print("Souhaitez vous récupérer une (p)ièce ou une (c)arte ? ");
                    choix = Interaction.lireUneChaine();
                    if (choix.equals("p")) {
                        this.getJoueur().ajouterPieces(1);
                        boucle = false;
                    } else if (choix.equals("c")) {
                        this.getJoueur().ajouterQuartierDansMain(pioche.piocher());
                        boucle = false;
                    }
                }
            }

        }
        nbPiecesPersonnage = new int[getPlateau().getNombreJoueurs()];
        // LE PERSONNAGE LE PLUS RICHE DONNE 1 PIECE D OR A L ABBE
        for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
            nbPiecesPersonnage[i] = getPlateau().getJoueur(i).nbPieces();
            if (nbPiecesPersonnage[i] == 0)
                pasPlusRiche++;
            System.out.println(nbPiecesPersonnage[i]);
        }
        if (pasPlusRiche == plateau.getNombreJoueurs())
            return;
        for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
            if (nbPiecesPersonnage[quelJoueur] < nbPiecesPersonnage[i]) {
                quelJoueur = i;
            }
        }
        joueur = getPlateau().getJoueur(quelJoueur);
        joueur.retirerPieces(1);
        this.getJoueur().ajouterPieces((joueur.nbPieces() == 0 ? 0 : 1));

    }

    @Override
    public void utiliserPouvoir() {}

    @Override
    public void utiliserPouvoirAvatar() {}
}
