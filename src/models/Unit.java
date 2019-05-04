package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Unit extends Card implements Serializable {
    private int HP;
    private int AP;
    private ArrayList<Buff> buffs;
    private SpecialPowerType specialPowerType;
    private UnitType unitType;
    private boolean combo;
    private int flag;
    private int range;

    Unit(int manaCost, int price, int HP, int AP,UnitType unitType,
         String name,  ArrayList<Spell> spells, String description, CardType type,
         SpecialPowerType specialPowerType, boolean combo, int flag, int range){
        super(manaCost, price, name,spells, description, type);
        this.HP = HP;
        this.AP = AP;
        this.specialPowerType = specialPowerType;
        this.combo = combo;
        this.unitType = unitType;
        this.flag = flag;
        this.range = range;
    }
    public int getHealthPoint(){
        return HP;
    }
    public int getAttackPoint(){
        return AP;
    }


    public int getAP() {
        return AP;
    }

    public SpecialPowerType getSpecialPowerType() {
        return specialPowerType;
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public int getHP() {
        return HP;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void addBuff(Buff buff) {
        this.buffs.add(buff);
    }

    public boolean isCombo() {
        return combo;
    }

    public int getFlag() {
        return flag;
    }

    public int getRange() {
        return range;
    }
}
