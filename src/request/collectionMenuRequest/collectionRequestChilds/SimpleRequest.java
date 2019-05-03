package request.collectionMenuRequest.collectionRequestChilds;

import request.collectionMenuRequest.CollectionRequest;

public class SimpleRequest extends CollectionRequest {
    private CollectionOptionList message;

    public CollectionOptionList getMessage() {
        return message;
    }

    public void setMessage(CollectionOptionList message) {
        this.message = message;
    }
}
