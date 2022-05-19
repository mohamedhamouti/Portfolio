import java.util.ArrayList;

/**
 * La classe de Pion (herite de Piece)
 */
public class Pion extends Piece {
	/**
	 * Constructeur de Pion par sa couleur
	 * @param couleur Couleur du Pion
	 */
	public Pion(boolean couleur) {
		this.couleur = couleur;
		HasMoved = false;
		if (couleur == true) {
			unicode = "♙ ";
		}else {unicode = "♟";}
	}
	
	/**
	 * Constructeur vide de Pion (herite de Piece)
	 */
	public Pion() {
		super();
	}
	
	/**
	 * Calcule le chemin possible pour un deplacement donne
	 * @param pos1 Position 1
	 * @param pos2 Position 2
	 * @return chemin Vide si impossible
	 */
	public ArrayList<Integer> DeplacementValide (int pos1, int pos2) {
		
		ArrayList<Integer> chemin = new ArrayList<Integer>();
		boolean k = false;

		if (!HasMoved && couleur && isInRange(pos1,pos2)) { // Blanc, n'a pas bougé
			if (pos2==pos1+1 || pos2==pos1+2) {
				while (!k) {
					chemin.add(pos1);
					pos1+=1;
					if (pos1==pos2) {
						k = true;
						chemin.add(pos2);
					}
				}
			}
		}
		
		if (!HasMoved && !couleur && isInRange(pos1,pos2)){ // Noir, n'a pas bougé
			if (pos2==pos1-1 || pos2==pos1-2) {
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
		
		if(HasMoved && couleur && isInRange(pos1,pos2)){ // Blanc, a bougé
			if (pos2==pos1+1) {
				chemin.add(pos1);
				chemin.add(pos2);
			}
		}
		if (HasMoved && !couleur && isInRange(pos1,pos2)){ // Noir, a bougé
			if (pos2==pos1-1) {
				chemin.add(pos1);
				chemin.add(pos2);
			}
		}
		
		if(couleur && isInRange(pos1,pos2)) { // Blanc, diagonale
			if (pos2==pos1+11 || pos2==pos1-9) {
				chemin.add(pos1);
				chemin.add(pos2);
				chemin.add(0);
			}
		}
		
		if(!couleur && isInRange(pos1,pos2)) // Noir, diagonale
			if (pos2==pos1-11 || pos2==pos1+9) {
				chemin.add(pos1);
				chemin.add(pos2);
				chemin.add(0);
		}

		return chemin;
	}
	
}