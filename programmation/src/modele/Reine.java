package modele;

public class Reine extends Personnage{

    private Joueur[] tableauJoueurs = new Joueur[getPlateau().getNombreJoueurs()];
    private int idReine = 0;
    private int gaucheReine;
    private int droiteReine;

    public Reine() {
        super("Reine", 4, Caracteristiques.REINE);
    }

    @Override
    public void percevoirRessourcesSpecifiques(){
        for(int i = 0; i < tableauJoueurs.length; i++){
            tableauJoueurs[i] = getPlateau().getJoueur(i);
        }

        for(int i = 0; i< tableauJoueurs.length; i++){
            if(tableauJoueurs[i].getPersonnage().getNom() == "Reine"){
                idReine = i;
            }
        }

        gaucheReine = idReine-1;
        droiteReine = idReine+1;

        if((idReine-1) < 0){
            gaucheReine = tableauJoueurs.length-1;
        }
        if((idReine+1)>tableauJoueurs.length-1){
            droiteReine = 0;
        }

        if(tableauJoueurs[gaucheReine].getPersonnage().getRang() == 4) this.getJoueur().ajouterPieces(3);
        if(tableauJoueurs[droiteReine].getPersonnage().getRang() == 4) this.getJoueur().ajouterPieces(3);

    }

    @Override
    public void utiliserPouvoir() {}

    @Override
    public void utiliserPouvoirAvatar() {}
    
}
