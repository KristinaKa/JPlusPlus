import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lipide extends PieceAJoueur {

	public Lipide(int x, int y, int joueur, int num) {
		super(x, y, joueur, num);
		setType('L');
	}

	public void affiche() {
		String nom;
		if (getJoueur() == 1) {
			nom = "L";
		} else {
			nom = "l";
		}
		if (getNum()<10)
			System.out.print(nom + getNum() + " ");
		else
			System.out.print(nom + getNum());
	}

	public int move(String direction, Plateau plateau) {
		// Les lipides peuvent se déplacer seulement en avant ! 
		//Mais avant est 'haut' pour jouer2 et 'bas' pour le joueur1
		int new_x = getx(), new_y = gety();
		
		System.out.println("Vous voulez vous déplacer de combien des cases ?");
		int nbCases = saisieEntier();
		
		if (nbCases <0 || nbCases > 3){
			System.out.println("Nombre des cases incorrect !");
			return 0;
		}
		
		if (direction.equals("s") && getJoueur() == 1)
			new_y += nbCases;
		else if (direction.equals("z") && getJoueur() == 2)
			new_y -= nbCases ;
		else
			return 0;
		
		if (plateau.valide(new_x, new_y) && plateau.vide(new_x, new_y)) {
			placer(plateau, new_x, new_y);
			return 1;
		}
		return 0;
	}


    public static int saisieEntier()
	{
		while(true)
		    {
			try{
			    BufferedReader buff = new BufferedReader
				(new InputStreamReader(System.in));
			    String chaine=buff.readLine();
			    int num= Integer.parseInt(chaine);
			    return num;
			}
			catch(IOException e){return 0;}
			catch(NumberFormatException e)
			    {
				System.out.println("Ce n'est pas un nombre !");
			    }
		    }
	    }	
	
}
