package modele;

public class Patricien extends Personnage {

    private String[] noble = {"manoir","château","palais"};

    public Patricien() {
        super("Patricien", 4, Caracteristiques.PATRICIEN);
        
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        // 1 piece d'or pour chaque quartier noble dans sa cite
        for(int i = 0; i < noble.length; i++){
            if (this.getJoueur().quartierPresentDansCite(noble[i])) this.getJoueur().ajouterPieces(1);
        }
    }

    @Override
    public void utiliserPouvoir() {}

    @Override
    public void utiliserPouvoirAvatar() {}
    
}
