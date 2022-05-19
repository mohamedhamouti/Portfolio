import java.util.ArrayList;

/**
 * La classe de Roi (herite de Piece)
 */
public class Roi extends Piece{

	/**
	 * Constructeur de Roi par sa couleur
	 * @param couleur Couleur du Roi
	 */
	public Roi(boolean couleur) {
		this.couleur = couleur;
		HasMoved = false;
		if (couleur == true) {
			unicode = "♔ ";
		}else {unicode = "♚ ";}
	}
	
	/**
	 * Constructeur vide de Roi (herite de Piece)
	 */
	public Roi() {
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
		
		// On établit une liste des coups possibles depuis pos1
		int[] moves = {pos1+1,pos1-1, // en colonne
		pos1+11,pos1-11,			  // en diagonale +
		pos1+10,pos1-10,			  // en ligne
		pos1+9,pos1-9};				  // en diagonale -

		for (int k : moves) {
			if (pos2==k && isInRange(pos1,pos2)) {			// k vérifie si la destination finale a été trouvée
				chemin.add(pos1);
				chemin.add(pos2);
			}
		}

		return chemin;
	}


}