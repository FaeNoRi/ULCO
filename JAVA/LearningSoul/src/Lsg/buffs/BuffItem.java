package Lsg.buffs;

import java.util.Locale;

import Lsg.bags.Collectible;

public abstract class BuffItem implements Collectible{
	
	private String name ; 
	
	public BuffItem(String name) {
		this.name = name ;
	}
	
	public abstract float computeBuffValue() ;
	
	public String getName() {
		return name;
	}
	
	public int getWeight() {
		return 1;
	}
	
	@Override
	public String toString() {
		return String.format(Locale.US, "(%s, %.2f) [%s kg]\n", getName(), computeBuffValue(), getWeight()) ;
	}
	
}
