package models;

public class Table {

    final int COLUMNS = 9, ROWS = 5;
    private Cell[][] cells = new Cell[ROWS][COLUMNS];

    public Table() {

        //set coordination of the cells for their coordination variable
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {

                cells[row][column].setCoordination(row, column);
            }
        }
    }

    public Cell getCellByCoordination(Coordination coordination) {

        return cells[coordination.getRow()][coordination.getColumn()];
    }
}
