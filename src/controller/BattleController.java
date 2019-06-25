package controller;

import models.*;
import models.GamePlay.GameLogic;
import models.GamePlay.Match;
import ui.battleUI.battleRequests.BattleRequest;
import ui.battleUI.battleRequests.*;
import ui.battleUI.battleViews.*;
import view.battleView.*;

import java.util.ArrayList;
import java.util.Collections;

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
        BattleLog.logTurnForWho(match.findPlayerPlayingThisTurn().getUserName());
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
        if (request.isForMove())
            selectAndUseCardRequestMove(request);

        else if (request.isForAttack())
            selectAndUseCardRequestAttack(request);

        else if (request.isForAttackCombo())
            selectAndUseCardRequestAttackCombo(request);

        else if (request.isForUseSpecialPower())
            selectAndUseCardRequestUseSpecialPower();
    }

    private void selectAndUseCardRequestMove(SelectAndUseCardRequest request) {

        Card card = Collection.findCardByCardID(gameLogic.getCardsInTablePlayerPlayingThisTurn(), request.getID());
        if (card == null) {
            BattleLog.errorInvalidCardID();
            return;
        }

        Coordination destinationCoordination = Coordination.getNewCoordination(
                request.getRow(), request.getColumn());

        if (battleLogicController.isOutOfTable(destinationCoordination)) {
            BattleLog.errorInvalidTarget();
        }

        Cell destinationCell = match.getTable().getCellByCoordination(destinationCoordination);

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
        if (!battleLogicController.isDirectionWithoutEnemyForMove(card.getCell(), destinationCell)) {
            BattleLog.errorCellNotAvailable();
            return;
        }

        //todo check flag

        gameLogic.moveProcess(card, destinationCell);
        BattleLog.logCardMoved(card.getCardID(),
                destinationCell.getCoordination().getRow(), destinationCell.getCoordination().getColumn());


    }

    private void selectAndUseCardRequestAttack(SelectAndUseCardRequest request) {

        Card attacker = Collection.findCardByCardID(gameLogic.getCardsInTablePlayerPlayingThisTurn(), request.getID());
        Card victim = Collection.findCardByCardID(
                gameLogic.getCardsInTablePlayerDoesNotPlayingThisTurn(), request.getOpponentCardID());

        if (attacker == null || victim == null || attacker.getTeam().equals(victim.getTeam())) {
            BattleLog.errorInvalidCardID();
            return;
        }

        if (((Unit) attacker).getUnitType() == UnitType.MELEE)
            selectAndUseCardRequestAttackMelee((Unit) attacker, (Unit) victim);

        else if (((Unit) attacker).getUnitType() == UnitType.RANGED)
            selectAndUseCardRequestAttackRanged((Unit) attacker, (Unit) victim);

        else if (((Unit) attacker).getUnitType() == UnitType.HYBRID)
            selectAndUseCardRequestAttackHybrid((Unit) attacker, (Unit) victim);
    }

    private void selectAndUseCardRequestAttackMelee(Unit attacker, Unit victim) {

        if (!Cell.isTargetCellAvailableForMeleeAttack(attacker.getCell(), victim.getCell())) {
            BattleLog.errorInvalidTarget();
            return;
        }
        gameLogic.attack(attacker, victim);
        BattleLog.logAttacked();
    }

    private void selectAndUseCardRequestAttackRanged(Unit attacker, Unit victim) {

        if (!Cell.isTargetCellAvailableForRangedAttack(
                attacker.getCell(), victim.getCell(), attacker.getRange())) {

            BattleLog.errorInvalidTarget();
            return;
        }
        gameLogic.attack(attacker, victim);
        BattleLog.logAttacked();
    }

    private void selectAndUseCardRequestAttackHybrid(Unit attacker, Unit victim) {

        gameLogic.attack(attacker, victim);
        BattleLog.logAttacked();
    }

    private void selectAndUseCardRequestAttackCombo(SelectAndUseCardRequest request) {

        Card opponent = Collection.findCardByCardID(
                gameLogic.getCardsInTablePlayerDoesNotPlayingThisTurn(), request.getOpponentCardID());
        if (opponent == null) {
            BattleLog.errorInvalidCardID();
            return;
        }

        ArrayList<String> myCardsID = request.getMyCardsID();
        ArrayList<Card> myCards = new ArrayList<>();

        for (String myCardID : myCardsID) {

            Card myCard = Collection.findCardByCardID(gameLogic.getCardsInTablePlayerPlayingThisTurn(), myCardID);
            if (myCard == null) {
                BattleLog.errorInvalidCardID();
                return;
            }
            if (!((Unit) myCard).isCombo()) {
                BattleLog.errorHasNotCombo();
                return;
            }
            myCards.add(myCard);
        }
        //todo
    }

    private void selectAndUseCardRequestUseSpecialPower() {

        gameLogic.useSpecialPower((Unit) match.findPlayerPlayingThisTurn().getHand().getHero());
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

        Coordination coordination = Coordination.getNewCoordination(request.getRow(), request.getColumn());

        if (battleLogicController.isOutOfTable(coordination)) {
            BattleLog.errorInvalidTarget();
            return;
        }

        Cell cell = match.getTable().getCellByCoordination(coordination);

        if (card instanceof Unit) insertCardRequestForUnit(cell, coordination, card);
        else insertCardRequestForSpell(cell, (Spell) card);
    }

    private void insertCardRequestForUnit(Cell cell, Coordination coordination, Card card) {

        if (battleLogicController.isCellFill(cell)) {
            BattleLog.errorCellIsFill();
            return;
        }
        if (!battleLogicController.isCellAvailableForInsert(coordination)) return;
        gameLogic.insertProcess((Unit) card, cell);
        BattleLog.logCardInserted(card.getCardName(), card.getCardID(),
                cell.getCoordination().getRow(), cell.getCoordination().getColumn());
    }

    private void insertCardRequestForSpell(Cell cell, Spell spell) {

        if (spell.getTarget().isAffectCells())
            gameLogic.insertProcess(spell, cell);

        else {      //for minions and hero
            if (!battleLogicController.isCellFill(cell)) {
                BattleLog.errorCellIsNotFill();
                return;
            }
            if (spell.getTarget().isTargetEnemy() &&
                    !cell.getCard().getTeam().equals(match.findPlayerDoesNotPlayingThisTurn().getUserName())) {
                BattleLog.errorItIsYourUnit();
                return;
            }
            if (!spell.getTarget().isTargetEnemy() &&
                    cell.getCard().getTeam().equals(match.findPlayerDoesNotPlayingThisTurn().getUserName())) {
                BattleLog.errorItIsUnitOfEnemy();
                return;
            }
            gameLogic.insertProcess(spell, cell);
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
            isEndedGame = false;

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
        gameInfoBattleViewHoldTheFlag.setPlayer1Mana(match.getPlayer1Mana());
        gameInfoBattleViewHoldTheFlag.setPlayer2Mana(match.getPlayer2Mana());

        for (Cell[] row : cells) {
            for (Cell cell : row) {

                if (cell.getFlag() != null) {

                    if (cell.getCard() == null) {

                        gameInfoBattleViewHoldTheFlag.setFlagCoordination(cell.getCoordination());

                    } else {

                        gameInfoBattleViewHoldTheFlag.setFlagHolderName(cell.getCard().getCardName());
                        gameInfoBattleViewHoldTheFlag.setFlagHolderTeam(cell.getCard().getTeam());
                        gameInfoBattleViewHoldTheFlag.show(gameInfoBattleViewHoldTheFlag);
                    }
                }
            }
        }
        gameInfoBattleViewHoldTheFlag.show(gameInfoBattleViewHoldTheFlag);
    }

    private void gameInfoRequestCollectTheFlagsMode(Cell[][] cells) {

        GameInfoBattleViewCollectTheFlags gameInfoBattleViewCollectTheFlags = new GameInfoBattleViewCollectTheFlags();

        for (Cell[] row : cells) {
            for (Cell cell : row) {


            }
        }
        gameInfoBattleViewCollectTheFlags.show(gameInfoBattleViewCollectTheFlags);
    }

    private void showMyMinionsRequest() {

        showMinionsRequestCommonCode(match.findPlayerPlayingThisTurn());
    }

    private void showOpponentMinionsRequest() {

        showMinionsRequestCommonCode(match.findPlayerDoesNotPlayingThisTurn());
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
        ShowCardsBattleView showCardsBattleView = new ShowCardsBattleView();

        for (Card card : cards) showCardsBattleView.setCard(card);
        if (match.findPlayerPlayingThisTurn().getHand().getReserveCard() != null)
            showCardsBattleView.setCard(match.findPlayerPlayingThisTurn().getHand().getReserveCard());

        showCardsBattleView.show(showCardsBattleView);
    }

    private void endTurnRequest() {

        gameLogic.switchTurn();
        BattleLog.logTurnForWho(match.findPlayerPlayingThisTurn().getUserName());

        if (match.getPlayer2().isAI() && match.getTurnNumber() % 2 == 0) playAI();
    }

    private void showCollectedItemRequest() {

        ShowCollectedItemsBattleView showCollectedItemsBattleView = new ShowCollectedItemsBattleView();
        ArrayList<Card> collectedItems = match.findPlayerPlayingThisTurn().getHand().getCollectedItems();

        for (Card item : collectedItems)
            showCollectedItemsBattleView.setItemInfo(item.getCardName(), item.getDescription());

        showCollectedItemsBattleView.show(showCollectedItemsBattleView);
    }

    private void playAI() {

        BattleLogicController battleLogicController = BattleLogicController.getBattleLogicController();
        Card hero = match.getPlayer2().getHand().getHero();

        ArrayList<Integer> rows = new ArrayList<>();
        rows.add(-2);
        rows.add(-1);
        rows.add(0);
        rows.add(1);
        rows.add(2);
        Collections.shuffle(rows);

        for (Integer row : rows) {
            for (int column = -2 + Math.abs(row); column <= 2 - Math.abs(row); column++) {

                if (row == 0 && column == 0) continue;

                try {
                    Coordination heroCoordination = hero.getCell().getCoordination();
                    Coordination coordination = Coordination.getNewCoordination(
                            heroCoordination.getRow() + row, heroCoordination.getColumn() + column);
                    Cell destinationCell = match.getTable().getCellByCoordination(coordination);

                    //check validity of destination
                    if (!battleLogicController.isCellAvailableForMove(hero.getCell(), destinationCell)) continue;
                    if (battleLogicController.isUnitStunned((Unit) hero)) continue;
                    if (battleLogicController.isAttackedPreviously(hero)) continue;
                    if (battleLogicController.isMovedPreviously(hero)) continue;
                    if (!battleLogicController.isDirectionWithoutEnemyForMove(hero.getCell(), destinationCell))
                        continue;

                    //move
                    gameLogic.moveProcess(hero, destinationCell);
                    BattleLog.logCardMoved(hero.getCardID(),
                            destinationCell.getCoordination().getRow(), destinationCell.getCoordination().getColumn());

                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        endTurnRequest();
    }
}
