import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;

public class Graphe {

	HashMap<Integer, ArrayList<Integer>> graphe;
	int nbSommet;

	public Graphe(){ }

	public Graphe(HashMap<Integer, ArrayList<Integer>> g, int n){
		graphe = g ;
		nbSommet = n;
	}

	public Graphe clone(){
		return new Graphe((HashMap<Integer, ArrayList<Integer>>)graphe.clone(), nbSommet);
	}

	public void lireConsole(){

		HashMap<Integer, ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();

		//lecture du fichier texte	
		try{
			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));

			String ligne = clavier.readLine(); // premiere ligne : nombre de sommets du graphe
			nbSommet = Integer.parseInt(ligne);

			ArrayList<Integer> v = new ArrayList<Integer>();
			int cpt = 0;
			int cpt2 = 1;

			for(int j = 0 ; j<nbSommet;j++){
				ligne = clavier.readLine();
				v = new ArrayList<Integer>();
				cpt = 0;
				int ligneMax = ligne.length();
				String nombre = "";
				for (int i = 0; i < ligneMax ; i++){

					if(ligne.charAt(0) != ' '){
						nombre += ligne.charAt(0);
						if(i+1 != ligneMax){
							ligne = ligne.substring(1);
							if(ligne.charAt(0)==' '){
								if (Integer.parseInt(nombre) != -1){
									v.add(cpt, Integer.parseInt(nombre));
									cpt++;
									nombre="";
								}					
							}
						}
						else{
							if(Integer.parseInt(nombre) != -1){
								v.add(cpt, Integer.parseInt(nombre));
								cpt++;
								nombre="";
							}
						}
					}
					else {

						ligne = ligne.substring(1);
					}

				} // Fin de la boucle for
				hm.put(cpt2, v);
				cpt2++;
			}
			graphe = hm;
		}

		catch (Exception e){
			System.out.println(e.toString());
		}
	}

	public void lireFichier(BufferedReader nomFichier){
		HashMap<Integer, ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();

		//lecture du fichier texte	
		try{
			BufferedReader br = new BufferedReader(nomFichier);

			String ligne = br.readLine();
			nbSommet = Integer.parseInt(ligne);

			ArrayList<Integer> v = new ArrayList<Integer>();
			int cpt = 0;
			int cpt2 = 1;

			while ((ligne = br.readLine()) != null){
				v = new ArrayList<Integer>();
				cpt = 0;
				int ligneMax = ligne.length();
				String nombre = "";
				for (int i = 0; i < ligneMax ; i++){

					if(ligne.charAt(0) != ' '){
						nombre += ligne.charAt(0);
						if(i+1 != ligneMax){
							ligne = ligne.substring(1);
							if(ligne.charAt(0)==' '){
								if (Integer.parseInt(nombre) != -1){
									v.add(cpt, Integer.parseInt(nombre));
									cpt++;
									nombre="";
								}					
							}
						}
						else{
							if(Integer.parseInt(nombre) != -1){
								v.add(cpt, Integer.parseInt(nombre));
								cpt++;
								nombre="";
							}
						}
					}
					else {

						ligne = ligne.substring(1);
					}

				} // Fin du for
				hm.put(cpt2, v);
				cpt2++;
			}
			br.close(); 
			graphe = hm;
		}

		catch (Exception e){
			System.out.println(e.toString());
		}
	}

	public static void affiche(ArrayList<Integer> v){
		if(!v.isEmpty()){
			for (int i = 0; i <= v.size()-1; i++){
				System.out.print(v.get(i));
				if(i != v.size())
					System.out.print(" | "); 
			}
		}
		System.out.println();
	}

	public void affiche(){
		for (int i = 1; i < graphe.size()+1 ;i++){
			System.out.print(Integer.toString(i) + " : ");
			affiche(graphe.get(i));
		}
	}

	public ArrayList<Integer> getSommetsIncidents(int sommet){
		return graphe.get(sommet);
	}

	public void enleverArete(Arete a){
		Object i = a.getS1();
		Object j = a.getS2();

		boolean ok;
		graphe.get(i).remove(j);
		graphe.get(j).remove(i);
	}

	public void ajouterArete(int i, int j){
		graphe.get(i).add(j);
		graphe.get(j).add(i);
	}
}
