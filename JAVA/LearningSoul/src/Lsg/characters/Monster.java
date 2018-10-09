package Lsg.characters;

public class Monster extends Character{
	
		private static int INSTANCES_M_COUNT = 0;
		
		public Monster(String N) {
			super();
			
			INSTANCES_M_COUNT++;
			this.setName(N+"_"+INSTANCES_M_COUNT);
			this.setLife(100);
			this.setMaxLife(100);
			this.setStamina(50);
			this.setMaxStamina(50);
		}
}
