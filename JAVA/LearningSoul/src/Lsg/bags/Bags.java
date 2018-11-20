package Lsg.bags;

import java.util.*;
import Lsg.consumables.Consumable;
import Lsg.consumables.food.*;
import Lsg.consumables.drinks.*;
import Lsg.weapons.*;
import Lsg.armors.*;
import Lsg.buffs.*;
import Lsg.buffs.rings.*;

public class Bags extends HashSet<Consumable> {
	
	private int capacity;
	private int weight;
	private HashSet<Collectible> items = new HashSet<Collectible>();
	
	public Bags(int cap) {
		this.capacity = cap;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getWeight() {
		return weight;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void push(Collectible item) {
		items.add(item);
		this.setWeight(this.getWeight() + item.getWeight());
	}
	
	public Collectible pop(Collectible item) {
		for(Collectible col : items) {
			if(col == item) {
				items.remove(col);
				return col;
			}
		}
		return null;
	}
	
	public boolean contains(Collectible item) {
		for(Collectible col : items) {
			if(col == item) {
				return true;
			}
		}
		return false;
	}
	
	public Collectible[] getItems() {
		int i=0;
		Collectible[] collR = new Collectible[items.size()];
		
		for(Collectible collI : items) {
			collR[i] = collI;
			i++;
		}
		return collR;
	}
	
	public String toString(){
		String str=""+this.getClass().getSimpleName()+" ["+items.size()+" items | "+this.getWeight()+"/"+this.getCapacity()+"]\n";
		if(items.size()==0) {
			str = str.concat("Le sac est vide\n");
			return str;
		}
		for(Collectible collI : items) {
			str = str.concat("¤ "+collI.toString()+"");
		}
		return str;
	}
	
	public void bagPrintStats() {
		System.out.println(this.toString());
	}
	
	public static void main (String args[]) {
		Bags bag = new SmallBag();
		
		bag.push(new Shotgun());
		bag.push(new DragonSlayerLeggings());
		bag.push(new RingedKnightArmor());
		
		bag.bagPrintStats();
	}
}
