package models;

public class Coordination {

    private int row, column;

    public int getRow() {

        return row;
    }

    public int getColumn() {

        return column;
    }

    public void setRow(int row) {

        this.row = row;
    }

    public void setColumn(int column) {

        this.column = column;
    }

    public static Coordination getNewCoordination(int row, int column) {

        Coordination coordination = new Coordination();
        coordination.setRow(row);
        coordination.setColumn(column);
        return coordination;
    }

    public void copyCoordination(Cell cell) {
        row = cell.getCoordination().row;
        column = cell.getCoordination().column;
    }
}
