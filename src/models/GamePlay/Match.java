package models.GamePlay;

import models.*;

import java.util.Random;

public class Match {

    Table table = new Table();
    Account player1, player2;
    GraveYard Player1GraveYard = new GraveYard();
    GraveYard Player2GraveYard = new GraveYard();
    private MatchType matchType;
    private GameLogic gameLogic;
    int turnNumber = 0;  //todo 0 or 1?
    int player1Mana = 2, player2Mana = 2, initialPlayer1Mana = 2, initialPlayer2Mana = 2;

    public Account getPlayer1() {

        return player1;
    }

    public Account getPlayer2() {

        return player2;
    }

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

    public GraveYard getPlayer1GraveYard() {

        return Player1GraveYard;
    }

    public GraveYard getPlayer2GraveYard() {

        return Player2GraveYard;
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

    public Account findPlayerPlayingThisTurn() {

        if (turnNumber % 2 == 1) return player1;
        return player2;
    }

    public void intializeTableModeKillTheHero() {

        Card hero1 = player1.getHand().getHero();
        Card hero2 = player2.getHand().getHero();

        table.getCells()[2][0].setCard(hero1);
        table.getCells()[2][8].setCard(hero2);

    }

    public void intializeTableModeHoldTheFlag() {

        intializeTableModeKillTheHero();

        Card flag = new Card(0, 0, "flag", null, "", CardType.FLAG, null);
        table.getCells()[2][4].setCard(flag);

    }

    public void intializeTableModeCollectTheFlag(int numberOfFlags) {

        intializeTableModeKillTheHero();

        Random random = new Random();

        int[] randomRow = new int[numberOfFlags], randomColumn = new int[numberOfFlags];
        for (int i = 0; i < numberOfFlags; i++) {
            randomColumn[i] = random.nextInt(9);
            randomRow[i] = random.nextInt(5);

            for (int j = 0; j < i; j++)
                if (randomColumn[i] == randomColumn[j] && randomRow[i] == randomRow[j]) {
                    i--;
                    break;
                } else if ((randomColumn[i] == 8 || randomColumn[i] == 0) && randomRow[i] == 2){
                    i--;
                    break;
                }
        }

        for (int i = 0; i < numberOfFlags; i++) {
            Card flag = new Card(0, 0, "flag", null, "", CardType.FLAG, null);
            table.getCells()[randomRow[i]][randomColumn[i]].setCard(flag);
        }
    }
}
