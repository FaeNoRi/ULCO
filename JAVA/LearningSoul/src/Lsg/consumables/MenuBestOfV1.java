package Lsg.consumables;

import Lsg.consumables.food.*;
import Lsg.consumables.drinks.*;

public class MenuBestOfV1 {
	
	Consumable[] menubestof = new Consumable[5];
	
	public MenuBestOfV1() {
		menubestof[0] = new Hamburger();
		menubestof[1] = new Wine();
		menubestof[2] = new Americain();
		menubestof[3] = new Coffee();
		menubestof[4] = new Whisky();
	}
	
	public String toString() {
		String str = "MenuBestOfV1 :\n";
	
		for(int i=1; i<=menubestof.length; i++) {
			str += i+" : "+menubestof[i-1].toString();
		}
		return str;
	}
	
	public void ConsumablePrintStats() {
		System.out.println(this.toString());
	}
	
	public static void main(String args[]) {
		
		MenuBestOfV1 mno = new MenuBestOfV1();
		System.out.println(mno.toString());
		//mno.ConsumablePrintStats();
	}
}
