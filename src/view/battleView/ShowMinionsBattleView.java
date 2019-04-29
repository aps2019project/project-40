package view.battleView;

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

    public void setCardsID(ArrayList<String> cardsID) {

        this.cardsID = cardsID;
    }

    public ArrayList<String> getCardsName() {

        return cardsName;
    }

    public void setCardsName(ArrayList<String> cardsName) {

        this.cardsName = cardsName;
    }

    public ArrayList<Integer> getHealthPoints() {

        return healthPoints;
    }

    public void setHealthPoints(ArrayList<Integer> healthPoints) {

        this.healthPoints = healthPoints;
    }

    public ArrayList<Coordination> getLocations() {

        return locations;
    }

    public void setLocations(ArrayList<Coordination> locations) {

        this.locations = locations;
    }

    public ArrayList<Integer> getAttackPoints() {

        return attackPoints;
    }

    public void setAttackPoints(ArrayList<Integer> attackPoints) {

        this.attackPoints = attackPoints;
    }
}
