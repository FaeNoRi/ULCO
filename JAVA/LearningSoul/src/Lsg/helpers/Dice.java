package Lsg.helpers;

public class Dice {
	
	private int faces;
	private int random;
	
	public Dice() {
		this.faces = 6;
		this.random= roll();
	}
	
	public Dice(int f) {
		this.faces = f;
		this.random= roll();
	}
	
	public int roll() {
		this.random = (int) Math.floor(Math.random() * faces);
		return random;
	}

	public int getFaces() {
		return faces;
	}

	public void setFaces(int f) {
		this.faces = f;
	}

	public int getRandom() {
		return random;
	}

	public void setRandom(int rand) {
		this.random = rand;
	}

	public String toString() {
		
		String stype = getClass().getSimpleName();
		int sfaces = this.getFaces();
		int srand = this.getRandom();
		
		String str = String.format("[%-10s] FACES : %-10s RANDOM : %-10s",stype,sfaces,srand);
		return str;
	}
	
	public void printStats() {
		System.out.println(this.toString());
	}

}
