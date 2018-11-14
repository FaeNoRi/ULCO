package Lsg.consumables;

import Lsg.consumables.food.*;
import java.util.*;

import Lsg.consumables.drinks.*;

public class MenuBestOfV4 extends LinkedHashSet<Consumable>{
	
	public MenuBestOfV4() {
		add(new Hamburger());
		add(new Wine());
		add(new Americain());
		add(new Coffee());
		add(new Whisky());
	}
	
	public String toString() {
		String str = "MenuBestOfV4 :\n";
		int i = 1;
		Iterator iterator = iterator();
		
		while(iterator.hasNext()) {
			Object elem = iterator.next();
			str += i+" : "+elem.toString();
			i++;
		}
		return str;
	}
	
	public void ConsumablePrintStats() {
		System.out.println(this.toString());
	}
	
	public static void main(String args[]) {
		
		MenuBestOfV4 mno = new MenuBestOfV4();
		System.out.println(mno.toString());
		//mno.ConsumablePrintStats();
	}
}
