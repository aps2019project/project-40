package models;

import org.graalvm.compiler.nodes.extended.ArrayRangeWriteNode;

import java.util.ArrayList;

public class Account {

    private String userName, password;
    private final int initialMoney = 15000;
    private int money;
    private Collection collection;
    private Hand hand;
    private int mana;
    private Table playingTable;
    private boolean isAI=false;
    private int winsNumber=0;

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

    public void playAI(Match match) {
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



}
