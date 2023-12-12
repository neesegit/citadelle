package application;

import modele.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import controleur.Interaction;

public class Jeu {
    private PlateauDeJeu m_plateauDeJeu;
    private int m_numeroConfiguration;
    private Random m_generateur;
    private Joueur m_premier;

    public Jeu(){
        m_plateauDeJeu=null;
        m_numeroConfiguration=1;
        m_generateur=new Random();
    }

    public void jouer(){
        System.out.println("Bienvenue");
        System.out.println("Menu");
        System.out.println("Jouer une partie (1)");
        System.out.println("Afficher les règles (2)");
        System.out.println("Quitter (3)");
        int choix=Interaction.lireUnEntier();
        switch(choix){
            case 1:
                jouerPartie();
                break;
            case 2:
                afficherLesRegles();
                break;
            case 3:
                System.out.println("Merci d'avoir utilisé le programme. Au revoir !");
                System.exit(0);
                break;
            default:
                System.out.println("Option invalide. Veuillez choisir une option valide.");
        }
    }

    private void afficherLesRegles(){
        System.out.println(
			"\nDeux à 9 joueurs s'affrontent pour construire le plus rapidement possible la plus prestigieuse cité." + 
			"\nPour cela, chaque joueur devra construire des quartiers, ayant chacun des coûts différents." + 
			"\nComme dans un jeu de rôle, chaque joueur doit se mettre dans la peau d'un personnage," + 
			"\nà ceci près que les joueurs changent de personnage à chaque tour de jeu." + 
			"\nCes personnages ont chacun des pouvoirs particuliers : la meilleure statégie est de choisir un personnage au bon moment du jeu."
			);

		System.out.println(
			"\nLa quatrième édition de Citadelles comportent 27 quartiers, répartis en 5 catégories : religieux, militaires, nobles, commerçants et merveilles. "+
			"\nLes quartiers sont présents plusieurs fois, à part les	merveilles qui sont uniques. "+
			"\nUne cité ne doit pas contenir deux quartiers identiques (sauf exception)."+
			"\nChaque quartier possède un nom et un coût de contruction. "+
			"\nSeuls les quartiers merveilles	possèdent chacun des effets particuliers liés au coût de construction, "+
			"\nà la perception des revenus ou encore au calcul des points."
		);
    }

    private void jouerPartie(){
        initialisation();
        do {
            tourDeJeu();
            gestionCouronne();
            reinitialisationPersonnages();
        } while (partieFinie());
    }

    private void initialisation(){
        Pioche pioche=Configuration.nouvellePioche();
        this.m_plateauDeJeu=Configuration.configurationDeBase(pioche);
        for(int i=0;i<9;i++){
            this.m_plateauDeJeu.getJoueur(i).ajouterPieces(2);
            for(int j=0;j<4;j++){
                this.m_plateauDeJeu.getJoueur(i).ajouterQuartierDansMain(pioche.piocher());
            }
        }
        this.m_plateauDeJeu.getJoueur(m_generateur.nextInt(3)).setPossedeCouronne(true);
    }

    private void gestionCouronne(){
        for(int i=0;i<this.m_plateauDeJeu.getNombreJoueurs();i++){
            if(this.m_plateauDeJeu.getJoueur(i).monPersonnage.getNom().equals("Roi")){
                this.m_plateauDeJeu.getJoueur(i).setPossedeCouronne(true);
            }
        }
    }

    private void reinitialisationPersonnages(){
        for(int i=0;i<this.m_plateauDeJeu.getNombrePersonnages();i++){
            if (this.m_plateauDeJeu.getPersonnage(i).getJoueur() != null)
                this.m_plateauDeJeu.getPersonnage(i).reinitialiser();
        }
    }

    private boolean partieFinie(){
        boolean retour=false;
        if(this.m_plateauDeJeu.getNombreJoueurs()>=4 && this.m_plateauDeJeu.getNombreJoueurs()<=7){
            for(int i=0;i<this.m_plateauDeJeu.getNombreJoueurs();i++){
                if(this.m_plateauDeJeu.getJoueur(i).nbQuartiersDansCite()>=7){
                    System.out.println("\t\nLa partie est terminé : " + this.m_plateauDeJeu.getJoueur(i).getNom() + " possède une cité complète\n");
                    for(int j = 0; j < this.m_plateauDeJeu.getNombreJoueurs(); j++){
                        int pointsBasilique = calculerPointsBasilique(this.m_plateauDeJeu.getJoueur(j));
                        this.m_plateauDeJeu.getJoueur(j).ajouterPoints(pointsBasilique);
                    }
                    calculDesPoints();
                    if(!retour){
                        this.m_premier=this.m_plateauDeJeu.getJoueur(i);
                        retour=true;
                    }
                }
            }
        }
        else{
            for(int i=0;i<this.m_plateauDeJeu.getNombreJoueurs();i++){
                if(this.m_plateauDeJeu.getJoueur(i).nbQuartiersDansCite()==8){
                    System.out.println("\t\nLa partie est terminé : " + this.m_plateauDeJeu.getJoueur(i).getNom() + " possède une cité complète\n");
                    for(int j = 0; j < this.m_plateauDeJeu.getNombreJoueurs(); j++){
                        int pointsBasilique = calculerPointsBasilique(this.m_plateauDeJeu.getJoueur(j));
                        this.m_plateauDeJeu.getJoueur(j).ajouterPoints(pointsBasilique);
                    }
                    calculDesPoints();
                    if(!retour){
                        this.m_premier=this.m_plateauDeJeu.getJoueur(i);
                        retour=true;
                    }
                }
            }
        }
        return retour;
    }

    private void tourDeJeu(){
        this.choixPersonnages();
        for(int i=0;i<this.m_plateauDeJeu.getNombrePersonnages();i++){
            Joueur joueurEnCours = this.m_plateauDeJeu.getPersonnage(i).getJoueur();
            effetBibliotheque(joueurEnCours);
            System.out.println("\nTour de " + this.m_plateauDeJeu.getPersonnage(i).getNom() +".");
            if(!this.m_plateauDeJeu.getPersonnage(i).getAssassine()){//Si le personnage n'est pas assassiné
                if(this.m_plateauDeJeu.getPersonnage(i).getVole()){//Si le personnage est volé
                    for(int j=0;j<this.m_plateauDeJeu.getNombrePersonnages();j++){
                        if(this.m_plateauDeJeu.getPersonnage(j).getNom().equals("Voleur")){
                            int pieces=this.m_plateauDeJeu.getPersonnage(i).getJoueur().nbPieces();
                            this.m_plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(pieces);
                            this.m_plateauDeJeu.getPersonnage(j).getJoueur().ajouterPieces(pieces);
                            this.m_plateauDeJeu.getPersonnage(i).reinitialiser();
                        }
                    }
                }
                this.percevoirRessources(i);
                this.m_plateauDeJeu.getPersonnage(i).percevoirRessourcesSpecifiques();
                System.out.println("Voulez-vous utiliser votre pouvoir (oui/non) ?");
                boolean choix=Interaction.lireOuiOuNon();
                if(choix){
                    this.m_plateauDeJeu.getPersonnage(i).utiliserPouvoir();
                }
                Joueur joueur=this.m_plateauDeJeu.getPersonnage(i).getJoueur();
                if(joueur.nbQuartiersDansMain()>0){
                    System.out.print("Voulez-vous utiliser construire un quartier (oui/non) ? ");
                    choix=Interaction.lireOuiOuNon();
                    if(choix){
                        for (int j = 0; j < (this.m_plateauDeJeu.getPersonnage(i) instanceof Architecte ? 3 : 1); j++) {
                            if (joueur.getMain().stream().anyMatch(quartier -> quartier.getCout() <= joueur.nbPieces())) {
                                System.out.println("Vous ne pouvez pas construire, pas assez de pièces. ("+joueur.nbPieces()+" pièces)");
                                break;
                            }
                            System.out.println("Voici les quartiers que vous possédez dans votre main : ");
                            for (int a = 0; a < joueur.nbQuartiersDansMain(); a++) {
                                Quartier quartier = joueur.getMain().get(a);
                                System.out.println(a+1 + ". " + quartier.getNom() + " ("+quartier.getCout()+" pièces)");
                            }
                            Quartier quartier;
                            do {
                                System.out.println("Vous avez " + joueur.nbPieces() + " pièces.");
                                System.out.print("Quel quartier voulez-vous construire ? ");
                                int choixQuartier = Interaction.lireUnEntier(1, joueur.nbQuartiersDansMain() + 1);
                                quartier = joueur.getMain().remove(choixQuartier-1);
                                if (quartier.getCout() > joueur.nbPieces())
                                    System.out.println("Vous n'avez pas assez de pièce pour construire ce quartier.");
                            } while (quartier.getCout() > joueur.nbPieces());

                            joueur.ajouterQuartierDansCite(quartier);
                            joueur.retirerPieces(quartier.getCout());
                            System.out.println(quartier.getNom() + " a été ajouté à votre cité.");
                            System.out.println(quartier.getCout() + " pièces vous ont été retirées.");
                        }
                    }
                }
                else{
                    System.out.println("Vous ne pouvez pas construire, vous n'avez de quartiers dans votre main.");
                }
            }
            else{
                System.out.println("Le personnage "+this.m_plateauDeJeu.getPersonnage(i).getNom()+" a été assassiné.");
            }
        }
    }

    private void choixPersonnages(){
        int cartes[]=new int[4];
        boolean same=false;
        Personnage personnages[]=new Personnage[27];
        for(int i=0;i<8;i++){
            personnages[i]=this.m_plateauDeJeu.getPersonnage(i);
        }

        //Trouver les 4 cartes parmis les 8 qui seront cachées.
        for(int i=0;i<4;i++){
            do {
                int nouvelleCarte=m_generateur.nextInt(0, 27);
                for(int j=0;j<4;j++){
                    if (cartes[j]==nouvelleCarte) {
                        same = true;
                        break;
                    }
                    else {
                        same=false;
                        cartes[i]=nouvelleCarte;
                    }
                }
            } while (same);
        }

        //Trouver le joueur qui a la couronne
        boolean trouver=false;
        int iteration=0;
        while(!trouver){
            if(this.m_plateauDeJeu.getJoueur(iteration).getPossedeCouronne()){
                trouver=true;
                //Si le joueur est un bot
                if(this.m_plateauDeJeu.getJoueur(iteration).isBot()){
                    boolean good=false;
                    int indice=0;
                    do {
                        indice = m_generateur.nextInt(0,27);
                        good = true;

                        for (int element : cartes) {
                            if (element == indice) {
                                good = false;
                                break;
                            }
                        }
                
                    } while (!good);
                    this.m_plateauDeJeu.getJoueur(iteration).monPersonnage=personnages[indice];
                    for (int j = indice; j < 27; j++) {
                        personnages[j] = personnages[j + 1];
                    }
                    personnages[26] = null;
                }
                //Si le joueur est un humain
                else{
                    System.out.println("Choix des personnages :");
                    //Affichage des cartes écartées visibles et cachées
                    for (int i = 0; i < 27; i++) {
                        int count=0;
                        for(int element:cartes){
                            count++;
                            if(element==i && count<=2){
                                System.out.println("Le personnage \""+this.m_plateauDeJeu.getPersonnage(element)+" est écarté face visible");
                            }
                            else if(element==i && count>2){
                                System.out.println("Un personnage est écarté face cachée");
                            }
                        }
                    }
                    //Affichage des cartes disponibles.
                    for (int i = 0; i < 27; i++) {
                        boolean indiceExclu = false;
                        if (this.m_plateauDeJeu.getPersonnage(i).getNom().equalsIgnoreCase("Navigatrice")) {
                            System.out.println("Vous n'avez pas le droit de construire");
                            return;
                        }
                        for (int element : cartes) {
                            if (element == i) {
                                indiceExclu = true;
                                break;
                            }
                        }
                        if (!indiceExclu) {
                            System.out.println((i+1)+" "+this.m_plateauDeJeu.getPersonnage(i));
                        }
                    }
                    System.out.println("Quel personnage souhaitez-vous prendre ?");
                    int choix=Interaction.lireUnEntier(1, 27);
                    this.m_plateauDeJeu.getJoueur(iteration).monPersonnage=personnages[choix-1];
                    for (int j = choix-1; j < 27; j++) {
                        personnages[j] = personnages[j + 1];
                    }
                    personnages[26] = null;
                }
                iteration--;
            }
            iteration++;
        }

        //Choix des personnages pour les joueurs sans couronne.
        for(int i=0;i<this.m_plateauDeJeu.getNombreJoueurs();i++){
            if(this.m_plateauDeJeu.getJoueur(i).monPersonnage==null){
                if(this.m_plateauDeJeu.getJoueur(i).isBot()){
                    boolean inValid=true;
                    do {
                        int randomIndice=m_generateur.nextInt(26-i);
                        if(personnages[randomIndice]!=null){
                            inValid=false;
                            this.m_plateauDeJeu.getJoueur(i).monPersonnage=personnages[randomIndice];
                            for (int j = randomIndice; j < 27; j++) {
                                personnages[j] = personnages[j + 1];
                            }
                            personnages[25-i]=null;
                        }
                    } while (inValid);
                }
                else{
                    boolean inValid=true;
                    do {
                        System.out.println("Voici les cartes disponibles :");
                        for(int j=0;j<=(25-i);j++){
                            System.out.println((i+1)+" "+personnages[j]);
                        }
                        System.out.println("Choisissez un indice entre 1 et "+(25-i)+" : ");
                        int userIndice = Interaction.lireUnEntier();
                        if (userIndice >= 0 && userIndice <= (25-i) && personnages[userIndice] != null) {
                            inValid = false;
                            this.m_plateauDeJeu.getJoueur(i).monPersonnage = personnages[userIndice];
                            for (int j = userIndice; j < 27; j++) {
                                personnages[j] = personnages[j + 1];
                            }
                            personnages[25 - i] = null;
                        } else {
                            System.out.println("Indice invalide. Veuillez réessayer.");
                        }
                    } while (inValid);
                }
            }
        }
    }

    private void percevoirRessources(int index){
        System.out.println("Que voulez vous faire ?");
        System.out.println("1 - Prendre 2 pièces d'or.");
        System.out.println("2 - Piocher 2 cartes Quartier.");
        int choix=Interaction.lireUnEntier(1,2);
        Joueur joueurActuel=this.m_plateauDeJeu.getJoueur(index);
        switch (choix){
            case 1:
                joueurActuel.ajouterPieces(2);
                break;
            case 2:
                Quartier quartier1=this.m_plateauDeJeu.getPioche().piocher();
                Quartier quartier2=this.m_plateauDeJeu.getPioche().piocher();
                System.out.println("Quel carte voulez-vous garder ?");
                System.out.println("1 - "+quartier1.getCaracteristiques()+"\n2 - "+quartier2.getCaracteristiques());
                int choix2=0;
                while(choix != 1 || choix != 2){
                    choix2=Interaction.lireUnEntier();
                }
                switch (choix2) {
                    case 1:
                        joueurActuel.ajouterQuartierDansMain(quartier1);
                        this.m_plateauDeJeu.getPioche().ajouter(quartier2);
                        break;
                    case 2:
                        joueurActuel.ajouterQuartierDansMain(quartier2);
                        this.m_plateauDeJeu.getPioche().ajouter(quartier1);
                        break;
                    default:
                        break;
                }
            default:
                break;
        }
    }

    private void calculDesPoints(){
        for(int i = 0; i < this.m_plateauDeJeu.getNombreJoueurs(); i++){
            Quartier[] quartiers = this.m_plateauDeJeu.getJoueur(i).getCite();
            int cout = 0;
            Set<String> typesDifferents = new HashSet<>(); // Ensemble pour stocker les types différents

            for(int j = 0; j < quartiers.length; j++){
                if(quartiers[j] != null){
                    cout += quartiers[j].getCout();
                    typesDifferents.add(quartiers[j].getType()); // Ajout du type du quartier à l'ensemble
                }
            }

            int points = cout;
            if(typesDifferents.size() >= 5){
                points += 3;
            }

            if(quartiers.length == 8){
                if(this.m_premier == this.m_plateauDeJeu.getJoueur(i)){
                    points += 4;
                } else {
                    points += 2;
                }
            }

            // Bonus pour les différentes Merveilles
            int pointsBasilique = calculerPointsBasilique(this.m_plateauDeJeu.getJoueur(i));
            this.m_plateauDeJeu.getJoueur(i).ajouterPoints(points + pointsBasilique);

            if (this.m_plateauDeJeu.getJoueur(i).monPersonnage.getNom().equals("Capitole")) {
                if (this.m_plateauDeJeu.getJoueur(i).aTroisQuartiersDuMemeType(this.m_plateauDeJeu) && !this.m_plateauDeJeu.getJoueur(i).aUtiliseEffetCapitole()) {
                    this.m_plateauDeJeu.getJoueur(i).ajouterPoints(3);
                    this.m_plateauDeJeu.getJoueur(i).setEffetCapitoleUtilise(true);
                }
            }

            if (this.m_plateauDeJeu.getJoueur(i).possedeDracoport()) {
                this.m_plateauDeJeu.getJoueur(i).ajouterPoints(2);
            }
        }
    }


    public int calculerPointsBasilique(Joueur joueur) {
        int pointsSupplementaires = 0;

        for (Quartier quartier : joueur.getCite()) {
            if (quartier.getCout() % 2 != 0) {
                pointsSupplementaires++;
            }
        }

        return pointsSupplementaires;
    }

    private void effetBibliotheque(Joueur joueur) {
        if (joueur.monPersonnage.getNom().equals("Bibliothèque")) {
            System.out.println("Effet de la Bibliothèque : vous pouvez piocher des cartes quartier au début de votre tour.");
            System.out.println("Voulez-vous piocher des cartes quartier ? (oui/non)");
            boolean choixPioche = Interaction.lireOuiOuNon();

            if (choixPioche) {
                int nombreCartesAPiocher = 2;
                for (int i = 0; i < nombreCartesAPiocher; i++) {
                    Quartier quartierPioche = m_plateauDeJeu.getPioche().piocher();
                    joueur.ajouterQuartierDansMain(quartierPioche);
                    System.out.println("Vous avez pioché : " + quartierPioche.getNom());
                }
                System.out.println("Vous avez conservé toutes les cartes piochées.");
            }
        }
    }

}
