package Lsg.characters;

import Lsg.helpers.*;
import Lsg.weapons.*;

public class Character {

	private String name;
	private int life;
	private int maxLife;
	private int stamina;
	private int maxStamina;
	private boolean vie;
	
	Dice dé = new Dice(101);
	Sword sword = new Sword();
	
	protected static int INSTANCES_C_COUNT = 0;
	
	public Character() {
		INSTANCES_C_COUNT++;
		this.name = "Character_"+INSTANCES_C_COUNT;;
		this.life = 10;
		this.maxLife = 10;
		this.stamina = 10;
		this.maxStamina = 10; 
	}
	
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

	protected int getStamina() {
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
	
	///////// METHODES ////////////////////////////////////
	
	public boolean isAlive() {
		if(this.life>0){
			this.vie = true;
		}else {
			this.vie = false;
		}			
		return vie;
		
	}
	
	public int attackWith(Weapon weapon) {
		int damages=0;
		if(weapon.getDurability()==0 || this.getStamina()== 0) {
			damages=0;
		}else {
			dé.roll();
			if(this.getStamina()>weapon.getStamCost()) {
				damages = weapon.getMinDamage()+Math.round(weapon.getMaxDamage()-weapon.getMinDamage()*dé.getRandom()/weapon.getMaxDamage()); 
				this.setStamina(this.getStamina()-weapon.getStamCost());
			}else {
				damages = weapon.getMinDamage()+Math.round(weapon.getMaxDamage()-weapon.getMinDamage()*dé.getRandom()/weapon.getMaxDamage()) - Math.round(weapon.getMaxDamage()*(weapon.getStamCost()-this.getStamina())/weapon.getMaxDamage());
				this.setStamina(0);
			}
		}
		return damages;
	}
	
	///////// AFFICHAGE ////////////////////////////////////
	public String toString() {
		this.isAlive();
		
		String stype = getClass().getSimpleName();
		String sname = this.getName();
		int slife = this.getLife();
		int sstam = this.getStamina();
		String sVie;
		
		if(this.vie==true) {
			sVie = "ALIVE";
		}else{
			sVie = "DEAD";
		}
		
		String str = String.format("[%-10s] %-40s LIFE : %-10s STAMINA : %-10s (%s)",stype,sname,slife,sstam,sVie);
		return str;
	}
	
	public void printStats() {
		System.out.println(this.toString());
	}
	
	public void attack() {
		System.out.println();
	}
}
