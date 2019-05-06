package view;

import models.History;

import java.util.ArrayList;

public class StartMenuView {
    private static StartMenuView startMenuView;

    public static StartMenuView getInstance() {
        if (startMenuView == null)
            startMenuView = new StartMenuView();
        return startMenuView;
    }

    public void showOptions() {
        System.out.println("1. Collection");
        System.out.println("2. Shop");
        System.out.println("3. Battle");
        System.out.println("4. Exit");
        System.out.println("5. Help");
        System.out.println("6. Save");
        System.out.println("7. Match histories");
        System.out.println("8. LoginMenu");
    }

    public void showHistory(ArrayList<History> histories) {
        for (History history : histories)
            System.out.println(history.getOponnentUserName() + " " + history.getYourStatus() + " " + history.getDifference() + " ago");
    }
}
