package Lsg.characters;

import Lsg.helpers.*;
import Lsg.weapons.*;

public class Hero extends Character{
	
		public Hero(String N, Weapon w) {
			super();
			
			this.setName(N);
			this.setLife(100);
			this.setMaxLife(100);
			this.setStamina(50);
			this.setMaxStamina(50);
			this.setWeapon(w);
		}
}

