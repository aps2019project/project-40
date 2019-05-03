import controller.AccountMenuController;
import controller.Controller;
import models.MatchType;
import view.battleMenuView.battleMenuViewChilds.BattleMenuError;

public class Main {

    public static void main(String[] args) {
        Controller controller=Controller.getInstance();
        controller.addStack(AccountMenuController.getInstance());
        controller.mainController();
    }
}
