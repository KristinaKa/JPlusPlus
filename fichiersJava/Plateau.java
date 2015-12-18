import java.util.Random;

public class Plateau {
	private int taille;
	private Piece plateau[][];
	private Metabolite [] metabolites = new Metabolite [40] ;

	public Plateau(int t) {
		taille = t;
		plateau = new Piece[t][t];
	}

	public void placer(Piece piece) {
		plateau[piece.gety()][piece.getx()] = piece;
	}

	public boolean valide(int x, int y) {
		// fonction qui verifie si les coordonnées pour
		// une case du tableau sont valides
		if (x >= 0 && x < taille && y >= 0 && y < taille) {
			return true;
		}
		return false;
	}

	public boolean vide(int x, int y) {
		if (plateau[y][x] == null) {
			return true;
		}
		return false;
	}

	public void initMetabolites() {
		// Initialisation des métabolites
		// Ils se placent aléatoirement entre les lignes 5 et 11 de la grille
		Random rand = new Random();
		int minY = 4;
		int maxY = 10;
		int color = 1;
		int randY, randX;
		for (int i = 0; i < 40; i++) {
			do {
				// nombre aléatoire entre 4 et 10
				randY = rand.nextInt((maxY - minY) + 1) + minY;
				// nombre aléatoire entre 0 et 14
				randX = rand.nextInt(15);

			}
			while (plateau[randY][randX] != null);
			metabolites[i] = new Metabolite(randX, randY, color, i);
			placer(metabolites[i]);

			if (color == 4) {
				color = 1;
			} else {
				color++;
			}
		}
	}

	public void deplacerMetabolites() {
		// Fonction qui deplace les metabolites à une, deux ou
		//trois cases de distance
		
		Random rand = new Random();
		int g, k; //le nombre des cases ajoutés à x et y
		int set = 0;
		
		for (Metabolite met : metabolites) {
			if (met == null)
				continue;
			int y = met.gety();
			int x = met.getx();
			for (int i=0; i<20; i++){
				// nb aléatoire entre -3 et 3
				g = rand.nextInt(7) -3;
				k = rand.nextInt(7) -3;
				
				// Si la nouvelle case reste la case du metabolite le boucle continue
				if (g==0 && k==0)
					continue;
				
				int newX = x+g;
				int newY = y+k;
				
				if (valide(newX, newY) && vide(newX, newY)) {
					plateau[y][x] = null;
					met.setx(newX);
					met.sety(newY);
					placer(met);
					set = 1;
					break;
				}
			}
			
			while (set == 0)
			{
				int newX = rand.nextInt(16);
				int newY = rand.nextInt(16);
				
				if (!(x==newX && y==newY) && valide(newX, newY) && vide(newX, newY)) {
					plateau[y][x] = null;
					met.setx(newX);
					met.sety(newY);
					placer(met);
					set = 1;
					break;
				}
			}
		}
	}

	public void eliminerMet(Metabolite met) {
		metabolites[ met.getNum() ]= null;
	}

	public void effacerPiece(int x, int y) {
		// efface la piece dans le tableau
		plateau[y][x] = null;
	}

	public Piece getPiece(int x, int y) {
		// retourne la piece dans la position x,y
		return plateau[y][x];
	}

	public boolean plusDeMetabolites(){
		for ( Metabolite met : metabolites){
			if (met != null)
				return false;
		}
		return true;
	}
	
	public int getTaille() {
		return taille;
	}

	public void affiche() {
		int y;
		int x;
		System.out.print("\n--------------------------JPlusPlus---------------------------\n");

		// Numeration colonnes
		System.out.print("\n   |");
		for (int i = 1; i <= taille; i++) {
			if (i < 10) {
				System.out.print(" " + i + " |");
			} else {
				System.out.print(i + " |");
			}
		}
		System.out
				.print("\n----------------------------------------------------------------\n");
		for (y = 0; y < taille; y++) {
			// numeration lignes
			if (y+1 < 10) {
				System.out.print(" ");
				System.out.print(y+1 + " ");
			} else {
				System.out.print(y+1 + " ");
			}
			for (x = 0; x < taille; x++) {
				if (plateau[y][x] == null) {
					System.out.print("|   ");
				} else {
					System.out.print("|");
					plateau[y][x].affiche();
				}
			}
			System.out.print("|\n----------------------------------------------------------------\n");
		}

		//System.out.println("\n");

	}

}
