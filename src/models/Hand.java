package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Hand  implements Serializable {

    private ArrayList<Card> cards = new ArrayList<>();
    private Card reserveCard;
    private Card selectedCard;
    private final int MAX_HANDS_CARD = 5;

    public Card getReserveCard() {

        return reserveCard;
    }

    public ArrayList<Card> getCards() {

        return cards;
    }

    private void fillEmptyPlace(Card card) {

        if (isThereEmptyPlace()) {


        }
    }

    private boolean isThereEmptyPlace() {

        if (cards.size() < MAX_HANDS_CARD) return true;
        return false;
    }
}
