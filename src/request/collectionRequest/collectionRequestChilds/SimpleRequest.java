package request.collectionRequest.collectionRequestChilds;

import request.collectionRequest.CollectionRequest;

public class SimpleRequest extends CollectionRequest {
    private CollectionOptionList message;

    public CollectionOptionList getMessage() {
        return message;
    }

    public void setMessage(CollectionOptionList message) {
        this.message = message;
    }
}
