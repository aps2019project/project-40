package command.battleRequest.BattleRequestChilds;

import command.battleRequest.BattleRequest;

public class ShowCardInfoRequest extends BattleRequest {

    private String cardID;

    public String getCardID() {

        return cardID;
    }

    public void setCardID(String cardID) {

        this.cardID = cardID;
    }
}
