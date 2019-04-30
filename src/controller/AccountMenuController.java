package controller;

import models.Account;
import request.accountMenuRequest.AccountMenuRequest;
import request.accountMenuRequest.accountMenuRequestChilds.AccountLoginRequest;
import request.accountMenuRequest.accountMenuRequestChilds.AccountSimpleRequest;

public class AccountMenuController {
    private static AccountMenuController accountMenuController;
    private Account account;

    public static AccountMenuController getInstance() {

        if (accountMenuController == null) {

            accountMenuController = new AccountMenuController();
        }

        return accountMenuController;
    }

    public void accountControllerMain() {
        AccountMenuRequest accountMenuRequest = AccountMenuRequest.getInstance().getCommand();

        if (accountMenuRequest instanceof AccountSimpleRequest)
            simpleCommand((AccountSimpleRequest) accountMenuRequest);

        if (accountMenuRequest instanceof AccountLoginRequest)
            loginCommand((AccountLoginRequest) accountMenuRequest);

    }

    private void simpleCommand(AccountSimpleRequest accountSimpleRequest) {

    }

    private void loginCommand(AccountLoginRequest accountLoginRequest) {

    }
}
