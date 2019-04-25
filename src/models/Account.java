package models;

public class Account {

    private String userName, password;
    private long money;
    private Collection collection;
    private Hand hand;
    private int mana;
    private Table playingTable;
    private boolean isAI;
    private int winsNumber;
    private String folderAddress;

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
