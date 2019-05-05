package controller;

import models.*;
import request.shopMenuRequest.ShopRequest;
import request.shopMenuRequest.shopRequestChilds.ShopRequestVariable;
import request.shopMenuRequest.shopRequestChilds.ShopRequestWithOutVariable;
import view.shopMenuView.ShopError;
import view.shopMenuView.ShopMenuView;

import java.util.ArrayList;

public class ShopController {
    private static ShopController shopController;
    private boolean isShopClosed = false;
    private Account account;
    private static Collection shopCollection = initializeShopCollection();
    private Collection myCollection;
    private ShopMenuView shopMenuView = ShopMenuView.getInstance();

    public static ShopController getInstance() {

        if (shopController == null) {

            shopController = new ShopController();
        }

        return shopController;
    }

    public void shopControllerMain() {
        account = Controller.getInstance().getAccount();
        myCollection = account.getCollection();
        while (!isShopClosed) {
            ShopRequest shopRequest = ShopRequest.getInstance().getCommand();

            if (shopRequest instanceof ShopRequestVariable)
                handelShopRequestVariable((ShopRequestVariable) shopRequest);

            else if (shopRequest instanceof ShopRequestWithOutVariable)
                handelShopRequestWithOutVariable((ShopRequestWithOutVariable) shopRequest);

        }
        Controller.getInstance().addStack(StartMenuController.getInstance());
    }

    public void handelShopRequestVariable(ShopRequestVariable shopRequestVariable) {
        switch (shopRequestVariable.getCommandType()) {
            case BUY:
                buy(shopRequestVariable.getNameOrId());
                break;

            case SELL:
                sell(shopRequestVariable.getNameOrId());
                break;

            case SEARCH:
                searchCollection(shopRequestVariable.getNameOrId(), shopCollection);
                break;

            case SEARCH_COLLECTION:
                searchCollection(shopRequestVariable.getNameOrId(), myCollection);
                break;
        }
    }

    private boolean checkBuyItem(ArrayList<Card> cards) {
        int numOfItemInCollection=0;
        return true;
    }

    public void buy(String cardName) {
        for (Card card : shopCollection.getCards())
            if (card.getCardName().equals(cardName)) {
                if (card.getType().equals(CardType.USABLE_ITEM) && checkBuyItem(myCollection.getCards())){
                    shopMenuView.showError(ShopError.ALREADY_3_ITEM);
                    return;
                }
                if (account.getMoney() - card.getPrice() >= 0){
                    account.setMoney(account.getMoney() - card.getPrice());
                    shopCollection.getCards().remove(card);
                    myCollection.getCards().add(card);
                    shopMenuView.showError(ShopError.SUCSSES);
                }
                else
                    shopMenuView.showError(ShopError.NOT_ENOUGH_MONEY);
                return;
            }
        shopMenuView.showError(ShopError.CARD_NOT_FOUND);
    }

    public void sell(String cardID) {
        for (Card card : myCollection.getCards())
            if (card.getCardID().equals(cardID)) {
                myCollection.getCards().remove(card);
                account.setMoney(account.getMoney() + card.getSellCost());
                return;
            }
        shopMenuView.showError(ShopError.CARD_NOT_FOUND);
    }

    public void searchCollection(String cardName, Collection collection) {
        ArrayList<String> cardIDs = new ArrayList<>();
        for (Card card : collection.getCards()) {
            if (card.getCardName().equals(cardName))
                cardIDs.add(card.getCardID());
        }

        if (cardIDs.size() == 0)
            shopMenuView.showError(ShopError.CARD_NOT_FOUND);

        else
            shopMenuView.showIDs(cardIDs);

    }

    public void handelShopRequestWithOutVariable(ShopRequestWithOutVariable shopRequestWithOutVariable) {
        switch (shopRequestWithOutVariable.getShopSimpleRequestList()) {
            case HELP:
                shopMenuView.showHelp();
                break;
            case EXIT:
                isShopClosed = true;
                return;
            case SHOW:
                shopMenuView.show(shopCollection.getCards(), true);
                return;
            case SHOW_COLLECTION:
                shopMenuView.show(myCollection.getCards(), false);
        }
    }

    private static Collection initializeShopCollection() {
        Collection collection = new Collection();
        JsonToCard.moveToCollection(collection);
        return collection;
    }
}
