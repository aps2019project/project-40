package models.GamePlay;

import models.*;

import java.util.ArrayList;

public class GameLogic {

    public final int PLAYER1_WINS = 1;
    public final int PLAYER2_WINS = 2;
    public final int MATCH_HAS_NOT_ENDED = 0;
    private final int NUMBER_OF_TURNS_TO_HOLD_THE_FLAG = 6;

    private Match match;
    int flagsNumber;
    int remainTurnToHoldingTheFlag;

    GameLogic(Match match) {

        this.match = match;
    }

    public void moveProcess(Card card, Cell cell) {

        card.getCell().setCard(null);
        cell.setCard(card);
        card.setCell(cell);
    }

    public int getMatchResult() {

        if (match.getMatchType() == MatchType.KILL_THE_HERO) {

            ArrayList<Card> player1Cards = match.player1.getCollection().getSelectedDeck().getCards();
            ArrayList<Card> player2Cards = match.player2.getCollection().getSelectedDeck().getCards();

            for (Card card : player1Cards) {
                if (card.getType() == CardType.HERO) {

                    Unit hero = (Unit) card;
                    if (hero.getHealthPoint() <= 0) return PLAYER2_WINS;
                    break;
                }
            }
            for (Card card : player2Cards) {
                if (card.getType() == CardType.HERO) {

                    Unit hero = (Unit) card;
                    if (hero.getHealthPoint() <= 0) return PLAYER1_WINS;
                    break;
                }
            }
        }

        else if (match.getMatchType() == MatchType.HOLD_THE_FLAG);
        else if (match.getMatchType() == MatchType.COLLECT_THE_FLAGS);

        return MATCH_HAS_NOT_ENDED;
    }


}
