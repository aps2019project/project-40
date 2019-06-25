package ui.battleUI.battleRequests;

public class EnterGraveYardRequest extends BattleRequest {

    private String cardID;
    private boolean isForShowInfo, isForShowCards, isForExit;

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

    public boolean isForExit() {

        return isForExit;
    }

    public void setForExit(boolean forExit) {

        isForExit = forExit;
    }
}
