import controller.AccountMenuController;
import controller.Controller;
import models.Account;
import models.Card;
import models.LoginMenu;


public class Main {

    public static void main(String[] args) {
        Controller controller = Controller.getInstance();
        controller.addStack(AccountMenuController.getInstance());
        controller.mainController();
    }
}
