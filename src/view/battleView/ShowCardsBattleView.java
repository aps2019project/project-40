package view.battleView;

import models.UnitType;

import java.util.ArrayList;

public class ShowCardsBattleView extends BattleView {

    private ArrayList<ShowCardInfoBattleView> cardsInfo = new ArrayList<>();

    public ArrayList<ShowCardInfoBattleView> getCardsInfo() {

        return cardsInfo;
    }

    public void setCardForHero(String name, int cost, String description, int manaPoint) {

        ShowCardInfoBattleViewHero showCardInfoBattleViewHero = new ShowCardInfoBattleViewHero();
        showCardInfoBattleViewHero.setName(name);
        showCardInfoBattleViewHero.setCost(cost);
        showCardInfoBattleViewHero.setDescription(description);
        cardsInfo.add(showCardInfoBattleViewHero);
    }

    public void setCardsForSpell(String name, int cost, String description, int manaPoint) {

        ShowCardInfoBattleViewSpell showCardInfoBattleViewSpell = new ShowCardInfoBattleViewSpell();
        showCardInfoBattleViewSpell.setName(name);
        showCardInfoBattleViewSpell.setCost(cost);
        showCardInfoBattleViewSpell.setDescription(description);
        showCardInfoBattleViewSpell.setManaPoint(manaPoint);
        cardsInfo.add(showCardInfoBattleViewSpell);
    }

    public void  setCardsForMinion(String name, int cost, String description, int attackPoint, int healthPoint,
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
    }
}
