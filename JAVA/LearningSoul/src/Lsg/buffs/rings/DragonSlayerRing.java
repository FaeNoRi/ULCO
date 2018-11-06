package Lsg.buffs.rings;

import Lsg.armors.ArmorItem;
import Lsg.armors.DragonSlayerLeggings;

public class DragonSlayerRing extends Ring{
	
	public DragonSlayerRing() {
		super("Dragon Slayer Ring", 14) ;
	}
	
	@Override
	public float computeBuffValue() {
		if(hero != null && hasDragonsSlayerItem()){
			return power ;
		}else return 0 ;
	}
	
	private boolean hasDragonsSlayerItem(){
		ArmorItem[] items = hero.getArmorItem() ;
		for(ArmorItem item: items){
			if(item instanceof DragonSlayerLeggings) return true ; 
		}
		return false ;
	}
	
}
