package request.battleRequest.BattleRequestChilds;

import request.battleRequest.BattleRequest;

public class ShowCardInfoRequest extends BattleRequest {

    private String cardID;

    public String getCardID() {

        return cardID;
    }

    public void setCardID(String cardID) {

        this.cardID = cardID;
    }
}
