package controller;

import models.Account;
import models.Card;
import models.Collection;
import models.Deck;
import request.collectionMenuRequest.CollectionRequest;
import request.collectionMenuRequest.collectionRequestChilds.Add;
import request.collectionMenuRequest.collectionRequestChilds.DeckCommand;
import request.collectionMenuRequest.collectionRequestChilds.Remove;
import request.collectionMenuRequest.collectionRequestChilds.SimpleRequest;
import view.collectionMenuView.CollectionMenuView;

public class CollectionController {
    private static CollectionController collectionController;
    private CollectionMenuView collectionMenuView = CollectionMenuView.getInstance();
    private boolean isCollectionClosed = false;
    private Account account;
    private Collection collection;

    public static CollectionController getInstance() {

        if (collectionController == null) {

            collectionController = new CollectionController();
        }

        return collectionController;
    }

    public void collectionControllerMain() {
        isCollectionClosed = false;
        account = Controller.getInstance().getAccount();
        collection = account.getCollection();
        while (!isCollectionClosed) {
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
        Controller.getInstance().addStack(StartMenuController.getInstance());
    }

    public void simpleRequest(SimpleRequest simpleRequest) {
        switch (simpleRequest.getMessage()) {
            case EXIT:
                isCollectionClosed = true;
                return;
            case SHOW:
                collectionMenuView.show(account.getCollection().getCards(), true);
                break;
            case HELP:
                collectionMenuView.showHelp();
                break;
            case SAVE:
                Account.save(account);
                break;
            case SHOW_ALL_DECKS:
                collectionMenuView.showAllDecks(account.getCollection());
                break;
        }
    }

    public void add(Add addRequst) {
        String cardID = addRequst.getCardID();
        String deckName = addRequst.getDeckName();
        CollectionErrors collectionErrors;

        collectionErrors = collection.addToDeck(cardID, deckName);
        if (collectionErrors != null)
            collectionMenuView.showError(collectionErrors);

    }

    public void handelDeckCommand(DeckCommand deckCommand) {
        Deck deck = collection.getDeckByName(deckCommand.getDeckName());
        switch (deckCommand.getType()) {
            case SHOW_DECK:
                collectionMenuView.showDeck(deck);
                break;
            case SELECT_DECK:
                selectDeck(deck);
                break;
            case DELETE_DECK:
                deleteDeck(deck);
                break;
            case CREATE_DECK:
                createDeck(deck, deckCommand.getDeckName());
                break;
            case VALIDATE_DECK:
                validateDeck(deck);
                break;
        }
    }

    public void deleteDeck(Deck deck) {
        if (deck == null) {
            collectionMenuView.showError(CollectionErrors.DECK_DOES_NOT_EXIST);
            return;
        }
        collection.getDecks().remove(deck);
        for (Card card : deck.getCards())
            collection.getCards().add(card);
    }

    public void selectDeck(Deck deck) {
        if (deck == null) {
            collectionMenuView.showError(CollectionErrors.DECK_DOES_NOT_EXIST);
            return;
        }

        if (deck.isDeckValidate())
            collection.setSelectedDeck(deck);
        else
            collectionMenuView.showError(CollectionErrors.DECK_IS_NOT_VALID);
    }

    public void createDeck(Deck deck, String deckName) {
        if (deck != null) {
            collectionMenuView.showError(CollectionErrors.ALREADY_A_DECK_WITH_THIS_USERNAME);
            return;
        }

        deck = new Deck();
        deck.setDeckName(deckName);

    }

    public void validateDeck(Deck deck) {
        if (deck == null) {
            collectionMenuView.showError(CollectionErrors.DECK_DOES_NOT_EXIST);
            return;
        }

        if (deck.isDeckValidate())
            collectionMenuView.showError(CollectionErrors.DECK_IS_NOT_VALID);
    }


    public void remove(Remove removeRequest) {
        String cardID = removeRequest.getCardID();
        String deckName = removeRequest.getDeckName();
        CollectionErrors collectionErrors;

        collectionErrors = collection.removeFromDeck(cardID, deckName);
        if (collectionErrors != null)
            collectionMenuView.showError(collectionErrors);
    }

}
