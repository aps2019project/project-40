package models;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards = new ArrayList<>();
    //private Hero hero;
    private int maxCardNumber;
    private Item item;

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
