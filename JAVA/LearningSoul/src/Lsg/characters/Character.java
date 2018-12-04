package Lsg.characters;

import Lsg.helpers.*;
import Lsg.weapons.*;
import Lsg.bags.*;
import Lsg.buffs.rings.*;
import Lsg.buffs.talismans.*;
import Lsg.consumables.*;
import Lsg.consumables.drinks.*;
import Lsg.consumables.food.*;
import Lsg.consumables.repair.RepairKit;
import Lsg.exceptions.*;

public abstract class Character {

	private String name;
	private int life, maxLife, stamina, maxStamina;
	private boolean vie;
	private Weapon weapon;
	private Consumable consumable;
	protected Bags bag ;

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
		this.weapon = new Sword();
	}
	
	///////// ACCESSEURS  ////////////////////////////////////
	
	public String getName() {
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
		return weapon;
	}
	
	public void setWeapon(Weapon w) {
		this.weapon = w;
	}
	
	public Consumable getConsumable() {
		return consumable;
	}

	public void setConsumable(Consumable consumable) {
		this.consumable = consumable;
	}

	///////////////////////// COMBAT ////////////////////////////////////////////////////////////////
	
	public boolean isAlive() {
		if(this.getLife() > 0){
			this.vie = true;
		}else {
			this.vie = false;
		}			
		return vie;
		
	}
	
	protected int attackWith(Weapon weapon) throws WeaponNullException, WeaponBrokenException, StaminaEmptyException {
		
		if(weapon == null) throw new WeaponNullException() ;
		if(weapon.isBroken()) throw new WeaponBrokenException(weapon) ;
		if(stamina == 0) throw new StaminaEmptyException() ;
		
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
	
	public int attack() throws WeaponNullException, WeaponBrokenException, StaminaEmptyException {
		return attackWith(this.getWeapon()) ;
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
	
	private void eat(Food food) throws ConsumeNullException, ConsumeEmptyException {
		if(food == null) throw new ConsumeNullException(food);
		System.out.println(getName() + " eats " + food);
		int newLife = getLife() + food.use() ;
		newLife = (newLife < maxLife) ? newLife : maxLife ;
		setLife(newLife);
	}

	private void drink(Drink drink) throws ConsumeNullException, ConsumeEmptyException {
		if(drink == null) throw new ConsumeNullException(drink);
		System.out.println(getName() + " drinks " + drink);
		int newStam = getStamina() + drink.use() ;
		newStam = (newStam < maxStamina) ? newStam : maxStamina ;
		setStamina(newStam);
	}

	private void repairWeaponWith(RepairKit kit) throws WeaponNullException, ConsumeNullException {
		if(weapon ==null) throw new WeaponNullException() ;
		System.out.println(getName() + " repairs " + weapon + " with " + kit);
		weapon.repairWith(kit);
	}

	public void use(Consumable consumable) throws ConsumeNullException, ConsumeEmptyException, ConsumeRepairNullWeaponException {
		if(consumable == null) throw new ConsumeNullException(consumable) ;
		if(consumable instanceof Drink){
			drink((Drink)consumable);
		}else if(consumable instanceof Food){
			eat((Food)consumable) ;
		}else if(consumable instanceof RepairKit){
			try {
				repairWeaponWith((RepairKit)consumable);
			} catch (WeaponNullException e) {
				throw new ConsumeRepairNullWeaponException(consumable) ;
			}
		}
	}
	
	public void consume() throws ConsumeNullException, ConsumeEmptyException, ConsumeRepairNullWeaponException {
		use(consumable);
	}
	
	private Consumable fastUseFirst(Class<? extends Consumable> type) throws ConsumeNullException, ConsumeEmptyException, ConsumeRepairNullWeaponException, BagNullException {
		if(type == null) return null ;
		Consumable found = null ;
		Collectible[] items = getBagItems() ;
		for(Collectible item: items){
			if(type.isInstance(item)){
				found = (Consumable)item ;
				break;
			}
		}
		use(found) ;
		if(found.getCapacity() == 0) pullOut(found) ;
		return found ;
	}
	
	public Drink fastDrink() throws ConsumeNullException, ConsumeEmptyException, BagNullException {
		System.out.println(name + " drinks FAST :");
		Drink drink = null ;
		try {
			drink = (Drink) fastUseFirst(Drink.class) ;
		} catch (ConsumeRepairNullWeaponException e) {
			e.printStackTrace();
		}
		return drink ;
	}

	public Food fastEat() throws ConsumeNullException, ConsumeEmptyException, BagNullException {
		System.out.println(name + " eats FAST :");
		Food food = null ;
		try {
			food = (Food) fastUseFirst(Food.class);
		} catch (ConsumeRepairNullWeaponException e) {
			e.printStackTrace();
		}
		return food ;
	}

	public RepairKit fastRepair() throws ConsumeNullException, ConsumeEmptyException, ConsumeRepairNullWeaponException, BagNullException {
		System.out.println(name + " repairs FAST :");
		return (RepairKit) fastUseFirst(RepairKit.class);
	}

	
	public void printConsummable(){
		consumable.consumableprintStats();
	}
	
	///////////////////////// BAGS ////////////////////////////////////////////////////////////////
	
	public Collectible[] getBagItems() throws BagNullException {
		if(bag == null) throw new BagNullException() ;
		return bag.getItems() ;
	}
	
	public int getBagCapacity() throws BagNullException {
		if(bag == null) throw new BagNullException() ;
		return bag.getCapacity() ;
	}

	public int getBagWeight() throws BagNullException {
		if(bag == null) throw new BagNullException() ;
		return bag.getWeight() ;
	}

	
	public void pickUp(Collectible item) throws BagNullException, BagFullException {
		if(bag == null) throw new BagNullException() ;
		System.out.print(name + " picks up " + item);
		bag.push(item);
	}
	
	public Collectible pullOut(Collectible item) throws BagNullException {
		if(bag == null) throw new BagNullException() ;
		System.out.print(name + " pulls out " + item);
		return bag.pop(item) ;
	}
	
	public Bags setBag(Bags newBag){
		String oldBagName, newBagName ;
		String nullBagName = "null" ;
		try{
			oldBagName = bag.getClass().getSimpleName() ;
		}catch (NullPointerException e){ oldBagName = nullBagName ; }
		try {
			newBagName = newBag.getClass().getSimpleName();
		}catch (NullPointerException e){ newBagName = nullBagName ; }
		System.out.println(name + " changes " + oldBagName + " for " + newBagName);
		Bags oldBag = bag ;
		Bags.transfer(oldBag, newBag);
		bag = newBag ;
		return oldBag ;
	}
	
	public void equip(Consumable consumable) throws BagNullException {
		if(bag == null) throw new BagNullException() ;
		if(consumable == null) return ;
		if(bag.contains(consumable)){
			pullOut(consumable) ;
			System.out.println(" and equips it !");
			setConsumable(consumable);
		}
	}
	
	public void equip(Weapon weapon) throws BagNullException {
		if(bag == null) throw new BagNullException() ;
		if(weapon == null) return ;
		if(bag.contains(weapon)){
			pullOut(weapon) ;
			System.out.println(" and equips it !");
			setWeapon(weapon);
		}
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
