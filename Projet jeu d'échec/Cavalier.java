import java.util.ArrayList;

/**
 * La classe du Cavalier (herite de Piece)
 */
public class Cavalier extends Piece {
	
	/**
	 * Constructeur de Cavalier par sa couleur
	 * @param couleur Couleur du Cavalier
	 */
	public Cavalier(boolean couleur) {
		this.couleur = couleur;
		HasMoved = false;
		if (couleur == true) {
			unicode = "♘ ";
		}else {unicode="♞ ";}
	}
	
	/**
	 * Constructeur vide de Cavalier (herite de Piece)
	 */
	public Cavalier() {
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
		// La formule est '(pos1/10+x)*10+(pos1%10+y)' avec x la distance en ligne et y en colonne
		int[] moves = {(pos1/10+1)*10+(pos1%10+2),(pos1/10+2)*10+(pos1%10+1),
		(pos1/10+2)*10+(pos1%10-1),(pos1/10+1)*10+(pos1%10-2),
		(pos1/10-1)*10+(pos1%10-2),(pos1/10-2)*10+(pos1%10-1),
		(pos1/10-2)*10+(pos1%10+1),(pos1/10-1)*10+(pos1%10+2)};
		
		for (int k : moves) {
			if (pos2==k && isInRange(pos1,pos2)) {			// k vérifie si la destination finale a été trouvée
				chemin.add(pos1);
				chemin.add(pos2);
			}
		}

		return chemin;
	}
}
