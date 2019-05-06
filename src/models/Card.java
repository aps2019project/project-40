package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Card implements Serializable {
    private int manaCost;
    private int price;
    private String cardID;
    private String cardName;
    private String team;
    private static int numberOfInstances;
    private ArrayList<Spell> spells;
    private String description;
    private CardType type;
    private Cell cell;

    public Cell getCell() {

        return cell;
    }

    public void setCell(Cell cell) {

        this.cell = cell;
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
}
