package models;

public class Card {
    private String cardName, cardID;
    private int manaCost, price;

    public String getCardName() {

        return cardName;
    }

    public String getCardID() {

        return cardID;
    }

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
