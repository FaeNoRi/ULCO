package Lsg.weapons;

import Lsg.bags.Collectible;
import Lsg.consumables.repair.RepairKit;
import Lsg.exceptions.ConsumeNullException;

public class Weapon implements Collectible {
	
	protected String name;
	protected int minDamage;
	protected int maxDamage;
	protected int stamCost;
	protected int durability;
	protected boolean broke;
	
	public static String MINDAM_STATS_STRING = "Min Damages";
	public static String MAXDAM_STATS_STRING = "Max Damages";
	public static String STAMCOST_STATS_STRING = "Stamina Cost";
	public static String DURABILITY_STAT_STRING = "Durability";
	
	public Weapon(String n, int minD, int maxD, int stamC, int dura) {
		this.name = n;
		this.minDamage = minD;
		this.maxDamage = maxD;
		this.stamCost = stamC;
		this.durability = dura;
	}

	public String getName() {
		return name;
	}

	public int getMinDamage() {
		return minDamage;
	}

	public int getMaxDamage() {
		return maxDamage;
	}

	public int getStamCost() {
		return stamCost;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int dura) {
		this.durability = dura;
	}

	public int getWeight() {
		return 2;
	}
	
	public void use() {
		durability--;
	}
	
	public boolean isBroken() {
		if(this.durability<=0){
			this.broke = true;
		}else {
			this.broke = false;
		}			
		return broke;
	}
	
	public void repairWith(RepairKit kit) throws ConsumeNullException {
		if(kit == null) throw new ConsumeNullException(kit) ;
		setDurability(durability + kit.use());
	}
	
	public String weaponToString() {
		
		String sname = this.getName();
		int sminD = this.getMinDamage();
		int smaxD = this.getMaxDamage();
		int sstamC = this.getStamCost();
		int sdura = this.getDurability();
		int sweight = this.getWeight();
		
		String str = String.format("%s ( "+MINDAM_STATS_STRING+" : %s | "+MAXDAM_STATS_STRING+" : %s | "+STAMCOST_STATS_STRING+" : %s | "+DURABILITY_STAT_STRING+" : %s) [%s kg]\n",sname,sminD,smaxD,sstamC,sdura,sweight);
		return str;
	}
	
	public void weaponprintStats() {
		System.out.println(this.weaponToString());
	}

}
