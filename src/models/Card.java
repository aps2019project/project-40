package models;

import java.util.ArrayList;

public class Card {
    private int manaCost;
    private int price;
    private String cardID;
    private int numberOfInstances;
    private String cardName;
    private ArrayList<Spell> spells;
    private String description;
    private CardType type;
    public int getPrice() {

        return price;
    }

    public String getCardID() {
        return cardID;
    }

    public int getManaCost() {

        return manaCost;
    }
    Card(){
        numberOfInstances += 1;
    }
    Card(int manaCost, int price, String cardName, ArrayList<Spell> spells, String description, CardType type){
        this.manaCost = manaCost;
        this.price = price;
        this.cardName = cardName;
        this.spells = spells;
        this.description = description;
        this.type = type;
    }

    public String getCardName() {
        return cardName;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public String getDescription() {
        return description;
    }

    public CardType getType() {
        return type;
    }

    @Override
    public String toString() {
        return ""+ manaCost + "\n" + description + "\n" + type + "\n" + cardName + "\n" + price;
    }
}
