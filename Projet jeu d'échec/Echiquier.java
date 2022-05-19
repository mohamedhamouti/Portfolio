import java.util.ArrayList;

/**
 * Echiquier courant
 */
public class Echiquier {
	/**
	 * Double tableau contenant toutes les Pieces de notre Echiquier
	 */
	public Piece [][] plateauEchec;

	/**
	 * Constructeur par defaut, initialise toutes les pieces selon un echiquier standard
	 */
	public Echiquier(){	
		
		plateauEchec = new Piece [8][8];

		/*Cote noir*/ 

		this.plateauEchec[0][0] = new Tour (false);
		this.plateauEchec[0][1] = new Cavalier(false);
		this.plateauEchec[0][2] = new Fou(false);
		this.plateauEchec[0][3] = new Dame(false) ;
		this.plateauEchec[0][4] = new Roi(false);
		this.plateauEchec[0][5] = new Fou(false);
		this.plateauEchec[0][6] = new Cavalier(false);
		this.plateauEchec[0][7] = new Tour (false);

		this.plateauEchec[1][0] = new Pion(false);
		this.plateauEchec[1][1] = new Pion(false);
		this.plateauEchec[1][2] = new Pion(false);
		this.plateauEchec[1][3] = new Pion(false);
		this.plateauEchec[1][4] = new Pion(false);
		this.plateauEchec[1][5] = new Pion(false);
		this.plateauEchec[1][6] = new Pion(false);
		this.plateauEchec[1][7] = new Pion(false);

		/*Cote Blanc*/ 

		this.plateauEchec[7][0] = new Tour (true);
		this.plateauEchec[7][1] = new Cavalier(true);
		this.plateauEchec[7][2] = new Fou(true);
		this.plateauEchec[7][3] = new Dame(true) ;
		this.plateauEchec[7][4] = new Roi(true);
		this.plateauEchec[7][5] = new Fou(true);
		this.plateauEchec[7][6] = new Cavalier(true);
		this.plateauEchec[7][7] = new Tour (true);

		this.plateauEchec[6][0] = new Pion(true);
		this.plateauEchec[6][1] = new Pion(true);
		this.plateauEchec[6][2] = new Pion(true);
		this.plateauEchec[6][3] = new Pion(true);
		this.plateauEchec[6][4] = new Pion(true);
		this.plateauEchec[6][5] = new Pion(true);
		this.plateauEchec[6][6] = new Pion(true);
		this.plateauEchec[6][7] = new Pion(true);
	}
	
	/**
	 * Methode pour creer une copie de notre echiquier avec des adresses mémoires différentes
	 * @param echiquier L'echiquier a copier
	 * @return newEchiquier La copie de cet echiquier
	 */
	public Echiquier clone(Echiquier echiquier) {
		Piece[][] oldtab = echiquier.plateauEchec;
		Piece[][] clonetab = new Piece[8][8];
		
		for(int i =0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(oldtab[i][j]!=null) {
					Piece p = oldtab[i][j];
					switch (p.getClass().getSimpleName()) {
					case "Pion" : clonetab[i][j]= new Pion(p.getCouleur());
					break;
					case "Cavalier" : clonetab[i][j]= new Cavalier(p.getCouleur());
					break;
					case "Fou" : clonetab[i][j]= new Fou(p.getCouleur());
					break;
					case "Dame" : clonetab[i][j]= new Dame(p.getCouleur());
					break;
					case "Roi" : clonetab[i][j]= new Roi(p.getCouleur());
					break;
					case "Tour" : clonetab[i][j]= new Tour(p.getCouleur());
					break;
					}
						
				}
			}
		}
		
		Echiquier newEchiquier = new Echiquier();
		newEchiquier.plateauEchec = clonetab;
		
		return newEchiquier;
	}


	/**
	 * Retourne la piece contenue aux coordonnes x et y
	 * @param x Premier int du double tableau
	 * @param y Deuxieme int du double tableau
	 * @return this.plateauEchec[x][y] La piece contenue a ces coordonnees
	 */
	public Piece getPiece(int x, int y) {
		return this.plateauEchec[x][y];
	}

	/**
	 * Affiche l'echiquier courant sous forme d'un tableau dans la console
	 */
	public void afficherEchec() {
		System.out.println("-------------------------");
		for (int i= 0; i<8; i+=1){
			System.out.print("|");
			for (int j = 0; j<8; j+=1){
				if (plateauEchec[i][j]!= null){
				System.out.print(plateauEchec[i][j].getUnicode());
				System.out.print("|");
				}
				else { System.out.print("  ");System.out.print("|");}



			}
			System.out.println("");
			System.out.println("-------------------------");
		}

	}
	
	/**
	 * Convertit des coordonnes tableau 2x2 en Id pour le calcul des deplacements
	 * @param coord Coordonnee
	 * @return id ID
	 */
	public int coordToId(int coord) {
        int id1 = coord%10;
        int id2 = coord/10;
        
        id1=id1+1;
        id2=8-id2;
        
        int id = (id1*10)+id2;
        return id;
        
    }
	
	/**
	 * Convertit un Id pour le calcul des deplacements en coordonnes tableau 2x2
	 * @param id ID
	 * @return coord Coordonnee
	 */
	public int idToCoord(int id){
        int coord1=id%10;
        int coord2=id/10;

        coord1=8-coord1;
        coord2-=1;

        int coord = (coord1*10)+coord2;
        return coord;
    }

	/**
	 * Deplace une Piece du tableau sans verification. Retourne les deplacements en Id pour sauvegarde
	 * @param i Position 1
	 * @param j Position 2
	 * @return tab Deux entiers, pour le fichier de sauvegarde
	 */
	  public int [] deplacer (int i, int j){
		 
		  	int x = i/10;
	        int y = i%10; 
	        
	        int x1  = j/10;
	        int y1 = j%10;
	        
	        int [] tab  = {i,j};
	        if (plateauEchec[x][y]!=null){
	        	Piece a = plateauEchec[x][y];
	            plateauEchec[x][y]=null;
	            plateauEchec[x1][y1]=a;
	            a.setHasMoved(true);
	            if(x1==0 && plateauEchec[x1][y1].getClass().getSimpleName().equals("Pion") ){
	                plateauEchec[x1][y1]= new Dame(true);
	                plateauEchec[x1][y1].setHasMoved(true);
	            }
	            else if (x1==7 && plateauEchec[x1][y1].getClass().getSimpleName().equals("Pion") ){
	                plateauEchec[x1][y1]= new Dame(false);
	                plateauEchec[x1][y1].setHasMoved(true);

	            }
	        }

	      
	        return tab;
	    }

    /**
	 * Verifie si un deplacement est legal en fonction des autres pieces
	 * TODO Roque pas encore implemente
	 * TODO Promotion pas encore implementee
	 * @param i Position 1
	 * @param j Position 2
	 * @return boolean true si legal, false si illegal
	 */
	public boolean deplacementFinal(int i, int j){ // paramètres : coordonnées i et j sur le double tableau
	
		int x = i/10;
		int y = i%10; 
		
		int x1  = j/10;
		int y1 = j%10;

		ArrayList<Integer> chemin = plateauEchec[x][y].DeplacementValide(coordToId(i),coordToId(j));
		
		if (plateauEchec[x][y].getClass().getSimpleName().equals("Pion") && chemin.size()!=0) { // Déplacement pour pion
			if (chemin.get(chemin.size()-1)==0){												// Manger en diagonale
				if (plateauEchec[x1][y1]==null || plateauEchec[x][y].getCouleur()==plateauEchec[x1][y1].getCouleur()){
					return false;
				}
			}else {
				for (int p = 1; p<chemin.size();p++) {											// Tout droit
					int m1=idToCoord(chemin.get(p))/10;
					int m2=idToCoord(chemin.get(p))%10;
					if (plateauEchec[m1][m2]!=null) {
						return false;
					}
				}
			}
			
			return true;
		}else if (chemin.size()!=0){														    // Déplacement pour tous les autres
			if (chemin.size()==2) {
				if (plateauEchec[x1][y1]!=null && plateauEchec[x][y].getCouleur()==plateauEchec[x1][y1].getCouleur()){
					return false;
				}
			}
			for (int t = 1; t < chemin.size()-1; t++){
				int p1=idToCoord(chemin.get(t))/10;
				int p2=idToCoord(chemin.get(t))%10;
				if (plateauEchec[p1][p2]!=null){
					return false;
				}
			if (plateauEchec[x1][y1]!=null && plateauEchec[x][y].getCouleur()==plateauEchec[x1][y1].getCouleur()){
				return false;
				}
			}
		}else{
			return false;
		}

		return true; 
	}
	
	/**
	 * Verifie si le roi indique est en situation d'echec
	 * @param couleur Couleur du Roi a verifier
	 * @return boolean true si oui, false sinon
	 */
	public boolean estEnEchec(boolean couleur) {
		
		int coordRoi = 0;
		
		for(int i=0;i<8;i++) { // boucle pour detecter la position du Roi a verifier
			for(int j=0;j<8;j++) {
				if(plateauEchec[i][j]!=null) {
					if(plateauEchec[i][j].getClass().getSimpleName().equals("Roi") && plateauEchec[i][j].getCouleur()==couleur) {
						coordRoi=i*10+j;
					}
				}
			}
		}
		
		
		
		for (int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(plateauEchec[i][j]!=null) {
					if (plateauEchec[i][j].getClass().getSimpleName().equals("Pion") && plateauEchec[i][j].getCouleur()!=couleur){							//echec pour les pions
						if((((i*10+j)-11==coordRoi)||((i*10+j)-9==coordRoi)&&!couleur) || (((i*10+j)+11==coordRoi)||((i*10+j)+9==coordRoi)&&couleur)){ //Un pion met en echec un roi
							return true;
						}
					}else if(plateauEchec[i][j].getCouleur()!=couleur && deplacementFinal(i*10+j, coordRoi)) {  // echec pour les autres
						return true;
					}
				}
			}
		}
		
		
		
		
		return false;
	}
} 