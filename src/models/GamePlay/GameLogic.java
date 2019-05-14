package models.GamePlay;

import models.*;
import view.battleView.BattleLog;

import java.util.ArrayList;

import static models.SpecialPowerType.ON_DEFEND;

public class GameLogic {

    public final int PLAYER1_WINS = 1;
    public final int PLAYER2_WINS = 2;
    public final int MATCH_HAS_NOT_ENDED = 0;
    public final int NUMBER_OF_TURNS_TO_HOLD_THE_FLAG = 6;

    private Match match;
    int flagsNumber;
    int remainTurnToHoldingTheFlag; //todo initialize in dead and get
    ArrayList<
            Card> attackedCardsInATurn = new ArrayList<>();      //todo add attacker to array
    ArrayList<Card> movedCardsInATurn = new ArrayList<>();
    ArrayList<Card> cardsInTablePlayer1 = new ArrayList<>(); //todo fill that in game and delete when minion die
    ArrayList<Card> cardsInTablePlayer2 = new ArrayList<>();

    public ArrayList<Card> getCardsInTablePlayerPlayingThisTurn() {

        if (match.findPlayerPlayingThisTurn().equals(match.player1))
            return getCardsInTablePlayer1();
        else
            return getCardsInTablePlayer2();
    }

    public ArrayList<Card> getCardsInTablePlayerDoesNotPlayingThisTurn() {

        if (match.findPlayerPlayingThisTurn().equals(match.player1))
            return getCardsInTablePlayer2();
        else
            return getCardsInTablePlayer1();
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

    public void cancelNegativeBuffs(Unit unit) {
        for (Buff buff : unit.getBuffs()) {
            if (!buff.isPositive() && buff.getDuration() >= 0) {
                unit.removeBuff(buff);
            }
        }
    }

    public void killUnit(Unit unit) {

    }

    public void ActivateOnDeathSpells(Unit unit) {
        if (unit.getSpecialPowerType() == ON_DEFEND) {
            for (Spell spell : unit.getSpells()) {

            }
        }
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

    public void applySpell(Spell spell, Unit unit) {
        //spell.setLastTimeCasted();
        unit.addBuff(spell.getBuff());
        applyBuff(spell.getBuff(), unit);
    }

    public void applyBuff(Buff buff, Unit unit) {

        if (buff.getWaitingTime() > 0) {
            buff.setWaitingTime(buff.getWaitingTime() - 1);
            return;
        }

        castBuffOnCards(buff, unit);
        // castBuffOnCells(buff,unit);
        castBuffOnUnits(buff, unit);
        castBuffOnUsers(buff, unit);

        buff.decrementDuration();
    }


    public void castBuffOnCards(Buff buff, Unit unit) {
        if (buff.getItemSpell() != null) {
            unit.addSpell(buff.getItemSpell());
        }
    }

    public void castBuffOnUsers(Buff buff, Unit unit) {
        String name = unit.getTeam();
        if (match.player1.getUserName().equals(name)) {
            match.player1Mana += buff.getManaChange();
        } else {
            match.player2Mana += buff.getManaChange();
        }
    }

    public void castBuffOnUnits(Buff buff, Unit victimUnit) {
        if (!(buff.isPositive())) return;
        if (buff.getDuration() <= 0) {
            return;
        }
        victimUnit.setDamageChange(victimUnit.getDamageChange() + buff.getWeaknessHP());
        victimUnit.setAP(victimUnit.getAP() - buff.getWeaknessAP());


        if (buff.getPoison() > 0 || !victimUnit.isCantBePoisoned()) {
            victimUnit.setHP(victimUnit.getHP() - buff.getWeaknessHP());
            if (victimUnit.getHP() <= 0) {
                killUnit(victimUnit);
            }
        }

        if (buff.isStun() && !victimUnit.isCantBeStunned()) {
            victimUnit.setCanMove(false);
        }

        if (buff.isDisarm() && !victimUnit.isCantBeDisarmed()) {
            victimUnit.setDisarm(true);
        }
        if (buff.getCancelBuff() > 0) {
            cancelPositiveBuffs(victimUnit);
        } else {
            cancelNegativeBuffs(victimUnit);
        }
        if (buff.isDisarm()) {
            victimUnit.setDisarm(true);
        }
        if (buff.isStun()) {
            victimUnit.setStunned(true);
        }
        if (buff.isNoDamageFromWeakers()) {
            victimUnit.setNoDamageFromWeakers(true);
        }

        if (buff.getWeaknessAP() > 10000) {
            killUnit(victimUnit);
        }

    }

    public void counterAttack(Unit attacker, Unit defender) {
        if (defender.isDisarm()) {
            BattleLog.isDisarm();
        }
        checkRangeForAttack(defender, attacker);
        if (!attacker.isNoBadEffect() && (attacker.isNoDamageFromWeakers() || defender.getAP() > attacker.getAP())) {
            damage(attacker, defender);
        }
    }


    public void damage(Unit attacker, Unit defender) {
        int ap = apCompute(attacker, defender);
        defender.setHP(defender.getHP() - ap);
        if (defender.getHP() <= 0) {
            killUnit(defender);
        }
    }

    public int apCompute(Unit attacker, Unit defender) {
        int ap = attacker.getAP();
        if (defender.getDamageChange() > 0) {
            ap += defender.getDamageChange();
        }
        return ap;
    }
}
