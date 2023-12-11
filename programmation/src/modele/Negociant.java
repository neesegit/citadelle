package modele;

public class Negociant extends Personnage{

    private String[] commercant = {"taverne", "échoppe", "marché", "comptoir", "port", "hôtel de ville"};

    private boolean isCommercant = false;

    public Negociant() {
        super("Negociant", 6, Caracteristiques.NEGOCIANT);
    }

    @Override
    public void percevoirRessourcesSpecifiques(){
        for(int i = 0; i < commercant.length; i++){
            if (this.getJoueur().quartierPresentDansCite(commercant[i])) this.getJoueur().ajouterPieces(1);
        }
    }
    @Override
    public void construire(Quartier nouveau){

        for(int i = 0; i < commercant.length; i++){
            if(nouveau.getType() == commercant[i]) isCommercant = true;
        }

        if (this.getDejaConstruit()) {
            if(isCommercant){
                if (this.getJoueur() == null || this.getAssassine() == true) {
                    return;
                }
                if (this.getJoueur().nbPieces() < nouveau.coutConstruction) {
                    System.out.println("Vous n'avez pas  d'or pour construire ce quartier");
                    return;
                }
                this.getJoueur().ajouterQuartierDansCite(nouveau);
                this.setDejaConstruit(false);
            } else {
                if (this.getJoueur() == null || this.getAssassine() == true) {
                    return;
                }
                if (this.getJoueur().nbPieces() < nouveau.coutConstruction) {
                    System.out.println("Vous n'avez pas  d'or pour construire ce quartier");
                    return;
                }
                this.getJoueur().ajouterQuartierDansCite(nouveau);
                this.getJoueur().retirerPieces(nouveau.coutConstruction);
                this.setDejaConstruit(false);
            }
            System.out.println("Vous construit un quartier dans ce tour");
        }

    }

    @Override
    public void utiliserPouvoir() {}

    @Override
    public void utiliserPouvoirAvatar() {}
    
}
