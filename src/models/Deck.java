package models;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Deck implements Serializable {
    private String deckName;
    private ArrayList<Card> cards = new ArrayList<>();
    private int maxCardNumber;
    private Hand hand = new Hand();

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean isDeckValidate() {
        int numOfCards = 0, numOfHero = 0, numOfItem = 0;
        for (Card card : cards)
            switch (card.getType()) {
                case SPELL:
                    numOfCards++;
                    break;
                case HERO:
                    numOfHero++;
                    break;
                case MINION:
                    numOfCards++;
                    break;
                case USABLE_ITEM:
                    numOfItem++;
            }
        return numOfCards == 20 && numOfHero == 1 && numOfItem == 1;

    }

    public Card getLastCard() {
        return null;
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
    }

    public static Deck deepClone(Deck object) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(bais);
            return (Deck) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Deck getDefaultMode1deck() {
        return null;
    }

    public static Deck getDefaultMode2deck() {
        return null;
    }

    public static Deck getDefaultMode3deck() {
        return null;
    }
}
