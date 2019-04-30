package request.startMenuController.startMenuRequestChilds;

import request.startMenuController.StartMenuRequest;

public class StartMenuOption extends StartMenuRequest {
    private StartMenuOptionList startMenuOptionList;

    public StartMenuOptionList getStartMenuOptionList() {
        return startMenuOptionList;
    }

    public void setStartMenuOptionList(StartMenuOptionList startMenuOptionList) {
        this.startMenuOptionList = startMenuOptionList;
    }
}
