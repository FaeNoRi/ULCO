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
		int weight = item.getWeight() ;
        if(this.weight + weight <= capacity){
            items.add(item) ;
            this.weight += weight ;
        }
	}
	
	public Collectible pop(Collectible item) {
        if(items.contains(item)){
            int weight = item.getWeight() ;
            items.remove(item) ;
            this.weight -= weight ;
            return item ;
        }else{
            return null ;
        }
	}
	
	public boolean contains(Collectible item) {
		return items.contains(item) ;
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
	
    public static void transfer(Bags from, Bags into){
        int size = into.getCapacity() ;
        Collectible[] items = from.getItems() ;
        for(Collectible item: items){
            if(item.getWeight() <= (size - into.getWeight())){
                into.push(from.pop(item));
            }
        }
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
        System.out.println();
        Bags bag = new Bags(10) ;
        bag.push(new RingedKnightArmor());
        bag.push(new DragonSlayerLeggings());
        bag.push(new Shotgun());
        System.out.println("Sac 1 :");
        System.out.println(bag);

        Bags newBag = new Bags(5) ;
        System.out.println("Sac 2 :");
        System.out.println(newBag);
        transfer(bag, newBag);
        System.out.println();
        System.out.println("Sac 2 après transfert :");
        System.out.println(newBag);

        System.out.println("Sac 1 après transfert :");
        System.out.println(bag);
	}
}
