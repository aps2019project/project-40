package models;

import view.battleView.BattleLog;

import java.io.Serializable;
import java.util.ArrayList;

public class Hand implements Serializable {

    private ArrayList<Card> deck = new ArrayList<>();
    private ArrayList<Card> handCards = new ArrayList<>();
    private Card hero;
    private Card reserveCard;
    private Card selectedCard;
    private ArrayList<Card> collectedItems = new ArrayList<>();

    public ArrayList<Card> getDeck() {

        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {

        this.deck = deck;
    }

    public Card getHero() {
        return hero;
    }

    public Card getReserveCard() {

        return reserveCard;
    }

    public ArrayList<Card> getHandCards() {

        return handCards;
    }

    public ArrayList<Card> getCollectedItems() {

        return collectedItems;
    }

    public void setItemToCollectiblesItem(Card card) {

        collectedItems.add(card);
    }

    public void initializeHand(Deck accountDeck) {

        Deck newDeck = Deck.deepClone(accountDeck);
        newDeck.shuffleCards();
        deck = newDeck.getCards();

        for (Card card : deck)
            if (card.getType().equals(CardType.HERO))
                hero = card;

        deck.remove(hero);
        handCards.add(deck.get(0));
        handCards.add(deck.get(1));
        handCards.add(deck.get(2));
        handCards.add(deck.get(3));
        handCards.add(deck.get(4));
        reserveCard = deck.get(5);

        for (Card card : handCards)
            deck.remove(card);

        deck.remove(reserveCard);
    }

    public void fillHandEmptyPlace() {

        if (isThereEmptyPlace()) {
            System.out.println("in fillHandEmptyPlace size of hand cards " + handCards.size());
            if (reserveCard != null) handCards.add(reserveCard);

            try {
                reserveCard = deck.get(0);
                deck.remove(0);
            } catch (ArrayIndexOutOfBoundsException e) {
                BattleLog.errorDeckOut();
                reserveCard = null;
            }
        }
    }

    private boolean isThereEmptyPlace() {
        return handCards.size() < 5;   //maximum of the hand's card
    }

    public void removeFromHand(Card card) {

        handCards.remove(card);
    }
}

