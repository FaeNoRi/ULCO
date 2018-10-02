package Lsg.characters;

public class Hero {

		private String name;
		private int life;
		private int maxLife;
		private int stamina;
		private int maxStamina;
		private boolean vie;
		
		public Hero() {
			name = "xXx_DaRk_SaSuKe_xXx";
			this.life = 100;
			this.maxLife = 100;
			this.stamina = 50;
			this.maxStamina = 50; 
		}
		
		public Hero(String N) {
			this.name = N;
			this.life = 100;
			this.maxLife = this.life;
			this.stamina = 50;
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

			String str = new String("[HERO]\t"+ this.getName() + "\tLIFE : "+this.getLife()+"\tSTAMINA : "+this.getStamina()+" ("+vVie+")");
			return str;
		}
		
		public void printStats() {
			System.out.println(this.toString());
		}
}

