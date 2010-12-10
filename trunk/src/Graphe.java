import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;

public class Graphe {

	/*
	 * Un graphe est composé d'un entier representant le nombre de sommet
	 *		et d'une HashMap :  la HashMap contient une liste d'entier (representant les sommets) 
	 * 							et des listes d'entiers (representant la liste adjacente du sommet precedent)
	 */
	HashMap<Integer, ArrayList<Integer>> graphe;
	int nbSommet;

	// Constructeur sans parametres
	public Graphe(){ }

	// COnstructeur avec parametres
	public Graphe(HashMap<Integer, ArrayList<Integer>> g, int n){
		graphe = g ;
		nbSommet = n;
	}

	// Fonction permettant de saisir un graphe, syntaxiquement correcte, a l'aide de la console
	public void lireConsole(){
		// Declaration d'une HashMap
		HashMap<Integer, ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();

		//lecture
		try{
			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
			String ligne = clavier.readLine(); // premiere ligne : nombre de sommets du graphe	
			int ligneMax = ligne.length(); // Variable pour la longueur de la ligne

			if(ligneMax == 0){ // Si la longueur de la ligne est egale a zero, alors la fonction recommence
				System.out.println("Saisie incorrecte.\nVeuillez resaisir tout le graphe, (en commençant par le nombre de sommet)."); // Message d'erreur
				this.lireConsole();
			}

			nbSommet = Integer.parseInt(ligne); // Mise a jour du nombre de sommet

			ArrayList<Integer> v;
			int cpt = 0;
			int cpt2 = 1;

			for(int j = 0 ; j<nbSommet;j++){ // Boucle For pour parcourrir toutes les lignes, il y a autant de ligne que de sommet
				ligne = clavier.readLine(); // Lecture d'une ligne
				v = new ArrayList<Integer>();
				cpt = 0;
				ligneMax = ligne.length();
				String nombre = "";
				boolean MoinsUntrouve = false;

				if(ligneMax == 0){ // Si la ligne est vide
					System.out.println("Saisie incorrecte.\nVeuillez resaisir tout le graphe, (en commençant par le nombre de sommet) :");
					this.lireConsole();
				}

				else{
					int i = 0;
					while (!MoinsUntrouve){ // Tant qu'on ne se trouve pas a la fin de la ligne, c'est-a-dire tant qu'on ne trouve pas de '-1'
						if(!MoinsUntrouve && i != ligneMax){	
							if(ligne.charAt(0) == '-' && ligne.charAt(1) == '1'){
								MoinsUntrouve = true;
							}
							else{
								if(ligne.charAt(0) != ' '){
									nombre += ligne.charAt(0);
									if(i+1 != ligneMax){
										ligne = ligne.substring(1);
										if(ligne.charAt(0)==' '){
											if (Integer.parseInt(nombre) != -1){
												v.add(cpt, Integer.parseInt(nombre)); // Ajout du nombre a la liste v a l'indice cpt
												cpt++;
												nombre=""; // MAJ du nombre
											}					
										}
									}
									else{
										if(Integer.parseInt(nombre) != -1){
											v.add(cpt, Integer.parseInt(nombre)); // Ajout du nombre a la liste v a l'indice cpt
											cpt++;
											nombre=""; // MAJ du nombre
										}
									}
								}

								else { // Suppression de l'espace
									ligne = ligne.substring(1);
								}
							}
						}
						else{ 
							if(i == ligneMax /*&& !MoinsUntrouve*/) { // Si bout de ligne mais pas de '-1' trouve
								System.out.println("Il faut saisir '-1' à la fin de chaque ligne.\nVeuillez resaisir tout le graphe, (en commençant par le nombre de sommet).");
								this.lireConsole();
								//break; 
							}
						} 
						i++;

					} // Fin de la boucle while, '-1' trouve => fin de ligne Ok 
					
					hm.put(cpt2, v); // MAJ de la HashMap : Ajout de la liste v a l'indice cpt2
					cpt2++;
				} // Fin du else 
			} // Fin du for, Saisie a la console terminee
			graphe = hm; // MAJ du graphe
		} // Fin du try

		catch (Exception e){ // Gestion des exceptions
			System.out.println(e.toString());
		}
	}


	// Fonction permettant de saisir un graphe, syntaxiquement correcte, a l'aide d'un fichier passe en parametre
	public void lireFichier(BufferedReader nomFichier){
		// Declaration d'une HashMap
		HashMap<Integer, ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();

		//lecture
		try{
			BufferedReader br = new BufferedReader(nomFichier);
			String ligne = br.readLine(); // premiere ligne : nombre de sommets du graphe	
			int ligneMax = ligne.length(); // Variable pour la longueur de la ligne

			if(ligneMax == 0){ // Si la longueur de la ligne est egale a zero, alors le programme se ferme
				System.out.println("########");
				System.out.println("Erreur a la ligne 1");
				System.out.println("Saisie incorrecte.\nIl faut commencer par le nombre de sommets.");System.out.println("Veuillez relancer le programme.");
				System.out.println("########");
				System.exit(0);
			}

			nbSommet = Integer.parseInt(ligne); // Mise a jour du nombre de sommet

			ArrayList<Integer> v;
			int cpt = 0;
			int cpt2 = 1;

			int j = 0;

			while ((ligne = br.readLine()) != null){ // Boucle pour parcourrir toutes les lignes
				v = new ArrayList<Integer>();
				cpt = 0;
				ligneMax = ligne.length();
				String nombre = "";
				boolean MoinsUnTrouve = false;

				if(ligneMax == 0){ // Si la ligne est vide
					System.out.println("########");
					System.out.println("Erreur a la ligne " + (j+2));
					if((j+1) == 1) 
						System.out.println("Saisie incorrecte.\nIl faut rentrer les voisins du premier sommet.");
					else
						System.out.println("Saisie incorrecte.\nIl faut rentrer les voisins du " + (j+1) + "ieme sommet.");
					System.out.println("Veuillez relancer le programme.");
					System.out.println("########");
					System.exit(0);
				}

				else{
					int i = 0;
					while (!MoinsUnTrouve){ // Tant qu'on ne se trouve pas a la fin de la ligne, c'est-a-dire tant qu'on ne trouve pas de '-1'
						if(!MoinsUnTrouve && i != ligneMax){	
							if(ligne.charAt(0) == '-' && ligne.charAt(1) == '1'){
								MoinsUnTrouve = true;
							}
							else{
								if(ligne.charAt(0) != ' '){
									nombre += ligne.charAt(0);
									if(i+1 != ligneMax){
										ligne = ligne.substring(1);
										if(ligne.charAt(0)==' '){
											if (Integer.parseInt(nombre) != -1){
												v.add(cpt, Integer.parseInt(nombre)); // Ajout du nombre a la liste v a l'indice cpt
												cpt++;
												nombre=""; // MAJ du nombre
												//affiche(v);
											}			 
										}
									}
									else{
										if(Integer.parseInt(nombre) != -1){
											v.add(cpt, Integer.parseInt(nombre)); // Ajout du nombre a la liste v a l'indice cpt
											cpt++;
											nombre=""; // MAJ du nombre
										}
									}
								}
								else { // Suppression de l'espace
									ligne = ligne.substring(1);
								}
							}

						}
						else{ 
							if(i == ligneMax /*&& !MoinsUnTrouve*/) { // Arriver au bout de la ligne sans -1
								System.out.println("########");
								System.out.println("Erreur a la ligne " + (j+2) + ", colonne " + (i+1));
								System.out.println("Il faut saisir '-1' à la fin de chaque ligne dans votre fichier (excepter la premiere ligne, nombre de sommet).");
								System.out.println("Veuillez relancer le programme.");
								System.out.println("########");
								System.exit(0); 
							}
						}
						i++;
					} // Fin de la boucle while, '-1' trouve => fin de ligne Ok

					hm.put(cpt2, v); // MAJ de la HashMap : Ajout de la liste v a l'indice cpt2
					cpt2++;
				} // Fin du else 
				j++;
			} // Fin du for, a la fin du fichier
			br.close(); 
			graphe = hm; // MAJ du graphe
			System.out.println("Le fichier a été lu avec succes.");
		} // Fin du try
		catch (Exception e){ // Gestion des exceptions
			System.out.println(e.toString());
		}
	}

	// Fonction pour afficher une liste d'entier
	public static void affiche(ArrayList<Integer> v){
		if(!v.isEmpty()){
			for (int i = 0; i <= v.size()-1; i++){
				System.out.print(v.get(i));
				if(i != v.size()-1)
					System.out.print(" | "); 
			}
		}
		System.out.println();
	}

	// Fonction pour afficher un graphe
	public void affiche(){
		for (int i = 1; i < graphe.size()+1 ;i++){
			System.out.print(Integer.toString(i) + " : ");
			affiche(graphe.get(i));
		}
	}

	// Fonction retournant les sommets incidents dans le graphe courant du sommet passe en parametre
	public ArrayList<Integer> getSommetsIncidents(int sommet){
		return graphe.get(sommet);
	}

	// Fonction enlevant l'arete a dans le graphe courant
	public void enleverArete(Arete a){
		Object i = a.getS1();
		Object j = a.getS2();

		graphe.get(i).remove(j);
		graphe.get(j).remove(i);
	}

	// Fonction ajoutant une arete, au graphe courant
	public void ajouterArete(int i, int j){
		graphe.get(i).add(j);
		graphe.get(j).add(i);
	}

	// Test si le graphe qui a ete saisi est correcte.
	public boolean testGraphe() {
		/*for (int i = 1; i < graphe.size()+1 ;i++){
			ArrayList l = graphe.get(i);
			if(!l.isEmpty()){
				for (int j = 0; i <= l.size(); i++){
					System.out.println("l.get(j) = " + l.get(j) + " et graphe.get(i).iterator().next() = " + graphe.get(i).iterator().next());
					for (int k = 0; k <= l.size(); k++)
						if()
						return false;
				}
			}
		}*/
		return true;
	}

	// Fonction permettant de determiner le sommet de depart pour chercher le cycle eulerien
	public int sommetDeDepart(){
		System.out.print("***************\nDe quel sommet voulez-vous partir pour trouver un cycle eulérien : ");

		// Lecture du sommet de départ
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String tmp;
		try {
			tmp = br.readLine();
			int n = Integer.parseInt(tmp); 
			System.out.println("***************\n");		
			//System.out.println("Sommet du debut : " + n);
			//System.out.println();
			return n;
		}
		catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
