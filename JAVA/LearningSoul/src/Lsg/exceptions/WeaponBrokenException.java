package Lsg.exceptions;

import Lsg.weapons.Weapon;

public class WeaponBrokenException extends Exception {

    private Weapon weapon ;

    public WeaponBrokenException(Weapon weapon) {
        super(weapon.getName() + " est brisé !");
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
