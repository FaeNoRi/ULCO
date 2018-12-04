package Lsg.exceptions;

import Lsg.consumables.Consumable;

public class ConsumeEmptyException extends ConsumeException {

    private static String MSG = "Consumable is empty !" ;

    public ConsumeEmptyException(Consumable consumable) {
        super(MSG, consumable);
    }
}
