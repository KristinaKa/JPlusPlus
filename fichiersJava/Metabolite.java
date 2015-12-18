public class Metabolite extends Piece {
	int couleur;
	// rouge=1, bleu=2, jaune=3, vert=4

	public static final String COL_RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String BLUE = "\u001B[34m";
	public static final String YELLOW = "\u001B[33m";
	public static final String GREEN = "\u001B[32m";
	public static final String WHITE = "\u001B[37m";

	public Metabolite(int x, int y, int coul, int num) {
		super(x, y, num);
		couleur = coul;
		setType('M');
	}

	public int getCouleur() {
		return couleur;
	}

	public void affiche() {
		String nom = "M";
		String COLOR = null;
		switch (couleur) {
		case 1:
			COLOR = RED;
			break;
		case 2:
			COLOR = BLUE;
			break;
		case 3:
			COLOR = YELLOW;
			break;
		case 4:
			COLOR = GREEN;
			break;
		default:
			COLOR = WHITE;
		}
		System.out.print(" " + COLOR + nom + COL_RESET + " ");
	}

}
