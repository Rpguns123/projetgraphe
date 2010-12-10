
public class Arete {
	
	private int s1, s2; // Deux sommets
	
	// Constructeur avec parametres
	public Arete(int x, int y){
		s1 = x;
		s2 = y;
	}
	
	// Constructeur sans parametres
	public Arete(){ 
		s1 = 0;
		s2 = 0;
	}
	
	// Fonction d'egalite
	public boolean isEqual(Arete j){
		if((s1 == j.s1 && s2 == j.s2)||(s1 == j.s2 && s2 == j.s1))
			return true;
		else
			return false;
		
	}
	
	// Affichage des de sommets, donc de l'arete
	public void afficher(){
		System.out.println("s1 : "+ Integer.toString(s1) + " & s2 : "+Integer.toString(s2));
	}

	// Setters & Getters des deux sommets, S1 et S2
	public void setS1(int x){
		this.s1 = x;
	}	
	
	public int getS1(){
		return this.s1;
	}
	
	public void setS2(int y){
		this.s2 = y;
	}
	
	public int getS2(){
		return this.s2;
	}

}
