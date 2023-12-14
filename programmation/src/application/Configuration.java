package application;

import controleur.Interaction;
import modele.*;

public class Configuration {

    private static Personnage abbe=new Abbe();
    private static Personnage alchimiste=new Alchimiste();
    private static Personnage archiviste=new Archiviste();
    private static Personnage assassin = new Assassin();
    private static Personnage architecte = new Architecte();
    private static Personnage capitaine=new Capitaine();
    private static Personnage cardinal=new Cardinal();
    private static Personnage condotierre = new Condottiere();
    private static Personnage diplomate=new Diplomate();
    private static Personnage eveque = new Eveque();
    private static Personnage espion=new Espion();
    private static Personnage magicienne = new Magicienne();
    private static Personnage marchande = new Marchande();
    private static Personnage negociant=new Negociant();
    private static Personnage navigatrice=new Navigatrice();
    private static Personnage roi = new Roi();
    private static Personnage voleur = new Voleur();
    
    private static Quartier temple = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
    private static Quartier eglise = new Quartier("église", Quartier.TYPE_QUARTIERS[0], 2);
    private static Quartier monastere = new Quartier("monastère", Quartier.TYPE_QUARTIERS[0], 3);
    private static Quartier cathedrale = new Quartier("cathédrale", Quartier.TYPE_QUARTIERS[0], 5);

    private static Quartier tour_guet = new Quartier("tour de guet", Quartier.TYPE_QUARTIERS[1], 1);
    private static Quartier prison = new Quartier("prison", Quartier.TYPE_QUARTIERS[1], 2);
    private static Quartier caserne = new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3);
    private static Quartier forteresse = new Quartier("forteresse", Quartier.TYPE_QUARTIERS[1], 5);

    private static Quartier manoir = new Quartier("manoir", Quartier.TYPE_QUARTIERS[2], 3);
    private static Quartier chateau = new Quartier("château", Quartier.TYPE_QUARTIERS[2], 4);
    private static Quartier palais = new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5);

    private static Quartier taverne = new Quartier("tarvene", Quartier.TYPE_QUARTIERS[3], 1);
    private static Quartier echope = new Quartier("échoppe", Quartier.TYPE_QUARTIERS[3], 2);
    private static Quartier marche = new Quartier("marché", Quartier.TYPE_QUARTIERS[3], 2);
    private static Quartier comptoir = new Quartier("comptoir", Quartier.TYPE_QUARTIERS[3], 3);
    private static Quartier port = new Quartier("port", Quartier.TYPE_QUARTIERS[3], 4);
    private static Quartier hotel_ville = new Quartier("hôtel de ville", Quartier.TYPE_QUARTIERS[3], 5);

    
    private static Quartier bibliotheque = new Quartier("Bibliothèque", Quartier.TYPE_QUARTIERS[4], 6);
    private static Quartier forge = new Quartier("Forge", Quartier.TYPE_QUARTIERS[4], 5);
    private static Quartier carriere = new Quartier("Carrière", Quartier.TYPE_QUARTIERS[4], 5);
    private static Quartier laboratoire = new Quartier("Laboratoire", Quartier.TYPE_QUARTIERS[4], 5);
    private static Quartier cour_miracle = new Quartier("Cour des Miracles", Quartier.TYPE_QUARTIERS[4], 2);
    private static Quartier manufacture = new Quartier("Manufacture", Quartier.TYPE_QUARTIERS[4], 5);
    private static Quartier donjon = new Quartier("Donjon", Quartier.TYPE_QUARTIERS[4], 3);
    private static Quartier salle_cartes = new Quartier("Salle des Cartes", Quartier.TYPE_QUARTIERS[4], 5);
    private static Quartier dracoport = new Quartier("Dracoport", Quartier.TYPE_QUARTIERS[4], 6);
    private static Quartier statue_equestre = new Quartier("Statue Équestre", Quartier.TYPE_QUARTIERS[4], 3);
    private static Quartier ecole_magie = new Quartier("École de Magie", Quartier.TYPE_QUARTIERS[4], 6);
    private static Quartier tresor_imperial = new Quartier("Trésor Impérial", Quartier.TYPE_QUARTIERS[4], 5);
    private static Quartier fontaine_souhaits = new Quartier("Fontaine aux Souhaits", Quartier.TYPE_QUARTIERS[4], 5);
    private static Quartier tripot = new Quartier("Tripot", Quartier.TYPE_QUARTIERS[4], 6);


    private static Quartier basilique = new Quartier("Basilique", Quartier.TYPE_QUARTIERS[4], 4);
    private static Quartier capitole = new Quartier("Capitole", Quartier.TYPE_QUARTIERS[4], 5);
    private static Quartier catacombes = new Quartier("Catacombes", Quartier.TYPE_QUARTIERS[4], 0);
    private static Quartier chantier = new Quartier("Chantier", Quartier.TYPE_QUARTIERS[4], 3);
    private static Quartier ecuries = new Quartier("Ecuries", Quartier.TYPE_QUARTIERS[4], 2);
    private static Quartier grande_muraille = new Quartier("Grande Muraille", Quartier.TYPE_QUARTIERS[4], 6);
    private static Quartier hospice = new Quartier("Hospice", Quartier.TYPE_QUARTIERS[4], 4);
    private static Quartier mine_dor = new Quartier("Mine d'Or", Quartier.TYPE_QUARTIERS[4], 6);
    private static Quartier monument = new Quartier("Monument", Quartier.TYPE_QUARTIERS[4], 4);
    private static Quartier musee = new Quartier("Musée", Quartier.TYPE_QUARTIERS[4], 4);
    private static Quartier necropole = new Quartier("Nécropole", Quartier.TYPE_QUARTIERS[4], 5);
    private static Quartier observatoire = new Quartier("Observatoire", Quartier.TYPE_QUARTIERS[4], 4);
    private static Quartier parc = new Quartier("Parc", Quartier.TYPE_QUARTIERS[4], 6);
    private static Quartier poudriere = new Quartier("Poudri", Quartier.TYPE_QUARTIERS[4], 3);
    private static Quartier theatre = new Quartier("Théâtre", Quartier.TYPE_QUARTIERS[4], 6);
    private static Quartier tour_divoire = new Quartier("Tour d’Ivoire", Quartier.TYPE_QUARTIERS[4], 6);

    
	public static Pioche nouvellePioche() {
		
		Pioche pioche = new Pioche();
		
		for (int i = 0; i < 2; i++) {
            pioche.ajouter(cathedrale);
            pioche.ajouter(forteresse);
            pioche.ajouter(hotel_ville);
        }
        for (int i = 0; i < 3; i++) {
            pioche.ajouter(monastere);
            pioche.ajouter(eglise);
            pioche.ajouter(temple);
            pioche.ajouter(tour_guet);
            pioche.ajouter(prison);
            pioche.ajouter(caserne);
            pioche.ajouter(palais);
            pioche.ajouter(echope);
            pioche.ajouter(comptoir);
            pioche.ajouter(port);
        }
        for (int i = 0; i < 4; i++) {
            pioche.ajouter(marche);
            pioche.ajouter(chateau);
        }
        for (int i = 0; i < 5; i++) {
            pioche.ajouter(taverne);
            pioche.ajouter(manoir);
        }

        pioche.melanger();

        return pioche;
	}
	
	public static PlateauDeJeu configurationDeBase(Pioche pioche) {
		
		PlateauDeJeu plateauDeJeu = new PlateauDeJeu();

		plateauDeJeu.ajouterPersonnage(architecte);
		plateauDeJeu.ajouterPersonnage(assassin);
		plateauDeJeu.ajouterPersonnage(condotierre);
		plateauDeJeu.ajouterPersonnage(eveque);
		plateauDeJeu.ajouterPersonnage(magicienne);
		plateauDeJeu.ajouterPersonnage(marchande);
		plateauDeJeu.ajouterPersonnage(roi);
		plateauDeJeu.ajouterPersonnage(voleur);
        plateauDeJeu.ajouterPersonnage(abbe);
        plateauDeJeu.ajouterPersonnage(alchimiste);
        plateauDeJeu.ajouterPersonnage(archiviste);
        plateauDeJeu.ajouterPersonnage(capitaine);
        plateauDeJeu.ajouterPersonnage(cardinal);
        plateauDeJeu.ajouterPersonnage(diplomate);
        plateauDeJeu.ajouterPersonnage(espion);
        plateauDeJeu.ajouterPersonnage(navigatrice);
        plateauDeJeu.ajouterPersonnage(negociant);

		System.out.println("Combien de joueur(s) pour cette partie (max : 9) ?");
        int nbJoueur=Interaction.lireUnEntier(1, 9);
        for(int i=0;i<nbJoueur;i++){

            System.out.print("Entrez le nom du joueur " + (i + 1) + " : ");
            String nomDuJoueur=Interaction.lireUneChaine();
            Joueur nouveauJoueur=new Joueur(nomDuJoueur);
            nouveauJoueur.setType(false);
            plateauDeJeu.ajouterJoueur(nouveauJoueur);
        }
        System.out.println("Voulez-vous ajouter des bots ?");

        boolean choix=Interaction.lireOuiOuNon();

        if(choix){
            System.out.println("Combien de bots voulez-vous (max : "+(9-nbJoueur)+")");
            int nbBots=Interaction.lireUnEntier(1, 9-nbJoueur);
            int i = nbJoueur;
            while (i <= nbBots) {
                Joueur nouveauJoueur=new Joueur("Boti" + (i - nbJoueur + 1));
                nouveauJoueur.setType(true);
                plateauDeJeu.ajouterJoueur(nouveauJoueur);
                i++;
            }
        }

        architecte.setPlateau(plateauDeJeu);
        assassin.setPlateau(plateauDeJeu);
        condotierre.setPlateau(plateauDeJeu);
        eveque.setPlateau(plateauDeJeu);
        magicienne.setPlateau(plateauDeJeu);
        marchande.setPlateau(plateauDeJeu);
        roi.setPlateau(plateauDeJeu);
        voleur.setPlateau(plateauDeJeu);
        abbe.setPlateau(plateauDeJeu);
        alchimiste.setPlateau(plateauDeJeu);
        archiviste.setPlateau(plateauDeJeu);
        capitaine.setPlateau(plateauDeJeu);
        cardinal.setPlateau(plateauDeJeu);
        diplomate.setPlateau(plateauDeJeu);
        espion.setPlateau(plateauDeJeu);
        negociant.setPlateau(plateauDeJeu);
        navigatrice.setPlateau(plateauDeJeu);

        pioche.ajouter(necropole);
        pioche.ajouter(observatoire);
        pioche.ajouter(parc);
        pioche.ajouter(poudriere);
        pioche.ajouter(theatre);
        pioche.ajouter(tour_divoire);
        pioche.ajouter(musee);
        pioche.ajouter(mine_dor);
        pioche.ajouter(monument);
        pioche.ajouter(basilique);
        pioche.ajouter(capitole);
        pioche.ajouter(catacombes);
        pioche.ajouter(chantier);
        pioche.ajouter(ecuries);
        pioche.ajouter(grande_muraille);
        pioche.ajouter(hospice);
        pioche.ajouter(bibliotheque);
        pioche.ajouter(forge);
        pioche.ajouter(carriere);
        pioche.ajouter(laboratoire);
        pioche.ajouter(cour_miracle);
        pioche.ajouter(manufacture);
        pioche.ajouter(donjon);
        pioche.ajouter(salle_cartes);
        pioche.ajouter(dracoport);
        pioche.ajouter(statue_equestre);
        pioche.ajouter(ecole_magie);
        pioche.ajouter(tresor_imperial);
        pioche.ajouter(fontaine_souhaits);
        pioche.ajouter(tripot);

        pioche.melanger();

        plateauDeJeu.setPioche(pioche);
	
		return plateauDeJeu;
	}

}
