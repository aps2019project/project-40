package models;

import java.io.*;
import java.util.ArrayList;

public class Card implements Serializable, Cloneable {
    private int manaCost;
    private int price;
    private int sellCost;
    private String cardID;
    private String cardName;
    private String team;
    private ArrayList<Spell> spells = new ArrayList<>();
    private String description;
    private CardType type;
    private Cell cell;

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

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

    public Card() {

    }

    public Card(int manaCost, int price, String cardName, ArrayList<Spell> spells,
                String description, CardType type, Cell cell) {
        this.manaCost = manaCost;
        this.price = price;
        this.cardName = cardName;
        this.spells = spells;
        this.description = description;
        this.type = type;
        this.cell = cell;
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

    public static Card deepClone(Card object) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(bais);
            return (Card) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setSellCost() {
        if (price > 0) {
            sellCost = 4 * price / 10;
        }
    }

    public void addSpell(Spell spell) {
        spells.add(spell);
    }


    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setType(CardType type) {
        this.type = type;
    }
}

