package models;

import java.util.ArrayList;

public class Unit extends Card{
    private ArrayList<Buff> buffs=new ArrayList<>();
    private String cardID;
    private  String name;
    private int healthPoint;
    private int attackPoint;
    private int numberOfFlags;
    private Account player;
    private boolean canMove;
    private boolean canAttack;
    private Spell specialPower;
    private SpecialPowerType specialPowerType;
    private boolean isFlying;

    public Unit(int mana,int price){
        super(mana,price);
    }

    public int getNumberOfFlags() {
        return numberOfFlags;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public void receiveAttack(int attackPoint){

    }
}
