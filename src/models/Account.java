package models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Account implements Serializable {

    private String userName, password;
    private int initialMoney = 15000;
    private int money;
    private Collection collection;
    private Hand hand;
    private boolean isAI = false;
    private int winsNumber = 0;

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

    public void playTurn() {
    }

    public void addToHand(Card card) {

    }

    public Collection getCollection() {

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
            FileOutputStream fos = new FileOutputStream("users/"+account.getUserName()+".ser");
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
}
