package controller;

import request.battleRequest.BattleRequest;
import request.battleRequest.BattleRequestChilds.*;
import view.battleView.BattleView;

public class BattleController {

    private static BattleController battleController;
    private BattleRequest battleRequest = BattleRequest.getInstance();
    private BattleView battleView = BattleView.getInstance();

    public static BattleController getInstance() {

        if (battleController == null) {

            battleController = new BattleController();
        }

        return battleController;
    }

    public void battleControllerMain(){

        //todo
    }

    private void manageRequest() {

        while (true) {

            BattleRequest request = battleRequest.getRequest();

            if (request instanceof SelectAndUseCardRequest)
                selectAndUseCardRequest((SelectAndUseCardRequest) request);

            if (request instanceof UseSpecialPowerRequest)
                useSpecialPowerRequest((UseSpecialPowerRequest) request);

            if (request instanceof ShowCardInfoRequest)
                showCardInfoRequest((ShowCardInfoRequest) request);

            if (request instanceof EnterGraveYardRequest)
                enterGraveYardRequest((EnterGraveYardRequest) request);

            if (request instanceof InsertCardRequest)
                insertCardRequest((InsertCardRequest) request);

            if (request instanceof RequestWithoutVariable)
                requestWithoutVariable((RequestWithoutVariable) request);
        }
    }

    private void selectAndUseCardRequest(SelectAndUseCardRequest request) {
        //todo
        if (request.isForMove());
        if (request.isForAttack());
        if (request.isForAttackCombo());
        if (request.isForShowInfo());
        if (request.isForUse());

    }

    private void useSpecialPowerRequest(UseSpecialPowerRequest request) {

        //todo
    }

    private void showCardInfoRequest(ShowCardInfoRequest request) {

        //todo
    }

    private void enterGraveYardRequest(EnterGraveYardRequest request) {
        //todo
        if (request.isForShowInfo());
        if (request.isForShowCards());
        if (request.isForExit());
    }

    private void insertCardRequest(InsertCardRequest request) {

        //todo
    }

    private void requestWithoutVariable(RequestWithoutVariable request) {
        //todo
        if (request.getEnumRequest() == RequestWithoutVariableEnum.GAME_INFO_REQUEST);
        if (request.getEnumRequest() == RequestWithoutVariableEnum.SHOW_MY_MINIONS_REQUEST);
        if (request.getEnumRequest() == RequestWithoutVariableEnum.SHOW_OPPONENT_MINIONS_REQUEST);
        if (request.getEnumRequest() == RequestWithoutVariableEnum.SHOW_NEXT_CARD_REQUEST);
        if (request.getEnumRequest() == RequestWithoutVariableEnum.SHOW_HAND_REQUEST);
        if (request.getEnumRequest() == RequestWithoutVariableEnum.END_TURN_REQUEST);
        if (request.getEnumRequest() == RequestWithoutVariableEnum.SHOW_COLLECTED_ITEM_REQUEST);
        if (request.getEnumRequest() == RequestWithoutVariableEnum.END_GAME_REQUEST);

        if (request.getEnumRequest() == RequestWithoutVariableEnum.HELP_REQUEST)
            helpRequest();
    }

    private void gameInfoRequest() {}

    private void showMyMinionsRequest() {}

    private void showOpponentMinionsRequest() {}

    private void showNextCardRequest() {}

    private void showHandRequest() {}

    private void endTurnRequest() {}

    private void showCollectedItemRequest() {}

    private void endGameRequest() {}

    private void helpRequest() {

        System.out.println("Game info");
        System.out.println("Show my minions");
        System.out.println("Show opponent minions");
        System.out.println("Show card info [card id]");
        System.out.println("Select [card id]");
        System.out.println("    Move to ([x],[y])");
        System.out.println("    Attack [opponent card id]");
        System.out.println("    Attack combo [opponent card id] [my card id] [my card id] ...");
        System.out.println("    exit");
        System.out.println("Use special power ([x],[y])");
        System.out.println("Show hand");
        System.out.println("Insert [card name] in ([x],[y])");
        System.out.println("End turn");
        System.out.println("Show collectibles");
        System.out.println("Select [collectible id]");
        System.out.println("    Show info");
        System.out.println("    Use ([x],[y])");
        System.out.println("    exit");
        System.out.println("Show next card");
        System.out.println("Enter graveyard");
        System.out.println("    Show info [card id]");
        System.out.println("    Show cards");
        System.out.println("    exit");
        System.out.println("Help");
        System.out.println("End game");
    }
}
