package controller;

import models.Account;
import models.LoginMenu;
import request.accountMenuRequest.AccountError;
import request.accountMenuRequest.AccountMenuRequest;
import request.accountMenuRequest.accountMenuRequestChilds.AccountCreate;
import request.accountMenuRequest.accountMenuRequestChilds.AccountLoginRequest;
import request.accountMenuRequest.accountMenuRequestChilds.AccountSimpleRequest;
import view.AccountMenuView;

import java.util.Collections;
import java.util.Comparator;

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

        if (accountMenuRequest instanceof AccountCreate)
            createCommand((AccountCreate) accountMenuRequest);

        Controller.getInstance().setAccount(account);
    }

    private void simpleCommand(AccountSimpleRequest accountSimpleRequest) {
        switch (accountSimpleRequest.getAccountOptionList()) {
            case SHOW_LEADERBOARD:
                showLeaderboard();
                break;
            case SAVE:
                break;
            case LOGOUT:
                account = null;
                break;
            case HELP:
                AccountMenuView accountMenuView = new AccountMenuView();
                accountMenuView.showHelp();
                break;
        }
    }

    private void loginCommand(AccountLoginRequest accountLoginRequest) {
        String userName = accountLoginRequest.getLine();
        if (LoginMenu.getInstance().checkIfAccountExist(userName)) {
            account = LoginMenu.getInstance().login(userName, AccountMenuRequest.getInstance().getPassWord());

            if (account == null)
                AccountMenuView.getInstance().showError(AccountError.PASSWORD_IS_INCORRECT);
            else
                Controller.getInstance().addStack(StartMenuController.getInstance());

        } else
            AccountMenuView.getInstance().showError(AccountError.USERNAME_DOESENT_EXIST);
    }

    private void createCommand(AccountCreate accountCreate) {
        String userName = accountCreate.getLine();
        if (LoginMenu.getInstance().checkIfAccountExist(userName)) {
            AccountMenuView.getInstance().showError(AccountError.USERNAME_ALREADY_EXIST);
            return;
        }
        Controller.getInstance().addStack(StartMenuController.getInstance());
        account=LoginMenu.getInstance().createAccount(userName, AccountMenuRequest.getInstance().getPassWord());

    }

    private void showLeaderboard() {
        if (LoginMenu.getUsers().size() > 0)
            Collections.sort(LoginMenu.getUsers(), new Comparator<Account>() {
                public int compare(Account account1, Account account2) {
                    return account1.getWinsNumber() - account2.getWinsNumber();
                }
            });
        AccountMenuView.getInstance().showLeaderBoard(LoginMenu.getUsers());
    }
}
