package request.battleRequest;

import request.Request;

public class BattleRequest extends Request {

    private static BattleRequest battleRequest;

    public static BattleRequest getInstance() {

        if (battleRequest == null) {

            battleRequest = new BattleRequest();
        }
        return battleRequest;
    }

    private boolean isCommandValid() {

        return false;
    }

    private void initializeCommandPatterns() {

    }
}
