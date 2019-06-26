package ui.battleUI.battleViews;

import models.Coordination;

import java.util.ArrayList;

public class GameInfoBattleViewCollectTheFlags extends GameInfoBattleView {

    private ArrayList<String[]> flagHoldersName = new ArrayList<>();
    private ArrayList<Coordination> flagsCoordination = new ArrayList<>();

    public ArrayList<String[]> getFlagHolders() {

        return flagHoldersName;
    }

    public void setFlagHolder(String flagHolderName, String flagHolderTeam, int flagsNumber) {

        String[] flagHolder = new String[3];
        flagHolder[0] = flagHolderName;
        flagHolder[1] = flagHolderTeam;
        flagHolder[2] = Integer.toString(flagsNumber);
        flagHoldersName.add(flagHolder);
    }

    public ArrayList<Coordination> getFlagsCoordination() {

        return flagsCoordination;
    }

    public void setFlagCoordination(Coordination flagCoordination) {

        flagsCoordination.add(flagCoordination);
    }
}
