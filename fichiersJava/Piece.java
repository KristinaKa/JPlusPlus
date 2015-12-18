abstract public class Piece {
	private int x;
	private int y;
	private char type;
	private int numero;
	private boolean enJeu;

	public Piece(int x, int y, int num) {
		this.x = x;
		this.y = y;
		numero = num;
		enJeu = true;
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public void setx(int x) {
		this.x = x;
	}

	public void sety(int y) {
		this.y = y;
	}

	public char getType() {
		return type;
	}

	public void setType(char c) {
		type = c;
	}

	public int getNum() {
		return numero;
	}

	public boolean getEnJeu() {
		return enJeu;
	}

	public void eliminer() {
		enJeu = false;
	}

	abstract public void affiche();
}
