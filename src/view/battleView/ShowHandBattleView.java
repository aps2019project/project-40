package view.battleView;

import java.util.ArrayList;

public class ShowHandBattleView extends BattleView {

    private ArrayList<ShowCardInfoBattleView> handCards = new ArrayList<>();
    private ShowCardInfoBattleView reserveCard;

    public ArrayList<ShowCardInfoBattleView> getHandCards() {

        return handCards;
    }

    public void setHandCards(ArrayList<ShowCardInfoBattleView> handCardsName) {

        //todo
    }

    public ShowCardInfoBattleView getReserveCard() {

        return reserveCard;
    }

    public void setReserveCard(ShowCardInfoBattleView reserveCardName) {

        this.reserveCard = reserveCardName;
    }
}
