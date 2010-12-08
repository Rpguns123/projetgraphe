import java.util.ArrayList;

public class Fleury {

	// Une fonction isthme qui prend un graphe, et deux points (un segment), en paramatre 
	// Retourne vrai si le segment est un isthme
	public static boolean isthme (Arete a, Graphe g){
		Graphe f = g.clone();

		int i = a.getS1();
		int j = a.getS2();

		boolean res = !chercherChemin(f, i, j, new ArrayList<Integer>(), i);
		return res;
	}

	static boolean chercherChemin(Graphe f, int i, int j, ArrayList<Integer> ch, int d) {

		ch.add(d);
		if((ch.get(0)== i && ch.get(ch.size()-1)==j ||(ch.get(0)== j && ch.get(ch.size()-1)==i ))){
			return true;
		}

		for(int x = 0; x < f.getSommetsIncidents(d).size(); x++){
			if(ch.size()>1)
				if((ch.get(1)== j && ch.get(0)==i) || (ch.get(1)== i && ch.get(0)==j)){
				}
				else{
					if(ch.indexOf(f.getSommetsIncidents(d).get(x)) == -1){
						return chercherChemin(f, i, j, ch, f.getSommetsIncidents(d).get(x));
					}
				}
		}
		return false;
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
				}
			}
			x = e.getS2();
			cycle += Integer.toString(x) + ' ';
			f.enleverArete(e);
		}
		return cycle;
	}


}
