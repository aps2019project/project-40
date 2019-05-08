package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Hand implements Serializable {

    private ArrayList<Card> deck = new ArrayList<>(); //todo add main deck to this deck
    private ArrayList<Card> handCards = new ArrayList<>();
    private Card reserveCard;
    private Card selectedCard;
    private ArrayList<Card> collectiblesItem = new ArrayList<>();
    private final int MAX_HANDS_CARD = 5;

    public ArrayList<Card> getDeck() {

        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {

        this.deck = deck;
    }

    public Card getReserveCard() {

        return reserveCard;
    }

    public ArrayList<Card> getHandCards() {

        return handCards;
    }

    public ArrayList<Card> getCollectiblesItem() {

        return collectiblesItem;
    }

    public void setItemToCollectiblesItem(Card card) {

        collectiblesItem.add(card);
    }

    public void fillHandEmptyPlace() {

        if (isThereEmptyPlace()) {
            if (reserveCard != null) handCards.add(reserveCard);
            try {
                reserveCard = deck.get(0);
                deck.remove(0);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
    }

    private boolean isThereEmptyPlace() {

        return handCards.size() <= MAX_HANDS_CARD;
    }
}
