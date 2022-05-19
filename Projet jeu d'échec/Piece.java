import java.util.ArrayList;

/**
 * Piece courante
 */
public abstract class Piece {
    
    /**
     * Couleur de la piece
     */
    public boolean couleur=true;
    
    /**
     * Indique si la piece a bouge
     */
    public boolean HasMoved=false;
    /**
     * L'unicode de la piece
     */
    
    public String unicode;
    
    /**
     * Un tableau d'entiers pour verifier si un deplacement est a portee de celui-ci
     */
    public int[] cases = {11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28,31,32,33,34,35,36,37,38,41,42,43,44,45,46,47,48,49,51,52,53,54,55,56,57,58,59,61,62,63,64,65,67,68,71,72,73,74,75,76,78,81,82,83,84,85,86,87,88};
    

    /**
     * Constructeur vide de la Piece
     * 
     */
    public Piece() {
        
    }
    
    /**
     * Getter de l'unicode de la Piece
     * @return unicode
     */
    public String getUnicode(){
        return this.unicode;
    }
    /**
     * Getter de la couleur de la Piece
     * @return couleur
     */
    public boolean getCouleur() {
        return this.couleur;
    }
    
    /**
     * Calcule le chemin possible pour un deplacement donne
     * @param i position 1
     * @param j position 2
     * @return chemin Vide si impossible
     */
    public abstract ArrayList<Integer> DeplacementValide(int i, int j);
    
    /**
     * Definit si la piece a bouge
     * @param HasMoved true si elle a bouge, false sinon
     */
    public void setHasMoved(boolean HasMoved){
        this.HasMoved = HasMoved;
    }
    
    
    /**
     * Verifie si les valeurs indiquees sont a portee de l'echiquier ET si elles ne sont pas egales
     * @param pos1 Position 1
     * @param pos2 Position 2
     * @return isInRange true si a portee, false sinon
     */
    public boolean isInRange(int pos1, int pos2) {
        boolean isInRange = false;
        boolean oneInRange = false;
        boolean twoInRange= false;
        
        for (int id : cases) {
            if (pos1==id) {
                oneInRange = true;
            }else if(pos2==id) {
                twoInRange=true;
            }
        }
        
        if (oneInRange && twoInRange) {
            isInRange=true;
        }
        
        return isInRange;
    }

       
}