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
    private boolean isEndedGame = false;

    //TODO add coordination for show minion

    public static BattleController getInstance() {

        if (battleController == null) {

            battleController = new BattleController();
        }

        return battleController;
    }

    public void mainBattleController(Match match) {

        this.match = match;
        gameLogic = match.getGameLogic();
        manageRequest();
    }

    private void manageRequest() {

        while (true) {

            BattleRequest request = battleRequest.getRequest();

            if (request instanceof SelectAndUseCardRequest)
                selectAndUseCardRequest((SelectAndUseCardRequest) request);

            if (request instanceof ShowCardInfoRequest)
                showCardInfoRequest((ShowCardInfoRequest) request);

            if (request instanceof EnterGraveYardRequest)
                enterGraveYardRequest((EnterGraveYardRequest) request);

            if (request instanceof InsertCardRequest)
                insertCardRequest((InsertCardRequest) request);

            if (request instanceof RequestWithoutVariable)
                requestWithoutVariable((RequestWithoutVariable) request);

            if (isEndedGame) break;
        }
    }

    private void selectAndUseCardRequest(SelectAndUseCardRequest request) {
        //todo
        if (request.isForMove() || request.isForAttack() || request.isForAttackCombo() || request.isForUseSpecialPower()) {

            Card card = Collection.findCardByCardID(
                    match.findPlayerPlayingThisTurn().getHand().getCardsInTable(), request.getID());

            if (card == null || !(card instanceof Unit)) {
                BattleLog.errorInvalidCardID();
                return;
            }

            Coordination destinationCoordination = new Coordination();
            destinationCoordination.setRow(request.getRow());
            destinationCoordination.setColumn(request.getColumn());
            if (isOutOfTable(destinationCoordination)) return;

            Cell destinationCell = match.getTable().getCellByCoordination(destinationCoordination);

            //doing request
            if (request.isForMove())
                selectAndUseCardRequestMove(request, card, destinationCoordination, destinationCell);

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

    private void selectAndUseCardRequestAttack(SelectAndUseCardRequest request) {
        //todo current easy
    }

    private void selectAndUseCardRequestMove(SelectAndUseCardRequest request, Card card,
                                             Coordination destinationCoordination, Cell destinationCell) {

        if (!isCellAvailableForMove(card.getCell(), destinationCell)) return;

        if (isUnitStun((Unit) card)) return;
        if (isAttackedPreviously(card)) {
            BattleLog.errorUnitAttacked();
            return;
        }
        if (isMovedPreviously(card)) {
            BattleLog.errorUnitMovedPreviously();
            return;
        }

        gameLogic.moveProcess(card, destinationCell);
        //todo if there is flag in cell get that
    }

    private boolean isAttackedPreviously(Card card) {

        ArrayList<Card> attackedCards = gameLogic.getAttackedCardsInATurn();

        for (Card attackedCard : attackedCards) {

            if (attackedCard.getCardName().equals(card.getCardName())) return true;
        }

        return false;
    }

    private boolean isMovedPreviously(Card card) {

        ArrayList<Card> movedCards = gameLogic.getMovedCardsInATurn();

        for (Card movedCard : movedCards) {

            if (movedCard.getCardName().equals(card.getCardName())) return true;
        }

        return false;
    }

    private boolean isCellAvailableForMove(Cell originCell, Cell destinationCell) {

        if (isCellFill(destinationCell)) return false;

        int rowDifference = destinationCell.getCoordination().getRow() - originCell.getCoordination().getRow();
        int columnDifference = destinationCell.getCoordination().getColumn() - originCell.getCoordination().getColumn();

        if (Math.abs(rowDifference) + Math.abs(columnDifference) > 2) {
            BattleLog.errorInvalidTarget();
            return false;
        }

        return true;
    }

    private boolean isUnitStun(Unit unit) {

        ArrayList<Buff> buffs = unit.getBuffs();

        for (Buff buff : buffs) {

            if (buff.isStun()) {
                BattleLog.errorUnitIsStun();
                return true;
            }
        }

        return false;
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

        if (!hasEnoughMana(card)) {
            BattleLog.errorNotEnoughMana();
            return;
        }

        Coordination coordination = new Coordination();
        coordination.setRow(request.getRow());
        coordination.setColumn(request.getColumn());
        if (isOutOfTable(coordination)) return;

        Cell cell = match.getTable().getCellByCoordination(coordination);

        if (card instanceof Unit) {

            if (isCellFill(cell)) {
                BattleLog.errorCellIsFill();
                return;
            }
            if (!isCellAvailable(coordination)) return;
            gameLogic.insertProcess((Unit) card, cell);

        } else {
            //TODO isTargetValid?
            gameLogic.insertProcess((Spell) card, cell);
        }
    }

    private boolean hasEnoughMana(Card card) {

        if (match.findPlayerPlayingThisTurn().equals(match.getPlayer1())) {

            return card.getManaCost() <= match.getPlayer1Mana();

        } else {

            return card.getManaCost() <= match.getPlayer2Mana();
        }
    }

    private boolean isOutOfTable(Coordination coordination) {

        if (coordination.getRow() >= Table.ROWS || coordination.getRow() < 0 ||
                coordination.getColumn() >= Table.COLUMNS || coordination.getColumn() < 0) {

            BattleLog.errorInvalidTarget();
            return true;
        }

        return false;
    }

    private boolean isCellFill(Cell cell) {

        if (cell.getCard() == null) return false;
        BattleLog.errorCellIsFill();
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

        BattleLog.errorCellNotAvailable();
        return false;
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

        ArrayList<Card> cards = match.findPlayerPlayingThisTurn().getHand().getHandCards();
        cards.add(match.findPlayerPlayingThisTurn().getHand().getReserveCard());
        ShowCardsBattleView showCardsBattleView = new ShowCardsBattleView();

        for (Card card : cards)
            showCardsBattleView.setCard(card);

        showCardsBattleView.show(showCardsBattleView);
    }

    private void endTurnRequest() {

        gameLogic.switchTurn();
    }

    private void showCollectedItemRequest() {

        ShowCollectedItemsBattleView showCollectedItemsBattleView = new ShowCollectedItemsBattleView();
        ArrayList<Card> collectedItems = match.findPlayerPlayingThisTurn().getHand().getCollectiblesItem();

        for (Card item : collectedItems)
            showCollectedItemsBattleView.setItemInfo(item.getCardName(), item.getDescription());

        showCollectedItemsBattleView.show(showCollectedItemsBattleView);
    }
}
