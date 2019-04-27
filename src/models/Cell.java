package models;

public class Cell {

    private Coordination coordination;
    private Card card;
    private Item item;
    public boolean isThereFlag = false;

    public Coordination getCoordination() {

        return coordination;
    }

    public void setCoordination(int row, int column) {

        coordination.setRow(row);
        coordination.setColumn(column);
    }

    public Card getCard() {

        return card;
    }

    public Item getItem() {

        return item;
    }
}
