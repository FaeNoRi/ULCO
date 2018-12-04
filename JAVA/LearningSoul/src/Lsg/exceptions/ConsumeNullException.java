package Lsg.exceptions;

import Lsg.consumables.Consumable;

public class ConsumeNullException extends ConsumeException {

    private static String MSG = "Consumable is null !" ;

    public ConsumeNullException(Consumable consumable) {
        super(MSG, consumable);
    }
}
