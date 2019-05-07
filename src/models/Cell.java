package models;

public class Cell {

    private Coordination coordination;
    private Card card;
    private Item item;
    private boolean isThereFlag = false;

    public Card getCard() {

        return card;
    }

    public void setCard(Card card) {

        this.card = card;
    }

    public Item getItem() {

        return item;
    }

    public void setItem(Item item) {

        this.item = item;
    }

    public boolean isThereFlag() {

        return isThereFlag;
    }

    public void setThereFlag(boolean thereFlag) {

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

    public int manhattanDistance(Cell cell) {
        return Math.abs(cell.coordination.getRow() - coordination.getRow()) +
                Math.abs(cell.coordination.getColumn() - coordination.getColumn());
    }
}
