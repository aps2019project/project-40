package models;

public class Table {

    public static final int COLUMNS = 9, ROWS = 5;
    private Cell[][] cells = new Cell[ROWS][COLUMNS];

    public Cell getCellByCoordination(Coordination coordination) {

        return cells[coordination.getRow()][coordination.getColumn()];
    }

    public Cell[][] getCells() {

        return cells;
    }

    public Table() {

        //set coordination of the cells for their coordination variable
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {

                cells[row][column] = new Cell();
                cells[row][column].setCoordination(row, column);
            }
        }
    }

    public boolean isInMap(int row, int column) {
        return row >= 0 && row < 5 && column >= 0 && column < 9;
    }

    public boolean isInMap(Coordination position) {
        return position.getRow() >= 0 && position.getRow() < 5 && position.getColumn() >= 0 && position.getColumn() < 9;
    }
}
