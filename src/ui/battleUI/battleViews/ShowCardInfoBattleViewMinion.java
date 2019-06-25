package ui.battleUI.battleViews;

import models.UnitType;

public class ShowCardInfoBattleViewMinion extends ShowCardInfoBattleView {

    private int attackPoint;
    private int healthPoint;
    private int manaPoint;
    private UnitType range;
    private boolean hasComboAbility;

    public int getAttackPoint() {

        return attackPoint;
    }

    public void setAttackPoint(int attackPoint) {

        this.attackPoint = attackPoint;
    }

    public int getHealthPoint() {

        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {

        this.healthPoint = healthPoint;
    }

    public int getManaPoint() {

        return manaPoint;
    }

    public void setManaPoint(int manaPoint) {

        this.manaPoint = manaPoint;
    }

    public UnitType getRange() {

        return range;
    }

    public void setRange(UnitType range) {

        this.range = range;
    }

    public boolean hasComboAbility() {

        return hasComboAbility;
    }

    public void setHasComboAbility(boolean hasComboAbility) {

        this.hasComboAbility = hasComboAbility;
    }
}
