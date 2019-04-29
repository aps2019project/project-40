package view.battleView;

import java.util.ArrayList;

public class ShowCardsBattleView extends BattleView {

    private ArrayList<String> cards = new ArrayList<>();

    public ArrayList<String> getCards() {

        return cards;
    }

    public void setCards(ArrayList<String> cards) {

        this.cards = cards;
    }
}
