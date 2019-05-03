package models.GamePlay;

public class GameLogic {

    private Match match;
    int flagsNumber;
    final int NUMBER_OF_TURNS_TO_HOLD_THE_FLAG = 6;
    int remainTurnToHoldingTheFlag;

    GameLogic(Match match) {

        this.match = match;
    }
}
