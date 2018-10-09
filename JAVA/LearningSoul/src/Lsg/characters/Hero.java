package Lsg.characters;

public class Hero extends Character{
	
		public Hero(String N) {
			super();
			
			this.setName(N);
			this.setLife(100);
			this.setMaxLife(100);
			this.setStamina(50);
			this.setMaxStamina(50);
		}
}

