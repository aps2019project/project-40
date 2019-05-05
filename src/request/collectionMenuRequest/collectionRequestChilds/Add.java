package request.collectionMenuRequest.collectionRequestChilds;

import request.collectionMenuRequest.CollectionRequest;

public class Add extends CollectionRequest {
    private String cardID, decknNme;

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getDeckName() {
        return decknNme;
    }

    public void setDecknNme(String decknNme) {
        this.decknNme = decknNme;
    }
}
