package models;

import java.io.Serializable;

public class Buff implements Serializable {
    private int duration;
    private int holy;
    private int power;
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

    public void decrementDuration() {
        duration -= 1;
    }

    public Buff(int duration, int holy, int power, int poison, int weaknessAP,
                int weaknessHP, boolean stun, boolean disarm,
                int unholy, int cancelBuff, boolean applyWhenTurnEnds,
                boolean lasts, int manaChange, SpecialMinion specialMinion, int waitingTime) {
        this.duration = duration;
        this.holy = holy;
        this.power = power;
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

    public int getPower() {
        return power;
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
        if (isStun()){
            result++;
        }
        if (result > 0){
            isPositive = true;
        }
        else {
            isPositive = false;
        }
    }
}