package request.battleMenuRequest.battleMenuRequestChilds;

import models.MatchType;
import request.battleMenuRequest.BattleMenuRequest;
import view.battleMenuView.BattleMenuView;
import view.battleMenuView.battleMenuViewChilds.BattleMenuError;

public class MultiPlayerMenuRequest extends BattleMenuRequest {
    private int numberOfFlags;
    private MatchType mode;

    public void setMode(String mode) {
        switch (Integer.parseInt(mode)) {
            case 1:
                this.mode = MatchType.KILL_THE_HERO;
                break;
            case 2:
                this.mode = MatchType.HOLD_THE_FLAG;
                break;
            case 3:
                this.mode = MatchType.COLLECT_THE_FLAGS;
                break;
            default:
                this.mode = null;
                BattleMenuView.getInstance().showError(BattleMenuError.INVALID_COMMAND);
        }
    }

    public MatchType getMode() {
        return mode;
    }

    public int getNumberOfFlags() {
        return numberOfFlags;
    }

    public void setNumberOfFlags(String numberOfFlags) {
        this.numberOfFlags = Integer.parseInt(numberOfFlags);
    }
}
