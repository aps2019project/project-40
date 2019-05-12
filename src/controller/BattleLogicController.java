package controller;

import models.*;
import models.GamePlay.GameLogic;
import models.GamePlay.Match;
import view.battleView.BattleLog;

import java.util.ArrayList;

public class BattleLogicController {

    private GameLogic gameLogic;
    private Match match;
    private static BattleLogicController battleLogicController;

    public static BattleLogicController getBattleLogicController() {

        if (battleLogicController == null) {
            battleLogicController = new BattleLogicController();
        }
        return battleLogicController;
    }

    public void setGameLogic(GameLogic gameLogic) {

        this.gameLogic = gameLogic;
    }

    public void setMatch(Match match) {

        this.match = match;
    }

    public boolean isAttackedPreviously(Card card) {

        ArrayList<Card> attackedCards = gameLogic.getAttackedCardsInATurn();

        for (Card attackedCard : attackedCards) {

            if (attackedCard.getCardName().equals(card.getCardName())) return true;
        }

        return false;
    }

    public boolean isMovedPreviously(Card card) {

        ArrayList<Card> movedCards = gameLogic.getMovedCardsInATurn();

        for (Card movedCard : movedCards) {

            if (movedCard.getCardName().equals(card.getCardName())) return true;
        }

        return false;
    }

    public boolean isCellAvailableForMove(Cell origin, Cell destination) {

        if (isCellFill(destination)) return false;
        if (getManhattanDistance(origin, destination) > 2)
            return false;
        return true;
    }

    public boolean isUnitStunned(Unit unit) {

        if (unit.isCanMove())
            return true;
        return false;
    }

    public int getManhattanDistance(Cell start, Cell finish) {

        int rowDifference = start.getCoordination().getRow() - finish.getCoordination().getRow();
        int columnDifference = start.getCoordination().getColumn() - finish.getCoordination().getColumn();

        return Math.abs(rowDifference) + Math.abs(columnDifference);
    }

    public boolean hasEnoughMana(Card card) {

        if (match.findPlayerPlayingThisTurn().equals(match.getPlayer1())) {

            return card.getManaCost() <= match.getPlayer1Mana();

        } else {

            return card.getManaCost() <= match.getPlayer2Mana();
        }
    }

    public boolean isOutOfTable(Coordination coordination) {

        if (coordination.getRow() >= Table.ROWS || coordination.getRow() < 0 ||
                coordination.getColumn() >= Table.COLUMNS || coordination.getColumn() < 0)
            return true;
        return false;
    }

    public boolean isCellFill(Cell cell) {

        if (cell.getCard() == null) return false;
        BattleLog.errorCellIsFill();
        return true;
    }

    public boolean isDirectionWithoutEnemyForMove(Cell origin, Cell destination) {

        if (getManhattanDistance(origin, destination) == 2) {

            if (Math.abs(origin.getCoordination().getRow() - destination.getCoordination().getRow()) == 1) {
                //means direction is diagonal

                Coordination coordination1 = Coordination.getNewCoordination(
                        origin.getCoordination().getRow(), destination.getCoordination().getColumn());
                Coordination coordination2 = Coordination.getNewCoordination(
                        destination.getCoordination().getRow(), origin.getCoordination().getColumn());

                Card card1InThisCoordination = match.getTable().getCellByCoordination(coordination1).getCard();
                Card card2InThisCoordination = match.getTable().getCellByCoordination(coordination2).getCard();

                try {
                    if (!card1InThisCoordination.getTeam().equals(match.findPlayerPlayingThisTurn()))
                        if (!card2InThisCoordination.getTeam().equals(match.findPlayerPlayingThisTurn()))
                            return false;

                } catch (NullPointerException e) {
                    return true;
                }
                return true;

            } else {

                Coordination coordination = Coordination.getNewCoordination(
                        (origin.getCoordination().getRow() + destination.getCoordination().getRow()) / 2,
                        (origin.getCoordination().getColumn() + destination.getCoordination().getColumn()) / 2);

                try {
                    if (!match.getTable().getCellByCoordination(coordination).getCard()
                            .getTeam().equals(match.findPlayerPlayingThisTurn().getUserName()))
                        return false;

                } catch (NullPointerException e) {
                    return true;
                }
                return true;
            }
        } else return true;
    }

    public boolean isCellAvailableForInsert(Coordination coordination) {

        Table table = match.getTable();
        Coordination aroundCoordination;

        for (int row = -1; row <= 1; row++) {
            for (int column = -1; column <= 1; column++) {

                if (row == 0 && column == 0) continue;

                try {
                    aroundCoordination = Coordination.getNewCoordination(
                            coordination.getRow() + row, coordination.getColumn() + column);

                    if (table.getCellByCoordination(aroundCoordination).getCard().getTeam().equals(
                            match.findPlayerPlayingThisTurn().getUserName())) return true;

                } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                }
            }
        }

        BattleLog.errorCellNotAvailable();
        return false;
    }

    public boolean isTargetCellAvailableForMeleeAttack(Cell attackerCell, Cell victimCell) {

        int manhattanDistance = getManhattanDistance(attackerCell, victimCell);

        if ((manhattanDistance <= 1 && manhattanDistance > 0) ||
                isCellsDiagonallyWith2ManhattanDistance(attackerCell, victimCell))

            return true;
        return false;
    }

    public boolean isTargetCellAvailableForRangedAttack(Cell attackerCell, Cell victimCell, int attackRange) {

        int manhattanDistance = getManhattanDistance(attackerCell, victimCell);

        if (manhattanDistance > 1 && manhattanDistance <= attackRange &&
                !isCellsDiagonallyWith2ManhattanDistance(attackerCell, victimCell))

            return true;
        return false;

    }

    private boolean isCellsDiagonallyWith2ManhattanDistance(Cell cell1, Cell cell2) {

        return
                Math.abs(
                        cell1.getCoordination().getRow() - cell2.getCoordination().getRow()) == 1 &&
                        Math.abs(cell1.getCoordination().getColumn() - cell2.getCoordination().getColumn()) == 1;
    }
}
