package Lsg.characters;

import java.lang.reflect.Array;
import java.util.*;
import Lsg.helpers.*;
import Lsg.weapons.*;
import Lsg.armors.*;
import Lsg.bags.*;
import Lsg.buffs.*;
import Lsg.buffs.rings.*;
import Lsg.exceptions.*;

public class Hero extends Character{
	
		private static int MAX_ARMOR_PIECES = 3;
		ArmorItem[] armortab = new ArmorItem[MAX_ARMOR_PIECES];
		
		private static int MAX_RINGS = 2;
		Ring[] ringtab = new Ring[MAX_RINGS];
		
///////////////////////////////////////////////////// INIT //////////////////////////////////////////////////////////
		
		public Hero(String N) {
			super();
			
			this.setName(N);
			this.setLife(100);
			this.setMaxLife(100);
			this.setStamina(50);
			this.setMaxStamina(50);
		}
		
		public Hero() {
			super();
			
			this.setName("Hero");
		}
		
///////////////////////////////////////////////////// ARMOR //////////////////////////////////////////////////////////
		
		public void setArmorItem(ArmorItem armor, int value) {
			if(value > 0 || value < this.MAX_ARMOR_PIECES) {
				armortab[value-1] = armor;
			}
		}
		
		public float getTotalArmor(){
			Float armorT = (float) 0;
			for(ArmorItem armorI : armortab) {
				if(armorI == null) {
					armorT = armorT + 0;
				}else {
					armorT = armorT + armorI.getArmorValue();
				}
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
		
		public float computeProtection() {
			return getTotalArmor();
		}
		
		public String armorToString() {
			String str="Armure de "+this.getName()+" :\n";
			int i = 1;
			
			for(ArmorItem armorI : armortab) {
				if(armorI == null) {
					str = str.concat("Slot "+i+": Empty\n");
				}else {
					str = str.concat("Slot "+i+": "+armorI.toString());
				}
				i++;
			}
			return str;
		}
		
		public void equip(ArmorItem item, int slot) throws BagNullException {
			if(item==null) return ;
			if(bag == null) throw new BagNullException() ;
			if(bag.contains(item)){
				pullOut(item) ;
				System.out.println(" and equips it !");
				setArmorItem(item, slot);
			}
		}
		
//////////////////////////////////////////////////// RINGS ///////////////////////////////////////////////////////////
		
		public void setRing(Ring ring, int value) {
			if(value > 0 || value < this.MAX_RINGS) {
				ringtab[value-1] = ring;
			}
		}
		
		public float getTotalBuffs(){
			Float buffT = (float) 0;
			for(Ring ringI : ringtab) {
				if(ringI == null) {
					buffT = buffT + 0;
				}else {
					buffT = buffT + ringI.computeBuffValue();
				}
			}	
			return buffT;
		}
		
		public float computeBuffValue() {
			return getTotalBuffs();
		}
		

		public String ringToString() {
			String str="Anneaux de "+this.getName()+" :\n";
			int i = 1;
			
			for(Ring ringI : ringtab) {
				if(ringI == null) {
					str =  str.concat("Slot "+i+": Empty\n");
				}else {
					str =  str.concat("Slot "+i+": "+ringI.toString());
				}
				i++;
			}
			return str;
		}
		
		public void equip(Ring item, int slot) throws BagNullException {
			if(item==null) return ;
			if(bag == null) throw new BagNullException() ;
			if(bag.contains(item)){
				pullOut(item) ;
				System.out.println(" and equips it !");
				setRing(item, slot);
			}
		}
		
//////////////////////////////////////////////////// AFFICHAGE ////////////////////////////////////////////////////////
		
		public void heroPrintStats() {
			System.out.println("////////////////////////////////////////////////////////// "+this.getClass().getSimpleName()+" ////////////////////////////////////////////////////////////");
			System.out.println(this.toString());
		}
		
		public void printArmor(){
			System.out.println(armorToString());
		}
		
		public void printRing(){
			System.out.println(ringToString());
		}

}

