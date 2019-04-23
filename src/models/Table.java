package models;

public class Table {

    final int COLUMNS = 9, ROWS = 5;
    private Cell[][] cells = new Cell[ROWS][COLUMNS];

    public Cell getCellByCoordination(Coordination coordination) {

        return cells[coordination.getRow()][coordination.getColumn()];
    }
}
