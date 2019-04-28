package models;

public class Buff {
    private int duration;
    private int holy;
    private int power;
    private int poison;
    private int weaknessAP;
    private int weaknessHP;
    private boolean stun;
    private boolean disarm;
    private int unholy;

    public void decrementDuration() {
        duration -= 1;
    }

    public Buff(int duration, int holy, int power, int poison, int weaknessAP, int weaknessHP, boolean stun, boolean disarm, int unholy) {
        this.duration = duration;
        this.holy = holy;
        this.power = power;
        this.poison = poison;
        this.weaknessAP = weaknessAP;
        this.weaknessHP = weaknessHP;
        this.stun = stun;
        this.disarm = disarm;
        this.unholy = unholy;
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
}
