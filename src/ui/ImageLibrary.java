package ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public enum ImageLibrary {

    Background("src/resources/battle/Background.png"),
    CursorImage("src/resources/battle/mouse_auto.png");

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
