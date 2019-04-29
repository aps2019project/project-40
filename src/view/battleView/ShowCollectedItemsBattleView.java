package view.battleView;

import java.util.ArrayList;

public class ShowCollectedItemsBattleView extends BattleView {

    private ArrayList<String> itemName = new ArrayList<>();

    public ArrayList<String> getItemName() {

        return itemName;
    }

    public void setItemName(ArrayList<String> itemName) {

        this.itemName = itemName;
    }
}
