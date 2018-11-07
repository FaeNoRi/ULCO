package Lsg;

import Lsg.armors.*;
import Lsg.characters.*;
import Lsg.helpers.*;
import Lsg.weapons.*;
import Lsg.buffs.rings.*;
import Lsg.buffs.talismans.*;
import java.util.*;

public class LearningSoulGame {

	Hero hero;
	Monster monster;
	
	Scanner sc = new Scanner(System.in);
	
	public static void main (String args[]) {
		LearningSoulGame game = new LearningSoulGame();
		game.play_v2();
	}
	
	public void refresh() {
		hero.heroPrintStats();
		monster.monsterPrintStats();
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
		hero = new Hero("Maid-chan", new Excalibur());
		
		//monster = new Monster("Tentacule",new SlapTentacle(),20);
		monster = new Lycanthrope();
	}
	
	public void play_v2() {
		
		this.init();
		
		hero.setArmorItem(new BlackWitchVeil(), 1);
		hero.setArmorItem(new RingedKnightArmor(), 2);
		hero.setArmorItem(new DragonSlayerLeggings(), 3);
		
		Ring ring = new DragonSlayerRing();
		Ring ring2 = new RingOfDeath();
		Talisman tali = new MoonStone();
		
		hero.setRing(ring , 1);
		hero.setRing(ring2 , 2);
		ring.setHero(hero);
		ring2.setHero(hero);
		
		monster.setTalisman(tali, 1);
		
		this.fight1v1();
		
	}
}
