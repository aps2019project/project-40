package view;

public class StartMenuView  {
    private static StartMenuView startMenuView;

    public static StartMenuView getInstance() {
        if (startMenuView == null)
            startMenuView = new StartMenuView();
        return startMenuView;
    }
    public void showOptions(){
        System.out.println("1. Collection");
        System.out.println("2. Shop");
        System.out.println("3. Battle");
        System.out.println("4. Exit");
        System.out.println("5. Help");
    }
}
