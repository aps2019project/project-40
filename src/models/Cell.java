package models;

public class Cell {

    private Coordination coordination = new Coordination();
    private Card card;
    private Card flag;
    private boolean isThereFlag = false;

    public Card getFlag() {
        return flag;
    }

    public void setFlag(Card flag) {
        this.flag = flag;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setThereIsFlag(boolean thereFlag) {
        isThereFlag = thereFlag;
    }

    public Coordination getCoordination() {
        return coordination;
    }

    public void setCoordination(int row, int column) {

        coordination.setRow(row);
        coordination.setColumn(column);
    }

    public boolean isAdjacent(Cell cell) {
        return Math.abs(cell.coordination.getRow() - coordination.getRow()) < 2 &&
                Math.abs(cell.coordination.getColumn() - coordination.getColumn()) < 2;
    }

    public static int getManhattanDistance(Cell start, Cell finish) {

        int rowDifference = start.getCoordination().getRow() - finish.getCoordination().getRow();
        int columnDifference = start.getCoordination().getColumn() - finish.getCoordination().getColumn();

        return Math.abs(rowDifference) + Math.abs(columnDifference);
    }

    public static boolean isTargetCellAvailableForMeleeAttack(Cell attackerCell, Cell victimCell) {

        int manhattanDistance = getManhattanDistance(attackerCell, victimCell);

        return (manhattanDistance <= 1 && manhattanDistance > 0) ||
                isCellsDiagonalWith2ManhattanDistance(attackerCell, victimCell);
    }

    public static boolean isTargetCellAvailableForRangedAttack(Cell attackerCell, Cell victimCell, int attackRange) {

        int manhattanDistance = getManhattanDistance(attackerCell, victimCell);

        return manhattanDistance > 1 && manhattanDistance <= attackRange &&
                !isCellsDiagonalWith2ManhattanDistance(attackerCell, victimCell);

    }

    private static boolean isCellsDiagonalWith2ManhattanDistance(Cell cell1, Cell cell2) {

        return
                Math.abs(cell1.getCoordination().getRow() - cell2.getCoordination().getRow()) == 1 &&
                        Math.abs(cell1.getCoordination().getColumn() - cell2.getCoordination().getColumn()) == 1;
    }
}
