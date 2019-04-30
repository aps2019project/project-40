import controller.AccountMenuController;
import controller.Controller;
import models.LoginMenu;

public class Main {
    public static void main(String[] args) {
        Controller controller=new Controller();
        controller.addStack(AccountMenuController.getInstance());
        controller.mainController();
    }
}
