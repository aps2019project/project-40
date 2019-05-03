package request.battleMenuRequest.battleMenuRequestChilds;

import models.MatchType;
import request.battleMenuRequest.BattleMenuRequest;

public class CustomGameRequest extends BattleMenuRequest {
    private int flagsNumber;
    private int mode;
    private String deckName;

    public String getDeckName() {
        return deckName;
    }

    public int getFlagsNumber() {
        return flagsNumber;
    }

    public MatchType getMode() {
        switch (mode) {
            case 1:
                return MatchType.KILL_THE_HERO;

            case 2:
                return MatchType.HOLD_THE_FLAG;

            case 3:
                return MatchType.COLLECT_THE_FLAGS;

            default:
                return null;
        }
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public void setFlagsNumber(int flagsNumber) {
        this.flagsNumber = flagsNumber;
    }
}
