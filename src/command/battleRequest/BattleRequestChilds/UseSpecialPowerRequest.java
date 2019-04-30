package command.battleRequest.BattleRequestChilds;

import command.battleRequest.BattleRequest;

public class UseSpecialPowerRequest extends BattleRequest {

    private int row, column;

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
