package models;

import java.io.Serializable;
import java.util.ArrayList;

public class TargetData implements Serializable {
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Unit> units = new ArrayList<>();
    private ArrayList<Cell> cells = new ArrayList<>();
    private ArrayList<Account> accounts = new ArrayList<>();

    public TargetData(ArrayList<Unit> units) {
        this.units = units;
    }

    public TargetData() {

    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
