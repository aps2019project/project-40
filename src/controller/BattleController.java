package controller;

import models.*;
import models.GamePlay.GameLogic;
import models.GamePlay.Match;
import request.battleRequest.BattleRequest;
import request.battleRequest.BattleRequestChilds.*;
import view.battleView.*;

import java.util.ArrayList;

public class BattleController {

    private static BattleController battleController;
    private BattleRequest battleRequest = BattleRequest.getInstance();
    private BattleView battleView = BattleView.getInstance();
    private Match match;
    private GameLogic gameLogic;

    //TODO check validity of card request
    //TODO check destination validity

    public static BattleController getInstance() {

        if (battleController == null) {

            battleController = new BattleController();
        }

        return battleController;
    }

    public void mainBattleController(Match match) {

        //todo
        this.match = match;
        gameLogic = match.getGameLogic();

    }

    private void manageRequest() {

        while (true) {

            BattleRequest request = battleRequest.getRequest();

            if (request instanceof SelectAndUseCardRequest)
                selectAndUseCardRequest((SelectAndUseCardRequest) request);

            if (request instanceof UseSpecialPowerRequest)
                useSpecialPowerRequest((UseSpecialPowerRequest) request);

            if (request instanceof ShowCardInfoRequest)
                showCardInfoRequest((ShowCardInfoRequest) request);

            if (request instanceof EnterGraveYardRequest)
                enterGraveYardRequest((EnterGraveYardRequest) request);

            if (request instanceof InsertCardRequest)
                insertCardRequest((InsertCardRequest) request);

            if (request instanceof RequestWithoutVariable)
                requestWithoutVariable((RequestWithoutVariable) request);
        }
    }

    private void selectAndUseCardRequest(SelectAndUseCardRequest request) {
        //todo
        if (request.isForMove()) ;
        if (request.isForAttack()) ;
        if (request.isForAttackCombo()) ;
        if (request.isForShowInfo()) ;
        if (request.isForUse()) ;

    }

    private void useSpecialPowerRequest(UseSpecialPowerRequest request) {


    }

    private void showCardInfoRequest(ShowCardInfoRequest request) {

        Card card = Collection.findCardByCardID(
                match.findPlayerPlayingThisTurn().getCollection().getSelectedDeck().getCards(), request.getCardID());

        try {
            ShowCardsBattleView showCardsBattleView = new ShowCardsBattleView();
            showCardsBattleView.setCard(card);
            showCardsBattleView.show(showCardsBattleView);

        } catch (NullPointerException e) {
            BattleLog.errorInvalidCardID();
        }
    }

    private void enterGraveYardRequest(EnterGraveYardRequest request) {
        //todo
        if (request.isForShowInfo()) ;
        if (request.isForShowCards()) ;
        if (request.isForExit()) ;
    }

    private void insertCardRequest(InsertCardRequest request) {

        Card card = Collection.findCardByCardName(
                match.findPlayerPlayingThisTurn().getHand().getCards(), request.getCardName());

        Coordination coordination = new Coordination();
        coordination.setRow(request.getRow());
        coordination.setColumn(request.getColumn());

        if (isOutOfTable(coordination)) {
            BattleLog.errorInvalidTarget();
            return;
        }

        Cell cell = match.getTable().getCellByCoordination(coordination);

        try {
            if (card instanceof Unit) {

                if (isCellFill(cell)) {
                    BattleLog.errorCellIsFill();
                    return;
                }
                if (!isCellAvailable(coordination)) {
                    BattleLog.errorCellNotAvailable();
                    return;
                }

                gameLogic.insertProcess((Unit) card, cell);

            } else {

            }

        } catch (NullPointerException e) {
        }
    }

    private boolean isOutOfTable(Coordination coordination) {

        if (coordination.getRow() >= Table.ROWS && coordination.getRow() < 0 &&
                coordination.getColumn() >= Table.COLUMNS && coordination.getColumn() < 0) {

            BattleLog.errorInvalidTarget();
            return true;
        }

        return false;
    }

    private boolean isCellFill(Cell cell) {

        if (cell.getCard() == null) return false;
        return true;
    }

    private boolean isCellAvailable(Coordination coordination) {

        Table table = match.getTable();
        Coordination aroundCoordination = new Coordination();

        for (int row = -1; row <= 1; row++) {
            for (int column = -1; column <= 1; column++) {

                if (row == 0 && column == 0) continue;

                try {
                    aroundCoordination.setRow(coordination.getRow() + row);
                    aroundCoordination.setColumn(coordination.getColumn() + column);
                    if (table.getCellByCoordination(aroundCoordination).getCard().getTeam().equals(
                            match.findPlayerPlayingThisTurn().getUserName())) return true;

                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return false;
    }

    private void requestWithoutVariable(RequestWithoutVariable request) {
        //todo
        if (request.getEnumRequest() == RequestWithoutVariableEnum.GAME_INFO_REQUEST)
            gameInfoRequest();

        else if (request.getEnumRequest() == RequestWithoutVariableEnum.SHOW_MY_MINIONS_REQUEST)
            showMyMinionsRequest();

        else if (request.getEnumRequest() == RequestWithoutVariableEnum.SHOW_OPPONENT_MINIONS_REQUEST)
            showOpponentMinionsRequest();

        else if (request.getEnumRequest() == RequestWithoutVariableEnum.SHOW_NEXT_CARD_REQUEST)
            showNextCardRequest();

        else if (request.getEnumRequest() == RequestWithoutVariableEnum.SHOW_HAND_REQUEST)
            showHandRequest();

        else if (request.getEnumRequest() == RequestWithoutVariableEnum.END_TURN_REQUEST) ;
        else if (request.getEnumRequest() == RequestWithoutVariableEnum.SHOW_COLLECTED_ITEM_REQUEST) ;
        else if (request.getEnumRequest() == RequestWithoutVariableEnum.END_GAME_REQUEST) ;

        else if (request.getEnumRequest() == RequestWithoutVariableEnum.HELP_REQUEST)
            BattleLog.showHelp();
    }

    private void gameInfoRequest() {

        Cell[][] cells = match.getTable().getCells();

        if (match.getMatchType() == MatchType.KILL_THE_HERO)
            gameInfoRequestKillTheHeroMode(cells);

        else if (match.getMatchType() == MatchType.HOLD_THE_FLAG)
            gameInfoRequestHoldTheFlagMode(cells);

        else if (match.getMatchType() == MatchType.COLLECT_THE_FLAGS)
            gameInfoRequestCollectTheFlagsMode(cells);
    }

    private void gameInfoRequestKillTheHeroMode(Cell[][] cells) {

        GameInfoBattleViewKillTheHero gameInfoBattleViewKillTheHero = new GameInfoBattleViewKillTheHero();
        gameInfoBattleViewKillTheHero.setPlayer1Mana(match.getPlayer1Mana());
        gameInfoBattleViewKillTheHero.setPlayer2Mana(match.getPlayer2Mana());

        //for find the hero
        for (Cell[] row : cells) {
            for (Cell cell : row) {

                try {
                    Card card = cell.getCard();

                    if (card.getType() == CardType.HERO) {

                        Unit hero = (Unit) card;

                        if (match.getPlayer1().getUserName().equals(card.getTeam()))
                            gameInfoBattleViewKillTheHero.setPlayer1HeroHP(hero.getHealthPoint());
                        else
                            gameInfoBattleViewKillTheHero.setPlayer2HeroHP(hero.getHealthPoint());
                    }
                } catch (NullPointerException e) {
                    //there isn't hero in this cell
                }
            }
        }
        gameInfoBattleViewKillTheHero.show(gameInfoBattleViewKillTheHero);
    }

    private void gameInfoRequestHoldTheFlagMode(Cell[][] cells) {

        GameInfoBattleViewHoldTheFlag gameInfoBattleViewHoldTheFlag = new GameInfoBattleViewHoldTheFlag();

        for (Cell[] row : cells) {
            for (Cell cell : row) {

                try {
                    Card card = cell.getCard();
                    if (card.getType() == CardType.FLAG) {

                        gameInfoBattleViewHoldTheFlag.setFlagCoordination(cell.getCoordination());
                        gameInfoBattleViewHoldTheFlag.show(gameInfoBattleViewHoldTheFlag);
                        return;
                    }
                } catch (NullPointerException e) {
                    //there isn't flag in this cell
                }
            }
        }

        for (Cell[] row : cells) {
            for (Cell cell : row) {

                try {
                    Card card = cell.getCard();

                    if (card.getType() == CardType.HERO || card.getType() == CardType.MINION) {

                        Unit unit = (Unit) card;
                        if (unit.getFlag() > 0) {
                            gameInfoBattleViewHoldTheFlag.setFlagHolderName(unit.getCardName());
                            gameInfoBattleViewHoldTheFlag.setFlagHolderTeam(card.getTeam());
                            gameInfoBattleViewHoldTheFlag.show(gameInfoBattleViewHoldTheFlag);
                            return;
                        }
                    }
                } catch (NullPointerException e) {
                    //there isn't unit in this cell
                }
            }
        }
    }

    private void gameInfoRequestCollectTheFlagsMode(Cell[][] cells) {

        GameInfoBattleViewCollectTheFlags gameInfoBattleViewCollectTheFlags = new GameInfoBattleViewCollectTheFlags();

        for (Cell[] row : cells) {
            for (Cell cell : row) {

                try {
                    Card card = cell.getCard();

                    if (card.getType() == CardType.FLAG)
                        gameInfoBattleViewCollectTheFlags.setFlagCoordination(cell.getCoordination());

                    else if (card.getType() == CardType.MINION || card.getType() == CardType.HERO) {

                        Unit unit = (Unit) card;

                        if (unit.getFlag() > 0)
                            gameInfoBattleViewCollectTheFlags.setFlagHolder(
                                    unit.getCardName(), unit.getTeam(), unit.getFlag());
                    }
                } catch (NullPointerException e) {
                    //there is any flag in this cell
                }
            }
        }
        gameInfoBattleViewCollectTheFlags.show(gameInfoBattleViewCollectTheFlags);
    }

    private void showMyMinionsRequest() {

        showMinionsRequestCommonCode(match.findPlayerPlayingThisTurn());
    }

    private void showOpponentMinionsRequest() {

        if (match.findPlayerPlayingThisTurn().equals(match.getPlayer1()))
            showMinionsRequestCommonCode(match.getPlayer2());

        else
            showMinionsRequestCommonCode(match.getPlayer1());
    }

    private void showMinionsRequestCommonCode(Account account) {

        ShowMinionsBattleView showMinionsBattleView = new ShowMinionsBattleView();
        Cell[][] cells = match.getTable().getCells();

        for (Cell[] row : cells) {
            for (Cell cell : row) {

                try {
                    Card card = cell.getCard();

                    if (card.getType() == CardType.MINION &&
                            account.getUserName().equals(card.getTeam())) {

                        Unit unit = (Unit) card;
                        showMinionsBattleView.setMinion(unit.getCardID(), unit.getCardName(),
                                unit.getHealthPoint(), cell.getCoordination(), unit.getAttackPoint());
                    }
                } catch (NullPointerException e) {
                    //there isn't my minion in this cell
                }
            }
        }
        showMinionsBattleView.show(showMinionsBattleView);
    }

    private void showNextCardRequest() {

        Account player = match.findPlayerPlayingThisTurn();
        Card card = player.getHand().getReserveCard();
        ShowCardsBattleView showCardsBattleView = new ShowCardsBattleView();

        try {
            showCardsBattleView.setCard(card);
            showCardsBattleView.show(showCardsBattleView);

        } catch (NullPointerException e) {
            System.out.println("You don't have reserve card");
        }
    }

    private void showHandRequest() {

        ArrayList<Card> cards = match.findPlayerPlayingThisTurn().getHand().getCards();
        cards.add(match.findPlayerPlayingThisTurn().getHand().getReserveCard());
        ShowCardsBattleView showCardsBattleView = new ShowCardsBattleView();

        for (Card card : cards)
            showCardsBattleView.setCard(card);

        showCardsBattleView.show(showCardsBattleView);
    }

    private void endTurnRequest() {
        //todo
    }

    private void showCollectedItemRequest() {
        //todo
    }

    private void endGameRequest() {
        //todo
    }
}
