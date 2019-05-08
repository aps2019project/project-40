package controller;

import models.*;
import models.GamePlay.GameLogic;
import models.GamePlay.Match;
import view.battleView.BattleLog;

import java.util.ArrayList;

public class BattleLogicController {

    private GameLogic gameLogic;
    private Match match;

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

    public boolean isCellAvailableForMove(Cell originCell, Cell destinationCell) {

        if (isCellFill(destinationCell)) return false;
        if (getManhattanDistance(originCell, destinationCell) > 2) {
            BattleLog.errorInvalidTarget();
            return false;
        }

        return true;
    }

    public boolean isUnitStun(Unit unit) {

        ArrayList<Buff> buffs = unit.getBuffs();

        for (Buff buff : buffs) {

            if (buff.isStun()) {
                BattleLog.errorUnitIsStun();
                return true;
            }
        }

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
                coordination.getColumn() >= Table.COLUMNS || coordination.getColumn() < 0) {

            BattleLog.errorInvalidTarget();
            return true;
        }

        return false;
    }

    public boolean isCellFill(Cell cell) {

        if (cell.getCard() == null) return false;
        BattleLog.errorCellIsFill();
        return true;
    }

    public boolean isCellAvailableForMelee(Cell attackerCell, Cell victimCell) {

        int manhattanDistance = getManhattanDistance(attackerCell, victimCell);

        if ((manhattanDistance < 2 && manhattanDistance > 0) ||

                (Math.abs(
                        attackerCell.getCoordination().getRow() - victimCell.getCoordination().getRow()) == 1 &&
                        Math.abs(attackerCell.getCoordination().getColumn() - victimCell.getCoordination().getColumn()) == 1))

            return true;

        BattleLog.errorInvalidTarget();
        return false;
    }

    public boolean isCellAvailableForRanged(Cell attackerCell, Cell victimCell, int attackRange) {

        int manhattanDistance = getManhattanDistance(attackerCell, victimCell);

        if (manhattanDistance > 1 && manhattanDistance <= attackRange &&

                !(Math.abs(
                        attackerCell.getCoordination().getRow() - victimCell.getCoordination().getRow()) == 1 &&
                        Math.abs(attackerCell.getCoordination().getColumn() - victimCell.getCoordination().getColumn()) == 1))

            return true;

        BattleLog.errorInvalidTarget();
        return false;

    }
}