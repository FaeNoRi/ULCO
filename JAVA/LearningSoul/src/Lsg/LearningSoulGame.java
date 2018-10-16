package Lsg;

import Lsg.characters.*;
import Lsg.helpers.*;
import Lsg.weapons.*;
import java.util.*;

public class LearningSoulGame {

	Hero hero;
	Monster monster;
	
	Scanner sc = new Scanner(System.in);
	
	public static void main (String args[]) {
		LearningSoulGame game = new LearningSoulGame();
		game.play_v1();

	}
	
	public void refresh() {
		hero.printStats();
		monster.printStats();
	}
	
	public void fight1v1() {
		this.refresh();
		
		while(true) {
			
			System.out.println("Appuyer sur ENTER pour lancer le prochain tour > ");
			String read = sc.nextLine();
		
			monster.getHitWith(hero.attack());
			if(monster.isAlive() == false) {break;}
			
			hero.getHitWith(monster.attack());
			if(hero.isAlive() == false) {break;}
			
			this.refresh();
		}
	}
	
	public void init(){
		hero = new Hero("Maid-chan", new Shotgun());
		monster = new Monster("Tentacule",new SlapTentacle());
	}
	
	public void play_v1() {
		
		this.init();
		this.fight1v1();
		
	}
}
