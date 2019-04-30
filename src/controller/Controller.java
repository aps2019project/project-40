package controller;

import models.Account;

import java.util.Stack;

public class Controller {
    private static Controller controller;
    private static Stack orderOfMenu=new Stack();
    private Account account;
    private boolean isProgramEnded = false;

    public static Controller getInstance(){
        if (controller==null)
            controller=new Controller();
        return controller;
    }

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

            if (currentMenu instanceof StartMenuController)
                StartMenuController.getInstance().startMenuControllerMain();
        }
    }

}
