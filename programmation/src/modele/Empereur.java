package modele;

public class Empereur extends Personnage {

    public Empereur() {
        super("Empereur", 4, Caracteristiques.EMPEREUR);
    }

    @Override
    public void percevoirRessourcesSpecifiques(){
        //TODO 1 piece d'or pour chaque quartier noble dans sa cite 
    }

    @Override
    public void utiliserPouvoir() {
        // TODO Donne couronne contre 1 pièce ou 1 carte
        throw new UnsupportedOperationException("Unimplemented method 'utiliserPouvoir'");
    }

    @Override
    public void utiliserPouvoirAvatar() {
        // TODO pareil mais avec un bot
        throw new UnsupportedOperationException("Unimplemented method 'utiliserPouvoirAvatar'");
    }
    
}
