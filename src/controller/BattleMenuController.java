package controller;

import models.*;
import models.GamePlay.Match;
import request.battleMenuRequest.BattleMenuRequest;
import request.battleMenuRequest.battleMenuRequestChilds.CustomGameRequest;
import request.battleMenuRequest.battleMenuRequestChilds.MultiPlayerMenuRequest;
import request.battleMenuRequest.battleMenuRequestChilds.RequestMatchType;
import view.battleMenuView.BattleMenuView;
import view.battleMenuView.battleMenuViewChilds.BattleMenuError;

import java.util.ArrayList;

public class BattleMenuController {
    private static BattleMenuController battleMenuController;
    private BattleMenuRequest battleMenuRequest = BattleMenuRequest.getInstance();
    private BattleMenuView battleMenuView = BattleMenuView.getInstance();
    private Account account;
    private MatchType matchType;

    public static BattleMenuController getInstance() {

        if (battleMenuController == null)
            battleMenuController = new BattleMenuController();

        return battleMenuController;
    }

    public void battleMenuControllerMain() {
        account = Controller.getInstance().getAccount();

        if (!checkValidateDeck()) {
            Controller.getInstance().addStack(StartMenuController.getInstance());
            battleMenuView.showError(BattleMenuError.INVALID_DECK);
            return;
        }

        battleMenuView.showBattleMenuPlayerType();
        BattleMenuRequest request = battleMenuRequest.getCommand();

        if (request instanceof RequestMatchType)
            handelMatchType((RequestMatchType) request);

    }

    public boolean checkValidateDeck() {

        return account.getCollection().getSelectedDeck().isDeckValidate();
    }

    public void handelMatchType(RequestMatchType requestMatchType) {
        if (requestMatchType.getMatchType() == null) {
            Controller.getInstance().addStack(StartMenuController.getInstance());
            return;
        }
        switch (requestMatchType.getMatchType()) {

            case MULTI_PLAYER:
                this.matchType = MatchType.MULTI_PLAYER;
                playMultiPlayer();
                break;

            case SINGLE_PLAYER:
                this.matchType = MatchType.SINGLE_PLAYER;
                if (requestMatchType.getModeOfGame().equals(MatchType.STORY))
                    playSinglePlayerStoryMode();

                else if (requestMatchType.getModeOfGame().equals(MatchType.CUSTOMGAME))
                    playSinglePlayerCustomMode();
                break;
        }

    }

    public void playMultiPlayer() {
        battleMenuView.showUsers(getUsernameOfAllUsers(account));
        String name = battleMenuRequest.getUserName();

        if (LoginMenu.getInstance().checkIfAccountExist(name) && !name.equals(account.getUserName())) {
            Account opponent = LoginMenu.getInstance().getAccountByUserName(name);
            if (opponent.getCollection().getSelectedDeck().isDeckValidate())
                startMultiPlayerGame(opponent);
            else battleMenuView.showError(BattleMenuError.INVALID_DECK_SECOND_PLAYER);

        } else
            battleMenuView.showError(BattleMenuError.INVALID_USER);

    }

    public void playSinglePlayerCustomMode() {
        CustomGameRequest customGameRequest = (CustomGameRequest) BattleMenuRequest.getInstance().customGame();
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
                Controller.getInstance().addStack(StartMenuController.getInstance());
                return;
            } else {
                playerAI = Account.getAIAccount(customGameRequest.getMode());
                Match match = new Match(customGameRequest.getMode(), account, playerAI);
                BattleController.getInstance().mainBattleController(match);
                Controller.getInstance().addStack(StartMenuController.getInstance());
                return;
            }

        }
    }

    public void playSinglePlayerStoryMode() {
        int mode = BattleMenuRequest.getInstance().storyGame();
        Account playerAI;
        Match match;
        switch (mode) {
            case 1:
                playerAI = Account.getAIAccount(MatchType.KILL_THE_HERO);
                match = new Match(MatchType.KILL_THE_HERO, account, playerAI);
                BattleController.getInstance().mainBattleController(match);
                Controller.getInstance().addStack(StartMenuController.getInstance());
                break;
            case 2:
                playerAI = Account.getAIAccount(MatchType.HOLD_THE_FLAG);
                match = new Match(MatchType.HOLD_THE_FLAG, account, playerAI);
                BattleController.getInstance().mainBattleController(match);
                Controller.getInstance().addStack(StartMenuController.getInstance());
                break;
            case 3:
                playerAI = Account.getAIAccount(MatchType.COLLECT_THE_FLAGS);
                match = new Match(6, account, playerAI);
                BattleController.getInstance().mainBattleController(match);
                Controller.getInstance().addStack(StartMenuController.getInstance());
                break;
            case 0:
                battleMenuView.showError(BattleMenuError.INVALID_COMMAND);
                break;
        }
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
        MultiPlayerMenuRequest request = (MultiPlayerMenuRequest) battleMenuRequest.startMultiPlayerGameRequest();
        if (request == null)
            return;
        else {
            if (request.getMode().equals(MatchType.COLLECT_THE_FLAGS)) {

                Match match = new Match(request.getNumberOfFlags(), account, player2);
                BattleController.getInstance().mainBattleController(match);
                Controller.getInstance().addStack(StartMenuController.getInstance());
                return;
            } else {
                Match match = new Match(request.getMode(), account, player2);
                BattleController.getInstance().mainBattleController(match);
                Controller.getInstance().addStack(StartMenuController.getInstance());

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
