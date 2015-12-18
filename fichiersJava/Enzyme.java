public class Enzyme extends PieceAJoueur {
	int couleur;
	int metCaptures; // nb des métabolites captures
	// peut être max 5

	// rouge=1, bleu=2, jaune=3, vert=4

	public static final String COL_RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String BLUE = "\u001B[34m";
	public static final String YELLOW = "\u001B[33m";
	public static final String GREEN = "\u001B[32m";
	public static final String WHITE = "\u001B[37m";

	public Enzyme(int x, int y, int joueur, int coul, int num) {
		super(x, y, joueur, num);
		couleur = coul;
		metCaptures = 0;
		setType('E');
	}

	public int move(String direction, Plateau plateau) {
		int ret = 0;
		// retourne 0 si l'enzyme peut pas se déplacer
		// 1 si l'enzyme se déplace dans une case vide ou avec un lipide
		// 2 si l'enzyme capture un Metabolite
		int new_x = getx(), new_y = gety();
		switch (direction) {
		case "z":
			new_y--;
			break;
		case "d":
			new_x++;
			break;
		case "s":
			new_y++;
			break;
		case "q":
			new_x--;
			break;
		default:
			return 0;
		}

		if (plateau.valide(new_x, new_y)) {
			if (plateau.vide(new_x, new_y)) {
				ret = 1;
			} else {
				Piece pieceDansLaCase = plateau.getPiece(new_x, new_y);
				// Si il y a un Metabolite dans la case
				if (pieceDansLaCase.getType() == 'M') {

					Metabolite met = (Metabolite) pieceDansLaCase;
					if (capturerMet(plateau, met))
					// déplacer si la capture est réussie
					{
						ret = 2;
					}
				}
				// Si il y a un lipide
				else {
				    pieceDansLaCase.eliminer();
					ret = 1;
				}
			}
			if (ret != 0)
				placer(plateau, new_x, new_y);
		}

		return ret;
	}

	public boolean capturerMet(Plateau p, Metabolite met) {
		if (metCaptures < 5 && couleur == met.getCouleur()) {
			p.eliminerMet(met);
			metCaptures++;
			return true;
		} else
			return false;
	}

	public void affiche() {
		String nom;
		if (getJoueur() == 1) {
			nom = "E";
		} else {
			nom = "e";
		}
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
		System.out.print(COLOR + nom + getNum() + COL_RESET + " ");
	}

}
