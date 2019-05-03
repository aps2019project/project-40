package models.GamePlay;

import models.Account;
import models.GraveYard;
import models.MatchType;
import models.Table;

public class Match {

    Table table;
    Account player1, player2;
    GraveYard Player1GraveYard = new GraveYard();
    GraveYard Player2GraveYard = new GraveYard();
    MatchType matchType;
    private GameLogic gameLogic;
    int turnNumber = 0;  //todo 0 or 1?
    int player1Mana, player2Mana;

    public Table getTable() {

        return table;
    }

    public MatchType getMatchType() {

        return matchType;
    }

    public GameLogic getGameLogic() {

        return gameLogic;
    }

    public int getPlayer1Mana() {

        return player1Mana;
    }

    public int getPlayer2Mana() {

        return player2Mana;
    }

    public Match(MatchType matchType, Account player1, Account player2) {

        //for "kill the hero" and "hold the flag"
        this.matchType = matchType;
        this.player1 = player1;
        this.player2 = player2;
        gameLogic = new GameLogic(this);
        if (matchType == MatchType.HOLD_THE_FLAG)
            gameLogic.remainTurnToHoldingTheFlag = gameLogic.NUMBER_OF_TURNS_TO_HOLD_THE_FLAG;
    }

    public Match(int flagsNumber, Account player1, Account player2) {

        //for "collect the flag"
        matchType = MatchType.COLLECT_THE_FLAGS;
        this.player1 = player1;
        this.player2 = player2;
        gameLogic = new GameLogic(this);
        gameLogic.flagsNumber = flagsNumber;
    }

    //todo code finds

    public Account findPlayerPlayingThisTurn() {

        if (turnNumber % 2 == 1) return player1;
        return player2;
    }
}
