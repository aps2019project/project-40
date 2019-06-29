package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.Card;
import models.GamePlay.Match;

import java.io.IOException;
import java.util.ArrayList;

public class GraveYardController {
    @FXML
    private Label cardName;
    @FXML
    private Pane pane;
    @FXML
    private Label typelabel;

    @FXML
    private ImageView forward;

    @FXML
    private ImageView back;

    @FXML
    private VBox vboxGraveYardCards;

    @FXML
    void change() {
        if (forward.isDisable()) {
            pane.setLayoutX(pane.getLayoutX() - 240);
            forward.setDisable(false);
            back.setDisable(true);

        } else {

            pane.setLayoutX(pane.getLayoutX() + 240);
            forward.setDisable(true);
            back.setDisable(false);

        }
    }

    public void setInformation(String cardName, String type) {
        this.cardName.setText(cardName);
        this.typelabel.setText(type);
    }

    public void loadCards(Match match) {
        ArrayList<Card> cards = match.getPlayer1GraveYard().getCards();
        Node[] nodes;
        for (int i = vboxGraveYardCards.getChildren().size() - 1; i >= 0; i--)
            vboxGraveYardCards.getChildren().remove(vboxGraveYardCards.getChildren().get(i));
        nodes = new Node[cards.size()];

        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../view/cardBackground/GraveYardItem.fxml"));
                nodes[i] = fxmlLoader.load();
                GraveYardController controller = fxmlLoader.getController();
                controller.setInformation(cards.get(i).getCardName(), cards.get(i).getType().toString());
                vboxGraveYardCards.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
