package request.collectionMenuRequest.collectionRequestChilds;

import request.collectionMenuRequest.CollectionRequest;

public class DeckCommand extends CollectionRequest {
    private String deckName;
    private CollectionOptionList collectionOptionList;

    public void setType(CollectionOptionList collectionOptionList) {
        this.collectionOptionList = collectionOptionList;
    }

    public CollectionOptionList getType() {
        return collectionOptionList;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }
}
