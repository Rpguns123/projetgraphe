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

		//boolean res = !existeChemin(f, i, j, new ArrayList<Integer>(), i);
		//return res;
		return !existeChemin(f, i, j, new ArrayList<Integer>(), i);
	}

	static boolean existeChemin(Graphe f, int i, int j, ArrayList<Integer> ch, int d) {
		int tmp = -1; 
		ch.add(d);
		if(ch.size()==1){
			tmp = chercherSommetSansB(f.getSommetsIncidents(d),ch,j);
			if(tmp == -1) return false;
			else return existeChemin(f,i,j,ch,tmp);
		}
		else{
			tmp = chercherSommetAvecB(f.getSommetsIncidents(d),ch,j);
			if(tmp == -1) return false;
			else if(tmp == j) return true;
			else return existeChemin(f,i,j,ch,tmp); 
		}
	}

	static Integer chercherSommetSansB(ArrayList<Integer> rch,ArrayList<Integer> dejaP,Integer b){
		boolean parcouru = true;

		Iterator<Integer> it = rch.iterator();
		while (it.hasNext()){
			Integer i = it.next();
			Iterator<Integer> it2 = dejaP.iterator();
			parcouru = true;
			while(it2.hasNext()){
				Integer j = it2.next();
				if(i == j)
					parcouru = false;
			}
			if(i != b && parcouru == true)
				return i;
		}
		return -1;
	}

	static Integer chercherSommetAvecB(ArrayList<Integer> rch,ArrayList<Integer> dejaP,Integer b){
		boolean parcouru = true;
		Integer tmp = -1;
		Iterator<Integer> it = rch.iterator();
		while (it.hasNext()){
			Integer i = it.next();
			if( i == b)
				return i;
			Iterator<Integer> it2 = dejaP.iterator();
			parcouru = true;
			while(it2.hasNext()){
				Integer j = it2.next();
				if(i == j)
					parcouru = false;
			}
			if(parcouru == true)
				tmp=i;
		}
		return tmp;
	}



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
