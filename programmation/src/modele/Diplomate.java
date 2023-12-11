package modele;

public class Diplomate extends Personnage{

    private String[] militaire = {"tour de guet","prison","caserne","forteresse"};

    public Diplomate() {
        super("Diplomate", 8, Caracteristiques.DIPLOMATE);
        
    }
    
    @Override
    public void utiliserPouvoir() {
    }

    @Override
    public void utiliserPouvoirAvatar() {
    }
    
}
