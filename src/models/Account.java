package models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Account implements Serializable {

    private String userName, password;
    private int money = 15000;
    private Collection collection;
    private Hand hand;
    private boolean isAI = false;
    private int winsNumber = 0;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Hand getHand() {
        return hand;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {


        this.userName = userName;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public boolean isPasswordCorrect(String password) {

        if (this.password.equals(password)) return true;
        return false;
    }

    public void addToHand(Card card) {

    }

    public Collection getCollection() {
        if (collection==null)
            collection=new Collection();
            return collection;
    }

    public int getWinsNumber() {
        return winsNumber;
    }

    public void incrementWinsNumber() {
        winsNumber++;
    }

    public void resetPlayerVariables() {
    }

    public static Account getAIAccount(MatchType matchType) {
        return null;
    }

    public static void save(Account account) {
        LoginMenu.getInstance().saveUserNames();
        if (account.getUserName().isEmpty())
            return;

        try {
            FileOutputStream fos = new FileOutputStream("users/" + account.getUserName() + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // write object to file
            oos.writeObject(account);
            System.out.println("Done");
            // closing resources
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean equals(Account account) {

        if (this.getUserName().equals(account.getUserName())) return true;
        return false;
    }
}
