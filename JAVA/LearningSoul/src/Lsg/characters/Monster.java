package Lsg.characters;

import Lsg.weapons.*;

public class Monster extends Character{
	
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
		
		protected float getSkinThickness() {
			return skinThickness;
		}

		protected void setSkinThickness(float skinThickness) {
			this.skinThickness = skinThickness;
		}
		
		public float computeProtection() {
			return getSkinThickness();
		}

		@Override
		public float computeBuffValue() {
			// TODO Auto-generated method stub
			return 0;
		}
}
