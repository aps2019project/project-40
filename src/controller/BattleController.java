package controller;

import models.*;
import models.GamePlay.Match;
import request.battleRequest.BattleRequest;
import request.battleRequest.BattleRequestChilds.*;
import view.battleView.*;

public class BattleController {

    private static BattleController battleController;
    private BattleRequest battleRequest = BattleRequest.getInstance();
    private BattleView battleView = BattleView.getInstance();
    private Match match;

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

        //todo
    }

    private void showCardInfoRequest(ShowCardInfoRequest request) {

        //todo
    }

    private void enterGraveYardRequest(EnterGraveYardRequest request) {
        //todo
        if (request.isForShowInfo()) ;
        if (request.isForShowCards()) ;
        if (request.isForExit()) ;
    }

    private void insertCardRequest(InsertCardRequest request) {

        //todo
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

        else if (request.getEnumRequest() == RequestWithoutVariableEnum.SHOW_HAND_REQUEST) ;

        else if (request.getEnumRequest() == RequestWithoutVariableEnum.END_TURN_REQUEST) ;
        else if (request.getEnumRequest() == RequestWithoutVariableEnum.SHOW_COLLECTED_ITEM_REQUEST) ;
        else if (request.getEnumRequest() == RequestWithoutVariableEnum.END_GAME_REQUEST) ;

        else if (request.getEnumRequest() == RequestWithoutVariableEnum.HELP_REQUEST)
            helpRequest();
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
            if (card.getType() == CardType.HERO)
                showCardsBattleView.setCardForHero(card.getCardName(), card.getPrice(), card.getDescription());

            else if (card.getType() == CardType.MINION) {

                Unit minion = (Unit) card;
                showCardsBattleView.setCardsForMinion(minion.getCardName(), minion.getPrice(), minion.getDescription(),
                        minion.getAttackPoint(), minion.getHealthPoint(), minion.getManaCost(), minion.getUnitType(),
                        minion.hasComboAbility());

            } else if (card.getType() == CardType.SPELL) {

                Unit spell = (Unit) card;
                showCardsBattleView.setCardsForSpell(spell.getCardName(), spell.getPrice(), spell.getDescription(),
                        spell.getManaCost());
            }

            showCardsBattleView.show(showCardsBattleView);

        } catch (NullPointerException e) {
            System.out.println("You don't have reserve card");
        }
    }

    private void showHandRequest() {

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

    private void helpRequest() {
        System.out.println("Game info");
        System.out.println("Show my minions");
        System.out.println("Show opponent minions");
        System.out.println("Show card info [card id]");
        System.out.println("Select [card id]");
        System.out.println("    Move to ([x],[y])");
        System.out.println("    Attack [opponent card id]");
        System.out.println("    Attack combo [opponent card id] [my card id] [my card id] ...");
        System.out.println("    exit");
        System.out.println("Use special power ([x],[y])");
        System.out.println("Show hand");
        System.out.println("Insert [card name] in ([x],[y])");
        System.out.println("End turn");
        System.out.println("Show collectibles");
        System.out.println("Select [collectible id]");
        System.out.println("    Show info");
        System.out.println("    Use ([x],[y])");
        System.out.println("    exit");
        System.out.println("Show next card");
        System.out.println("Enter graveyard");
        System.out.println("    Show info [card id]");
        System.out.println("    Show cards");
        System.out.println("    exit");
        System.out.println("Help");
        System.out.println("End game");
    }
}
