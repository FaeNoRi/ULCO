package Lsg.exceptions;

import Lsg.bags.Bags;

public class BagFullException extends Exception {

    private Bags bag ;

    public BagFullException(Bags bag) {
        super(bag.getClass().getSimpleName() + " is full") ;
        this.bag = bag;
    }

    public Bags getBag() {
        return bag;
    }

}
