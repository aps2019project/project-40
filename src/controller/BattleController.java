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
    }

    private void insertCardRequest(InsertCardRequest request) {

        //todo
    }

    private void requestWithoutVariable(RequestWithoutVariable request) {

        //todo
    }
}
