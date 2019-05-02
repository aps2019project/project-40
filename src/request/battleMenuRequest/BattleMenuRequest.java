package request.battleMenuRequest;

import models.MatchType;
import request.Request;
import request.battleMenuRequest.battleMenuRequestChilds.RequestMatchType;
import view.battleMenuView.BattleMenuView;
import view.battleMenuView.battleMenuViewChilds.BattleMenuError;

public class BattleMenuRequest extends Request {
    private static BattleMenuRequest battleMenuRequest;
    private BattleMenuView battleMenuView = BattleMenuView.getInstance();

    public static BattleMenuRequest getInstance() {

        if (battleMenuRequest == null)
            battleMenuRequest = new BattleMenuRequest();

        return battleMenuRequest;
    }

    public BattleMenuRequest getCommand(){
        String command=scanner.nextLine();
        if (command.equals("1"))
            return getSinglePlayerCommand();

        if (command.equals("2"))
            return getMultiPlayerCommand();

        battleMenuView.showError(BattleMenuError.INVALID_COMMAND);
        return null;
    }

    private BattleMenuRequest getSinglePlayerCommand(){
        RequestMatchType requestMatchType=new RequestMatchType();
        requestMatchType.setMatchType(MatchType.SINGLE_PLAYER);

        battleMenuView.showSinglePlayerCommand();
        String command=scanner.nextLine();
        if (command.equals("1"))
            requestMatchType.setModeOfGame(MatchType.STORY);
        else if (command.equals("2"))
            requestMatchType.setModeOfGame(MatchType.CUSTOMGAME);
        else {
            battleMenuView.showError(BattleMenuError.INVALID_COMMAND);
            return null;
        }

        return requestMatchType;
    }

    private BattleMenuRequest getMultiPlayerCommand(){
        RequestMatchType requestMatchType=new RequestMatchType();
        requestMatchType.setMatchType(MatchType.MULTI_PLAYER);
        return requestMatchType;
    }
}
