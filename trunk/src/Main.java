import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) {

		Graphe g = new Graphe(); // Declaration d'un graphe

		// Initialisation du graphe, soit par le fichier soit par la console
		if(args.length == 1){ // Si un fichier est present.
			try {
				BufferedReader lecteurAvecBuffer = new BufferedReader (new FileReader(args[0]));
				g.lireFichier(lecteurAvecBuffer); // Init graphe
			} 
			catch (FileNotFoundException e) { // Gestion des exceptions
				e.printStackTrace();
			}
		}

		else
			g.lireConsole(); // Si aucun fichier n'est present, lecteure console


		if(g.testGraphe()){ // Si le graphe est correcte
			//g.affiche();
			int n = g.sommetDeDepart(); // Choix du sommet de depart pour le chercher le cycle eulerien
			System.out.println("Cycle eulérien : " + Fleury.algoFleury(g, n)); // Affichage
		} 
		
		else { // Graphe incorrecte car perte de voisin
			System.out.println("Graphe incorrecte.\n" +
					"Vous avez surement oublié quelque voisin ...\n" +
					"Veuillez relancer le programme.");
		}

	}

	/*
	 6
	 2 5 -1
	 1 3 4 5 -1
	 2 4 5 6 -1
	 2 3 5 6 -1
	 1 2 3 4 -1
	 3 4 -1
	 */

	/*5
	2 3 -1
	3 4 -1
	1 2 4 5 -1
	3 5 -1
	4 3 -1
	 */
	

	/*3
	2 -1
	3 -1
	1 2 -1*/
}
