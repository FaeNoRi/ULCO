package Lsg.consumables;

import Lsg.consumables.food.*;
import java.util.*;

import Lsg.consumables.drinks.*;

public class MenuBestOfV3 extends HashSet<Consumable>{
	
	
	public MenuBestOfV3() {
		add(new Hamburger());
		add(new Wine());
		add(new Americain());
		add(new Coffee());
		add(new Whisky());
	}
	
	public String toString() {
		String str = "MenuBestOfV3 :\n";
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
		
		MenuBestOfV3 mno = new MenuBestOfV3();
		System.out.println(mno.toString());
		//mno.ConsumablePrintStats();
	}
}
