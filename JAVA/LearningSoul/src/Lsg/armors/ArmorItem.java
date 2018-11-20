package Lsg.armors;

import Lsg.bags.Collectible;

public class ArmorItem implements Collectible{

	protected String name;
	protected float armorValue;
	
	
	public ArmorItem(String n, float armval) {
		this.name = n;
		this.armorValue = armval;
	}
	
	
	public String getName() {
		return name;
	}
	public float getArmorValue() {
		return armorValue;
	}
	
	public int getWeight() {
		return 4;
	}

	public String toString() {
		String str = new String(this.getName()+" ("+this.getArmorValue()+") ["+this.getWeight()+" kg]\n");
		return str;
	}
	
	public void printStats() {
		System.out.println(this.toString());
	}
}
