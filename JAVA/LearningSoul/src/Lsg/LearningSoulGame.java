package Lsg;

import Lsg.characters.*;
import Lsg.helpers.*;
import Lsg.weapons.*;

public class LearningSoulGame {

	public static void main (String args[]) {
		
		Hero hero = new Hero("xXx_DaRk_SaSuKe_xXx");
		Monster monstre1 = new Monster("Souris demoniaque du néant profond");
		Monster monstre2 = new Monster("Souris demoniaque du néant profond");
		Dice dé = new Dice(20);
		Sword arme = new Sword();
		
		hero.printStats();
		monstre1.printStats();
		monstre2.printStats();
		arme.printStats();
	}
}
