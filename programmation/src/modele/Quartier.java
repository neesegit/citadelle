package modele;

public class Quartier {
    //set the variable
    String nom, type, caracteristiques;
    int coutConstruction;
    public static final String[] TYPE_QUARTIERS = {"RELIGIEUX","MILITAIRE", "NOBLE", "COMMERCANT", "MERVEILLE"};

    public String getNom(){
        return this.nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public String getType(){
        return this.type;
    }

    public static final int RANG_POUVOIR_MAX = 8;

    public void setType(String type){
        // Utilisation de equals pour comparer les chaînes de caractères
        if (type.equals(this.TYPE_QUARTIERS[0]) || type.equals(this.TYPE_QUARTIERS[1]) ||
                type.equals(this.TYPE_QUARTIERS[2]) || type.equals(this.TYPE_QUARTIERS[3]) ||
                type.equals(this.TYPE_QUARTIERS[4])){
            this.type = type;
        } else {
            this.type = "";
        }
    }

    public int getCout(){
        return this.coutConstruction;
    }

    public void setCout(int coutConstruction){
        // verifier que le cout est bien entre 1 et 6
        // si le cout est de 0 exploit ??
        if (coutConstruction > 0 && coutConstruction < 7 ){
            this.coutConstruction = coutConstruction;
        }else {
            this.coutConstruction = 0;
        }
    }

    public String getCaracteristiques(){
        return this.caracteristiques;
    }

    public void setCaracteristiques(String caracteristiques){
        this.caracteristiques = caracteristiques;
    }

    //set les données du quartier
    public Quartier(String nom, String type, int coutConstruction, String caracteristiques){
        this.nom = nom;
        this.type = type;
        this.coutConstruction = coutConstruction;
        this.caracteristiques = caracteristiques;
    }

    //permet de donner des données par défault
    public Quartier(){
        this("","",0,"");
    }

    //permet de créer un quartier en laissant la caracteristique vide
    public Quartier(String nom, String type, int coutConstruction){
        this(nom, type, coutConstruction,"");
    }
}