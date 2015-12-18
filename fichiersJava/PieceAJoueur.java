abstract public class PieceAJoueur extends Piece {
	private int joueur;

	public int getJoueur() {
		return joueur;
	}

	public PieceAJoueur(int x, int y, int joueur, int num) {
		super(x, y, num);
		this.joueur = joueur;

	}

	abstract public int move(String direction, Plateau p);

	public void placer(Plateau p, int new_x, int new_y)
	{
		p.effacerPiece(getx(), gety());
		setx(new_x);
		sety(new_y);
		p.placer(this);
	}
}
