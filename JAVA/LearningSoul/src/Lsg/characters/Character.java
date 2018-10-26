package Lsg.characters;

import Lsg.helpers.*;
import Lsg.weapons.*;

public abstract class Character {

	private String name;
	private int life;
	private int maxLife;
	private int stamina;
	private int maxStamina;
	private boolean vie;
	private Weapon arme;
	
	Dice dé = new Dice(101);
	
	protected static int INSTANCES_C_COUNT = 0;
	
	public Character() {
		INSTANCES_C_COUNT++;
		this.name = "Character_"+INSTANCES_C_COUNT;;
		this.life = 10;
		this.maxLife = 10;
		this.stamina = 10;
		this.maxStamina = 10; 
		this.arme = new Sword();
	}
	
	///////// ACCESSEURS  ////////////////////////////////////
	
	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected int getLife() {
		return life;
	}

	protected void setLife(int life) {
		this.life = life;
	}

	protected int getMaxLife() {
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
	
	protected Weapon getWeapon() {
		return arme;
	}
	
	protected void setWeapon(Weapon w) {
		this.arme = w;
	}
	///////// METHODES ////////////////////////////////////
	
	public boolean isAlive() {
		if(this.getLife() > 0){
			this.vie = true;
		}else {
			this.vie = false;
		}			
		return vie;
		
	}
	
	private int attackWith(Weapon weapon) {
		int Sreduc, damages=0;
		if(weapon.getDurability()==0 || this.getStamina()== 0) {
			damages=0;
		}else {
			dé.roll();
			if(dé.getRandom() >= 91 ) {
				System.out.println("!!! Coup Critique ! Precision de "+dé.getRandom()+" !!!");
			}
			if(this.getStamina()>weapon.getStamCost()) {
				damages = weapon.getMinDamage()+Math.round((weapon.getMaxDamage()-weapon.getMinDamage())*dé.getRandom()/100); 
				this.setStamina(this.getStamina()-weapon.getStamCost());
				weapon.use();
				System.out.println("\n!!! "+this.getName()+" attaque avec "+ weapon.getName()+" --> "+damages+" Dégats !!!");
			}else {
				damages = weapon.getMinDamage()+Math.round((weapon.getMaxDamage()-weapon.getMinDamage())*dé.getRandom()/100);
				Sreduc = damages - Math.round((weapon.getMaxDamage()*(weapon.getStamCost()-this.getStamina()))/100);
				this.setStamina(0);
				weapon.use();
				int difdamage= damages-Sreduc;
				System.out.println("\n!!! "+this.getName()+" attaque avec "+ weapon.getName()+" ("+damages+") --> Fatigue ! Dégats Réduits de "+difdamage+" : "+Sreduc+" Dégats!!!");
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
		
		System.out.println("La protection de "+this.getName()+" réduit les dégats de "+reduc+" !!!\n");
		
		int vieR = this.getLife()-nvalue;
		rest = (vieR < 0)  ? 0 : vieR;
		this.setLife(rest);
		
		boolean survie = this.isAlive();
		if(survie == false) {
			System.out.println("\n!!! "+this.getName()+" est mort(e) !!!\n");
		}
		
		return rest;
	}
	
	public abstract float computeProtection();
	
	///////// AFFICHAGE ////////////////////////////////////
	public String toString() {
		this.isAlive();
		
		String stype = getClass().getSimpleName();
		String sname = this.getName();
		int slife = this.getLife();
		int sstam = this.getStamina();
		float sprotec = this.computeProtection();

		String sVie;
		
		if(this.vie==true) {
			sVie = "ALIVE";
		}else{
			sVie = "DEAD";
		}
		
		String str = String.format("[%-10s] %-40s LIFE : %-10s STAMINA : %-10s PROTECTION : %-10s (%s)\n",stype,sname,slife,sstam,sprotec,sVie);
		return str;
	}
	
	public void printStats() {
		System.out.println("/////////////////////////////////////////////////////// "+this.getClass().getSimpleName()+" ////////////////////////////////////////////////////////////");
		System.out.println(this.toString());
	}
}
