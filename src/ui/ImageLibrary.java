package ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public enum ImageLibrary {

    Background("src/resources/battle/Background.png"),
    CursorImage("src/resources/battle/mouse_auto.png"),
    LeftImageHeader("src/resources/battle/gameInfo/season_rank_gold.png"),
    RightImageHeader("src/resources/battle/gameInfo/season_rank_silver.png"),
    FillMana("src/resources/battle/gameInfo/icon_mana.png"),
    EmptyMana("src/resources/battle/gameInfo/icon_mana_inactive.png"),
    EndTurnButton("src/resources/battle/buttons/button_end_turn_mine.png"),
    EndTurnPopup("src/resources/battle/popup/notification_your_turn.png"),
    HandCardAround("src/resources/battle/cardsAround/handCardBackground.png");

    private Image image;

    ImageLibrary(String path) {

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(path));
            image = new Image(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getImage() {
        return image;
    }
}
