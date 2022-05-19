import java.util.ArrayList;

/**
 * La classe du Fou (herite de Piece)
 */
public class Fou extends Piece{

	/**
	 * Constructeur de Fou par sa couleur /!\ LE FOU NOIR EST BUGUE, SON CARACTERE SERA "NF" /!\
	 * @param couleur Couleur du Fou
	 */
	public Fou(boolean couleur) {
		this.couleur = couleur;
		HasMoved = false;
		if (couleur == true) {
			unicode = "♗ ";
		}else {unicode="NF";}
	}
	
	/**
	 * Constructeur vide de Fou (herite de Piece)
	 */
	public Fou() {
		super();
	}
	
	/**
	 * Calcule le chemin possible pour un deplacement donne
	 * @param pos1 Position 1
	 * @param pos2 Position 2
	 * @return chemin Vide si impossible
	 */
	public ArrayList<Integer> DeplacementValide(int pos1, int pos2) {
		
		ArrayList<Integer> chemin = new ArrayList<Integer>();
		ArrayList<Integer> cheminVide = new ArrayList<Integer>();
		boolean k = false;
		boolean l = true;
		
		if(pos1%11==pos2%11 && isInRange(pos1,pos2)){ // Vérifie le déplacement dans les diagonales bas vers haut
			if(pos1<pos2) {					 		  // Direction positive
				while (!k) {						  // k vérifie si la destination finale a été trouvée
					chemin.add(pos1);
					pos1+=11;
					if (!isInRange(pos1,11)) {
						l = false;					  // l vérifie à tout instant si on est à portée
					}
					if (pos1==pos2) {
						k = true;
						chemin.add(pos2);
					}
				}
			}else if(pos1%10>pos2%10){				  // Direction négative
				while (!k) {
					chemin.add(pos1);
					pos1-=11;
					if (!isInRange(pos1,11)) {
						l = false;
					}
					if (pos1==pos2) {
						k = true;
						chemin.add(pos2);
					}
				}
			}		
		}
		
		if(pos1%9==pos2%9 && isInRange(pos1,pos2)){ // Vérifie le déplacement dans les diagonales haut vers bas
			if(pos1/10<pos2/10) {					  // Direction positive
				while (!k) {
					chemin.add(pos1);
					pos1+=9;
					if (!isInRange(pos1,11)) {
						l = false;
					}
					if (pos1==pos2) {
						k = true;
						chemin.add(pos2);
					}
				}
			}else if (pos1/10>pos2/10) {			  // Direction négative
				while(!k) {
					chemin.add(pos1);
					pos1-=9;
					if (!isInRange(pos1,11)) {
						l = false;
					}
					if (pos1==pos2) {
						k = true;
						chemin.add(pos2);
					}
				}
			}
		}
		
		if (!l) {
			return cheminVide;
		}else {
		return chemin;
		}
	}


}