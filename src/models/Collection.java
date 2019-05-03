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

    public Deck getSelectedDeck() {

        return selectedDeck;
    }

    public static Card findCardByCardID(ArrayList<Card> cards, String cardID) {

        for (Card card : cards) {

            if (card.getCardID().equals(cardID)) return card;
        }

        return null;        //added by amirhossein todo pay attention return null
    }

    public static Card findCardByCardName(ArrayList<Card> cards, String cardName) {

        for (Card card : cards) {

            if (card.getCardID().equals(cardName)) return card;
        }

        return null;        //added by amirhossein todo pay attention return null
    }
}
