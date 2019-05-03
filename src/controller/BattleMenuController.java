package controller;

import models.Account;
import models.LoginMenu;
import models.MatchType;
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

        if (battleMenuRequest instanceof RequestMatchType)
            handelMatchType((RequestMatchType) battleMenuRequest);


    }

    public boolean checkValidateDeck() {
        return account.getCollection().getSelectedDeck().isDeckValidate();
    }

    public void handelMatchType(RequestMatchType requestMatchType) {

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
        battleMenuView.showUsers(getUsernamOfAllUsers(account));
        String name = battleMenuRequest.getUserName();

        if (LoginMenu.getInstance().checkIfAccountExist(name)) {
            Account opponent = LoginMenu.getInstance().getAccountByUserName(name);
            if (opponent.getCollection().getSelectedDeck().isDeckValidate())
                startMultiPlayerGame(opponent);
            else battleMenuView.showError(BattleMenuError.INVALID_DECK_SECOND_PLAYER);

        } else
            battleMenuView.showError(BattleMenuError.INVALID_USER);

    }

    public void playSinglePlayerCustomMode() {
        CustomGameRequest customGameRequest=(CustomGameRequest) BattleMenuRequest.getInstance().customGame();
        if (customGameRequest.getMode()==null)
            battleMenuView.showError(BattleMenuError.INVALID_COMMAND);
        //else if (customGameRequest.getDeckName())
    }

    public void playSinglePlayerStoryMode() {
        int mode=BattleMenuRequest.getInstance().storyGame();
        if (mode==0)
            battleMenuView.showError(BattleMenuError.INVALID_COMMAND);
    }

    public static ArrayList<String> getUsernamOfAllUsers(Account currentAccount) {
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

            //todo
            //Match match=new Match();

        }
    }

}
