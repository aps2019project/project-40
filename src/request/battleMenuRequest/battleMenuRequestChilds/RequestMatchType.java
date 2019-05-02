package request.battleMenuRequest.battleMenuRequestChilds;

import models.MatchType;
import request.battleMenuRequest.BattleMenuRequest;

public class RequestMatchType extends BattleMenuRequest {
    private MatchType matchType;
    private MatchType modeOfGame;

    public MatchType getMatchType() {
        return matchType;
    }

    public MatchType getModeOfGame() {
        return modeOfGame;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    public void setModeOfGame(MatchType modeOfGame) {
        this.modeOfGame = modeOfGame;
    }
}
