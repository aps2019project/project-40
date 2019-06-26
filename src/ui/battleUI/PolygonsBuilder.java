package ui.battleUI;

import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class PolygonsBuilder {

    private final int GRID_PANE_LAYOUT_X = 340;
    private final int GRID_PANE_LAYOUT_Y = 230;
    private final int GRID_PANE_V_SPACE = 5;
    private final int GRID_PANE_H_SPACE = 8;
    private final int POLYGON_HEIGHT = 50;
    private final int POLYGON_WIDTH = 60;

    private static PolygonsBuilder polygonsBuilder;
    private GridPane polygons = new GridPane();

    public static PolygonsBuilder getPolygonsBuilder() {
        if (polygonsBuilder == null) polygonsBuilder = new PolygonsBuilder();
        return polygonsBuilder;
    }

    public GridPane getPolygons() {

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
        perspective(polygons);
        return polygons;
    }

    private void perspective(GridPane polygons) {

        PerspectiveTransform e = new PerspectiveTransform();
        e.setUrx(9 * POLYGON_WIDTH + 8 * GRID_PANE_V_SPACE - 20);
        e.setUry(0);
        e.setUlx(20);
        e.setUly(0);
        e.setLrx(9 * POLYGON_WIDTH + 8 * GRID_PANE_V_SPACE);
        e.setLry(5 * POLYGON_HEIGHT + 4 * GRID_PANE_H_SPACE);
        e.setLlx(0);
        e.setLly(5 * POLYGON_HEIGHT + 4 * GRID_PANE_H_SPACE);

        polygons.setEffect(e);
    }
}
