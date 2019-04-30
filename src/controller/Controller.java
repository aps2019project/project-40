package controller;

import models.Account;

import java.util.Stack;

public class Controller {
    private Stack orderOfMenu;
    private Account account;
    private boolean isProgramEnded = false;

    public void setAccount(Account account) {
        this.account = account;
    }

    public void addStack(Object object) {
        orderOfMenu.push(object);
    }

    public void endProgram(){
        isProgramEnded=true;
    }

    public void mainController() {
        while (!isProgramEnded) {
            Object currentMenu = orderOfMenu.peek();

            if (currentMenu instanceof ShopController)
                ShopController.getInstance().shopControllerMain();

            if (currentMenu instanceof AccountMenuController)
                AccountMenuController.getInstance().accountControllerMain();

            if (currentMenu instanceof BattleController)
                BattleController.getInstance().battleControllerMain();

            if (currentMenu instanceof CollectionController)
                CollectionController.getInstance().collectionControllerMain();
        }
    }

}
