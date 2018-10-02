package Lsg;

import Lsg.characters.Hero;
import Lsg.characters.Monster;

public class LearningSoulGame {

	public static void main (String args[]) {
		
		Hero hero = new Hero();
		Monster monstre1 = new Monster("Souris demoniaque du néant profond");
		Monster monstre2 = new Monster("Souris demoniaque du néant profond");
		
		hero.printStats();
		monstre1.printStats();
		monstre2.printStats();
	}
}
