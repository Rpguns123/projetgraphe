import java.util.ArrayList;
import java.util.Iterator;

public class Fleury {

	/* Une fonction isthme qui prend un graphe, et deux points (un segment), en paramatre 
	 * Retourne vrai si le segment est un isthme
	 */
	public static boolean isthme (Arete a, Graphe g){
		Graphe f = g;
		int i = a.getS1();
		int j = a.getS2();

		return !existeChemin(f, i, j, new ArrayList<Integer>(), i);
	}

	/*
	 * Fonction qui test dans le graphe si il existe un chemin de i à j
	 * Graphe f : graphe dans lequel chercher le chemin
	 * int i,j : point de départ et d'arrivé du chemin recherché
	 * ArrayList ch : liste contenant la liste des chemins deja parcouru
	 * int d : sommet en cours dans le parcours du graphe
	 */
	static boolean existeChemin(Graphe f, int i, int j, ArrayList<Integer> ch, int d) {
		int tmp = -1; 
		ch.add(d);
		// Si ch ne contient qu'un élément, il faut eviter le chemin ij qui est l'arete ij. Similaire au fait de supprimer l'arrete ij du graphe f
		if(ch.size()==1){
			tmp = chercherSommetSansB(f.getSommetsIncidents(d),ch,j); //on cherche un sommet voisin en evitant le sommet j
			if(tmp == -1) return false; //Si aucun sommet n'est trouver, le chemin n'existe pas
			else return existeChemin(f,i,j,ch,tmp);//sinon on continue notre chemin a partir du sommet trouve
		}
		else{//Cas ou ch contient plus d'un élément
			tmp = chercherSommetAvecB(f.getSommetsIncidents(d),ch,j);//on cherche un sommet voisin de d avec en priorite le sommet j
			if(tmp == -1) return false;//Si aucun sommet n'est trouver, le chemin n'existe pas
			else if(tmp == j) return true;//Si le sommet trouver est j il existe un chemin entre i et j et on retourne true
			else return existeChemin(f,i,j,ch,tmp); //sinon on continue la recherche
		}
	}

	/*
	 * Cherche un sommet de la liste rch qui ne se trouve pas dans dejaP et qui n'est pas le sommet b
	 * Renvoie -1 si aucun sommet n'est trouve
	 */
	static Integer chercherSommetSansB(ArrayList<Integer> rch,ArrayList<Integer> dejaP,Integer b){
		boolean parcouru = true;

		Iterator<Integer> it = rch.iterator();
		while (it.hasNext()){//Parcours de la liste rch
			Integer i = it.next();
			Iterator<Integer> it2 = dejaP.iterator();
			parcouru = true;
			while(it2.hasNext()){//Parcours de la liste dejaP
				Integer j = it2.next();
				if(i == j)//Si le sommet se trouve dans la liste on va changer de sommet
					parcouru = false;
			}
			if(i != b && parcouru == true)//Si le sommet est pas dans la liste dejaP et n'est pas b on le renvoie
				return i;
			//Sinon on continue de chercher dans rch
		}
		//Si aucun sommet n'est trouver, on retourne -1
		return -1;
	}

	/*
	 * Cherche un sommet de la liste rch qui ne se trouve pas dans dejaP et qui est de preference le sommet b
	 * Renvoie -1 si aucun sommet n'est trouve
	 */
	static Integer chercherSommetAvecB(ArrayList<Integer> rch,ArrayList<Integer> dejaP,Integer b){
		boolean parcouru = true;
		Integer tmp = -1;
		Iterator<Integer> it = rch.iterator();
		while (it.hasNext()){//Parcours de la liste rch
			Integer i = it.next();
			if( i == b)//Si le sommet trouve est le sommet rechercher on renvoie i
				return i;
			Iterator<Integer> it2 = dejaP.iterator();
			parcouru = true;
			while(it2.hasNext()){//Parcours de la liste dejaP
				Integer j = it2.next();
				if(i == j)//Si le sommet se trouve dans la liste on va changer de sommet
					parcouru = false;
			}
			if(parcouru == true)//Si le sommet n'est pas dans la liste dejaP on renvoie ce sommet
				tmp=i;
			//Sinon on continue de chercher dans rch
		}
		// On retourne le sommet trouve ou -1 sinon
		return tmp;
	}


	/*
	 * Algorithme de Fleury qui renvoie le cycle eulerien du graphe g a partir du sommet sommet
	 */
	public static String algoFleury(Graphe g, int sommet){

		String cycle = Integer.toString(sommet) + ' ';
		int x = sommet;
		Graphe f = g;

		while(!f.getSommetsIncidents(x).isEmpty()){
			Arete e = new Arete(x, f.getSommetsIncidents(x).get(0));
			if(isthme(e, f)){
				for(Integer v : f.getSommetsIncidents(x)){
					e = new Arete (x,v);
					if(!isthme(e, f)){
						break;
					}
					else{
					}
				}
			}
			x = e.getS2();
			cycle += Integer.toString(x) + ' ';
			f.enleverArete(e);
		}
		if(x == sommet)
			return cycle;
		else
			return "Aucun pour ce graphe !";
	}

}
