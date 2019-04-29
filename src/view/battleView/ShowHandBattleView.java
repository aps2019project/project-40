package view.battleView;

import models.Account;

import java.util.ArrayList;

public class ShowHandBattleView extends BattleView {

    private ArrayList<String> handCardsName = new ArrayList<>();
    private String reserveCardName;

    public ArrayList<String> getHandCardsName() {

        return handCardsName;
    }

    public void setHandCardsName(ArrayList<String> handCardsName) {

        this.handCardsName = handCardsName;
    }

    public String getReserveCardName() {

        return reserveCardName;
    }

    public void setReserveCardName(String reserveCardName) {

        this.reserveCardName = reserveCardName;
    }
}
