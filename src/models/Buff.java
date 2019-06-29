package models;

import java.io.Serializable;

public class Buff implements Serializable {
    private String name;
    private int duration;
    private int holy;
    private int poison;
    private int weaknessAP;
    private int weaknessHP;
    private boolean stun;
    private boolean disarm;
    private int manaChange;
    private int unholy;
    private int cancelBuff;
    private boolean applyWhenTurnEnds;
    private boolean lasts;
    private SpecialMinion specialMinion;
    private int waitingTime;
    private boolean isPositive;
    private boolean continuous;
    private boolean onStartUsable;
    private Spell itemSpell;
    private boolean noDamageFromWeakers;
    private boolean noPoison;

    public void decrementDuration() {
        duration -= 1;
    }

    public Buff(int duration, int holy, int poison, int weaknessAP,
                int weaknessHP, boolean stun, boolean disarm,
                int unholy, int cancelBuff, boolean applyWhenTurnEnds,
                boolean lasts, int manaChange,
                SpecialMinion specialMinion, int waitingTime,
                boolean continuous, boolean onStartUsable,
                Spell itemSpell,
                boolean noPoison) {
        this.duration = duration;
        this.holy = holy;
        this.poison = poison;
        this.weaknessAP = weaknessAP;
        this.weaknessHP = weaknessHP;
        this.stun = stun;
        this.disarm = disarm;
        this.unholy = unholy;
        this.cancelBuff = cancelBuff;
        this.applyWhenTurnEnds = applyWhenTurnEnds;
        this.lasts = lasts;
        this.manaChange = manaChange;
        this.specialMinion = specialMinion;
        this.waitingTime = waitingTime;
        this.itemSpell = itemSpell;
        this.onStartUsable = onStartUsable;
        this.continuous = continuous;
        this.noDamageFromWeakers = noDamageFromWeakers;
        this.noPoison = noPoison;
    }

    public int getUnholy() {
        return unholy;
    }

    public boolean isDisarm() {
        return disarm;
    }

    public boolean isStun() {
        return stun;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isLasts() {
        return lasts;
    }

    public int getHoly() {
        return holy;
    }

    public int getWeaknessHP() {
        return weaknessHP;
    }

    public int getWeaknessAP() {
        return weaknessAP;
    }

    public int getPoison() {
        return poison;
    }

    public int getCancelBuff() {
        return cancelBuff;
    }

    public boolean isApplyWhenTurnEnds() {
        return applyWhenTurnEnds;
    }

    public int getManaChange() {
        return manaChange;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public SpecialMinion getSpecialMinion() {
        return specialMinion;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive() {
        int result = -weaknessAP
                + -weaknessHP +
                -cancelBuff + manaChange + poison;
        if (isDisarm()) {
            result++;
        }
        if (isStun()) {
            result++;
        }
        isPositive = result > 0;
    }

    public boolean isContinuous() {
        return continuous;
    }

    public boolean getOnStartUsable() {
        return onStartUsable;
    }

    public Spell getItemSpell() {
        return itemSpell;
    }

    public boolean isNoDamageFromWeakers() {
        return noDamageFromWeakers;
    }

    public boolean isNoPoison() {
        return noPoison;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Buff() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHoly(int holy) {
        this.holy = holy;
    }

    public void setPoison(int poison) {
        this.poison = poison;
    }

    public void setWeaknessAP(int weaknessAP) {
        this.weaknessAP = weaknessAP;
    }

    public void setWeaknessHP(int weaknessHP) {
        this.weaknessHP = weaknessHP;
    }

    public void setLasts(boolean lasts) {
        this.lasts = lasts;
    }
}