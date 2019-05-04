package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Deck  implements Serializable {
    private String deckName;
    private ArrayList<Card> cards = new ArrayList<>();
    private int maxCardNumber;
    private Item item;

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public ArrayList<Card> getCards() {

        return cards;
    }

    public boolean isDeckValidate(){
        return true;
    }

    public Card getLastCard(){
        return  null;
    }

    public void shuffleCards(){
        Collections.shuffle(cards);
    }
}
