import java.io.*;

public class Joueur {
	private String nom;
	private int id;
	private PieceAJoueur[] mesPieces = new PieceAJoueur[28];
	private int score = 0;

	// mesPieces : tableau qui contient les enzymes du joueur dans les prémiers
	// 8 positions et les lipides dans les 20 positions successives

	public Joueur(String nom, int id) {
		this.nom = nom;
		this.id = id;
	}

	public void init(Plateau p) {
		// Initialisation des 8 Enzymes
		int x, y;
		if (id == 1) {
			y = 0;
		} else {
			y = p.getTaille() - 1;
		}
		int color = 1;
		int num = 1;
		for (x = 0; x < p.getTaille(); x += 2) {
			mesPieces[num - 1] = new Enzyme(x, y, id, color, num);
			p.placer(mesPieces[num - 1]);

			if (color == 4) {
				color = 1;
			} else {
				color++;
			}
			num++;

		}

		// Initialisation des 20 Lipides
		if (id == 1){
			x = 0;
			y = 3;
		}
		else if (id == 2)
		{
			x = 1;
			y = 12;
		}
		for(int i=0; i<20; i++){
			mesPieces[i+8] = new Lipide(x, y, id, i+1);
			p.placer(mesPieces[i+8]);
			if( (id == 1 && y == 1) || (id == 2 && y == 11) )
				y+=2;
			else{
				x++;
				y--;
			}
		}
	}

	public void joue(Plateau plateau) {
		String code;
		String[] codes;
		char typePiece;
		int num;
		String dir;
		PieceAJoueur piece;
		boolean deplacementOK = false;
		System.out.println("Joueur " + nom + " c'est à vous à jouer !");
		while (!deplacementOK) {
			System.out.println("Usage : <Type:E/L> <Numéro de la pièce> <Direction (Z/Q/S/D)>");

			code = saisieChaine();
			codes = code.split(" ");

			while (codes.length != 3) {
				System.out.println("Vous avez bien separé par d' espaces ? Reéssayez !");
				code = saisieChaine();
				codes = code.split(" ");
			}
			
			System.out.println();
			typePiece = codes[0].charAt(0);

			try {

				num = Integer.parseInt(codes[1]);
			} catch (NumberFormatException e) {
				System.out.println("Vous avez donner une lettre à place d'un nombre !");
				continue;
			}
			dir = codes[2];

			if (typePiece != 'E' && typePiece != 'L') {
				System.out.println("Type de pièce invalide");
				continue;
			}
			
			piece = findPiece(num, typePiece);
			if (piece == null) {
				System.out.println("Numéro de la pièce incorrect !");
				continue;
			}

			int moved = piece.move(dir, plateau);
			if ( moved == 1)
				deplacementOK = true;
			else if (moved == 2){
				score++;
				deplacementOK = true;
			}	
			else
				System.out.println("Donnez un code de déplacement valide !");
		}
	}

	public boolean eliminerPiecesTues()
	// Effacent les pieces du Joueur qui ne sont plus enjeu (enzymes ou lipides) 
	// Pas besoin de les eliminer du tableau car elles sont superposés par d'autres
	{
		for (int i = 0; i < 28; i++) {
			if (mesPieces[i] != null && mesPieces[i].getEnJeu() == false) {
				mesPieces[i] = null;
				return true;
			}
		}
		return false;
	}

	
	public PieceAJoueur findPiece(int num, char typePiece)
	// fonction qui retourne la piece (Enzyme ou Lipide) avec le numero
	// donné par le joueur
	{
		for (PieceAJoueur piece : mesPieces) {
			if (piece.getType() == typePiece && piece.getNum() == num) {
				return piece;
			}
		}

		return null;
	}

	public int getScore(){
		return score;
	}
	public String getNom(){
		return nom;
	}
	
	public static String saisieChaine() {
		try {
			BufferedReader buff = new BufferedReader(new InputStreamReader(
					System.in));
			String chaine = buff.readLine();
			return chaine;
		} catch (IOException e) {
			System.out.println("Impossible de travailler" + e);
			return null;
		}
	}

}
