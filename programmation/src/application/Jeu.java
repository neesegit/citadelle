package application;

import modele.*;

import java.util.ArrayList;
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
        System.out.println("Welcome in citadelle :))))");
        System.out.println("Menu");
        System.out.println("Jouer une partie (1)");
        System.out.println("Afficher les règles (2)");
        System.out.println("Quitter (3)");
        int choix=Interaction.lireUnEntier(1,4);
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
            System.out.println("Avant gestionCouronne");
            gestionCouronne();
            System.out.println("Après gestionCouronne");
            System.out.println("Avant reinitialisationPersonnages");
            reinitialisationPersonnages();
            System.out.println("Après reinitialisationPersonnages");
        } while (partieFinie());
        System.out.println("partieFinie");
        calculDesPoints();
    }

    private void initialisation(){
        Pioche pioche=Configuration.nouvellePioche();
        this.m_plateauDeJeu=Configuration.configurationDeBase(pioche);
        for(int i=0;i<this.m_plateauDeJeu.getNombrePersonnages();i++){
            System.out.println(this.m_plateauDeJeu.getPersonnage(i));
        }
        System.out.println("Configuration terminée");
        System.out.println("this.m_plateau : "+this.m_plateauDeJeu);
        for(int i=0;i<this.m_plateauDeJeu.getNombreJoueurs();i++){
            this.m_plateauDeJeu.getJoueur(i).ajouterPieces(2);
            for(int j=0;j<4;j++){
                this.m_plateauDeJeu.getJoueur(i).ajouterQuartierDansMain(pioche.piocher());
            }
        }
        this.m_plateauDeJeu.getJoueur(m_generateur.nextInt(this.m_plateauDeJeu.getNombreJoueurs())).setPossedeCouronne(true);
        System.out.print("Initialisation terminée");
    }

    private void gestionCouronne(){
        for(int i=0;i<this.m_plateauDeJeu.getNombrePersonnages();i++){
            if(this.m_plateauDeJeu.getPersonnage(i).getJoueur()!=null) {
                if (this.m_plateauDeJeu.getPersonnage(i).getNom().equals("Roi")) {
                    this.m_plateauDeJeu.getPersonnage(i).getJoueur().setPossedeCouronne(true);
                }
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
        boolean partieFinie=false;
        if(this.m_plateauDeJeu.getNombreJoueurs()>=4 && this.m_plateauDeJeu.getNombreJoueurs()<=7){
            for(int i=0;i<this.m_plateauDeJeu.getNombrePersonnages();i++){
                if(this.m_plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansCite()>=7){
                    System.out.println("\t\nLa partie est terminé : " + this.m_plateauDeJeu.getPersonnage(i).getJoueur().getNom() + " possède une cité complète\n");
                    /*for(int j = 0; j < this.m_plateauDeJeu.getNombreJoueurs(); j++){
                        int pointsBasilique = calculerPointsBasilique(this.m_plateauDeJeu.getJoueur(j));
                        this.m_plateauDeJeu.getJoueur(j).ajouterPoints(pointsBasilique);
                    }
                    calculDesPoints();*/
                    if(!partieFinie){
                        this.m_premier=this.m_plateauDeJeu.getPersonnage(i).getJoueur();
                        partieFinie=true;
                    }
                }
            }
        }
        else{
            for(int i=0;i<this.m_plateauDeJeu.getNombreJoueurs();i++){
                if(this.m_plateauDeJeu.getJoueur(i).nbQuartiersDansCite()==8){
                    System.out.println("\t\nLa partie est terminé : " + this.m_plateauDeJeu.getJoueur(i).getNom() + " possède une cité complète\n");
                    /*for(int j = 0; j < this.m_plateauDeJeu.getNombreJoueurs(); j++){
                        int pointsBasilique = calculerPointsBasilique(this.m_plateauDeJeu.getJoueur(j));
                        this.m_plateauDeJeu.getJoueur(j).ajouterPoints(pointsBasilique);
                    }
                    calculDesPoints();*/
                    if(!partieFinie){
                        this.m_premier=this.m_plateauDeJeu.getPersonnage(i).getJoueur();
                        partieFinie=true;
                    }
                }
            }
        }
        return partieFinie;
    }

    private void tourDeJeu(){
        this.choixPersonnages();
        System.out.println("Après choixPersonnages");
        for(int i=0;i<this.m_plateauDeJeu.getNombrePersonnages();i++){
            Joueur joueur = this.m_plateauDeJeu.getPersonnage(i).getJoueur();
            if(joueur!=null) {
                effetBibliotheque(joueur);
                System.out.println("\nTour de "+this.m_plateauDeJeu.getPersonnage(i).getJoueur().getNom()+", qui est " + this.m_plateauDeJeu.getPersonnage(i).getNom() + ".");
                Quartier[] cite = joueur.getCite();
                boolean gotManufacture = false;
                boolean gotMineOr = false;
                boolean gotObservatoire = false;
                boolean gotParc = false;
                boolean gotPoudriere = false;
                if (!this.m_plateauDeJeu.getPersonnage(i).getAssassine()) {//Si le personnage n'est pas assassiné
                    if (this.m_plateauDeJeu.getPersonnage(i).getVole()) {//Si le personnage est volé
                        for (int j = 0; j < this.m_plateauDeJeu.getNombrePersonnages(); j++) {
                            if (this.m_plateauDeJeu.getPersonnage(j).getNom().equals("Voleur")) {
                                int pieces = this.m_plateauDeJeu.getPersonnage(i).getJoueur().nbPieces();
                                this.m_plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(pieces);
                                this.m_plateauDeJeu.getPersonnage(j).getJoueur().ajouterPieces(pieces);
                                this.m_plateauDeJeu.getPersonnage(i).reinitialiser();
                            }
                        }
                    }
                    for (int b = 0; b < joueur.nbQuartiersDansCite(); b++) {
                        if (cite[b].getNom().equals("Manufacture")) {
                            gotManufacture = true;
                        }
                        if (cite[b].getNom().equals("Mine d'Or")) {
                            gotMineOr = true;
                        }
                        if (cite[b].getNom().equals("Observatoire")) {
                            gotObservatoire = true;
                        }
                        if (cite[b].getNom().equals("Parc")) {
                            gotParc = true;
                        }
                        if (cite[b].getNom().equals("Poudri")) {
                            gotPoudriere = true;
                        }
                    }
                    System.out.println("Nb quartiers dans main avant : "+joueur.getMain().size());
                    System.out.println("Nb pièces avant : "+joueur.nbPieces());
                    this.percevoirRessources(i, gotMineOr, gotObservatoire);
                    System.out.println("Nb quartiers dans main après : "+joueur.getMain().size());
                    System.out.println("Nb pièces après : "+joueur.nbPieces());
                    this.m_plateauDeJeu.getPersonnage(i).percevoirRessourcesSpecifiques();
                    System.out.println("Voulez-vous utiliser votre pouvoir (oui/non) ?");
                    boolean choix = Interaction.lireOuiOuNon();
                    if (choix) {
                        this.m_plateauDeJeu.getPersonnage(i).utiliserPouvoir();
                    }

                    if (joueur.nbQuartiersDansMain() > 0) {
                        if(!joueur.getPersonnage().getNom().equals("Navigatrice")) {
                            System.out.print("Voulez-vous construire un quartier (oui/non) ? ");
                            choix = Interaction.lireOuiOuNon();
                            if (choix) {
                                for (int j = 0; j < (this.m_plateauDeJeu.getPersonnage(i) instanceof Architecte ? 3 : 1); j++) {
                                    boolean peutConstruire=false;
                                    ArrayList<Quartier> main=joueur.getMain();
                                    for(int z=0;z<joueur.nbQuartiersDansMain();z++){
                                        if(main.get(z).getCout()<= joueur.nbPieces()){
                                            peutConstruire=true;
                                        }
                                    }
                                    if(peutConstruire) {
                                        System.out.println("Voici les quartiers que vous possédez dans votre main : ");
                                        for (int a = 0; a < joueur.nbQuartiersDansMain(); a++) {
                                            Quartier quartier = joueur.getMain().get(a);
                                            System.out.println((a + 1) + " - " + quartier.getNom() + " (" + quartier.getCout() + " pièces)");
                                        }
                                        Quartier quartier;
                                        int choixQuartier = 0;
                                        System.out.println("Vous avez " + joueur.nbPieces() + " pièces.");
                                        System.out.print("Quel quartier voulez-vous construire ? ");
                                        boolean nonConstruit = true;
                                        do {
                                            choixQuartier = Interaction.lireUnEntier(1, joueur.nbQuartiersDansMain() + 1);
                                            quartier = joueur.getMain().get(choixQuartier - 1);
                                            if (quartier.getNom().equals("Nécropole")) {
                                                System.out.println("Vous voulez construire la Merveille Nécropole dans votre cité.");
                                                System.out.println("Vous pouvez :");
                                                System.out.println("1 - Détruire un quartier pour construire " + quartier.getNom());
                                                System.out.println("2 - Payer " + quartier.getCout() + " pièces.");
                                                int choixDetruireOuPayer = Interaction.lireUnEntier(1, 3);
                                                if (choixDetruireOuPayer == 1) {
                                                    System.out.println("Quelle quartier voulez-vous détruire ?");
                                                    for (int a = 0; a < joueur.nbQuartiersDansCite(); a++) {
                                                        System.out.println((a + 1) + " - " + cite[a]);
                                                    }
                                                    int indiceQuartierADetruire = Interaction.lireUnEntier(1, joueur.nbQuartiersDansCite() + 1);
                                                    joueur.retirerQuartierDansCite(cite[indiceQuartierADetruire - 1].getNom());
                                                    joueur.ajouterQuartierDansCite(quartier);
                                                    joueur.getMain().remove(choixQuartier - 1);
                                                    nonConstruit = false;
                                                } else {
                                                    if (gotManufacture) {
                                                        if (quartier.getType().equals("MERVEILLE")) {
                                                            if (quartier.getCout() - 1 > joueur.nbPieces()) {
                                                                System.out.println("Vous n'avez pas assez de pièce pour construire ce quartier.");
                                                            } else {
                                                                joueur.ajouterQuartierDansCite(quartier);
                                                                joueur.getMain().remove(choixQuartier - 1);
                                                                joueur.retirerPieces(quartier.getCout() - 1);
                                                                System.out.println((quartier.getCout() - 1) + " pièces vous ont été retirées.");
                                                                nonConstruit = false;
                                                            }
                                                        } else {
                                                            if (quartier.getCout() > joueur.nbPieces()) {
                                                                System.out.println("Vous n'avez pas assez de pièce pour construire ce quartier.");
                                                            } else {
                                                                joueur.ajouterQuartierDansCite(quartier);
                                                                joueur.getMain().remove(choixQuartier - 1);
                                                                joueur.retirerPieces(quartier.getCout());
                                                                System.out.println(quartier.getCout() + " pièces vous ont été retirées.");
                                                                nonConstruit = false;
                                                            }
                                                        }
                                                    } else {
                                                        if (quartier.getCout() > joueur.nbPieces()) {
                                                            System.out.println("Vous n'avez pas assez de pièces pour construire ce quartier.");
                                                        } else {
                                                            joueur.ajouterQuartierDansCite(quartier);
                                                            joueur.getMain().remove(choixQuartier - 1);
                                                            joueur.retirerPieces(quartier.getCout());
                                                            System.out.println(quartier.getCout() + " pièces vous ont été retirées.");
                                                            nonConstruit = false;
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (gotManufacture) {
                                                    if (quartier.getType().equals("MERVEILLE")) {
                                                        if (quartier.getCout() - 1 > joueur.nbPieces()) {
                                                            System.out.println("Vous n'avez pas assez de pièce pour construire ce quartier.");
                                                        } else {
                                                            joueur.ajouterQuartierDansCite(quartier);
                                                            joueur.getMain().remove(choixQuartier - 1);
                                                            joueur.retirerPieces(quartier.getCout() - 1);
                                                            System.out.println((quartier.getCout() - 1) + " pièces vous ont été retirées.");
                                                            nonConstruit = false;
                                                        }
                                                    } else {
                                                        if (quartier.getCout() > joueur.nbPieces()) {
                                                            System.out.println("Vous n'avez pas assez de pièce pour construire ce quartier.");
                                                        } else {
                                                            joueur.ajouterQuartierDansCite(quartier);
                                                            joueur.getMain().remove(choixQuartier - 1);
                                                            joueur.retirerPieces(quartier.getCout());
                                                            System.out.println(quartier.getCout() + " pièces vous ont été retirées.");
                                                            nonConstruit = false;
                                                        }
                                                    }
                                                } else {
                                                    if (quartier.getCout() > joueur.nbPieces()) {
                                                        System.out.println("Vous n'avez pas assez de pièce pour construire ce quartier.");
                                                    } else {
                                                        joueur.ajouterQuartierDansCite(quartier);
                                                        joueur.getMain().remove(choixQuartier - 1);
                                                        joueur.retirerPieces(quartier.getCout());
                                                        System.out.println(quartier.getCout() + " pièces vous ont été retirées.");
                                                        nonConstruit = false;
                                                    }
                                                }
                                            }
                                        } while (nonConstruit);
                                        System.out.println(quartier.getNom() + " a été ajouté à votre cité.");
                                    }
                                    else{
                                        System.out.println("Vous ne pouvez construire aucun de vos quartiers.");
                                    }
                                }
                            }
                        }
                        else{
                            System.out.println("En tant que Navigatrice, vous ne pouvez pas construire de quartiers.");
                        }
                        if (joueur.nbQuartiersDansMain() > 0 && joueur.quartierPresentDansCite("Laboratoire")) {
                            System.out.println("Voulez-vous défausser 1 carte aléatoirement pour recevoir 2 pièces d'or (oui/non)");
                            choix = Interaction.lireOuiOuNon();
                            if (choix) {
                                System.out.println("Voici votre main : ");
                                ArrayList<Quartier> joueurMain = joueur.getMain();
                                for (int j = 0; j < joueur.nbQuartiersDansMain(); j++) {
                                    System.out.println((j + 1) + " " + joueurMain.get(j));
                                }
                                Quartier quartier = joueur.retirerQuartierDansMain();
                                joueur.ajouterPieces(2);
                                System.out.println("Carte retirée : " + quartier);
                                joueurMain = joueur.getMain();
                                System.out.println("Nouvelle main : ");
                                for (int j = 0; j < joueur.nbQuartiersDansMain(); j++) {
                                    System.out.println((j + 1) + " " + joueurMain.get(j));
                                }
                            }
                        }
                    } else {
                        System.out.println("Vous ne pouvez pas construire, vous n'avez de quartiers dans votre main.");
                    }
                    if (gotParc) {
                        //Il faut traiter la partie bizarre de la Sorciere
                        if (joueur.nbQuartiersDansMain() == 0) {
                            System.out.println("Vous n'avez plus de cartes en main, la Merveille Parc vous permet d'en piocher deux.");
                            Quartier quartier1 = this.m_plateauDeJeu.getPioche().piocher();
                            Quartier quartier2 = this.m_plateauDeJeu.getPioche().piocher();
                            joueur.ajouterQuartierDansMain(quartier1);
                            joueur.ajouterQuartierDansMain(quartier2);
                        }
                    }
                    if (gotPoudriere) {
                        System.out.println("La Merveille Proudrière vous permet de détruire un de vos quartier ou un quartier adverse.");
                        System.out.println("1 - Détruire un quartier allié.");
                        System.out.println("2 - Détruire un quartier adverse.");
                        System.out.println("3 - Ne rien faire.");
                        String nomQuartierADetruire;
                        int choix3 = Interaction.lireUnEntier(1, 4);
                        switch (choix3) {
                            case 1:
                                System.out.println("Lequel de vos quartiers voulez-vous détruire.");
                                for (int j = 0; j < joueur.nbQuartiersDansCite(); j++) {
                                    System.out.println((j + 1) + " - " + cite[j]);
                                }
                                choix3 = Interaction.lireUnEntier(1, joueur.nbQuartiersDansCite()+1);
                                nomQuartierADetruire = cite[choix3 - 1].getNom();
                                joueur.retirerQuartierDansCite(nomQuartierADetruire);
                                System.out.println("Le quartier " + nomQuartierADetruire + " a été détruit.");
                                break;
                            case 2:
                                System.out.println("De quel adversaire voulez-vous détruire le quartier ?");
                                for (int j = 0; j < this.m_plateauDeJeu.getNombrePersonnages(); j++) {
                                    System.out.println((j + 1) + " - " + this.m_plateauDeJeu.getPersonnage(j).getJoueur().getNom());
                                }
                                choix3 = Interaction.lireUnEntier(1, this.m_plateauDeJeu.getNombrePersonnages()+1);
                                Joueur joueurCible = null;
                                boolean citeComplete = true;
                                Quartier[] citeJoueurCible = null;
                                do {
                                    joueurCible = this.m_plateauDeJeu.getPersonnage(choix3 - 1).getJoueur();
                                    if (this.m_plateauDeJeu.getNombreJoueurs() >= 4 && this.m_plateauDeJeu.getNombreJoueurs() <= 7) {
                                        if (joueurCible.nbQuartiersDansCite() < 7) {
                                            citeComplete = false;
                                        }
                                    } else {
                                        if (joueurCible.nbQuartiersDansCite() < 8) {
                                            citeComplete = false;
                                        }
                                    }

                                } while (citeComplete);
                                citeJoueurCible = joueurCible.getCite();
                                System.out.println("Lequel des quartiers d'adversaire voulez vous détruire ?");
                                for (int j = 0; j < joueurCible.nbQuartiersDansCite(); j++) {
                                    System.out.println((j + 1) + " - " + citeJoueurCible[j].getNom());
                                }
                                choix3 = Interaction.lireUnEntier(1, joueurCible.nbQuartiersDansCite()+1);
                                nomQuartierADetruire = citeJoueurCible[choix3 - 1].getNom();
                                joueurCible.retirerQuartierDansCite(nomQuartierADetruire);
                                System.out.println("Le quartier " + nomQuartierADetruire + " de " + joueurCible.getNom() + " a été détruit.");
                                break;
                            case 3:
                                break;
                            default:
                                break;
                        }
                    }
                } else {
                    System.out.println("Le personnage " + this.m_plateauDeJeu.getPersonnage(i).getNom() + " a été assassiné.");
                }
            }
        }
    }

    private void choixPersonnages(){
        System.out.println("entree personnage");
        int[] cartes =new int[4];
        System.out.println("indice carte face caché face visible ");
        Personnage[] personnages =new Personnage[this.m_plateauDeJeu.getNombrePersonnages()];
        System.out.println("initie les peronnages le groupe");
        for(int i=0;i<this.m_plateauDeJeu.getNombrePersonnages();i++){
            personnages[i]=this.m_plateauDeJeu.getPersonnage(i);
        }
        System.out.println("a initié tout les personnages");
        //Trouver les 4 cartes parmis les 8 qui seront cachées.
        int index = 0;
        while (index < 4) {
            int nouvelleCarte = m_generateur.nextInt(0, this.m_plateauDeJeu.getNombrePersonnages());
            boolean valeurPresente = false;

            // Vérifier si la nouvelle valeur est déjà dans le tableau
            for (int i = 0; i < index; i++) {
                if (cartes[i] == nouvelleCarte) {
                    valeurPresente = true;
                    break;
                }
            }
            // Si la valeur n'est pas déjà présente, l'ajouter au tableau
            if (!valeurPresente) {
                cartes[index] = nouvelleCarte;
                index++;
            }
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
                    System.out.println("Joueur "+this.m_plateauDeJeu.getJoueur(iteration).getNom()+", vous avez la couronne.");
                    System.out.println("Choix des personnages :");
                    //Affichage des cartes écartées visibles et cachées
                    for (int i = 0; i < this.m_plateauDeJeu.getNombrePersonnages(); i++) {
                        int count=0;
                        for(int element:cartes){
                            count++;
                            if(element==i){
                                if(count<=2){
                                    System.out.println("Le personnage "+this.m_plateauDeJeu.getPersonnage(element).getNom()+" est écarté face visible");
                                }
                                else{
                                    System.out.println("Un personnage est écarté face cachée");
                                }
                            }
                        }
                    }
                    //Affichage des cartes disponibles.
                    for (int i = 0; i < this.m_plateauDeJeu.getNombrePersonnages(); i++) {
                        boolean indiceExclu = false;
                        for (int element : cartes) {
                            if (element == i) {
                                indiceExclu = true;
                                break;
                            }
                        }
                        if (!indiceExclu) {
                            System.out.println((i+1)+" - "+this.m_plateauDeJeu.getPersonnage(i).getNom());
                        }
                    }
                    System.out.println("Quel personnage souhaitez-vous prendre ?");
                    int choix=Interaction.lireUnEntier(1, this.m_plateauDeJeu.getNombrePersonnages()+1);
                    this.m_plateauDeJeu.getJoueur(iteration).monPersonnage=personnages[choix-1];
                    for (int j = choix-1; j < this.m_plateauDeJeu.getNombrePersonnages(); j++) {
                        if((j+1)!=this.m_plateauDeJeu.getNombrePersonnages()) {
                            personnages[j] = personnages[j + 1];
                        }
                    }
                    personnages[this.m_plateauDeJeu.getNombrePersonnages()-1] = null;
                }
                System.out.println("personnages[] après couronné a choisi");
                for(int b=0;b<personnages.length;b++){
                    System.out.println(personnages[b]);
                }
                System.out.println("Personnage choisi");
                System.out.println("this.m_plateauDeJeu.getNombrePersonnages() : "+this.m_plateauDeJeu.getNombrePersonnages());
                for(int i=0;i<this.m_plateauDeJeu.getNombrePersonnages();i++){
                    if(this.m_plateauDeJeu.getJoueur(iteration).monPersonnage==this.m_plateauDeJeu.getPersonnage(i)){
                        this.m_plateauDeJeu.getPersonnage(i).setJoueur(this.m_plateauDeJeu.getJoueur(iteration));
                    }
                }
                iteration--;
            }
            iteration++;
        }

        //Choix des personnages pour les joueurs sans couronne.
        int indiceDissident=0;
        for(int i=0;i<this.m_plateauDeJeu.getNombreJoueurs();i++){
            if(this.m_plateauDeJeu.getJoueur(i).getPossedeCouronne()){
                indiceDissident=i;
            }
        }
        if(indiceDissident!=(this.m_plateauDeJeu.getNombreJoueurs()-1)){
            this.m_plateauDeJeu.permuterJoueur(this.m_plateauDeJeu.getNombreJoueurs()-1,indiceDissident);
        }
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
                    for(int j=0;j<this.m_plateauDeJeu.getNombrePersonnages();j++){
                        if(this.m_plateauDeJeu.getJoueur(i).monPersonnage==this.m_plateauDeJeu.getPersonnage(j)){
                            this.m_plateauDeJeu.getPersonnage(j).setJoueur(this.m_plateauDeJeu.getJoueur(i));
                        }
                    }
                }
                else{
                    boolean inValid=true;
                    do {
                        System.out.println("Joueur "+this.m_plateauDeJeu.getJoueur(i).getNom()+" : \nVoici les personnages disponibles :");
                        System.out.println("this.m_plateauDeJeu.getNombrePersonnages()-1-i : "+(this.m_plateauDeJeu.getNombrePersonnages()-1-i));
                        for(int j=0;j<(this.m_plateauDeJeu.getNombrePersonnages()-1-i);j++){
                            System.out.println((j+1)+" "+personnages[j].getNom());
                        }
                        System.out.println("Choisissez un indice entre 1 et "+(this.m_plateauDeJeu.getNombrePersonnages()-1-i)+" : ");
                        int userIndice = Interaction.lireUnEntier(1,this.m_plateauDeJeu.getNombrePersonnages()+1-i);
                        if (userIndice >= 0 && userIndice <= (this.m_plateauDeJeu.getNombrePersonnages()-1-i) && personnages[userIndice-1] != null) {
                            inValid = false;
                            this.m_plateauDeJeu.getJoueur(i).monPersonnage = personnages[userIndice-1];
                            for (int j = userIndice; j < this.m_plateauDeJeu.getNombrePersonnages(); j++) {
                                if((j+1)!=this.m_plateauDeJeu.getNombrePersonnages()) {
                                    personnages[j] = personnages[j + 1];
                                }
                            }
                            personnages[this.m_plateauDeJeu.getNombrePersonnages()-2 - i] = null;
                        } else {
                            System.out.println("Indice invalide. Veuillez réessayer.");
                        }
                    } while (inValid);
                    for(int j=0;j<this.m_plateauDeJeu.getNombrePersonnages();j++){
                        if(this.m_plateauDeJeu.getJoueur(i).monPersonnage==this.m_plateauDeJeu.getPersonnage(j)){
                            this.m_plateauDeJeu.getPersonnage(j).setJoueur(this.m_plateauDeJeu.getJoueur(i));
                        }
                    }
                }
            }
        }
        for(int i=0;i<this.m_plateauDeJeu.getNombreJoueurs();i++){
            System.out.println("Joueur "+this.m_plateauDeJeu.getJoueur(i).getNom()+" a le personnage "+this.m_plateauDeJeu.getJoueur(i).getPersonnage().getNom());
        }
    }

    private void percevoirRessources(int index,boolean gotMineOr,boolean gotObservatoire){
        System.out.println("Que voulez vous faire ?");
        if(gotMineOr){
            System.out.println("1 - Prendre 3 pièces d'or.");
        }
        else{
            System.out.println("1 - Prendre 2 pièces d'or.");
        }
        
        if(gotObservatoire){
            System.out.println("2 - Piocher 3 cartes Quartier.");
        }
        else{
            System.out.println("2 - Piocher 2 cartes Quartier.");
        }
        int choix=Interaction.lireUnEntier(1,3);
        Joueur joueurActuel=this.m_plateauDeJeu.getPersonnage(index).getJoueur();
        switch (choix){
            case 1:
                if(gotMineOr){joueurActuel.ajouterPieces(3);}
                else{joueurActuel.ajouterPieces(2);}
                break;
            case 2:
                if(!gotObservatoire){
                    Quartier quartier1=this.m_plateauDeJeu.getPioche().piocher();
                    Quartier quartier2=this.m_plateauDeJeu.getPioche().piocher();
                    System.out.println("Quel carte voulez-vous garder ?");
                    System.out.println("1 - "+quartier1.getNom()+"\n2 - "+quartier2.getNom());
                    int choix2=Interaction.lireUnEntier(1,3);
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
                }
                else{
                    Quartier quartier1=this.m_plateauDeJeu.getPioche().piocher();
                    Quartier quartier2=this.m_plateauDeJeu.getPioche().piocher();
                    Quartier quartier3=this.m_plateauDeJeu.getPioche().piocher();
                    System.out.println("Quel carte voulez-vous garder ?");
                    System.out.println("1 - "+quartier1.getNom()+"\n2 - "+quartier2.getNom()+"\n3 - "+quartier3.getNom());
                    int choix2=Interaction.lireUnEntier(1, 4);
                    switch (choix2) {
                        case 1:
                            joueurActuel.ajouterQuartierDansMain(quartier1);
                            this.m_plateauDeJeu.getPioche().ajouter(quartier2);
                            this.m_plateauDeJeu.getPioche().ajouter(quartier3);
                            break;
                        case 2:
                            joueurActuel.ajouterQuartierDansMain(quartier2);
                            this.m_plateauDeJeu.getPioche().ajouter(quartier1);
                            this.m_plateauDeJeu.getPioche().ajouter(quartier3);
                            break;
                        case 3:
                            joueurActuel.ajouterQuartierDansMain(quartier3);
                            this.m_plateauDeJeu.getPioche().ajouter(quartier1);
                            this.m_plateauDeJeu.getPioche().ajouter(quartier2);
                        default:
                            break;
                    }
                }
            default:
                break;
        }
    }

    private void calculDesPoints(){
        for(int i = 0; i < this.m_plateauDeJeu.getNombreJoueurs(); i++){
            Quartier[] quartiers = this.m_plateauDeJeu.getJoueur(i).getCite();
            Joueur joueur=this.m_plateauDeJeu.getJoueur(i);
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

            if(this.m_plateauDeJeu.getNombreJoueurs()>=4 && this.m_plateauDeJeu.getNombreJoueurs()<=7) {
                if (quartiers.length >= 7) {
                    if (this.m_premier == this.m_plateauDeJeu.getJoueur(i)) {
                        points += 4;
                    } else {
                        points += 2;
                    }
                }
            }
            else{
                if (quartiers.length == 8) {
                    if (this.m_premier == this.m_plateauDeJeu.getJoueur(i)) {
                        points += 4;
                    } else {
                        points += 2;
                    }
                }
            }

            // Bonus pour les différentes Merveilles
            int pointsBasilique = calculerPointsBasilique(this.m_plateauDeJeu.getJoueur(i));
            points+=pointsBasilique;

            /*if (joueur.monPersonnage.getNom().equals("Capitole")) {
                if (joueur.aTroisQuartiersDuMemeType(this.m_plateauDeJeu) && !this.m_plateauDeJeu.getJoueur(i).aUtiliseEffetCapitole()) {
                    points+=3;
                    joueur.setEffetCapitoleUtilise(true);
                }
            }*/
            for(int z=0;z< joueur.nbQuartiersDansCite();z++) {
                if (quartiers[z].getNom().equals("Dracoport")) {
                    points += 2;
                }
            }

            for(int j=0;j<joueur.nbQuartiersDansCite();j++){
                //Salle des cartes
                if(quartiers[j].getNom().equals("Salle des Cartes")){
                    points+=joueur.nbQuartiersDansMain();
                }
                //Statue équestre
                if(quartiers[j].getNom().equals("Statue Équestre")){
                    if(joueur.getPossedeCouronne()){
                        points+=5;
                    }
                }
                //Trésor impérial
                if(quartiers[j].getNom().equals("Trésor Impérial")){
                    points+=joueur.nbPieces();
                }
                //Tour d'ivoire
                if(quartiers[j].getNom().equals("Tour d’Ivoire")){
                    int nbMerveille=0;
                    boolean gotCourDesMiracles=false;
                    for(int b=0;b<joueur.nbQuartiersDansCite();b++){
                        if(quartiers[b].getType().equals("MERVEILLE")){
                            nbMerveille++;
                            if(quartiers[b].getNom().equals("Cour des Miracles")){
                                gotCourDesMiracles=true;
                            }
                        }
                    }
                    if(nbMerveille==1){
                        points+=5;
                    }
                    else if(nbMerveille==2 && gotCourDesMiracles){
                        System.out.println("Vous avez la Tour d'Ivoire et la Cour des Miracles comme uniques Merveilles.");
                        System.out.println("Considérez-vous toujours la Cour des Miracles comme une Merveille ?");
                        System.out.println("Si oui, vous n'avez pas de points en plus.");
                        System.out.println("Si non, vous avez 5 points en plus.");
                        System.out.println("Votre choix (oui/non):");
                        boolean choix=Interaction.lireOuiOuNon();
                        if(!choix){
                            points+=5;
                        }
                    }
                }
            }
            joueur.setPoints(points);
        }
        for (int i = 0; i < this.m_plateauDeJeu.getNombreJoueurs(); i++) {
            Joueur joueur = this.m_plateauDeJeu.getJoueur(i);
            System.out.println("Le joueur " + joueur.getNom() + " a " + joueur.getPoints() + " points.");
        }
        Joueur joueurGagnant = determineJoueurGagnant();
        System.out.println("Le joueur gagnant est : " + joueurGagnant.getNom() + " avec " + joueurGagnant.getPoints() + " points.");
    }

    private Joueur determineJoueurGagnant() {
        Joueur joueurGagnant = this.m_plateauDeJeu.getJoueur(0);

        for (int i = 1; i < this.m_plateauDeJeu.getNombreJoueurs(); i++) {
            Joueur joueur = this.m_plateauDeJeu.getJoueur(i);
            if (joueur.getPoints() > joueurGagnant.getPoints()) {
                joueurGagnant = joueur;
            }
        }

        return joueurGagnant;
    }
    public int calculerPointsBasilique(Joueur joueur) {
        int pointsSupplementaires = 0;

        for (Quartier quartier : joueur.getCite()) {
            if(quartier!=null) {
                if (quartier.getCout() % 2 != 0) {
                    pointsSupplementaires++;
                }
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
