package controller;

import models.Account;
import models.Collection;
import request.collectionMenuRequest.CollectionRequest;
import request.collectionMenuRequest.collectionRequestChilds.Add;
import request.collectionMenuRequest.collectionRequestChilds.DeckCommand;
import request.collectionMenuRequest.collectionRequestChilds.Remove;
import request.collectionMenuRequest.collectionRequestChilds.SimpleRequest;
import view.collectionMenuView.CollectionMenuView;

public class CollectionController {
    private static CollectionController collectionController;
    private CollectionMenuView collectionMenuView = CollectionMenuView.getInstance();
    private boolean isShopClosed = false;
    private Account account;
    private Collection collection;

    public static CollectionController getInstance() {

        if (collectionController == null) {

            collectionController = new CollectionController();
        }

        return collectionController;
    }

    public void collectionControllerMain() {
        account = Controller.getInstance().getAccount();
        collection = account.getCollection();
        while (!isShopClosed) {
            CollectionRequest collectionRequest = CollectionRequest.getInstance().getCollectionOptionList();

            if (collectionRequest instanceof SimpleRequest)
                simpleRequest((SimpleRequest) collectionRequest);

            if (collectionRequest instanceof Add)
                add((Add) collectionRequest);

            if (collectionRequest instanceof Remove)
                remove((Remove) collectionRequest);

            if (collectionRequest instanceof DeckCommand)
                handelDeckCommand((DeckCommand) collectionRequest);

        }
    }

    public void simpleRequest(SimpleRequest simpleRequest) {
        switch (simpleRequest.getMessage()) {
            case EXIT:
                isShopClosed = true;
                return;
            case SHOW:
                break;
            case HELP:
                collectionMenuView.showHelp();
                break;
            case SAVE:
                Account.save(account);
                break;
            case SHOW_ALL_DECKS:
                showAllDecks();
                break;
        }
    }

    public void add(Add addRequst) {
        String cardID = addRequst.getCardID();
        String deckName = addRequst.getDeckName();
        CollectionErrors collectionErrors;

        collectionErrors = collection.addToDeck(cardID,deckName);
        if (collectionErrors != null)
            collectionMenuView.showError(collectionErrors);

    }

    public void handelDeckCommand(DeckCommand deckCommand) {
        if (!collection.isDeckNameValid(deckCommand.getDeckName())) {
            collectionMenuView.showError(CollectionErrors.CARD_NOT_FOUND);
            return;
        }

        switch (deckCommand.getType()) {
            case SHOW_DECK:

                break;
            case SELECT_DECK:

                break;
            case DELETE_DECK:
                break;
            case CREATE_DECK:
                break;
            case VALIDATE_DECK:
                break;
        }
    }

    public void remove(Remove removeRequest) {
        String cardID = removeRequest.getCardID();
        String deckName = removeRequest.getDeckName();
        CollectionErrors collectionErrors;

        collectionErrors = collection.removeFromDeck(cardID,deckName);
        if (collectionErrors != null)
            collectionMenuView.showError(collectionErrors);
    }

    public void showAllDecks() {

    }


}
