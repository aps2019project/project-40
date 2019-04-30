package request.battleRequest.BattleRequestChilds;

import request.battleRequest.BattleRequest;

import java.util.ArrayList;

public class SelectAndUseCardRequest extends BattleRequest {

    private String id, opponentCardID;
    private ArrayList<String> myCardsID = new ArrayList<>();
    private int row, column;
    private boolean isForMove;
    private boolean isForAttack;
    private boolean isForAttackCombo;
    private boolean isForShowInfo;
    private boolean isForUse;

    public boolean isForHelp() {

        return isForHelp;
    }

    public void setForHelp(boolean forHelp) {

        isForHelp = forHelp;
    }

    private boolean isForHelp;

    public String getID() {

        return id;
    }

    public void setID(String id) {

        this.id = id;
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

    public boolean isForShowInfo() {

        return isForShowInfo;
    }

    public void setForShowInfo(boolean forShowInfo) {

        isForShowInfo = forShowInfo;
    }

    public boolean isForUse() {

        return isForUse;
    }

    public void setForUse(boolean forUse) {

        isForUse = forUse;
    }
}
