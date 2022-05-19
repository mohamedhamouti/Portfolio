import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer; 

/**
 * Partie courante
 */
public class Partie {
	
	/**
	 * Echiquier dans lequel on joue
	 */
	public Echiquier echiquier;
	
	/**
	 * Couleur correspondant au joueur actuel
	 */
	public boolean joueur;
	
	/**
	 * Nom de la partie (pour sauvegarde)
	 */
	public String nomPartie;
	
	/**
	 * Constructeur par defaut d'une partie
	 */
	
	/**
	 * Variable pour initialiser le chargement
	 */
	public int x;
	/**
	 * Variable pour initialiser le chargement
	 */
	public int y;
	/**
	 * Liste de coups de la partie actuelle. Est enregistree lors de la sauvegarde
	 */
	public ArrayList<Integer> listeCoups = new ArrayList<Integer>();
	
	/**
	 * Contructeur par défaut, initialise un nouvel echiquier
	 */
	public Partie() {
		echiquier = new Echiquier();
		joueur = true;
		nomPartie = "default";
	}
	
	/**
	 * Verifie si un coup n'engendrerait pas une situation d'echec (Piece clouee) en creant une copie temporaire du tableau
	 * @param i position 1
	 * @param j position 2
	 * @param couleur couleur du Roi a verifier
	 * @return boolean
	 */
	public boolean estCloue(int i, int j, boolean couleur){ // Crée un deuxième échiquier avec le coup associé et vérifie les echecs
		Echiquier echiquierTest = echiquier.clone(echiquier);
		
		echiquierTest.deplacer(i,j);
		
		return echiquierTest.estEnEchec(couleur);

	}
	
	/**
	* Verifie si le roi indique est en echec et mat. Renvoie 1 si tout va bien, -1 s'il y a echec et mat, et 0 si PAT
	* @param couleur couleur du roi
	* @return int 1: pas d'echec et mat -1: echec et mat 0: PAT
	*/
	public int echecEtMat(boolean couleur) {
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (echiquier.plateauEchec[i][j]!=null && echiquier.plateauEchec[i][j].getCouleur()==couleur) {
					for (int k=0;k<8;k++) {
						for (int l=0;l<8;l++) {
							if (i*10+j!=k*10+l && echiquier.deplacementFinal(i*10+j,k*10+l)) {
								if (!estCloue(i*10+j,k*10+l,couleur)){
									return 1;
								}
							}
						}
					}
				}
			}
		}
		if (echiquier.estEnEchec(couleur)) {
			return -1;
		}
		return 0;
	}
	
	/**
	 * Charge et joue un array de coups depuis sauvegarde.txt
	 */
	public void charger (){
		ArrayList <Integer> elements = new ArrayList<Integer>();
		int[] tab;
		int x=0;
		int y=0;
		
		File dataFile = new File("sauvegarde.txt");
	try {
		InputStream ips = new FileInputStream(dataFile);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String ligne;
		while ((ligne = br.readLine()) != null) {
		// recuperation de la ligne courante
			// separation de la ligne avec le toke " " (espace)
			//
			String token = " ";
			StringTokenizer stringTokenizer = new StringTokenizer(ligne, token);
			// Parcours des tokens de la ligne
			while (stringTokenizer.hasMoreElements()) {
				String element = (String) stringTokenizer.nextElement();
				int a = Integer.valueOf(element);
				elements.add(a);

				

			}
			}
			br.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			for(int i=0;i<elements.size();i+=2){
				
					x=elements.get(i);		
			
					y=elements.get(i+1);
					
					tab=echiquier.deplacer(x,y);
					listeCoups.add(tab[0]);
					listeCoups.add(tab[1]);
					
					joueur=!joueur;
					echiquier.afficherEchec();
					try {
						Thread.sleep(700);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
			}	
	}
	
	/**
	 * Enregistre un array de coups dans sauvegarde.txt
	 * @param listeCoups Array de coups effectués pendant la partie
	 */
	public void sauvegarder(ArrayList<Integer> listeCoups) throws IOException {		
		
		File outFile = new File("sauvegarde.txt");
		try (FileWriter fileWriter = new FileWriter(outFile)) {
			System.out.println("Writer file: " + outFile.getAbsolutePath());
			System.out.println("With encoding: " + fileWriter.getEncoding());


			for(int i=0;i<listeCoups.size();i+=1){
				if(i%2==0){
				fileWriter.write(String.valueOf(listeCoups.get(i))+" ");
				}
				if(i%2!=0){
				fileWriter.write(String.valueOf(listeCoups.get(i)));
				fileWriter.write("\n");

				}
			    

			}
		}
	}
	
	/**
	 * Menu permettant a un utilisateur d'interagir avec le programme, de selectionner un mode de demarrage, d'entrer des valeurs...
	 */
	public void menu() {
		int choix =0;
		int depart = 0;
		int arrivee = 0;
		int[] tab;
		
		boolean game = true;  // true tant que la partie est en cours
		Scanner sc = new Scanner(System.in);
		System.out.println("Que souhaitez vous faire ? ");
		System.out.println("1:Lancer une nouvelle partie ");
		System.out.println("2:Charger une partie existante");
		System.out.println("3: Sortie \n ");
		
		while(choix!=1 && choix!=2 && choix!=3){
			System.out.println("Entrez votre choix:");
			choix=sc.nextInt();
		}

		switch(choix)
		{
			case 1:				
			System.out.println("Lancement de la nouvelle partie");
			echiquier.afficherEchec();
			break;

			case 2:
			System.out.println("Chargement de la partie");
			echiquier.afficherEchec();
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			charger();
			break;

			case 3:
			System.out.println("Bonne continuation ! :D");
			game = false;
			break;				 				
		}
		
		
		while (game) { // true tant que l'utilisateur ne veut pas arreter de jouer
			boolean valide=false;
			if(joueur) {
				System.out.println("Blanc: A vous de jouer ! (Ecrivez -1 à tout moment pour quitter)");
			}else {
				System.out.println("Noir: A vous de jouer ! (Ecrivez -1 à tout moment pour quitter)");
			}
				
			
			do {
				System.out.println("Quelle est votre position de départ ?");
				depart = sc.nextInt();
				System.out.println("Quelle est votre position d'arrivée ?");
				arrivee = sc.nextInt();
				

				if (depart==-1 || arrivee ==-1) {
					System.out.println("Vous quittez la partie...");
					try {
						sauvegarder(listeCoups);
					} catch (IOException e) {
						e.printStackTrace();
						
					sc.close();
					}
					game=false;
					break;
				}
				else if(depart<=-1 || depart > 77 || arrivee<=-1 || arrivee > 77){
					echiquier.afficherEchec();
					System.out.println("Coordonnées pas à portée, réessayez");
				}
				else if(echiquier.plateauEchec[depart/10][depart%10]==null) {
                    echiquier.afficherEchec();
                    System.out.println("Vous avez selectionné une pièce vide, réessayez");    
                }
				else if(echiquier.plateauEchec[depart/10][depart%10].getCouleur()==joueur && echiquier.plateauEchec[depart/10][depart%10]!=null  &&  (echiquier.deplacementFinal(depart, arrivee)) && !estCloue(depart,arrivee,joueur)) {
					tab=echiquier.deplacer(depart, arrivee);
					listeCoups.add(tab[0]);
					listeCoups.add(tab[1]);
					valide=true;
				}else if(echiquier.plateauEchec[depart/10][depart%10].getCouleur()!=joueur){
					echiquier.afficherEchec();
					System.out.println("Choisissez une pièce de votre couleur, réessayez");	
				}
				else {
					echiquier.afficherEchec();
					System.out.println("Le coup inséré n'est pas légal, réessayez");				
				}
				
			}while(!valide);
			
			switch(echecEtMat(!joueur))
			{
			case -1:
				if (joueur){
					System.out.println("Echec et mat ! Le joueur blanc a gagné !");
				}		
				else{System.out.println("Echec et mat ! Le joueur noir a gagné !");}
				game=false;
				break;
			
			case 0:
				System.out.println("PAT ! Il y a égalité !");
				game=false;
				break;
				
			case 1:
				echiquier.afficherEchec();
				System.out.println("Fin du tour");
				valide=!valide;
				joueur=!joueur;
				break;
			}
			
		}
		
	}
} 