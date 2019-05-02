package controller;

import models.BattleMenu;
import request.startMenuController.StartMenuRequest;
import request.startMenuController.startMenuRequestChilds.StartMenuOption;
import view.StartMenuView;

public class StartMenuController {
    private static StartMenuController startMenuController;

    public static StartMenuController getInstance() {
        if (startMenuController == null)
            startMenuController = new StartMenuController();
        return startMenuController;
    }

    public void startMenuControllerMain() {
        StartMenuView startMenuView=StartMenuView.getInstance();
        startMenuView.showOptions();
        StartMenuRequest startMenuRequest=StartMenuRequest.getInstance().getCommand();
        if (startMenuRequest==null)
            return;
        if (startMenuRequest instanceof StartMenuOption)
            enterCommand((StartMenuOption) startMenuRequest);
    }

    private void enterCommand(StartMenuOption startMenuOption){
        switch (startMenuOption.getStartMenuOptionList()){
            case COLLECTION:
                Controller.getInstance().addStack(CollectionController.getInstance());
                break;
            case SHOP:
                Controller.getInstance().addStack(ShopController.getInstance());
                break;
            case BATTLE:
                Controller.getInstance().addStack(BattleMenuController.getInstance());
                break;
            case EXIT:
                Controller.getInstance().endProgram();
                break;
            case HELP:
                break;
        }
    }

}
