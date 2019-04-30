package models;

import java.util.ArrayList;

public class LoginMenu {
    private static ArrayList<Account> users=new ArrayList<Account>();
    private static LoginMenu loginMenu;

    static {
        initializeUsers();
    }
    public static LoginMenu getInstance() {
        if (loginMenu == null)
            loginMenu = new LoginMenu();
        return loginMenu;
    }

    public void createAccount(String userName, String passWord) {

    }

    public boolean checkIfAccountExist(String userName) {
        for (Account account : users)
            if (account.getUserName().equals(userName))
                return true;
        return false;
    }

    public Account login(String userName, String passWord) {
        for (Account account : users) {
            if (account.getUserName().equals(userName)) {
                if (account.isPasswordCorrect(passWord))
                    return account;
            }
        }
        return null;
    }

    public void getLeaderBoard() {
    }

    public void save(Account account) {
    }

    public void logout(Account account) {
    }

    private static void initializeUsers(){

    }
    public static ArrayList<Account> getUsers() {
        return users;
    }


}
