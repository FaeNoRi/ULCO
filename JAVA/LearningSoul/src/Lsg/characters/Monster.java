package Lsg.characters;
public class Monster {

		private String name;
		private int life;
		private int maxLife;
		private int stamina;
		private int maxStamina;
		private boolean vie;
		private static int INSTANCES_COUNT = 0;
		
		public Monster() {
			this.name = "Souris demoniaque du néant profond";
			this.life = 10;
			this.maxLife = 10;
			this.stamina = 10;
			this.maxStamina = 10; 
		}
		
		public Monster(String N) {
			INSTANCES_COUNT++;
			this.name = N+"_"+INSTANCES_COUNT;
			this.life = 10;
			this.maxLife = this.life;
			this.stamina = 10;
			this.maxStamina = this.stamina;
			
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getLife() {
			return life;
		}

		public void setLife(int life) {
			this.life = life;
		}

		public int getMaxLife() {
			return maxLife;
		}

		public void setMaxLife(int maxLife) {
			this.maxLife = maxLife;
		}

		public int getStamina() {
			return stamina;
		}

		public void setStamina(int stamina) {
			this.stamina = stamina;
		}

		public int getMaxStamina() {
			return maxStamina;
		}

		public void setMaxStamina(int maxStamina) {
			this.maxStamina = maxStamina;
		}
		
		public boolean isAlive() {
			if(this.life>0){
				this.vie = true;
			}else {
				this.vie = false;
			}			
			return vie;
			
		}
		public String toString() {
			this.isAlive();
			String vVie;
			if(this.vie==true) {
				vVie = "ALIVE";
			}else{
				vVie = "DEAD";
			}

			String str = new String("[MONSTRE] "+ this.getName() + " LIFE : "+this.getLife()+" STAMINA : "+this.getStamina()+" ("+vVie+")");
			return str;
		}
		
		public void printStats() {
			System.out.println(this.toString());
		}
}
