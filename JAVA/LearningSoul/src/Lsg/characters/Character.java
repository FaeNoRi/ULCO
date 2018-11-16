package Lsg.characters;

import Lsg.helpers.*;
import Lsg.weapons.*;
import Lsg.buffs.rings.*;
import Lsg.buffs.talismans.*;
import Lsg.consumables.*;
import Lsg.consumables.drinks.*;
import Lsg.consumables.food.*;
import Lsg.consumables.repair.RepairKit;

public abstract class Character {

	private String name;
	private int life, maxLife, stamina, maxStamina;
	private boolean vie;
	private Weapon arme;
	private Consumable consumable;

	Dice dé = new Dice(101);
	
	protected static int INSTANCES_C_COUNT = 0;
	public static String LIFE_STATS_STRING = "Life";
	public static String STAM_STATS_STRING = "Stamina";
	public static String PROTEC_STATS_STRING = "Protection";
	public static String BUFF_STATS_STRING = "Buffs";
	
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
	
	public Consumable getConsumable() {
		return consumable;
	}

	public void setConsumable(Consumable consumable) {
		this.consumable = consumable;
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
		int Sreduc, stam = this.getStamina(), wstam = weapon.getStamCost();;
		int damages=0, minD = weapon.getMinDamage(), maxD = weapon.getMaxDamage(), prec = dé.getRandom();
		
		if(weapon.getDurability()==0 || stam == 0) {
			damages=0;
		}else {
			dé.roll();
			if(prec >= 91 ) {
				System.out.println("!!! Coup Critique ! Precision de "+prec+" !!!");
			}
			if(stam > wstam) {
				damages = minD + Math.round((maxD - minD) * prec/100); 
				this.setStamina(stam - wstam);
				damages = damages + Math.round(damages*(int) this.computeBuffValue()/100);
				weapon.use();
				System.out.println(this.getName()+" attaque avec "+ weapon.getName()+" --> "+damages+" Dégats !!!");
			}else {
				damages = minD + Math.round((maxD - minD) * prec/100);
				damages = damages + Math.round(damages*(int) this.computeBuffValue()/100);
				Sreduc = damages - Math.round((maxD*(wstam - stam))/100);
				this.setStamina(0);
				weapon.use();
				int difdamage= damages-Sreduc;
				System.out.println(this.getName()+" attaque avec "+ weapon.getName()+" ("+damages+") --> Fatigue ! Dégats Réduits de "+difdamage+" : "+Sreduc+" Dégats!!!");
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
		
		System.out.println("La protection de "+this.getName()+" réduit les dégats de "+reduc+" !!!");
		
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
		System.out.println("Stamina restante : "+this.getStamina()+" !!!\n");
		if(	this.getStamina() > this.getMaxStamina()){
			this.setStamina(this.getMaxStamina()); 
		}
	}
	
	private void eat(Food f) {
		System.out.println(this.getName()+" mange "+f.toString());
		int regen = f.use();

		this.setLife(this.getLife()+regen);
		System.out.println("Points de vie restants : "+this.getLife()+" !!!\n");
		if(	this.getLife() > this.getMaxLife()){
			this.setLife(this.getMaxLife()); 
		}
	}
	
	private void repairWeaponWith(RepairKit kit) {
		Weapon arme = this.getWeapon();
		System.out.println(this.getName()+" répare "+arme+" avec "+kit.toString());
		System.out.println("Durabilité restante : "+arme.getDurability()+" !!!\n");
		arme.repairWith(kit);
	}
	
	public void use(Consumable consumable) {
		if(consumable instanceof Drink) {
			this.drink( (Drink) consumable);
		}else if(consumable instanceof Food) {
			this.eat( (Food) consumable);
		}else if(consumable instanceof RepairKit) {
			this.repairWeaponWith( (RepairKit) consumable);
		}
	}
	
	public void consume() {
		Consumable cons = this.getConsumable();
		use(cons);
	}
	
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
