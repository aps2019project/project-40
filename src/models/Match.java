package models;

public class Match {

    private Table table;
    private Account player1, player2;
    private GraveYard Player1GraveYard = new GraveYard();
    private GraveYard Player2GraveYard = new GraveYard();
    public GameLogic gameLogic;
    public int turnNumber = 0;  //todo 0 or 1?

    public Match(Account player1, Account player2) {

        this.player1 = player1;
        this.player2 = player2;
        gameLogic = new GameLogic();
    }

    //todo code finds

    public Account findPlayerPlayingThisTurn() {

        if (turnNumber % 2 == 1) return player1;
        return player2;
    }

    public Table getTable() {

        return table;
    }


}
