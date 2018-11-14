package Lsg.consumables;

import Lsg.consumables.food.*;
import java.util.*;

import Lsg.consumables.drinks.*;

public class MenuBestOfV2 {
	
	HashSet<Consumable> menubestof = new HashSet<Consumable>();
	
	public MenuBestOfV2() {
		menubestof.add(new Hamburger());
		menubestof.add(new Wine());
		menubestof.add(new Americain());
		menubestof.add(new Coffee());
		menubestof.add(new Whisky());
	}
	
	public String toString() {
		String str = "MenuBestOfV2 :\n";
		int i = 1;
		
		for(Consumable consI : menubestof) {
			str += i+" : "+consI.toString();
			i++;
		}
		return str;
	}
	
	public void ConsumablePrintStats() {
		System.out.println(this.toString());
	}
	
	public static void main(String args[]) {
		
		MenuBestOfV2 mno = new MenuBestOfV2();
		System.out.println(mno.toString());
		//mno.ConsumablePrintStats();
	}
}
