package Lsg.armors;

public class ArmorItem {

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

	public String toString() {
		String str = new String(this.getName()+" ("+this.getArmorValue()+")");
		return str;
	}
	
	public void printStats() {
		System.out.println(this.toString());
	}
}
