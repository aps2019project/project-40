package models;

public class Card {
    private int manaCost;
    private int price;

    public int getPrice() {
        return price;
    }

    public int getManaCost() {
        return manaCost;
    }

    public Card(int manaCost, int price){
        this.manaCost = manaCost;
        this.price = price;
    }
}
