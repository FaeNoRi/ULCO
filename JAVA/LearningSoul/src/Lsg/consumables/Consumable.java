package Lsg.consumables;

public class Consumable {
	
	private String name;
	private int capacity;
	private String stat;
	
	public Consumable(String n, int cap, String stat) {
		this.name = n;
		this.capacity = cap;
		this.stat = stat;
	}

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

	public String getStat() {
		return stat;
	}
	
	public void setCapacity( int cap) {
		this.capacity = cap;
	}
	
	public int use() {
		int amount = this.getCapacity();
		this.setCapacity(0);
		
		return amount;
		
	}
	
	public String toString() {
		
		String sname = this.getName();
		int scap = this.getCapacity();
		String sstat = this.getStat();
		
		String str = String.format("%s [%s %s]\n",sname,scap,sstat);
		return str;
	}
	
	public void consumableprintStats() {
		System.out.println(this.toString());
	}
	
}
