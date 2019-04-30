package command.battleRequest.BattleRequestChilds;

import command.battleRequest.BattleRequest;

public class EnterGraveYardRequest extends BattleRequest {

    private String cardID;
    private boolean isForShowInfo, isForShowCards, isForHelp;

    public String getCardID() {

        return cardID;
    }

    public void setCardID(String cardID) {

        this.cardID = cardID;
    }

    public boolean isForShowInfo() {

        return isForShowInfo;
    }

    public void setForShowInfo(boolean forShowInfo) {

        isForShowInfo = forShowInfo;
    }

    public boolean isForShowCards() {

        return isForShowCards;
    }

    public void setForShowCards(boolean forShowCards) {

        isForShowCards = forShowCards;
    }

    public boolean isForHelp() {

        return isForHelp;
    }

    public void setForHelp(boolean forHelp) {

        isForHelp = forHelp;
    }
}
