package controller;

import models.Account;
import models.Collection;
import request.shopMenuRequest.ShopRequest;
import request.shopMenuRequest.shopRequestChilds.ShopRequestVariable;
import request.shopMenuRequest.shopRequestChilds.ShopRequestWithOutVariable;
import view.shopMenuView.ShopMenuView;

public class ShopController {
    private static ShopController shopController;
    private boolean isShopClosed = false;
    private Account account;
    private static Collection shopCollection = initializeShopCollection();
    private ShopMenuView shopMenuView = ShopMenuView.getInstance();

    public static ShopController getInstance() {

        if (shopController == null) {

            shopController = new ShopController();
        }

        return shopController;
    }

    public void shopControllerMain() {
        account = Controller.getInstance().getAccount();
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

                break;

            case SELL:

                break;

            case SEARCH:

                break;

            case SEARCH_COLLECTION:

                break;

            default:
        }

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
                return;
            case SHOW_COLLECTION:

        }
    }

    private static Collection initializeShopCollection() {
        return null;
    }
}
