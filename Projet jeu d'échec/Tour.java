import java.util.ArrayList;

/**
 * La classe de Tour (herite de Piece)
 */
public class Tour extends Piece {

	/**
	 * Constructeur de Fou par sa couleur
	 * @param couleur Couleur de la piece
	 */
	public Tour(boolean couleur) {
		this.couleur = couleur;
		HasMoved = false;
		if (couleur == true) {
			unicode = "♖ ";
		}else {unicode="♜ ";}
	}
	
	/**
	 * Constructeur vide de Tour (herite de Piece)
	 */
	public Tour() {
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
		boolean k = false;
		
		if(pos1/10==pos2/10 && isInRange(pos1,pos2)){ // Vérifie le déplacement dans les lignes
			if(pos1%10<pos2%10) {					  // Direction +
				while (!k) {						  // k vérifie si la destination finale a été trouvée
					chemin.add(pos1);
					pos1+=1;
					if (pos1==pos2) {
						k = true;
						chemin.add(pos2);
					}
				}
			}else if(pos1%10>pos2%10){				  // Direction -
				while (!k) {
					chemin.add(pos1);
					pos1-=1;
					if (pos1==pos2) {
						k = true;
						chemin.add(pos2);
					}
				}
			}		
		}
		
		if(pos1%10==pos2%10 && isInRange(pos1,pos2)){ // Vérifie le déplacement dans les colonnes
			if(pos1/10<pos2/10) {					  // Direction +
				while (!k) {
					chemin.add(pos1);
					pos1+=10;
					if (pos1==pos2) {
						k = true;
						chemin.add(pos2);
					}
				}
			}else if (pos1/10>pos2/10) {			  // Direction -
				while(!k) {
					chemin.add(pos1);
					pos1-=10;
					if (pos1==pos2) {
						k = true;
						chemin.add(pos2);
					}
				}
			}
		}
		return chemin;
	}
}