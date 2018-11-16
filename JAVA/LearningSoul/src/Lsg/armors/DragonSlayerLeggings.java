package Lsg.armors;

import Lsg.bags.Collectible;

public class DragonSlayerLeggings extends ArmorItem implements Collectible {
	
	public DragonSlayerLeggings() {
		super("DragonSlayerLeggings", (float)10.2);
	}
	
	public int getWeight() {
		return 3;
	}
}
