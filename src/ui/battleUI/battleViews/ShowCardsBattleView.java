package ui.battleUI.battleViews;

import models.*;

import java.util.ArrayList;

public class ShowCardsBattleView extends BattleView {

    private ArrayList<ShowCardInfoBattleView> cardsInfo = new ArrayList<>();

    public ArrayList<ShowCardInfoBattleView> getCardsInfo() {

        return cardsInfo;
    }

    public void setCard(Card card) {

        if (card.getType() == CardType.HERO)
            setCardForHero(card.getCardName(), card.getPrice(), card.getDescription());

        else if (card.getType() == CardType.MINION) {

            Unit minion = (Unit) card;
            setCardsForMinion(minion.getCardName(), minion.getPrice(), minion.getDescription(),
                    minion.getAttackPoint(), minion.getHealthPoint(), minion.getManaCost(), minion.getUnitType(),
                    minion.hasComboAbility());

        } else if (card.getType() == CardType.SPELL) {

            Spell spell = (Spell) card;
            setCardsForSpell(spell.getCardName(), spell.getPrice(), spell.getDescription(),
                    spell.getManaCost());
        }
    }

    private void setCardForHero(String name, int cost, String description) {

        ShowCardInfoBattleViewHero showCardInfoBattleViewHero = new ShowCardInfoBattleViewHero();
        showCardInfoBattleViewHero.setName(name);
        showCardInfoBattleViewHero.setCost(cost);
        showCardInfoBattleViewHero.setDescription(description);
        cardsInfo.add(showCardInfoBattleViewHero);
    }

    private void setCardsForSpell(String name, int cost, String description, int manaPoint) {

        ShowCardInfoBattleViewSpell showCardInfoBattleViewSpell = new ShowCardInfoBattleViewSpell();
        showCardInfoBattleViewSpell.setName(name);
        showCardInfoBattleViewSpell.setCost(cost);
        showCardInfoBattleViewSpell.setDescription(description);
        showCardInfoBattleViewSpell.setManaPoint(manaPoint);
        cardsInfo.add(showCardInfoBattleViewSpell);
    }

    private void setCardsForMinion(String name, int cost, String description, int attackPoint, int healthPoint,
                                   int manaPoint, UnitType unitType, boolean hasComboAbility) {

        ShowCardInfoBattleViewMinion showCardInfoBattleViewMinion = new ShowCardInfoBattleViewMinion();
        showCardInfoBattleViewMinion.setName(name);
        showCardInfoBattleViewMinion.setCost(cost);
        showCardInfoBattleViewMinion.setDescription(description);
        showCardInfoBattleViewMinion.setAttackPoint(attackPoint);
        showCardInfoBattleViewMinion.setHealthPoint(healthPoint);
        showCardInfoBattleViewMinion.setManaPoint(manaPoint);
        showCardInfoBattleViewMinion.setRange(unitType);
        showCardInfoBattleViewMinion.setHasComboAbility(hasComboAbility);
        cardsInfo.add(showCardInfoBattleViewMinion);
    }
}
