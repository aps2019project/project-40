package models;

import java.util.ArrayList;

public class Unit extends Card{
    private int HP;
    private int AP;
    private ArrayList<Buff> buffs;
    private Spell specialPower;
    private SpecialPowerType specialPowerType;
    private UnitType unitType;
    private boolean combo;
    private int flag;

    Unit(int manaCost, int price, int HP, int AP,UnitType unitType,
         String name,  ArrayList<Spell> spells, String description, CardType type,
         SpecialPowerType specialPowerType, Spell specialPower, boolean combo, int flag){
        super(manaCost, price, name,spells, description, type);
        this.HP = HP;
        this.AP = AP;
        this.specialPowerType = specialPowerType;
        this.specialPower = specialPower;
        this.combo = combo;
        this.unitType = unitType;
        this.flag = flag;
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

    public Spell getSpecialPower() {
        return specialPower;
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
}
