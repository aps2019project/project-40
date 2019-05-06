package view;

import models.Account;
import request.accountMenuRequest.AccountError;

import java.util.ArrayList;

public class AccountMenuView {
    private static AccountMenuView accountMenuView;

    public static AccountMenuView getInstance() {
        if (accountMenuView == null)
            accountMenuView = new AccountMenuView();
        return accountMenuView;
    }

    public void showLeaderBoard(ArrayList<Account> users) {
        for (int i = 0; i < users.size(); i++)
            System.out.println((i + 1) + "-UserName : " + users.get(i).getUserName() + " - Wins : " + users.get(i).getWinsNumber());
    }

    public void showHelp() {
        System.out.println("create account [userName]");
        System.out.println("login [userName]");
        System.out.println("show leaderboard");
        System.out.println("save");
        System.out.println("logout");
        System.out.println("help");
    }

    public void showError(AccountError accountError) {
        switch (accountError) {
            case USERNAME_ALREADY_EXIST:
                System.out.println("Username already exist please try another one");
                break;
            case PASSWORD_IS_INCORRECT:
                System.out.println("password is not correct");
                break;
            case USERNAME_DOESENT_EXIST:
                System.out.println("username does not exist");
                break;
            case ALREADY_LOGGED_IN:
                System.out.println("you are already logged in");
                break;
        }

    }
}


