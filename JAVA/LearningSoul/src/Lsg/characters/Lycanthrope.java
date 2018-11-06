package Lsg.characters;

import Lsg.weapons.*;

public class Lycanthrope extends Monster {
	
	public Lycanthrope() {
		super("Lycanthrope",new Claw(),30);
		
		this.setLife(150);
		this.setMaxLife(150);
		this.setStamina(50);
		this.setMaxStamina(50);
	
	}


}

