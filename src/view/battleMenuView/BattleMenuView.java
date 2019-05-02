package view.battleMenuView;

import view.View;

public class BattleMenuView extends View {
    private static BattleMenuView battleMenuView;

    public static BattleMenuView getInstance() {

        if (battleMenuView == null)
            battleMenuView = new BattleMenuView();

        return battleMenuView;
    }
}
