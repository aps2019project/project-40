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

    public Account createAccount(String userName, String passWord) {
        Account account=new Account();
        account.setPassword(passWord);
        account.setUserName(userName);
        users.add(account);
        return account;
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

    public void save(Account account) {
    }

    private static void initializeUsers(){

    }
    public static ArrayList<Account> getUsers() {
        return users;
    }

    public Account getAccountByUserName(String userName){
        for (Account account:users){
            if (account.getUserName().equals(userName))
                return account;
        }
        return null;
    }


}
