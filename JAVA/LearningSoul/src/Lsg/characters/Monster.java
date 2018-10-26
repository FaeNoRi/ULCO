package Lsg.characters;

import Lsg.weapons.*;

public class Monster extends Character{
	
		private static int INSTANCES_M_COUNT = 0;
		private float skinThickness;
		
		public Monster(String N, Weapon w) {
			super();
			
			INSTANCES_M_COUNT++;
			this.setName(N+"_"+INSTANCES_M_COUNT);
			this.setLife(20);
			this.setMaxLife(100);
			this.setStamina(50);
			this.setMaxStamina(50);
			this.setWeapon(w);
			this.setSkinThickness(20);
		}
		
		protected float getSkinThickness() {
			return skinThickness;
		}

		protected void setSkinThickness(float skinThickness) {
			this.skinThickness = skinThickness;
		}
		
		public float computeProtection() {
			return getSkinThickness();
		}
}
