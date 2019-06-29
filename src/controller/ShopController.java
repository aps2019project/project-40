package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import models.*;
import models.GamePlay.Match;
import view.shopMenuView.ShopError;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopController implements Initializable {
    private Collection shopCollection = initializeShopCollection();
    private double x, y;
    Node[] shopNodes;
    @FXML
    private TextField searchCArdName;

    @FXML
    private Label money;

    @FXML
    private Label labelErrorInShop;

    @FXML
    private RadioButton optSearchInShop;

    @FXML
    private HBox hBox2;

    @FXML
    private HBox hBox1;

    @FXML
    private RadioButton optSearchInCollection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Controller.getInstance().shopController = this;
        money.setText(String.valueOf(Controller.getInstance().getAccount().getMoney()));
        showCards(shopCollection.getCards());
    }

    @FXML
    void showShop(ActionEvent event) {
        showCards(shopCollection.getCards());
    }

    @FXML
    void showCollection(ActionEvent event) {
        showCards(Controller.getInstance().getAccount().getCollection().getCards());
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
            button.setOpacity(0.6);
        }
    }

    @FXML
    void search() {
        if (searchCArdName.getText().trim().isEmpty()) {
            if (optSearchInCollection.isSelected())
                showCards(Controller.getInstance().getAccount().getCollection().getCards());
            else
                showCards(shopCollection.getCards());
        } else {
            if (optSearchInCollection.isSelected())
                searchCollection(searchCArdName.getText(), Controller.getInstance().getAccount().getCollection());
            else
                searchCollection(searchCArdName.getText(), shopCollection);
        }

    }

    @FXML
    void gotoStartMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/StartMenuView.fxml"));
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


    private boolean checkBuyItem(ArrayList<Card> cards) {
        int numOfItemInCollection = 0;
        for (Card card : cards)
            if (card.getType().equals(CardType.USABLE_ITEM))
                numOfItemInCollection++;

        return numOfItemInCollection >= 3;
    }

    public void buy(String cardName) {
        Account account = Controller.getInstance().getAccount();
        for (Card card : shopCollection.getCards())
            if (card.getCardName().equals(cardName)) {
                if (card.getType().equals(CardType.USABLE_ITEM) &&
                        checkBuyItem(Controller.getInstance().getAccount().getCollection().getCards())) {
                    labelErrorInShop.setText(ShopError.ALREADY_3_ITEM.toString());
                    return;
                }
                if (account.getMoney() - card.getPrice() >= 0) {
                    Card newCard = Card.deepClone(card);
                    account.setID(newCard);
                    account.getCollection().getCards().add(newCard);
                    account.setMoney(account.getMoney() - card.getPrice());
                    money.setText(String.valueOf(account.getMoney() - card.getPrice()));
                    labelErrorInShop.setText(ShopError.SUCCESS.toString());
                } else
                    labelErrorInShop.setText(ShopError.NOT_ENOUGH_MONEY.toString());
                return;
            }
        labelErrorInShop.setText(ShopError.CARD_NOT_FOUND.toString());
    }

    public void sell(String cardName) {
        Account account = Controller.getInstance().getAccount();
        ArrayList<Card> cards = account.getCollection().getCards();
        for (int i = cards.size() - 1; i >= 0; i--)
            if (cards.get(i).getCardName().equals(cardName)) {
                account.getCollection().getCards().remove(cards.get(i));
                account.setMoney(account.getMoney() + cards.get(i).getSellCost());
                money.setText(String.valueOf(account.getMoney() + cards.get(i).getSellCost()));
                account.getCollection().getCards().remove(cards.get(i));
                labelErrorInShop.setText(ShopError.SUCCESS.toString());
                return;
            }
        labelErrorInShop.setText(ShopError.CARD_NOT_FOUND.toString());
    }

    public void searchCollection(String cardName, Collection collection) {

        ArrayList<Card> cards = new ArrayList<>();
        for (Card card : collection.getCards()) {
            try {
                Pattern pattern=Pattern.compile(cardName);
                Matcher matcher=pattern.matcher(card.getCardName());
                if (matcher.find())
                    cards.add(card);
            } catch (Exception e) {
            }
        }
        if (cards.size() == 0)
            labelErrorInShop.setText(ShopError.CARD_NOT_FOUND.toString());
        else
            showCards(cards);
    }

    public Collection initializeShopCollection() {
        Collection collection = new Collection();
        JsonToCard.moveToCollection(collection);
        return collection;
    }

    public void showCards(ArrayList<Card> cards) {
        Node[] nodes;
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
                    if (cards.equals(shopCollection.getCards()))
                        buy(card.getCardName());
                    else sell(card.getCardName());
                });
                hBox1.getChildren().add(nodes[i]);
            } catch (IOException e) {

            }
        }
        for (; i < nodes.length; i++) {
            try {
                Card card = cards.get(i);
                FXMLLoader fxmlLoader = new FXMLLoader();
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
                    if (cards.equals(shopCollection.getCards()))
                        buy(card.getCardName());
                    else sell(card.getCardName());
                });
                CardController cardController = fxmlLoader.getController();
                cardController.setInformation(card);
                hBox2.getChildren().add(nodes[i]);
            } catch (IOException e) {

            }
        }
    }
}
