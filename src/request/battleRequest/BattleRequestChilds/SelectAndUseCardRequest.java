package request.battleRequest.BattleRequestChilds;

import request.battleRequest.BattleRequest;

import java.util.ArrayList;

public class SelectAndUseCardRequest extends BattleRequest {

    private String cardID, opponentCardID;
    private ArrayList<String> myCardsID = new ArrayList<>();
    private int row, column;
    private boolean isForMove, isForAttack, isForAttackCombo;

    public String getCardID() {

        return cardID;
    }

    public void setCardID(String cardID) {

        this.cardID = cardID;
    }

    public String getOpponentCardID() {

        return opponentCardID;
    }

    public void setOpponentCardID(String opponentCardID) {

        this.opponentCardID = opponentCardID;
    }

    public ArrayList<String> getMyCardsID() {

        return myCardsID;
    }

    public void setMyCardsID(ArrayList<String> myCardsID) {

        this.myCardsID = myCardsID;
    }

    public int getRow() {

        return row;
    }

    public void setRow(int row) {

        this.row = row;
    }

    public int getColumn() {

        return column;
    }

    public void setColumn(int column) {

        this.column = column;
    }

    public boolean isForMove() {

        return isForMove;
    }

    public void setForMove(boolean forMove) {

        isForMove = forMove;
    }

    public boolean isForAttack() {

        return isForAttack;
    }

    public void setForAttack(boolean forAttack) {

        isForAttack = forAttack;
    }

    public boolean isForAttackCombo() {

        return isForAttackCombo;
    }

    public void setForAttackCombo(boolean forAttackCombo) {

        isForAttackCombo = forAttackCombo;
    }
}
