
public class Arete {
	
	int s1, s2; // Deux sommets
	
	public Arete(int x, int y){
		s1 = x;
		s2 = y;
	}
	
	public Arete(){ // Constructeur sans parametres
		s1 = 0;
		s2 = 0;
	}
	
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
