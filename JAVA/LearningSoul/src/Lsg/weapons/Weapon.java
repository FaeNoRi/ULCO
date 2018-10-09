package Lsg.weapons;

public class Weapon {
	
	protected String name;
	protected int minDamage;
	protected int maxDamage;
	protected int stamCost;
	protected int durability;
	protected boolean broke;
	
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
	
	public void use() {
		durability--;
	}
	
	protected boolean isBroken() {
		if(this.durability<=0){
			this.broke = true;
		}else {
			this.broke = false;
		}			
		return broke;
	}
	
	public String toString() {
		
		String stype = getClass().getSimpleName();
		String sname = this.getName();
		int sminD = this.getMinDamage();
		int smaxD = this.getMaxDamage();
		int sstamC = this.getStamCost();
		int sdura = this.getDurability();
		
		String str = String.format("[%-10s] %s (min: %s | max: %s | stam: %s | dur: %s)",stype,sname,sminD,smaxD,sstamC,sdura);
		return str;
	}
	
	public void printStats() {
		System.out.println(this.toString());
	}
}
