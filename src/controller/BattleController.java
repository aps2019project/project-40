package controller;

public class BattleController {
    private static BattleController battleController;

    public static BattleController getInstance() {

        if (battleController == null) {

            battleController = new BattleController();
        }

        return battleController;
    }
    public void battleControllerMain(){

    }
}
