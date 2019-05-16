package models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {

    private String userName, password;
    private int money = 150000;
    private Collection collection;
    private ArrayList<History> matchHistories = new ArrayList<>();
    private Hand hand = new Hand();
    private boolean isAI = false;
    private int winsNumber = 0;

    public Hand getHand() {
        return hand;
    }

    public ArrayList<History> getMatchHistories() {
        return matchHistories;
    }

    public void addMatchHistory(History history) {
        matchHistories.add(history);
    }

    public boolean isAI() {
        return isAI;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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

        return this.password.equals(password);
    }

    public Collection getCollection() {
        if (collection == null)
            collection = new Collection();
        return collection;
    }

    public int getWinsNumber() {
        return winsNumber;
    }

    public void incrementWinsNumber() {
        winsNumber++;
    }

    public void resetPlayerVariables() {
        winsNumber = 0;
        money = 15000;
        collection = new Collection();
    }

    public static Account getAIAccount(MatchType matchType) {
        Account account = new Account();
        account.setUserName("Bot");
        account.isAI = true;
        switch (matchType) {
            case KILL_THE_HERO:
                account.getCollection().getDecks().add(Deck.getDefaultMode1deck());
                account.getCollection().setSelectedDeck(Deck.getDefaultMode1deck());
                break;
            case HOLD_THE_FLAG:
                account.getCollection().getDecks().add(Deck.getDefaultMode2deck());
                account.getCollection().setSelectedDeck(Deck.getDefaultMode2deck());
                break;
            case COLLECT_THE_FLAGS:
                account.getCollection().getDecks().add(Deck.getDefaultMode3deck());
                account.getCollection().setSelectedDeck(Deck.getDefaultMode3deck());
                break;
        }
        return account;
    }

    public void setID(Card card) {
        int instanceNum = 0;
        try {
            instanceNum = collection.getNumberOfCardWithName(card.getCardName());
        } catch (NullPointerException e) {
        }
        card.setCardID(userName + "_" + card.getCardName() + "_" + (instanceNum + 1));
        card.setTeam(userName);
        card.setSellCost();
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
        return this.getUserName().equals(account.getUserName());
    }

}
