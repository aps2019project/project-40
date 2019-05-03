package view.battleMenuView;

import view.View;
import view.battleMenuView.battleMenuViewChilds.BattleMenuError;

import java.util.ArrayList;

public class BattleMenuView extends View {
    private static BattleMenuView battleMenuView;

    public static BattleMenuView getInstance() {

        if (battleMenuView == null)
            battleMenuView = new BattleMenuView();

        return battleMenuView;
    }

    public void showError(BattleMenuError battleMenuError){
        System.out.println(battleMenuError);
    }

    public void showBattleMenuPlayerType(){
        System.out.println("1. Single Player");
        System.out.println("2. Multi Player");
    }

    public void showSinglePlayerCommand(){
        System.out.println("1. Story");
        System.out.println("2. Custom Game");
    }

    public void showUsers(ArrayList<String> users) {
        for (int i = 0; i <users.size() ; i++) {
            System.out.println(i+1+". "+users.get(0));
        }
    }
}
