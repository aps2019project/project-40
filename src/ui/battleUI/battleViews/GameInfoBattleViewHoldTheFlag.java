package ui.battleUI.battleViews;

import models.Coordination;

public class GameInfoBattleViewHoldTheFlag extends GameInfoBattleView {

    private Coordination flagCoordination;
    private String flagHolderName;
    private String flagHolderTeam;

    public String getFlagHolderTeam() {

        return flagHolderTeam;
    }

    public void setFlagHolderTeam(String flagHolderTeam) {

        this.flagHolderTeam = flagHolderTeam;
    }

    public Coordination getFlagCoordination() {

        return flagCoordination;
    }

    public void setFlagCoordination(Coordination flagCoordination) {

        this.flagCoordination = flagCoordination;
    }

    public String getFlagHolderName() {

        return flagHolderName;
    }

    public void setFlagHolderName(String flagHolderName) {

        this.flagHolderName = flagHolderName;
    }
}
