package modele;

public class Cardinal extends Personnage{

    private int compteurQuartierReligieux = 0;
    private String[] religieux = {"temple", "église", "monastère", "cathédrale"};
    
    private Joueur monJoueur = this.getJoueur();

    public Cardinal(){
        super("Cardinal", 5, Caracteristiques.CARDINAL);
    }

    @Override
    public void utiliserPouvoir(){
        //Ajout de 1 pièce par quartier religieux présent dans la cité
        for(int i = 0; i < religieux.length; i++){
            if(monJoueur.quartierPresentDansCite(religieux[i])) compteurQuartierReligieux++;
        }
        monJoueur.ajouterPieces(compteurQuartierReligieux);

        //Vérifier que le Joueur à assez d'or pour batir un quartier, sinon
        //peut prendre de l'or en échange de carte à un autre joueur
    }
}
