package Lsg.characters;

import Lsg.buffs.talismans.*;
import Lsg.weapons.*;

public class Monster extends Character{
	
		private static int MAX_TALISMAN = 1;
		Talisman[] talitab = new Talisman[MAX_TALISMAN];
	
		private static int INSTANCES_M_COUNT = 0;
		private float skinThickness;
		
		public Monster(String N, Weapon w, float skinT) {
			super();
			
			INSTANCES_M_COUNT++;
			this.setName(N+"_"+INSTANCES_M_COUNT);
			this.setLife(10);
			this.setMaxLife(10);
			this.setStamina(10);
			this.setMaxStamina(10);
			this.setWeapon(w);
			this.setSkinThickness(skinT);
		}
		
		public Monster() {
			
			INSTANCES_M_COUNT++;
			this.setName("Monster_"+INSTANCES_M_COUNT);
			this.setLife(10);
			this.setMaxLife(10);
			this.setStamina(10);
			this.setMaxStamina(10);
			this.setWeapon(new Sword());
			this.setSkinThickness(20);
		}
		
/////////////////////////////////////////// Protection ////////////////////////////////////////////// 
		
		protected float getSkinThickness() {
			return skinThickness;
		}

		protected void setSkinThickness(float skinThickness) {
			this.skinThickness = skinThickness;
		}
		
		public float computeProtection() {
			return getSkinThickness();
		}
		
/////////////////////////////////////////// Talismans //////////////////////////////////////////////
		
		public void setTalisman(Talisman tali, int value) {
			if(value > 0 || value < this.MAX_TALISMAN) {
				talitab[value-1] = tali;
			}
		}
		
		public float getTotalBuffs(){
			Float buffT = (float) 0;
			for(Talisman talI : talitab) {
				if(talI == null) {
					buffT = buffT + 0;
				}else {
					buffT = buffT + talI.computeBuffValue();
				}
			}	
			return buffT;
		}
		
		public float computeBuffValue() {
			return getTotalBuffs();
		}
		

		public String talismanToString() {
			String str="Talisman de "+this.getName()+" : \n";
			int i = 1;
			
			for(Talisman talI : talitab) {
				if(talI == null) {
					str = str.concat("Slot "+i+": Empty\n");
				}else {
					str = str.concat("Slot "+i+": "+talI.toString());
				}
				i++;
			}
			return str;
		}
		
////////////////////////////////////// Affichage /////////////////////////////////////////////////
		
		public void monsterPrintStats() {
			System.out.println("////////////////////////////////////////////////////////// "+this.getClass().getSimpleName()+" ////////////////////////////////////////////////////////////");
			System.out.println(this.toString());
			System.out.println(this.talismanToString());
		}

}
