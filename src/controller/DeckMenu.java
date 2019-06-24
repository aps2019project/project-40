package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import models.Account;
import models.Card;
import models.Deck;

import java.io.IOException;
import java.util.ArrayList;

public class DeckMenu {
    private Deck deck;
    private double x, y;
    @FXML
    private TextField txtCardId;

    @FXML
    private HBox hBox2;

    @FXML
    private HBox hBox1;

    @FXML
    private Label labelError;

    @FXML
    void gotoCollectionMenu() {
        Account.save(Controller.getInstance().getAccount());
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/collectionMenuView/CollectionMenuView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            scene.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            scene.setOnMouseDragged(event -> {

                Controller.stage.setX(event.getScreenX() - x);
                Controller.stage.setY(event.getScreenY() - y);

            });
            Controller.stage.setScene(scene);
        } catch (IOException e) {
        }

    }

    @FXML
    void validateDeck() {
        if (deck.isDeckValidate())
            labelError.setText(CollectionErrors.DECK_IS_VALID.toString());
        else
            labelError.setText(CollectionErrors.DECK_IS_NOT_VALID.toString());
    }

    @FXML
    void selectDeck() {
        if (deck.isDeckValidate())
            Controller.getInstance().getAccount().getCollection().setSelectedDeck(deck);
        else
            labelError.setText(CollectionErrors.DECK_IS_NOT_VALID.toString());
    }

    @FXML
    void removeCard() {
        String cardID = txtCardId.getText();
        CollectionErrors collectionErrors;

        collectionErrors = Controller.getInstance().getAccount().getCollection().removeFromDeck(cardID, deck.getDeckName());
        if (collectionErrors != null)
            labelError.setText(collectionErrors.toString());
        txtCardId.setText("");
        showCards();
    }

    @FXML
    void mouseIn(MouseEvent event) {
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            button.setOpacity(1);

        }
    }

    @FXML
    void mouseOut(MouseEvent event) {
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            button.setOpacity(0.8);
        }
    }

    public void setInfo(Deck deck) {
        this.deck = deck;
        showCards();
    }

    public void showCards() {
        Node[] nodes;
        ArrayList<Card> cards = deck.getCards();
        nodes = new Node[cards.size()];
        for (int i = hBox1.getChildren().size() - 1; i >= 0; i--) {
            hBox1.getChildren().remove(hBox1.getChildren().get(i));
        }
        for (int i = hBox2.getChildren().size() - 1; i >= 0; i--) {
            hBox2.getChildren().remove(hBox2.getChildren().get(i));
        }
        int i = 0;
        for (; i < nodes.length / 2 + nodes.length % 2; i++) {
            try {


                Card card = cards.get(i);
                final String ID = card.getCardID();
                FXMLLoader fxmlLoader = new FXMLLoader();
                switch (card.getType()) {
                    case USABLE_ITEM:
                        fxmlLoader.setLocation(getClass().getResource("../view/cardBackground/Item.fxml"));
                        break;
                    case MINION:
                        fxmlLoader.setLocation(getClass().getResource("../view/cardBackground/MinionCard.fxml"));
                        break;
                    case HERO:
                        fxmlLoader.setLocation(getClass().getResource("../view/cardBackground/General.fxml"));
                        break;
                    case SPELL:
                        fxmlLoader.setLocation(getClass().getResource("../view/cardBackground/SpellCard.fxml"));
                        break;
                }
                nodes[i] = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setInformation(card);
                nodes[i].setOnMouseClicked(event -> {
                    txtCardId.setText(ID);
                });
                hBox1.getChildren().add(nodes[i]);
            } catch (IOException e) {

            }
        }
        for (; i < nodes.length; i++) {
            try {
                Card card = cards.get(i);
                FXMLLoader fxmlLoader = new FXMLLoader();
                final String ID = card.getCardID();
                boolean isContinue = false;
                switch (card.getType()) {
                    case USABLE_ITEM:
                        fxmlLoader.setLocation(getClass().getResource("../view/cardBackground/Item.fxml"));
                        break;
                    case MINION:
                        fxmlLoader.setLocation(getClass().getResource("../view/cardBackground/MinionCard.fxml"));
                        break;
                    case HERO:
                        fxmlLoader.setLocation(getClass().getResource("../view/cardBackground/General.fxml"));
                        break;
                    case SPELL:
                        fxmlLoader.setLocation(getClass().getResource("../view/cardBackground/SpellCard.fxml"));
                        break;
                    default:
                        isContinue = true;

                }
                if (isContinue)
                    continue;
                nodes[i] = fxmlLoader.load();
                nodes[i].setOnMouseClicked(event -> {
                    txtCardId.setText(ID);
                });
                CardController cardController = fxmlLoader.getController();
                cardController.setInformation(card);
                hBox2.getChildren().add(nodes[i]);
            } catch (IOException e) {

            }
        }

    }
}
