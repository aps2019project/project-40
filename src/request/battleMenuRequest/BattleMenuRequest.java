package request.battleMenuRequest;

import request.Request;

public class BattleMenuRequest extends Request {
    private static BattleMenuRequest battleMenuRequest;

    public static BattleMenuRequest getInstance() {

        if (battleMenuRequest == null)
            battleMenuRequest = new BattleMenuRequest();

        return battleMenuRequest;
    }


}
