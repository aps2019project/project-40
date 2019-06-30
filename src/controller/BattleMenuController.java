package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.*;
import models.GamePlay.Match;
import request.battleMenuRequest.battleMenuRequestChilds.MultiPlayerMenuRequest;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BattleMenuController implements Initializable {
    double x, y;
    private Account account;
    private MatchType matchType;
    @FXML
    private Button btnMultiPlayer;

    @FXML
    private AnchorPane singlePlayerPane;

    @FXML
    private ToggleGroup mode;

    @FXML
    private TextField flagsNum;

    @FXML
    private RadioButton radioMode1;

    @FXML
    private RadioButton radioMode3;

    @FXML
    private RadioButton radioMode2;

    @FXML
    private Button btnMode3;

    @FXML
    private Button btnMode1;

    @FXML
    private Button btnMode2;

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


    @FXML
    void playSinglePlayer(ActionEvent event) {
        if (!checkValidateDeck())
            return;

        singlePlayerPane.setVisible(true);
    }

    @FXML
    void storyMode(ActionEvent event) {
        int mode = 0;
        Button button = (Button) event.getSource();
        if (button.equals(btnMode1))
            mode = 1;
        else if (button.equals(btnMode2))
            mode = 2;
        else if (button.equals(btnMode3))
            mode = 3;
        Account playerAI;
        Match match;
        switch (mode) {
            case 1:
                playerAI = Account.getAIAccount(MatchType.KILL_THE_HERO);
                match = new Match(MatchType.KILL_THE_HERO, account, playerAI);
                BattleController.getInstance().mainBattleController(match, Controller.stage);
                break;
            case 2:
                playerAI = Account.getAIAccount(MatchType.HOLD_THE_FLAG);
                match = new Match(MatchType.HOLD_THE_FLAG, account, playerAI);
                BattleController.getInstance().mainBattleController(match, Controller.stage);
                break;
            case 3:
                playerAI = Account.getAIAccount(MatchType.COLLECT_THE_FLAGS);
                match = new Match(6, account, playerAI);
                BattleController.getInstance().mainBattleController(match, Controller.stage);
                break;
        }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        account = Controller.getInstance().getAccount();
    }

    public boolean checkValidateDeck() {
        if (account.getCollection().getSelectedDeck() != null)
            return account.getCollection().getSelectedDeck().isDeckValidate();
        return false;
    }

    public void playMultiPlayer() {
/*        battleMenuView.showUsers(getUsernameOfAllUsers(account));
        String name = battleMenuRequest.getUserName();

        if (LoginMenu.getInstance().checkIfAccountExist(name) && !name.equals(account.getUserName())) {
            Account opponent = LoginMenu.getInstance().getAccountByUserName(name);
            if (opponent.getCollection().getSelectedDeck().isDeckValidate())
                startMultiPlayerGame(opponent);
            else battleMenuView.showError(BattleMenuError.INVALID_DECK_SECOND_PLAYER);

        } else
            battleMenuView.showError(BattleMenuError.INVALID_USER);*/

    }

    public void playSinglePlayerCustomMode() {
/*        CustomGameRequest customGameRequest = (CustomGameRequest) BattleMenuRequest.getInstance().customGame();
        if (customGameRequest.getMode() == null)
            battleMenuView.showError(BattleMenuError.INVALID_COMMAND);
        else {
            account.getCollection().changeSelectedDeck(customGameRequest.getDeckName());
            if (!checkValidateDeck()) {
                battleMenuView.showError(BattleMenuError.INVALID_DECK);
                return;
            }

            Account playerAI;
            if (customGameRequest.getMode().equals(MatchType.COLLECT_THE_FLAGS)) {
                playerAI = Account.getAIAccount(MatchType.COLLECT_THE_FLAGS);
                Match match = new Match(customGameRequest.getFlagsNumber(), account, playerAI);
                BattleController.getInstance().mainBattleController(match);

                return;
            } else {
                playerAI = Account.getAIAccount(customGameRequest.getMode());
                Match match = new Match(customGameRequest.getMode(), account, playerAI);
                BattleController.getInstance().mainBattleController(match);

                return;
            }

        }*/
    }


    public static ArrayList<String> getUsernameOfAllUsers(Account currentAccount) {
        ArrayList<String> listOfUsers = new ArrayList<>();
        for (Account account : LoginMenu.getUsers()) {
            if (account.getUserName().equals(currentAccount.getUserName()))
                continue;

            listOfUsers.add(account.getUserName());
        }
        return listOfUsers;
    }

    public void startMultiPlayerGame(Account player2) {
        MultiPlayerMenuRequest request = null;//= (MultiPlayerMenuRequest) battleMenuRequest.startMultiPlayerGameRequest();
        if (request == null)
            return;
        else {
            if (request.getMode().equals(MatchType.COLLECT_THE_FLAGS)) {

                Match match = new Match(request.getNumberOfFlags(), account, player2);
                BattleController.getInstance().mainBattleController(match, Controller.stage);
                return;
            } else {
                Match match = new Match(request.getMode(), account, player2);
                BattleController.getInstance().mainBattleController(match, Controller.stage);

            }

        }
    }

    public void matchIsFinished(Account player2, GameStatus gameStatus) {
        History history = new History();
        history.setYourStatus(gameStatus);
        history.setLocalDateTime();
        history.setOponnentUserName(player2.getUserName());
        account.addMatchHistory(history);
        if (gameStatus.equals(GameStatus.WIN))
            account.incrementWinsNumber();

        if (player2.isAI())
            return;

        History newHistory = new History();
        history.setOponnentUserName(account.getUserName());
        history.setLocalDateTime();
        switch (gameStatus) {
            case WIN:
                history.setYourStatus(GameStatus.LOST);
                break;
            case DRAW:
                history.setYourStatus(GameStatus.DRAW);
                break;
            case LOST:
                history.setYourStatus(GameStatus.WIN);
                player2.incrementWinsNumber();
                break;
        }
        player2.addMatchHistory(newHistory);
    }

}
