package controller;

public class StartMenuController {
    private static StartMenuController startMenuController;

    public static StartMenuController getInstance() {
        if (startMenuController == null)
            startMenuController = new StartMenuController();
        return startMenuController;
    }

    public void startMenuControllerMain() {

    }
}
