package models;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginMenu {
    private static ArrayList<Account> users = new ArrayList<Account>();
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
        Account account = new Account();
        account.setPassword(passWord);
        account.setUserName(userName);
        users.add(account);
        return account;
    }

    public void saveUserNames() {
        for (Account account : users)
            try {
                FileWriter writer = new FileWriter("listOfUserName.txt");
                writer.write(account.getUserName());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    private static void initializeUsers() {
        try {
            File file = new File("listOfUserName.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String userName = sc.nextLine();
                Account account = null;
                try {
                    FileInputStream is = new FileInputStream("users/" + userName + ".ser");
                    ObjectInputStream ois = new ObjectInputStream(is);
                    account = (Account) ois.readObject();
                    users.add(account);
                    ois.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Account> getUsers() {
        return users;
    }

    public Account getAccountByUserName(String userName) {
        for (Account account : users) {
            if (account.getUserName().equals(userName))
                return account;
        }
        return null;
    }


}
