package Lsg.exceptions;

import Lsg.weapons.Weapon;

public class WeaponBrokenException extends Exception {

    private Weapon weapon ;

    public WeaponBrokenException(Weapon weapon) {
        super(weapon.getName() + " est bris� !");
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
