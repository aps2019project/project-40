package models.GamePlay;

import models.*;

import java.util.ArrayList;

public class GameLogic {

    public final int PLAYER1_WINS = 1;
    public final int PLAYER2_WINS = 2;
    public final int MATCH_HAS_NOT_ENDED = 0;
    public final int NUMBER_OF_TURNS_TO_HOLD_THE_FLAG = 6;

    private Match match;
    int flagsNumber;
    int remainTurnToHoldingTheFlag; //todo initialize in dead and get
    private ArrayList<Unit> attackedUnitsInATurn = new ArrayList<>();       //todo add attacker to array
    private ArrayList<Unit> movedUnitsInATurn = new ArrayList<>();       //todo add moved to array

    public ArrayList<Unit> getAttackedUnitsInATurn() {

        return attackedUnitsInATurn;
    }

    public ArrayList<Unit> getMovedUnitsInATurn() {

        return movedUnitsInATurn;
    }

    GameLogic(Match match) {

        this.match = match;
    }

    public void moveProcess(Card card, Cell cell) {

        card.getCell().setCard(null);
        cell.setCard(card);
        card.setCell(cell);
    }

    public int getMatchResult() {

        ArrayList<Card> player1Cards = match.player1.getCollection().getSelectedDeck().getCards();
        ArrayList<Card> player2Cards = match.player2.getCollection().getSelectedDeck().getCards();
        ArrayList<Unit> player1Units = new ArrayList<>();
        ArrayList<Unit> player2Units = new ArrayList<>();

        for (Card card : player1Cards)
            if (card instanceof Unit)
                player1Units.add((Unit) card);

        for (Card card : player2Cards)
            if (card instanceof Unit)
                player2Units.add((Unit) card);


        if (match.getMatchType() == MatchType.KILL_THE_HERO)
            return getMatchResultForKillTheHero(player1Units, player2Units);

        else if (match.getMatchType() == MatchType.HOLD_THE_FLAG)
            return getMatchResultForHoldTheFlag(player1Units, player2Units);

        else if (match.getMatchType() == MatchType.COLLECT_THE_FLAGS)
            return getMatchResultForCollectTheFlags(player1Units, player2Units);

        return MATCH_HAS_NOT_ENDED;
    }

    private int getMatchResultForKillTheHero(ArrayList<Unit> player1Units, ArrayList<Unit> player2Units) {

        for (Unit unit : player1Units) {
            if (unit.getType() == CardType.HERO) {

                if (unit.getHealthPoint() <= 0) return PLAYER2_WINS;
                break;
            }
        }
        for (Unit unit : player2Units) {
            if (unit.getType() == CardType.HERO) {

                if (unit.getHealthPoint() <= 0) return PLAYER1_WINS;
                break;
            }
        }

        return MATCH_HAS_NOT_ENDED;
    }

    private int getMatchResultForHoldTheFlag(ArrayList<Unit> player1Units, ArrayList<Unit> player2Units) {

        if (remainTurnToHoldingTheFlag <= 0) {
            //todo refactor it
            for (Unit unit : player1Units)
                if (unit.getFlag() > 0)
                    return PLAYER1_WINS;

            for (Unit unit : player2Units)
                if (unit.getFlag() > 0)
                    return PLAYER2_WINS;

        }

        return MATCH_HAS_NOT_ENDED;
    }

    private int getMatchResultForCollectTheFlags(ArrayList<Unit> player1Units, ArrayList<Unit> player2Units) {

        int sumOfPlayer1Flag = 0, sumOfPlayer2Flag = 0;

        for (Unit unit : player1Units)
            sumOfPlayer1Flag += unit.getFlag();

        for (Unit unit : player2Units)
            sumOfPlayer2Flag += unit.getFlag();

        if (sumOfPlayer1Flag >= Math.ceil(flagsNumber / 2)) return PLAYER1_WINS;
        if (sumOfPlayer2Flag >= Math.ceil(flagsNumber / 2)) return PLAYER2_WINS;

        return MATCH_HAS_NOT_ENDED;
    }

    public void insertProcess(Unit unit, Cell cell) {

        cell.setCard(unit);
        unit.setCell(cell);
        decrementMana(unit.getManaCost());
    }

    public void insertProcess(Spell spell, Cell cell) {

        //todo
        decrementMana(spell.getManaCost());
    }

    private void decrementMana(int mana) {

        if (match.findPlayerPlayingThisTurn().equals(match.getPlayer1()))
            match.player1Mana -= mana;
        else
            match.player2Mana -= mana;
    }

    public void switchTurn() {

        match.turnNumber++;
        manaHandler();
        //todo fill hand
    }

    private void manaHandler() {

        if (match.findPlayerPlayingThisTurn().equals(match.getPlayer1())) {

            if (match.turnNumber <= 15) {
                match.initialPlayer1Mana++;
            }
            match.player1Mana = match.initialPlayer1Mana;

        } else {

            if (match.turnNumber <= 14) {
                match.initialPlayer1Mana++;
            }
            match.player2Mana = match.initialPlayer2Mana;
        }

    }

    public void cancelPositiveBuffs(Unit unit) {
        for (Buff buff : unit.getBuffs()) {
            if (buff.isPositive() && buff.getDuration() >= 0 && !buff.isLasts()) {
                unit.removeBuff(buff);
            }
        }
    }

    public void killUnit(Unit unit) {

    }

    public void ActivateOnDeathSpells(Unit unit) {

    }

    public void checkRangeForAttack(Unit attacker, Unit defender) {
        if (attacker.getUnitType() == UnitType.MELEE) {
            if (!attacker.getCell().isAdjacent(defender.getCell())) {
                throw new IllegalArgumentException("can't attack"); //
            }
        }
        if (attacker.getUnitType() == UnitType.RANGED) {
            if (attacker.getCell().isAdjacent(defender.getCell())) {
                throw new IllegalArgumentException("can't attack"); //
            }
        }
        if (attacker.getUnitType() == UnitType.HYBRID) {
            if (attacker.getCell().isAdjacent(defender.getCell())) {
                throw new IllegalArgumentException("can't attack"); //
            }
        }
    }
}
