package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Spell extends Card  implements Serializable {
    private String spellName;
    private Target target;
    private Buff buff;

    public Spell(int manaCost, int price, String name,
                 ArrayList<Spell> spells, String description, CardType type,
                 String spellName, Buff buff, Target target) {
        super(manaCost, price, name,spells,description,type);
        this.spellName = spellName;
        this.buff = buff;
        this.target = target;
    }

    public Target getTarget() {
        return target;
    }

    public Buff getBuff() {
        return buff;
    }

    public String getSpellName() {
        return spellName;
    }
}
