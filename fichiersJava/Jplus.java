import java.io.*;

public class Jplus {

	public static void main(String[] args) {

		int tour = 0; // L'indice du joueur qui joue

		// Initialisation plateau, joueurs, pieces
		Plateau plateau = new Plateau(15);
		Joueur[] joueurs = new Joueur[2];
		// System.out.println("Nom du joueur 1 :");
		// String nom1 = saisieChaine();
		// System.out.println("Nom du joueur 2 :");
		// String nom2 = saisieChaine();
		joueurs[0] = new Joueur("A", 1);
		joueurs[1] = new Joueur("B", 2);
		joueurs[0].init(plateau);
		joueurs[1].init(plateau);
		plateau.initMetabolites();
		welcome();

		while ( ! finJeu(joueurs,plateau) ) {
			
			plateau.affiche();
			afficheScores(joueurs);
			joueurs[tour].joue(plateau);
			tour = (tour + 1)%2;
			joueurs[tour].eliminerPiecesTues();
			if (tour == 0)
				plateau.deplacerMetabolites();
			
		}
	}

	public static void welcome() {
		System.out.println("Bienvenu dans JPlusPlus !");
		System.out.println("JplusPlus est une version tres biologisee du jeu des echecs."); 
		System.out.println("Dans ce jeu vous disposez chacun des enzymes et des lipides et\n" +
				"vous pouvez utiliser vos enzymes pour transformer les metabolites,\n" +
				"commun a les deux joueurs.\n" +
				"Le but du jeu est de transformer le maximum des metabolites avec ses\n" +
				"enzymes.\nBonne chance !");
		System.out.println("Pour le deplacement donnez les informations necessaires\n" +
				"(nombres ou chiffres) separes par des espaces dans l'ordre demande !");

	}

	public static boolean finJeu(Joueur[] joueurs, Plateau plateau){
		for (Joueur joueur : joueurs){
			if ( joueur.getScore() == 25 ){
				System.out.println("Joueur "+joueur.getNom()+" vous avez gagne !");
				return true;
			}
		}	
		if (plateau.plusDeMetabolites()){
			System.out.println("Il n'y a plus des metabolites ! Fin du jeu !");
			return true;
		}
		return false;
		
	}
	
	public static void afficheScores(Joueur[] joueurs){
		//System.out.println("----------------------------------------------------------------");
		for (Joueur joueur : joueurs){
			System.out.println("Score joueur "+joueur.getNom()+" : "+joueur.getScore());
		}
		System.out.println("----------------------------------------------------------------");
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
