package ui.battleUI.battleViews;

import models.Coordination;

import java.util.ArrayList;

public class ShowMinionsBattleView extends BattleView {

    private ArrayList<String> cardsID = new ArrayList<>();
    private ArrayList<String> cardsName = new ArrayList<>();
    private ArrayList<Integer> healthPoints = new ArrayList<>();
    private ArrayList<Coordination> locations = new ArrayList<>();
    private ArrayList<Integer> attackPoints = new ArrayList<>();

    public ArrayList<String> getCardsID() {

        return cardsID;
    }

    public ArrayList<String> getCardsName() {

        return cardsName;
    }

    public ArrayList<Integer> getHealthPoints() {

        return healthPoints;
    }

    public ArrayList<Coordination> getLocations() {

        return locations;
    }

    public ArrayList<Integer> getAttackPoints() {

        return attackPoints;
    }

    public void setMinion(String cardID, String cardName, int healthPoint, Coordination location, int attackPoint) {

        this.cardsID.add(cardID);
        this.cardsName.add(cardName);
        this.healthPoints.add(healthPoint);
        this.locations.add(location);
        this.attackPoints.add(attackPoint);
    }
}
