package Lsg;

import Lsg.armors.*;
import Lsg.bags.*;
import Lsg.characters.*;
import Lsg.helpers.*;
import Lsg.weapons.*;
import Lsg.consumables.*;
import Lsg.consumables.food.*;
import Lsg.consumables.drinks.*;
import Lsg.consumables.repair.RepairKit;
import Lsg.buffs.rings.*;
import Lsg.buffs.talismans.*;
import java.util.*;

public class LearningSoulGame {

	Hero hero;
	Monster monster;
	Consumable coffee;
	
	public static String BULLET_POINT = "\u2219";
	
	Scanner sc = new Scanner(System.in);
	
	public static void main (String args[]) {
		LearningSoulGame game = new LearningSoulGame();
		//game.init();
		game.testExceptions();
	}
	
	public void testExceptions(){
		hero.setWeapon(null);
		fight1v1();
	}
	
	public void refresh() {
		hero.heroPrintStats();
		Weapon weaponH = hero.getWeapon();
		weaponH.weaponprintStats();
		hero.printArmor();
		hero.printRing();
		hero.printConsummable();
		
		monster.monsterPrintStats();
		Weapon weaponM = monster.getWeapon();
		weaponM.weaponprintStats();
		
	}
	
	
	public void fight1v1() {
		this.refresh();
		
		while(true) {
			
			System.out.println("Appuyer sur 1 pour attaquer ou sur 2 pour consommer un objet > ");
			int read = sc.nextInt();
			
			/// Tour du hero
			if(read==1){
				monster.getHitWith(hero.attack());
				if(monster.isAlive() == false) {break;}
			
			}else if(read==2){
				hero.consume();	
			}
			/// Tour du monster
			hero.getHitWith(monster.attack());
			if(hero.isAlive() == false) {break;}
			
			this.refresh();
		}
	}
	
	public void init(){
		hero = new Hero("Maid-chan", new Excalibur());
		
		//monster = new Monster("Tentacule",new SlapTentacle(),20);
		monster = new Lycanthrope();
		
		/// Armor
		hero.setArmorItem(new BlackWitchVeil(), 1);
		hero.setArmorItem(new RingedKnightArmor(), 2);
		hero.setArmorItem(new DragonSlayerLeggings(), 3);
		
		///Rings&Talisman
		Ring ring = new DragonSlayerRing();
		Ring ring2 = new RingOfDeath();
		Talisman tali = new MoonStone();
		hero.setRing(ring , 1);
		hero.setRing(ring2 , 2);
		ring.setHero(hero);
		ring2.setHero(hero);
		monster.setTalisman(tali, 1);
		
		/// Consumable
		hero.setConsumable(new Hamburger());
		
		this.title();
		this.fight1v1();
	}
	
//	public void play_v2() {
//		
//	}

	public void title() {
		System.out.println("///////////////////////////////////////////////////");
		System.out.println("///           THE LEARNING SOULS GAME           ///");
		System.out.println("///////////////////////////////////////////////////\n");
	}
	
	public void createExhaustedHero() {
		hero = new Hero();
		Weapon GA = new Weapon("Grosse Arme",0,0,1000,100);
		hero.setWeapon(GA);
		hero.getHitWith(99);
		hero.attackWith(GA);
		hero.printStats();
	}
	
}





















