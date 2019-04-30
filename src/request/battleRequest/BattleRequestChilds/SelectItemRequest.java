package request.battleRequest.BattleRequestChilds;

import request.battleRequest.BattleRequest;

public class SelectItemRequest extends BattleRequest {

    private String collectableID;
    private int row, column;
    private boolean isForShowInfo, isForUse;

    public String getCollectableID() {

        return collectableID;
    }

    public void setCollectableID(String collectableID) {

        this.collectableID = collectableID;
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
