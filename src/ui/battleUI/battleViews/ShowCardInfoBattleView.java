package ui.battleUI.battleViews;

public class ShowCardInfoBattleView extends BattleView {

    private String name;
    private int cost;
    private String description;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getCost() {

        return cost;
    }

    public void setCost(int cost) {

        this.cost = cost;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }
}
