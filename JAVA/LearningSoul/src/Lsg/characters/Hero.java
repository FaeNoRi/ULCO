package Lsg.characters;

import java.lang.reflect.Array;
import java.util.*;
import Lsg.helpers.*;
import Lsg.weapons.*;
import Lsg.armors.*;

public class Hero extends Character{
	
		private static int MAX_ARMOR_PIECES = 3;
		ArmorItem[] armortab = new ArmorItem[MAX_ARMOR_PIECES];
		
		public Hero(String N, Weapon w) {
			super();
			
			this.setName(N);
			this.setLife(100);
			this.setMaxLife(100);
			this.setStamina(50);
			this.setMaxStamina(50);
			this.setWeapon(w);
		}
		
		public void setArmorItem(ArmorItem armor, int value) {
			if(value > 0 || value < this.MAX_ARMOR_PIECES) {
				armortab[value-1] = armor;
			}
		}
		
		public float getTotalArmor(){
			Float armorT = (float) 0;
			for(ArmorItem armorI : armortab) {
				armorT = armorT + armorI.getArmorValue();
			}
			return armorT;
		}
		
		public ArmorItem[] getArmorItem() {
			int i=1;
			ArmorItem[] armortabR = new ArmorItem[MAX_ARMOR_PIECES];
			
			for(ArmorItem armorI : armortab) {
				if(armortab[i-1] == null) {
					i++;
				}else {
					armortabR[i-1] = armorI;
				}
			}
			return armortabR;
		}
		
		public float
		
		public String toString() {
			String str="", strF="[ARMOR]   ";
			int i = 1;
			
			for(ArmorItem armorI : armortab) {
				if(armortab[i-1] == null) {
					str = i+": Empty   ";
				}else {
					str = i+": "+armortab[i-1].toString()+"   ";
				}
				strF = strF.concat(str);
				i++;
			}
			
			return strF;
		}
		
		public void printStats() {
			System.out.println(this.toString());
		}
}

