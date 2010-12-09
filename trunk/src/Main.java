
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {

		Graphe g = new Graphe();

		if(args.length == 1){
			try {
				BufferedReader lecteurAvecBuffer = new BufferedReader (new FileReader(args[0]));
				g.lireFichier(lecteurAvecBuffer);
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		else
			g.lireConsole();

		if(g.testGraphe()){

			//g.affiche();

			while(true){
				System.out.println("***************\nDe quel sommet voulez-vous partir : ");

				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				String tmp;
				try {
					tmp = br.readLine();
					int n = Integer.parseInt(tmp); 

					System.out.println("***************\n");		

					System.out.println("Sommet du debut : " + n);
					System.out.println();
					System.out.println(Fleury.algoFleury(g, n));
				} 
				catch (IOException e) {
					e.printStackTrace();
				} 

			}
		}
		else {
			System.out.println("Graphe incorrecte.\nVeuillez relancer le programme.");
		}

	}

	/*
	 6
	 2 5
	 1 5 4 3
	 2 5 4 6
	 5 2 3 6
	 1 2 3 4
	 4 3
	 */

	/*5
	2 3 -1
	3 4 -1
	1 2 4 5 -1
	3 5 -1
	4 3 -1
	 */
}
