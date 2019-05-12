package models.GamePlay;

import controller.BattleLogicController;
import models.*;
import view.battleView.BattleLog;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {

    public final int PLAYER1_WINS = 1;
    public final int PLAYER2_WINS = 2;
    public final int MATCH_HAS_NOT_ENDED = 0;
    public final int NUMBER_OF_TURNS_TO_HOLD_THE_FLAG = 6;

    private Match match;
    int flagsNumber;
    int remainTurnToHoldingTheFlag; //todo initialize in dead and get
    ArrayList<Card> attackedCardsInATurn = new ArrayList<>();      //todo add attacker to array
    ArrayList<Card> movedCardsInATurn = new ArrayList<>();
    ArrayList<Card> cardsInTablePlayer1 = new ArrayList<>(); //todo fill that in game and delete when minion die
    ArrayList<Card> cardsInTablePlayer2 = new ArrayList<>();

    public ArrayList<Card> getCardsInTablePlayerPlayingThisTurn() {

        if (match.findPlayerPlayingThisTurn().equals(match.player1))
            return getCardsInTablePlayer1();
        else
            return getCardsInTablePlayer2();
    }

    public ArrayList<Card> getCardsInTablePlayer2() {

        return cardsInTablePlayer2;
    }

    public ArrayList<Card> getCardsInTablePlayer1() {

        return cardsInTablePlayer1;
    }

    public ArrayList<Card> getAttackedCardsInATurn() {

        return attackedCardsInATurn;
    }

    public ArrayList<Card> getMovedCardsInATurn() {

        return movedCardsInATurn;
    }

    GameLogic(Match match) {

        this.match = match;
    }

    public void moveProcess(Card card, Cell cell) {

        card.getCell().setCard(null);
        cell.setCard(card);
        card.setCell(cell);
        movedCardsInATurn.add(card);
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
        if (match.findPlayerPlayingThisTurn().equals(match.player1)) cardsInTablePlayer1.add(unit);
        else cardsInTablePlayer2.add(unit);
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

    public void useItem(Spell spell, Cell cell) {


    }

    public void switchTurn() {

        match.turnNumber++;
        manaHandler();
        match.findPlayerPlayingThisTurn().getHand().fillHandEmptyPlace();
        attackedCardsInATurn = new ArrayList<>();
        movedCardsInATurn = new ArrayList<>();
    }

    private void manaHandler() {

        if (match.findPlayerPlayingThisTurn().equals(match.getPlayer1())) {

            if (match.turnNumber <= 15) {
                match.initialPlayer1ManaInBeginningTurn++;
            }
            System.err.println("mana player1 :" + match.initialPlayer1ManaInBeginningTurn);
            match.player1Mana = match.initialPlayer1ManaInBeginningTurn;

        } else {

            if (match.turnNumber <= 14) {
                match.initialPlayer2ManaInBeginningTurn++;
            }
            System.err.println("mana player2 :" + match.initialPlayer2ManaInBeginningTurn);
            match.player2Mana = match.initialPlayer2ManaInBeginningTurn;
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
