package modele;

public class Echevin extends Personnage{


    /*
     * REGLE JETON : 
     * 0 = PAS POSE
     * 1 = FACE CACHEE
     * 2 = REVELER
     */
    private int jeton1 = 0;
    private int jeton2 = 0;
    private int jeton3 = 0;

    //private int[] tableauJeton = {"jeton1": 0};

    public Echevin() {
        super("Echevin", 1, Caracteristiques.ECHEVIN);
    }

    @Override
    public void utiliserPouvoir() {}

    @Override
    public void utiliserPouvoirAvatar() {}
    
}
