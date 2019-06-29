package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import models.Card;
import models.CardType;
import models.Deck;

import java.io.IOException;


public class DeckController {
    private Deck deck;
    @FXML
    private Label numOfMinion;

    @FXML
    private Label numOfSpells;

    @FXML
    private Label deckName;

    @FXML
    private Label numOfItem;
    private double x, y;

    @FXML
    void deleteDeck() {
        Deck deck = Controller.getInstance().getAccount().getCollection().getDeckByName(deckName.getText());
        Controller.getInstance().getAccount().getCollection().getDecks().remove(deck);
        for (Card card : deck.getCards())
            Controller.getInstance().getAccount().getCollection().getCards().add(card);
        Controller.getInstance().collectionController.showDecks();
        Controller.getInstance().collectionController.showCards();
    }

    void setInformation(Deck deck) {
        this.deck = deck;
        deckName.setText(deck.getDeckName());
        numOfMinion.setText(String.valueOf(deck.countCardType(CardType.MINION)));
        numOfItem.setText(String.valueOf(deck.countCardType(CardType.USABLE_ITEM)));
        numOfSpells.setText(String.valueOf(deck.countCardType(CardType.SPELL)));
    }

    @FXML
    void gotoDeckMenu() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/collectionMenuView/DeckMenu.fxml"));
            Parent root = fxmlLoader.load();
            DeckMenu deckMenu = fxmlLoader.getController();
            deckMenu.setInfo(deck);
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

}
