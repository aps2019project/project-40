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
    private Match match;
    private GameLogic gameLogic;
    private BattleLogicController battleLogicController;
    private boolean isEndedGame = false;

    public static BattleController getInstance() {

        if (battleController == null) {

            battleController = new BattleController();
        }

        return battleController;
    }

    public void mainBattleController(Match match) {

        this.match = match;
        gameLogic = match.getGameLogic();
        battleLogicController = BattleLogicController.getBattleLogicController();
        battleLogicController.setGameLogic(gameLogic);
        battleLogicController.setMatch(match);
        manageRequest();
    }

    private void manageRequest() {

        while (true) {

            BattleRequest request = battleRequest.getRequest();

            if (request instanceof SelectAndUseCardRequest)
                selectAndUseCardRequest((SelectAndUseCardRequest) request);

            else if (request instanceof ShowCardInfoRequest)
                showCardInfoRequest((ShowCardInfoRequest) request);

            else if (request instanceof EnterGraveYardRequest)
                enterGraveYardRequest((EnterGraveYardRequest) request);

            else if (request instanceof InsertCardRequest)
                insertCardRequest((InsertCardRequest) request);

            else if (request instanceof RequestWithoutVariable)
                requestWithoutVariable((RequestWithoutVariable) request);

            if (isEndedGame) break;
        }
    }

    private void selectAndUseCardRequest(SelectAndUseCardRequest request) {
        //todo
        if (request.isForMove() || request.isForAttack() || request.isForAttackCombo() || request.isForUseSpecialPower()) {

            //check validity
            Card card = Collection.findCardByCardID(gameLogic.getCardsInTablePlayerPlayingThisTurn(), request.getID());

            if (card == null || !(card instanceof Unit)) {
                BattleLog.errorInvalidCardID();
                return;
            }

            Coordination destinationCoordination = new Coordination();
            destinationCoordination.setRow(request.getRow());
            destinationCoordination.setColumn(request.getColumn());
            if (battleLogicController.isOutOfTable(destinationCoordination)) return;

            Cell destinationCell = match.getTable().getCellByCoordination(destinationCoordination);

            //doing request
            if (request.isForMove())
                selectAndUseCardRequestMove(card, destinationCell);

            else if (request.isForAttack()) ;
            else if (request.isForAttackCombo()) ;
            else if (request.isForUseSpecialPower()) ;

        } else {

            Card item = Collection.findCardByCardName(
                    match.findPlayerPlayingThisTurn().getHand().getCollectiblesItem(), request.getID());

            if (item == null) {
                BattleLog.errorInvalidItemName();
                return;
            }

            //doing request
            if (request.isForShowInfo())
                selectAndUseCardRequestShowInfo(request, item);

            else if (request.isForUseItem()) ;
        }
    }

    private void selectAndUseCardRequestAttack(Unit unit, Cell targetCell, Coordination coordination) {

        if (!battleLogicController.isCellFill(targetCell)) {
            BattleLog.errorCellIsNotFill();
            return;
        }

        if (unit.getUnitType() == UnitType.MELEE) {

            if (!battleLogicController.isCellAvailableForMelee(unit.getCell(), targetCell)) return;
            //todo f(unit, cell)
        } else if (unit.getUnitType() == UnitType.RANGED) {

            if (!battleLogicController.isCellAvailableForRanged(unit.getCell(), targetCell, unit.getRange())) return;


        } else if (unit.getUnitType() == UnitType.HYBRID) ;
    }

    private void selectAndUseCardRequestMove(Card card, Cell destinationCell) {

        if (!battleLogicController.isCellAvailableForMove(card.getCell(), destinationCell)) {
            BattleLog.errorInvalidTarget();
            return;
        }
        if (battleLogicController.isUnitStunned((Unit) card)) {
            BattleLog.errorUnitIsStunned();
            return;
        }
        if (battleLogicController.isAttackedPreviously(card)) {
            BattleLog.errorUnitAttacked();
            return;
        }
        if (battleLogicController.isMovedPreviously(card)) {
            BattleLog.errorUnitMovedPreviously();
            return;
        }
        if (!battleLogicController.isDirectionWithoutEnemy(card.getCell(), destinationCell)) {
            BattleLog.errorCellNotAvailable();
            return;
        }

        gameLogic.moveProcess(card, destinationCell);
        BattleLog.logCardMoved(card.getCardID(),
                destinationCell.getCoordination().getRow(), destinationCell.getCoordination().getColumn());
        //todo if there is flag in cell get that
    }

    private void selectAndUseCardRequestShowInfo(SelectAndUseCardRequest request, Card item) {

        ShowSelectedItemInfoBattleView showSelectedItemInfoBattleView = new ShowSelectedItemInfoBattleView();
        showSelectedItemInfoBattleView.setName(item.getCardName());
        showSelectedItemInfoBattleView.setDescription(item.getDescription());
        showSelectedItemInfoBattleView.show(showSelectedItemInfoBattleView);
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
        //todo help
        if (request.isForShowInfo())
            enterGraveYardRequestShowInfo(request.getCardID());

        else if (request.isForShowCards())
            enterGraveYardRequestShowCards();

        else if (request.isForExit()) return;
    }

    private void enterGraveYardRequestShowInfo(String cardID) {

        ShowCardsBattleView showCardsBattleView = new ShowCardsBattleView();

        try {
            if (match.findPlayerPlayingThisTurn().equals(match.getPlayer1()))
                showCardsBattleView.setCard(Collection.findCardByCardID(match.getPlayer1GraveYard().getCards(), cardID));
            else
                showCardsBattleView.setCard(Collection.findCardByCardID(match.getPlayer2GraveYard().getCards(), cardID));

            showCardsBattleView.show(showCardsBattleView);
        } catch (NullPointerException e) {
            BattleLog.errorInvalidCardID();
        }
    }

    private void enterGraveYardRequestShowCards() {

        ShowCardsBattleView showCardsBattleView = new ShowCardsBattleView();
        ArrayList<Card> cards;
        if (match.findPlayerPlayingThisTurn().equals(match.getPlayer1()))
            cards = match.getPlayer1GraveYard().getCards();
        else
            cards = match.getPlayer2GraveYard().getCards();

        for (Card card : cards)
             showCardsBattleView.setCard(card);

        showCardsBattleView.show(showCardsBattleView);
    }

    private void insertCardRequest(InsertCardRequest request) {

        Card card = Collection.findCardByCardName(
                match.findPlayerPlayingThisTurn().getHand().getHandCards(), request.getCardName());

        if (card == null) {
            BattleLog.errorInvalidCardName();
            return;
        }
        if (!battleLogicController.hasEnoughMana(card)) {
            BattleLog.errorNotEnoughMana();
            return;
        }

        Coordination coordination = new Coordination();
        coordination.setRow(request.getRow());
        coordination.setColumn(request.getColumn());
        if (battleLogicController.isOutOfTable(coordination)) return;

        Cell cell = match.getTable().getCellByCoordination(coordination);

        if (card instanceof Unit) {

            if (battleLogicController.isCellFill(cell)) {
                BattleLog.errorCellIsFill();
                return;
            }
            if (!battleLogicController.isCellAvailableForInsert(coordination)) return;
            gameLogic.insertProcess((Unit) card, cell);
            BattleLog.logCardInserted(card.getCardName(), card.getCardID(),
                    cell.getCoordination().getRow(), cell.getCoordination().getColumn());

        } else {
            //TODO isTargetValid?
            gameLogic.insertProcess((Spell) card, cell);
        }
    }

    private void requestWithoutVariable(RequestWithoutVariable request) {

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

        else if (request.getEnumRequest() == RequestWithoutVariableEnum.END_TURN_REQUEST)
            endTurnRequest();

        else if (request.getEnumRequest() == RequestWithoutVariableEnum.SHOW_COLLECTED_ITEM_REQUEST)
            showCollectedItemRequest();

        else if (request.getEnumRequest() == RequestWithoutVariableEnum.END_GAME_REQUEST)
            isEndedGame = true;

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

                        if (match.getPlayer1().getUserName().equals(hero.getTeam()))
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
        ArrayList<Card> cards;

        if (account.equals(match.getPlayer1())) {
            cards = gameLogic.getCardsInTablePlayer1();
        } else {
            cards = gameLogic.getCardsInTablePlayer2();
        }

        for (Card card : cards) {

            Unit unit = (Unit) card;
            showMinionsBattleView.setMinion(unit.getCardID(), unit.getCardName(), unit.getHealthPoint(),
                    unit.getCell().getCoordination(), unit.getAttackPoint());
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
            BattleLog.errorHasNotReserveCard();
        }
    }

    private void showHandRequest() {

        ArrayList<Card> cards = match.findPlayerPlayingThisTurn().getHand().getHandCards();
        cards.add(match.findPlayerPlayingThisTurn().getHand().getReserveCard());
        ShowCardsBattleView showCardsBattleView = new ShowCardsBattleView();

        for (Card card : cards)
            showCardsBattleView.setCard(card);

        showCardsBattleView.show(showCardsBattleView);
    }

    private void endTurnRequest() {

        gameLogic.switchTurn();
        BattleLog.logTurnSwitched();
    }

    private void showCollectedItemRequest() {

        ShowCollectedItemsBattleView showCollectedItemsBattleView = new ShowCollectedItemsBattleView();
        ArrayList<Card> collectedItems = match.findPlayerPlayingThisTurn().getHand().getCollectiblesItem();

        for (Card item : collectedItems)
            showCollectedItemsBattleView.setItemInfo(item.getCardName(), item.getDescription());

        showCollectedItemsBattleView.show(showCollectedItemsBattleView);
    }
}
