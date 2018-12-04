package Lsg;

import Lsg.armors.*;
import Lsg.exceptions.*;
import Lsg.bags.*;
import Lsg.characters.*;
import Lsg.characters.Character;
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
	
	Scanner scanner = new Scanner(System.in);
	
	public static void main (String args[]) {
		LearningSoulGame game = new LearningSoulGame();
		game.init();
		//game.testExceptions();
		game.fight1v1();
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
		
		Character agressor = hero ;
		Character target = monster ;
		int action ; 
		int attack, hit ;
		Character tmp ;

		while(hero.isAlive() && monster.isAlive()){ 

			action = 1 ; 
			System.out.println();

			if(agressor == hero) {
				do {
					System.out.println("Appuyer sur 1 pour attaquer ou sur 2 pour consommer un objet > ");
					action = scanner.nextInt();
				}
				while(action < 1 || action > 2) ;
				System.out.println();
			}

			if(action == 2){
				try {
					hero.consume();
				} catch (ConsumeNullException e) {
					System.out.println("Action Impossible : aucun consommable n'est équipé !");
				} catch (ConsumeEmptyException e) {
					System.out.println("Action Impossible : " + e.getConsumable().getName() + " est vide !");
				} catch (ConsumeRepairNullWeaponException e) {
					System.out.println("Action Impossible : aucune arme n'est équipée !");
				}
				System.out.println();
			}else{
				try {
					attack = agressor.attack() ;
				} catch (WeaponNullException e) {
					System.out.println("Attention :  aucune arme n'est équipée !!!\n");
					attack = 0 ;
				} catch (WeaponBrokenException e) {
					System.out.println("Attention : " + e.getMessage() + "\n");
					attack = 0 ;
				} catch (StaminaEmptyException e) {
					System.out.println("Action Impossible : plus de stamina !!!\n");
					attack = 0 ;
				}
				hit = target.getHitWith(attack);
			}

			refresh();
			
			tmp = agressor ;
			agressor = target ;
			target = tmp ;
			
		}
		
		Character winner = (hero.isAlive()) ? hero : monster ;
	}
	
	public void init(){
		hero = new Hero("Maid-chan");
		
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
		System.out.println("Create exhausted hero : ");
		hero = new Hero() ;

		// pour vider la vie
		hero.getHitWith(99) ;

		// pour depenser la stam
		hero.setWeapon(new Weapon("Grosse Arme", 0, 0, 1000, 100));
		try {
			hero.attack() ;
		} catch (WeaponNullException e) {
			e.printStackTrace();
		} catch (WeaponBrokenException e) {
			e.printStackTrace();
		} catch (StaminaEmptyException e) {
			e.printStackTrace();
		}

		System.out.println(hero);

	}
}





















