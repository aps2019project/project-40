package ui.battleUI;

import javafx.scene.ImageCursor;
import javafx.scene.image.*;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.ImageLibrary;

public class BattleUI {

    private ImageView background;

    public void battleUI(Stage mainStage) {

        Pane pane = new Pane();
        background = new ImageView(ImageLibrary .Background.getImage());
        background.fitHeightProperty().bind(mainStage.heightProperty());
        background.fitWidthProperty().bind(mainStage.widthProperty());

        GridPane polygons = PolygonsBuilder.getPolygonsBuilder().getPolygons();

        pane.getChildren().addAll(background, polygons);
        Scene scene = new Scene(pane, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("/ui/style/style.css").toExternalForm());
        scene.setCursor(new ImageCursor(ImageLibrary.CursorImage.getImage()));
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }
}
