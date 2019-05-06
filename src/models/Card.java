package models;

import java.io.*;
import java.util.ArrayList;

public class Card implements Serializable, Cloneable {
    private int manaCost;
    private int price;
    private String cardID;
    private String cardName;
    private int sellCost;
    private String team;
    private static int numberOfInstances = 0;
    private ArrayList<Spell> spells = new ArrayList<>();
    private String description;
    private CardType type;

    public int getSellCost() {
        return sellCost;
    }

    public int getPrice() {
        return price;
    }

    public String getCardID() {
        return cardID;
    }

    public int getManaCost() {
        return manaCost;
    }

    Card() {
        numberOfInstances += 1;
    }

    Card(int manaCost, int price, String cardName, ArrayList<Spell> spells, String description, CardType type) {
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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "" + manaCost + "\n" + description + "\n" + type + "\n" + cardName + "\n" + price;
    }

    public static  Card deepClone(Card object){
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(bais);
            return (Card) objectInputStream.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

