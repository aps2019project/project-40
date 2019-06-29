package models.GamePlay;

import models.Card;

import java.util.ArrayList;

public class GraveYard {

    private ArrayList<Card> cards = new ArrayList<>();

    public ArrayList<Card> getCards() {

        return cards;
    }

    public void addCardToGraveYard(Card card) {

        cards.add(card);
    }
}
