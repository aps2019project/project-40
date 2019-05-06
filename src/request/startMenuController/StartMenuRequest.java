package request.startMenuController;

import request.Request;
import request.startMenuController.startMenuRequestChilds.StartMenuOption;
import request.startMenuController.startMenuRequestChilds.StartMenuOptionList;

public class StartMenuRequest extends Request {
    private static StartMenuRequest startMenuRequest;

    public static StartMenuRequest getInstance() {
        if (startMenuRequest == null)
            startMenuRequest = new StartMenuRequest();
        return startMenuRequest;
    }

    public StartMenuRequest getCommand() {
        String command = scanner.nextLine().toLowerCase().trim();
        StartMenuOption startMenuOption = new StartMenuOption();
        if (command.indexOf("exit") >= 0)
            startMenuOption.setStartMenuOptionList(StartMenuOptionList.EXIT);
        else if (command.indexOf("help") >= 0)
            startMenuOption.setStartMenuOptionList(StartMenuOptionList.HELP);
        else if (command.indexOf("save") >= 0)
            startMenuOption.setStartMenuOptionList(StartMenuOptionList.SAVE);
        else if (command.indexOf("match history") >= 0)
            startMenuOption.setStartMenuOptionList(StartMenuOptionList.MSTCH_HISTORY);
        else if (command.indexOf("enter") >= 0) {
            String choice = command.split("\\s")[1];
            switch (choice) {
                case "collection":
                    startMenuOption.setStartMenuOptionList(StartMenuOptionList.COLLECTION);
                    break;
                case "battle":
                    startMenuOption.setStartMenuOptionList(StartMenuOptionList.BATTLE);
                    break;
                case "shop":
                    startMenuOption.setStartMenuOptionList(StartMenuOptionList.SHOP);
                    break;
                case "loginmenu":
                    startMenuOption.setStartMenuOptionList(StartMenuOptionList.LOGIN_MENU);
                    break;
            }
        } else
            return null;
        return startMenuOption;
    }
}
