package models;

import java.util.ArrayList;

public class LoginMenu {
    private static LoginMenu loginMenu;
    public static LoginMenu getInstance() {

        if (loginMenu == null) {

            loginMenu = new LoginMenu();
        }

        return loginMenu;
    }
    private ArrayList<Account> users;

    public void createAccount(String userName, String passWord) {

    }

    public void checkIfAccountExist(String userName) {
    }

    public void login(String userName, String passWord) {
    }

    public void getLeaderBoard() {
    }

    public void save(Account account) {
    }

    public void logout(Account account) {
    }

    public static String findUserFolder(Account account) {
    }

}
