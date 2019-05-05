package models;

import java.io.Serializable;

public class Usable extends Item implements Serializable {
    public Spell getSpell() {
        return spell;
    }
}
