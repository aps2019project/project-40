package models;

import java.util.ArrayList;

public class Unit extends Card{
    private String name;
    private int HP;
    private int AP;
    private ArrayList<Buff> buffs;
    private Account player;
    private boolean canMove;
    private boolean canAttack;
    private Spell specialPower;
    private SpecialPowerType specialPowerType;
    private String cardID;
    private boolean isFlying;

    public int getNumberOfFlags(){
        return 0;
    }
    public int getHealthPoint(){
        return HP;
    }
    public int getAttackPoint(){
        return AP;
    }
}
