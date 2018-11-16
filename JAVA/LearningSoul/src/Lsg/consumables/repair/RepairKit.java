package Lsg.consumables.repair;

import Lsg.consumables.*;
import Lsg.weapons.Weapon;

public class RepairKit extends Consumable{

		public RepairKit(){
			super("Repair Kit",10,Weapon.DURABILITY_STAT_STRING);
		}
		
		@Override
		public int use() {
			int amount = 1;
			this.setCapacity(this.getCapacity()-1);
			
			return amount;	
		}
}
