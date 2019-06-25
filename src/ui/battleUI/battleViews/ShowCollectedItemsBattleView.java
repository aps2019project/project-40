package ui.battleUI.battleViews;

import java.util.HashMap;

public class ShowCollectedItemsBattleView extends BattleView {

    private HashMap<String, String> itemsInfo = new HashMap<>();

    public HashMap<String, String> getItemInfo() {

        return itemsInfo;
    }

    public void setItemInfo(String name, String description) {

        itemsInfo.put(name, description);
    }
}
