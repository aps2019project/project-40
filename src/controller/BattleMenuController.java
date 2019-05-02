package controller;

import models.Account;
import request.battleMenuRequest.BattleMenuRequest;
import view.battleMenuView.BattleMenuView;

public class BattleMenuController {
    private static BattleMenuController battleMenuController;
    private BattleMenuRequest battleMenuRequest = BattleMenuRequest.getInstance();
    private BattleMenuView battleMenuView = BattleMenuView.getInstance();
    private Account account;

    public static BattleMenuController getInstance() {

        if (battleMenuController == null)
            battleMenuController = new BattleMenuController();

        return battleMenuController;
    }

    public void battleMenuControllerMain(){
        account=Controller.getInstance().getAccount();
        if (checkValidateDeck()){

        }
    }

    public boolean checkValidateDeck(){
        return account.getCollection().getSelectedDeck().isDeckValidate();
    }
}
