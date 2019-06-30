package ui.battleUI;

import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import models.Cell;
import models.GamePlay.Match;


public class TableBuilder {

    private final int GRID_PANE_LAYOUT_X = 340;
    private final int GRID_PANE_LAYOUT_Y = 230;
    private final int GRID_PANE_V_SPACE = 5;
    private final int GRID_PANE_H_SPACE = 8;
    private final int POLYGON_HEIGHT = 50;
    private final int POLYGON_WIDTH = 60;

    private GridPane polygons = new GridPane();
    private StackPane[][] table = new StackPane[5][9];

    GridPane getPolygons() {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {

                Rectangle rectangle = new Rectangle(POLYGON_WIDTH, POLYGON_HEIGHT);
                rectangle.setOpacity(0.2);
                rectangle.getStyleClass().add("enterMouseOnPolygon");
                polygons.add(rectangle, j, i);
            }
        }

        polygons.setVgap(GRID_PANE_V_SPACE);
        polygons.setHgap(GRID_PANE_H_SPACE);
        polygons.setLayoutX(GRID_PANE_LAYOUT_X);
        polygons.setLayoutY(GRID_PANE_LAYOUT_Y);
        //todo perspective(polygons);

        return polygons;
    }

    AnchorPane getTable(Match match) {

        AnchorPane tablePane = new AnchorPane();

        for (int row = 0; row < 5; row++) {

            int y = getY(row);

            for (int column = 0; column < 9; column++) {

                table[row][column] = new StackPane();
                table[row][column].relocate(getX(column), y);
                tablePane.getChildren().add(table[row][column]);
            }
        }

        updateTable(match);

        tablePane.relocate(0, 0);
        tablePane.setMouseTransparent(true);
        //todo perspective(tablePane);

        return tablePane;
    }

    public void updateTable(Match match) {

        Cell[][] cells = match.getTable().getCells();

        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 9; column++) {

                table[row][column].getChildren().removeAll(table[row][column].getChildren());

                if (cells[row][column].getFlag() != null)
                    table[row][column].getChildren().add(new ImageView("resources/battle/flag/flag"));

                if (cells[row][column].getCard() != null) {

                    ImageView image;

                    try {
                        image = new ImageView("resources/battle/units/" +
                                cells[row][column].getCard().getCardName() + "/stand");

                    } catch (Exception e) {
                        e.printStackTrace();
                        image = new ImageView("resources/battle/units/default/stand");
                    }


                    if (cells[row][column].getCard().getTeam().equals(match.getPlayer1().getUserName())) {
                        image.setScaleX(-1.3);
                        image.setScaleY(1.3);
                    } else {
                        image.setScaleX(1.3);
                        image.setScaleY(1.3);
                    }

                    table[row][column].getChildren().add(image);
                }
            }
        }
    }

    private void perspective(Pane Pane) {

        PerspectiveTransform e = new PerspectiveTransform();
        e.setUrx(9 * POLYGON_WIDTH + 8 * GRID_PANE_V_SPACE - 9);        //9 for perspective
        e.setUry(0);
        e.setUlx(9);
        e.setUly(0);
        e.setLrx(9 * POLYGON_WIDTH + 8 * GRID_PANE_V_SPACE);
        e.setLry(5 * POLYGON_HEIGHT + 4 * GRID_PANE_H_SPACE);
        e.setLlx(0);
        e.setLly(5 * POLYGON_HEIGHT + 4 * GRID_PANE_H_SPACE);

        Pane.setEffect(e);
    }

    private int getX(int column) {
        return GRID_PANE_LAYOUT_X + column * (GRID_PANE_H_SPACE + POLYGON_WIDTH);
    }

    private int getY(int row) {
        return GRID_PANE_LAYOUT_Y + row * (GRID_PANE_V_SPACE + POLYGON_HEIGHT) - 25;
        //10 because legs of units come in center of cell
    }
}
