package request.battleRequest.BattleRequestChilds;

import request.battleRequest.BattleRequest;

public class InsertCard extends BattleRequest {

    private String cardName;
    private int row, column;

    public String getCardName() {

        return cardName;
    }

    public void setCardName(String cardName) {

        this.cardName = cardName;
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
}
