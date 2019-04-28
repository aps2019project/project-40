package models;

public class Buff {
    private int duration;
    private int holy;
    private int power;
    private int poison;
    private int weaknessAP;
    private int WeaknessHP;
    private boolean stun;
    private boolean disarm;
    private int unholy;
    public void decrementDuration(){
        duration -= 1;
    }

}
