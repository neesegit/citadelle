package modele;

public class Caracteristiques {
	public static String ASSASSIN= 
		"L'assassin peut tuer un personnage de son choix. " +
		"Celui-ci ne jouera pas ce tour-ci.";
	public static String ECHEVIN= 
		"L'echevin place des mandats de requisition, face cachee, " +
		"sous 3 jetons Personnage differents. Il peut reveler le " +
		"mandat signe pour confisquer le premier quartier bati par " +
		"le joueur cible, qui recupere le cout de construction.";
	public static String SORCIERE=
		"Apres avoir pris or ou carte, la Sorciere ensorcelle un " +
		"personnage et interrompt son tour. Elle le terminera en " +
		"jouant a la place du personnage ensorcele";
	public static String MAITRE_CHANTEUSE=
		"La Maitre-chanteuse place des menaces, face cachee, sous " +
		"2 jetons Personnage differents. Un personnage menace peut " +
		"lui payer la moitie de son or pour retirer la menace. Si " +
		"la Maitre-chanteuse revele le vrai jeton Menace, elle " +
		"prend tout l'or du joueur cible";
	public static String ESPION=
		"L'Espion choisit un type (couleur) de quartier et regarde " +
		"la main d'un autre joueur. Pour chaque quartier du type " +
		"indique, il recoit 1 piece d'or de ce joueur et pioche 1 " +
		"carte Quartier.";
	public static String VOLEUR=
		"Le Voleur peut piller le tresor du personnage de son choix. " +
		"Quand le personnage detrousse sera revele, il donnera tout " +
		"son or au Voleur.";
	public static String MAGICIENNE=
		"La Magicienne peut, au choix : soit echanger la totalite " +
		"des cartes de sa main avec celle d'un autre joueur, soit " +
		"echanger des cartes de sa main contre le meme nombre de " +
		"cartes de la pioche.";
	public static String SORCIER=
		"Le Sorcier choisit 1 carte dans la main d'un autre joueur " +
		"et peut soit payer son cout pour la batir, soit l'ajouter " +
		"a sa main. Il peut batir des quartiers identiques a "+
		"d'autres quartiers de sa cite.";
	public static String VOYANTE=
		"La Voyante prend 1 carte au hasard dans la main de chaque " +
		"joueur, puis donne a chacun 1 carte de son choix. Son " +
		"permis de construire est de 2 quartiers.";
	public static String EMPEREUR=
		"L'Empereur donne la Couronne a un autre joueur, qui lui " +
		"paie 1 piece d'or ou 1 carte. Chaque quartier Noble dans " +
		"sa cite lui rapporte 1 piece d'or.";
	public static String PATRICIEN=
		"Le Patricien prend la Couronne et choisira en premier " +
		"en premier son Personnage lors du prochain tour. " +
		"Chaque quartier Noble dans sa cite lui rapporte 1 " +
		"piece d'or.";
	public static String ROI = 
		"Le Roi prend la Couronne et choisira " +
		"en premier son personnage lors du prochain tour." +
		"Chaque quartier Noble dans sa cite lui rapporte 1 " +
		"piece d'or.";
	public static String ABBE=
		"Le joueur le plus riche doit donner 1 piece d'or a l'Abbe." +
		"Chaque quartier Religieux dans sa cite lui rapport 1 piece " +
		"d'or ou 1 carte.";
	public static String CARDINAL=
		"Si le Cardinal n'a pas assez d'or pour batir un quartier, " +
		"il peut le prendre a un joueur en echange de carte (1 " +
		"carte = 1 piece d'or). Chaque quartier Religieux dans sa " +
		"cite lui rapport 1 carte quartier.";
	public static String EVEQUE=
		"La cite de l'Eveque est protegee contre les personnages " +
		"de rang 8 (gemme rouge). Chaque quartier Religieux dans sa "+
		"cite lui rapport 1 piece d'or";
	public static String ALCHIMISTE=
		"A la fin de son tour, l'Alchimiste recupere l'or qu'il a " +
		"depense pour batir des quartiers durant son tour.";
	public static String MARCHANDE=
		"La Marchande recoit 1 piece d'or supplementaire. Chaque " +
		"quartier Commercant dans sa cite lui rapporte 1 piece d'or.";
	public static String NEGOCIANT=
		"Le Negociant peut batir des quartiers Commercants sans " +
		"restrictions. Chaque quartier Commercant dans sa cite " +
		"lui rapporte 1 piece d'or.";
	public static String ARCHITECTE=
		"L'Architecte pioche 2 cartes supplementaires. Son permis " +
		"de construire est de 3 quartiers.";
	public static String ARCHIVISTE=
		"L'Archiviste pioche 7 cartes et en conserve 1 de son choix. " +
		"Son permis de construire est de 2 quartiers.";
	public static String NAVIGATRICE=
		"La Navigatrice gagne 4 piece d'or ou 4 cartes. Elle ne " +
		"ne peut batir de quartiers.";
	public static String CAPITAINE=
		"Le Capitaine peut confisquer un quartier de cout inferieur " +
		"ou egal a 3, en payant son cout a son proprietaire." +
		"Chaque quartier Militaire dans sa cite lui rapporte 1 " +
		"piece d'or.";
	public static String DIPLOMATE=
		"Le Diplomate peut echanger l'un de ses quartiers batis " +
		"avec un quartier adverse. Chaque quartier Militaire " +
		"dans sa cite lui rapporte 1 piece d'or.";
	public static String CONDOTTIERE=
		"Le Condottiere peut detruire un quartier en payant " +
		"son cout de construction moins 1. " +
		"Chaque quartier Militaire dans sa cite lui rapporte 1 " +
		"piece d'or.";
	public static String ARTISTE=
		"L'Artiste peut embellir un ou deux de ses quartiers en " +
		"leur assignant 1 piece d'or. Un meme quartier ne peut etre " +
		"embelli qu'une seule fois.";
	public static String BAILLI=
		"Chaque fois qu'un joueur batit un quartier, il doit placer " +
		"1 piece d'or sur le jeton Bailli. Prenez toutes les pieces" +
		"d'or sur le jeton Bailli.";
	public static String REINE=
		"Si la Reine est a cote du joueur qui a choisi la carte " +
		"du personnage de rang 4 (gemme jaune), elle recoit 3 " +
		"pieces d'or.";
}
