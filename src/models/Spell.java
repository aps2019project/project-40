package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Spell extends Card implements Serializable {
    private String spellName;
    private Target target;
    private Buff buff;
    private int coolDown;
    private int heroManaCost;
    private SpecialPowerType specialPowerType;

    public Spell(int manaCost, int price, String name,
                 ArrayList<Spell> spells, String description, CardType type,
                 String spellName, Buff buff, Target target,
                 int heroManaCost, SpecialPowerType specialPowerType,
                 Cell cell) {
        super(manaCost, price, name, spells, description, type, cell);
        this.spellName = spellName;
        this.buff = buff;
        this.target = target;
        this.heroManaCost = heroManaCost;
        this.specialPowerType = specialPowerType;
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

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public int getHeroManaCost() {
        return heroManaCost;
    }

    public SpecialPowerType getSpecialPowerType() {
        return specialPowerType;
    }
}
