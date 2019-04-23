package models;

import java.util.ArrayList;

public class Collection {

    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Deck> decks = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private Deck selectedDeck;

    public void setSelectedDeck(Deck selectedDeck) {

        this.selectedDeck = selectedDeck;
    }

    public ArrayList<Deck> getDecks() {

        return decks;
    }

    public ArrayList<Card> getCards() {

        return cards;
    }

    public ArrayList<Item> getItems() {

        return items;
    }

    //todo get selected card
}
