package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Account;
import models.Card;
import models.Deck;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CollectionController implements Initializable {
    private double x, y;
    @FXML
    private HBox hBox2;

    @FXML
    private HBox hBox1;

    @FXML
    private TextField txtToDeckName;

    @FXML
    private VBox deckVBox;

    @FXML
    private TextField txtCardId;

    @FXML
    private TextField txtDeckName;

    @FXML
    private Label labelError;

    @FXML
    private Label labelErrorInAdd;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Controller.getInstance().collectionController = this;
        showCards();
        showDecks();
    }

    @FXML
    void btnNewDeck(ActionEvent event) {
        labelError.setText("");
        for (Deck deck : Controller.getInstance().getAccount().getCollection().getDecks())
            if (deck.getDeckName().equals(txtDeckName.getText())) {
                createDeck(deck, deck.getDeckName());
                return;
            }
        createDeck(null, txtDeckName.getText());

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


    public void add() {
        String cardID = txtCardId.getText(), deckName = txtToDeckName.getText();
        if (cardID.isEmpty() || deckName.isEmpty())
            labelErrorInAdd.setText("No field can be empty");
        CollectionErrors collectionErrors = Controller.getInstance().getAccount().getCollection().addToDeck(cardID, deckName);
        if (collectionErrors != null)
            labelErrorInAdd.setText(collectionErrors.toString());
        txtCardId.setText("");
        showCards();
        showDecks();
    }

    public void createDeck(Deck deck, String deckName) {
        if (deckName.isEmpty()) {
            labelError.setText("Name cant be empty");
            return;
        }
        if (deck != null) {
            labelError.setText(CollectionErrors.ALREADY_A_DECK_WITH_THIS_USERNAME.toString());
            return;
        }
        deck = new Deck();
        deck.setDeckName(deckName);
        Controller.getInstance().getAccount().getCollection().getDecks().add(deck);
        showDecks();
    }


    public void saveDeckToName(String name, Account account) {
        if (account.getUserName().isEmpty())
            return;
        try {
            FileOutputStream fos = new FileOutputStream("defaultDecks/" + name + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // write object to file
            oos.writeObject(account.getCollection().getSelectedDeck());
            // closing resources
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCards() {
        Node[] nodes;
        ArrayList<Card> cards = Controller.getInstance().getAccount().getCollection().getCards();
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

    public void showDecks() {
        Node[] nodes;
        ArrayList<Deck> decks = Controller.getInstance().getAccount().getCollection().getDecks();
        nodes = new Node[decks.size()];
        for (int i = deckVBox.getChildren().size() - 1; i >= 0; i--)
            deckVBox.getChildren().remove(deckVBox.getChildren().get(i));
        for (int i = 0; i < nodes.length; i++) {
            try {
                final String deckName = decks.get(i).getDeckName();
                Deck deck = decks.get(i);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../view/cardBackground/DeckView.fxml"));
                nodes[i] = fxmlLoader.load();
                nodes[i].setOnMouseClicked(event -> {
                    txtToDeckName.setText(deckName);
                });
                DeckController deckController = fxmlLoader.getController();
                deckController.setInformation(deck);
                deckVBox.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
