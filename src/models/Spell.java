package models;

public class Spell extends Card{
    private String spellName;
    private Target target;
    private Buff buff;
    private Account account;

    private void initializeTarget(){

    }
    private void initializeBuff(){}


    public Spell(int manaCost, int price, String spellName, Buff buff, Target target, Account account) {
        super(manaCost, price);
        this.spellName = spellName;
        this.buff = buff;
        this.target = target;
        this.account = account;
    }

    public String getSpellName() {
        return spellName;
    }

    public Target getTarget() {
        return target;
    }

    public Account getAccount() {
        return account;
    }

    public Buff getBuff() {
        return buff;
    }

}
