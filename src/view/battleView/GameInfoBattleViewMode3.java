package view.battleView;

import models.Coordination;
import java.util.ArrayList;
import java.util.HashMap;

public class GameInfoBattleViewMode3 extends GameInfoBattleView {

    private HashMap<String, String> flagHoldersName = new HashMap<>();
    private ArrayList<Coordination> flagsCoordination = new ArrayList<>();

    public HashMap<String, String> getFlagHoldersName() {

        return flagHoldersName;
    }

    public void setFlagHoldersName(HashMap<String, String> flagHoldersName) {

        this.flagHoldersName = flagHoldersName;
    }

    public ArrayList<Coordination> getFlagsCoordination() {

        return flagsCoordination;
    }

    public void setFlagsCoordination(ArrayList<Coordination> flagsCoordination) {

        this.flagsCoordination = flagsCoordination;
    }
}
