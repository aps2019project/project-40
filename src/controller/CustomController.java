package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class CustomController {

    @FXML
    private TextField txtHP;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAP;

    @FXML
    private TextField txtMana;

    @FXML
    private ImageView imgCard;
    @FXML
    private Label labelError;

    @FXML
    void addImage() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                Image image = new Image(file.toURI().toString());
                imgCard.setImage(image);
            } catch (Exception e) {
                labelError.setText("PLEASE SELECT IMAGE FILE");
            }
        }
    }

    @FXML
    void addCard() {
        try {
            int mana = Integer.parseInt(txtMana.getText());
            int ap = Integer.parseInt(txtAP.getText());
            int hp = Integer.parseInt(txtHP.getText());

        } catch (Exception e) {
        }
    }

}
