package Lsg.characters;

import Lsg.helpers.*;
import Lsg.weapons.*;
import Lsg.buffs.rings.*;
import Lsg.buffs.talismans.*;
import Lsg.consumables.*;
import Lsg.consumables.drinks.*;
import Lsg.consumables.food.*;

public abstract class Character {

	private String name;
	private int life, maxLife, stamina, maxStamina;
	private boolean vie;
	private Weapon arme;
	
	Dice d� = new Dice(101);
	
	protected static int INSTANCES_C_COUNT = 0;
	public String LIFE_STATS_STRING = "Life";
	public String STAM_STATS_STRING = "Stamina";
	public String PROTEC_STATS_STRING = "Protection";
	public String BUFF_STATS_STRING = "Buffs";
	
	public Character() {
		INSTANCES_C_COUNT++;
		this.name = "Character_"+INSTANCES_C_COUNT;;
		this.life = 100;
		this.maxLife = 100;
		this.stamina = 100;
		this.maxStamina = 100; 
		this.arme = new Sword();
	}
	
	///////// ACCESSEURS  ////////////////////////////////////
	
	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public int getLife() {
		return life;
	}

	protected void setLife(int life) {
		this.life = life;
	}

	public int getMaxLife() {
		return maxLife;
	}

	protected void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	public int getStamina() {
		return stamina;
	}

	protected void setStamina(int stamina) {
		this.stamina = stamina;
	}

	protected int getMaxStamina() {
		return maxStamina;
	}

	protected void setMaxStamina(int maxStamina) {
		this.maxStamina = maxStamina;
	}
	
	public Weapon getWeapon() {
		return arme;
	}
	
	public void setWeapon(Weapon w) {
		this.arme = w;
	}
	///////// METHODES ////////////////////////////////////
	
	///////////////////////// COMBAT ////////////////////////////////////////////////////////////////
	public boolean isAlive() {
		if(this.getLife() > 0){
			this.vie = true;
		}else {
			this.vie = false;
		}			
		return vie;
		
	}
	
	public int attackWith(Weapon weapon) {
		int Sreduc, damages=0;
		if(weapon.getDurability()==0 || this.getStamina()== 0) {
			damages=0;
		}else {
			d�.roll();
			if(d�.getRandom() >= 91 ) {
				System.out.println("!!! Coup Critique ! Precision de "+d�.getRandom()+" !!!");
			}
			if(this.getStamina()>weapon.getStamCost()) {
				damages = weapon.getMinDamage()+Math.round((weapon.getMaxDamage()-weapon.getMinDamage())*d�.getRandom()/100); 
				this.setStamina(this.getStamina()-weapon.getStamCost());
				damages = damages + Math.round(damages*(int) this.computeBuffValue()/100);
				weapon.use();
				System.out.println(this.getName()+" attaque avec "+ weapon.getName()+" --> "+damages+" D�gats !!!");
			}else {
				damages = weapon.getMinDamage()+Math.round((weapon.getMaxDamage()-weapon.getMinDamage())*d�.getRandom()/100);
				damages = damages + Math.round(damages*(int) this.computeBuffValue()/100);
				Sreduc = damages - Math.round((weapon.getMaxDamage()*(weapon.getStamCost()-this.getStamina()))/100);
				this.setStamina(0);
				weapon.use();
				int difdamage= damages-Sreduc;
				System.out.println(this.getName()+" attaque avec "+ weapon.getName()+" ("+damages+") --> Fatigue ! D�gats R�duits de "+difdamage+" : "+Sreduc+" D�gats!!!");
			}
		}
		return damages;
	}
	
	public int attack() {
		return this.attackWith(arme);
	}
	
	public int getHitWith(int value) {
		int rest, nvalue, reduc, protec = (int) this.computeProtection();
		if(protec >= 100) {
			nvalue = 0; 
			reduc = value;
		}else {
			reduc = Math.round((value*protec)/100);
			nvalue = value - reduc;
		}
		
		System.out.println("La protection de "+this.getName()+" r�duit les d�gats de "+reduc+" !!!");
		
		int vieR = this.getLife()-nvalue;
		rest = (vieR < 0)  ? 0 : vieR;
		this.setLife(rest);
		
		System.out.println("Points de vie restants : "+rest+" !!!\n");
		
		boolean survie = this.isAlive();
		if(survie == false) {
			System.out.println("!!! "+this.getName()+" est mort(e) !!!\n");
		}
		
		return rest;
	}
	
	public abstract float computeProtection();

	public abstract float computeBuffValue();
	
	///////////////////////// OBJET ////////////////////////////////////////////////////////////////
	
	private void drink(Drink d) {
		System.out.println(this.getName()+" boit "+d.toString());
		int regen = d.use();
		
		this.setStamina(this.getStamina()+regen);
		if(	this.getStamina() > this.getMaxStamina()){
			this.setStamina(this.getMaxStamina()); 
		}
	}
	
	private void eat(Food f) {
		System.out.println(this.getName()+" mange "+f.toString());
		int regen = f.use();

		this.setLife(this.getLife()+regen);
		if(	this.getLife() > this.getMaxLife()){
			this.setLife(this.getMaxLife()); 
		}
	}
	
	public void use(Consumable consumable) {
		if(consumable instanceof Drink) {
			this.drink( (Drink) consumable);
		}else if(consumable instanceof Food) {
			this.eat( (Food) consumable);
		}
	}
//	
//	public static void main (String args[]) {
//		Hero hero = new Hero();
//		hero.setStamina(0);
//		hero.printStats();
//		
//		Coffee d = new Coffee();
//		hero.use(d);
//		hero.printStats();
//		
//	}
//	
	
	
	///////// AFFICHAGE ////////////////////////////////////
	public String toString() {
		this.isAlive();
		
		String stype = "[ "+getClass().getSimpleName()+" ]";
		String sname = this.getName();
		int slife = this.getLife();
		int sstam = this.getStamina();
		float sprotec = this.computeProtection();
		float sbuffs = this.computeBuffValue();

		String sVie;
		
		if(this.vie==true) {
			sVie = "ALIVE";
		}else{
			sVie = "DEAD";
		}
		
		String str = String.format("%-10s %-20s "+LIFE_STATS_STRING+" : %-10s "+STAM_STATS_STRING+" : %-10s "+PROTEC_STATS_STRING+" : %-10s "+BUFF_STATS_STRING+" : %-10s (%s)\n",stype,sname,slife,sstam,sprotec,sbuffs,sVie);
		return str;
	}

	public void printStats() {
		System.out.println("/////////////////////////////////////////////////////// "+this.getClass().getSimpleName()+" ////////////////////////////////////////////////////////////");
		System.out.println(this.toString());
	}
}
