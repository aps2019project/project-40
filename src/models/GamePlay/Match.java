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
    int turnNumber = 1;
    int player1Mana = 2, player2Mana = 2;
    int initialPlayer1ManaInBeginningOfEachTurn = 2, initialPlayer2ManaInBeginningOfEachTurn = 2;

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

    public int getTurnNumber() {
        return turnNumber;
    }

    public Match(MatchType matchType, Account player1, Account player2) {

        //Constructor for "kill the hero" and "hold the flag"
        this.matchType = matchType;
        this.player1 = player1;
        this.player2 = player2;
        gameLogic = new GameLogic(this);

        initializePlayerVariables();
        if (matchType == MatchType.KILL_THE_HERO) initializeTableModeKillTheHero();
        else initializeTableModeHoldTheFlag();
    }

    public Match(int flagsNumber, Account player1, Account player2) {

        //Constructor for "collect the flag"
        matchType = MatchType.COLLECT_THE_FLAGS;
        this.player1 = player1;
        this.player2 = player2;
        gameLogic = new GameLogic(this);
        gameLogic.flagsNumber = flagsNumber;

        initializePlayerVariables();
        initializeTableModeCollectTheFlag(flagsNumber);
    }

    public Account findPlayerPlayingThisTurn() {

        if (turnNumber % 2 == 1) return player1;
        return player2;
    }

    public Account findPlayerDoesNotPlayingThisTurn() {

        if (turnNumber % 2 == 1) return player2;
        return player1;
    }

    private void initializeTableModeKillTheHero() {

        Card hero1 = player1.getHand().getHero();
        Card hero2 = player2.getHand().getHero();

        Cell cellHero1 = table.getCells()[2][1];
        Cell cellHero2 = table.getCells()[2][7];
        cellHero1.setCard(hero1);
        cellHero2.setCard(hero2);
        hero1.setCell(cellHero1);
        hero2.setCell(cellHero2);

        gameLogic.cardsInTablePlayer1.add(hero1);
        gameLogic.cardsInTablePlayer2.add(hero2);
    }

    private void initializePlayerVariables() {

        player1.getHand().initializeHand(player1.getCollection().getSelectedDeck());
        player2.getHand().initializeHand(player2.getCollection().getSelectedDeck());
    }

    private void initializeTableModeHoldTheFlag() {

        //for put hero on table
        initializeTableModeKillTheHero();

        Card flag = new Card(
                0, 0, "flag", null, "", CardType.FLAG, table.getCells()[2][4]);
        table.getCells()[2][4].setFlag(flag);
    }

    private void initializeTableModeCollectTheFlag(int numberOfFlags) {

        //for put hero on table
        initializeTableModeKillTheHero();

        Random random = new Random();

        int[] randomRow = new int[numberOfFlags], randomColumn = new int[numberOfFlags];

        for (int i = 0; i < numberOfFlags; i++) {

            randomColumn[i] = random.nextInt(9);
            randomRow[i] = random.nextInt(5);

            for (int j = 0; j < i; j++) {
                if (randomColumn[i] == randomColumn[j] && randomRow[i] == randomRow[j]) {
                    i--;
                    break;
                } else if ((randomColumn[i] == 8 || randomColumn[i] == 0) && randomRow[i] == 2) {
                    i--;
                    break;
                }
            }
        }

        for (int i = 0; i < numberOfFlags; i++) {
            Card flag = new Card(0, 0, "flag_" + i + 1, null,
                    "", CardType.FLAG, table.getCells()[randomRow[i]][randomColumn[i]]);
            table.getCells()[randomRow[i]][randomColumn[i]].setFlag(flag);
        }
    }
}
