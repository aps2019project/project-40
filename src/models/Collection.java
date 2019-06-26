package models;


import controller.CollectionErrors;

import java.io.Serializable;
import java.util.ArrayList;

public class Collection implements Serializable {

    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Deck> decks = new ArrayList<>();
    private Deck selectedDeck = null;

    public void setSelectedDeck(Deck selectedDeck) {

        this.selectedDeck = selectedDeck;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public ArrayList<Card> getCards() {

        return cards;
    }

    public Deck getSelectedDeck() {

        return selectedDeck;
    }

    public void changeSelectedDeck(String deckName) {
        for (Deck deck : decks)
            if (deck.getDeckName().equals(deckName)) {
                setSelectedDeck(deck);
                return;
            }
    }

    public static Card findCardByCardID(ArrayList<Card> cards, String cardID) {

        for (Card card : cards) {

            if (card.getCardID().equals(cardID)) return card;
        }

        return null;        //added by amirhossein todo pay attention return null
    }

    public static Card findCardByCardName(ArrayList<Card> cards, String cardName) {

        for (Card card : cards) {

            if (card.getCardName().equals(cardName)) return card;
        }

        return null;        //added by amirhossein todo pay attention return null
    }

    public void addCardToCollection(Card card) {
        cards.add(card);
    }

    public CollectionErrors addToDeck(String cardID, String deckName) {
        Deck chosenDeck = null;
        for (Deck deck : decks)
            if (deck.getDeckName().equals(deckName)) {
                chosenDeck = deck;
                break;
            }
        Card chosenCard = null;
        for (Card card : cards)
            if (card.getCardID().equals(cardID)) {
                chosenCard = card;
                break;
            }

        if (chosenDeck == null)
            return CollectionErrors.DECK_DOES_NOT_EXIST;

        for (Card card : chosenDeck.getCards())
            if (card.getCardID().equals(cardID))
                return CollectionErrors.ALREADY_IN_DECK;

        if (chosenCard == null)
            return CollectionErrors.CARD_NOT_FOUND;

        chosenDeck.getCards().add(chosenCard);
        cards.remove(chosenCard);
        return null;
    }

    public CollectionErrors removeFromDeck(String cardID, String deckName) {
        Deck chosenDeck = null;
        for (Deck deck : decks)
            if (deck.getDeckName().equals(deckName)) {
                chosenDeck = deck;
                break;
            }
        if (chosenDeck == null)
            return CollectionErrors.DECK_DOES_NOT_EXIST;

        Card chosenCard = null;
        for (Card card : chosenDeck.getCards())
            if (card.getCardID().equals(cardID)) {
                chosenCard = card;
                break;
            }
        if (chosenCard == null)
            return CollectionErrors.CARD_NOT_FOUND;

        chosenDeck.getCards().remove(chosenCard);
        cards.add(chosenCard);
        return null;
    }

    public Deck getDeckByName(String deckName) {
        for (Deck deck : decks)
            if (deck.getDeckName().equals(deckName))
                return deck;
        return null;
    }

    public int getNumberOfCardWithName(String userName) {
        int num = 0;
        for (Card card : cards)
            if (card.getCardName().equals(userName))
                num++;

        for (Deck deck : decks)
            for (Card card : deck.getCards())
                if (card.getCardName().equals(userName))
                    num++;
        return num;
    }
}
