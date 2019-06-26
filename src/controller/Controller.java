package controller;

import javafx.stage.Stage;
import models.Account;

public class Controller {
    private static Controller controller;
    private Account account = null;
    public ShopController shopController = null;
    public CollectionController collectionController = null;
    public static Stage stage;
    public double x, y;


    public static Controller getInstance() {
        if (controller == null)
            controller = new Controller();
        return controller;
    }

    private Controller() {
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
