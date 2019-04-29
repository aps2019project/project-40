package request.collectionRequest.collectionRequestChilds;

import request.collectionRequest.CollectionRequest;

public class DeckCommand extends CollectionRequest {
    private String deckName;

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }
}
