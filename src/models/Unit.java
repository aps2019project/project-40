package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Unit extends Card implements Serializable {
    private int HP;
    private int AP;
    private ArrayList<Buff> buffs = new ArrayList<>();
    private SpecialPowerType specialPowerType;
    private Spell specialPower;
    private UnitType unitType;
    private boolean combo;
    private int range;
    private boolean canMove;
    private boolean canAttack;
    private boolean cantBeStunned;
    private boolean cantBeDisarmed;
    private boolean cantBePoisoned;
    private boolean isDisarm;
    private boolean isStunned;
    private int damageChange;
    private boolean noDamageFromWeakers;
    private boolean noBadEffect;

    Unit(int manaCost, int price, int HP, int AP, UnitType unitType,
         String name, ArrayList<Spell> spells, String description, CardType type,
         SpecialPowerType specialPowerType,
         boolean combo, int range, Cell cell, boolean canMove,
         boolean canAttack, boolean cantBeStunned, boolean cantBeDisarmed,
         boolean cantBePoisoned) {
        super(manaCost, price, name, spells, description, type, cell);
        this.HP = HP;
        this.AP = AP;
        this.specialPowerType = specialPowerType;
        this.combo = combo;
        this.unitType = unitType;
        this.range = range;
        this.canMove = canMove;
        this.canAttack = canAttack;
        this.cantBeDisarmed = cantBeDisarmed;
        this.cantBePoisoned = cantBePoisoned;
        this.cantBeStunned = cantBeStunned;
    }

    Unit() {

    }

    public Spell getSpecialPower() {
        return specialPower;
    }

    public int getHealthPoint() {
        return HP;
    }

    public int getAttackPoint() {
        return AP;
    }

    public SpecialPowerType getSpecialPowerType() {
        return specialPowerType;
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void addBuff(Buff buff) {
        this.buffs.add(buff);
    }

    public int getRange() {
        return range;
    }

    public boolean hasComboAbility() {

        return combo;
    }

    public void setBuffs(ArrayList<Buff> buffs) {
        this.buffs = buffs;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setAP(int AP) {
        this.AP = AP;
    }

    public boolean isCombo() {
        return combo;
    }

    public void setCombo(boolean combo) {
        this.combo = combo;
    }

    public int getHP() {
        return HP;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public int getAP() {
        return AP;
    }

    public boolean isCantBeDisarmed() {
        return cantBeDisarmed;
    }

    public boolean isCantBePoisoned() {
        return cantBePoisoned;
    }

    public boolean isCantBeStunned() {
        return cantBeStunned;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public void setCantBeDisarmed(boolean cantBeDisarmed) {
        this.cantBeDisarmed = cantBeDisarmed;
    }

    public void setCantBePoisoned(boolean cantBePoisoned) {
        this.cantBePoisoned = cantBePoisoned;
    }

    public void setCantBeStunned(boolean cantBeStunned) {
        this.cantBeStunned = cantBeStunned;
    }

    public void removeBuff(Buff buff) {
        buffs.remove(buff);
    }

    public int getDamageChange() {
        return damageChange;
    }

    public void setDamageChange(int damageChange) {
        this.damageChange = damageChange;
    }

    public boolean isDisarm() {
        return isDisarm;
    }

    public boolean isStunned() {
        return isStunned;
    }

    public void setStunned(boolean stunned) {
        isStunned = stunned;
    }

    public void setDisarm(boolean disarm) {
        isDisarm = disarm;
    }

    @Override
    public String toString() {
        return "" + AP + "\n" + HP;
    }

    public boolean isNoDamageFromWeakers() {
        return noDamageFromWeakers;
    }

    public boolean isNoBadEffect() {
        return noBadEffect;
    }

    public void setNoDamageFromWeakers(boolean noDamageFromWeakers) {
        this.noDamageFromWeakers = noDamageFromWeakers;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public void setRange(int range) {
        this.range = range;
    }

}
