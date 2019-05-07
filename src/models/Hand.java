package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Hand implements Serializable {

    private ArrayList<Card> cards = new ArrayList<>();
    private Card reserveCard;
    private Card selectedCard;
    private ArrayList<Card> collectiblesItem = new ArrayList<>();
    private final int MAX_HANDS_CARD = 5;

    public Card getReserveCard() {

        return reserveCard;
    }

    public ArrayList<Card> getCards() {

        return cards;
    }

    public ArrayList<Card> getCollectiblesItem() {

        return collectiblesItem;
    }

    public void setItemToCollectiblesItem(Card card) {

        collectiblesItem.add(card);
    }

    private boolean isThereEmptyPlace() {

        return cards.size() < MAX_HANDS_CARD;
    }
}
