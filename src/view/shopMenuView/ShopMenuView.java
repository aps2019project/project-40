package view.shopMenuView;

import models.Card;
import models.CardType;
import models.Unit;

import java.util.ArrayList;

public class ShopMenuView {
    private static ShopMenuView shopMenuView;

    public static ShopMenuView getInstance() {

        if (shopMenuView == null)
            shopMenuView = new ShopMenuView();

        return shopMenuView;
    }

    public void showHelp() {
        System.out.println("exit");
        System.out.println("show");
        System.out.println("show collection");
        System.out.println("search [card name|item name]");
        System.out.println("buy [card name|item name]");
        System.out.println("sell [card ID|item ID]");
        System.out.println("search collection [card name|item name]");
        System.out.println("help");
    }

    public void showError(ShopError shopError) {
        System.out.println(shopError);
    }

    public void show(ArrayList<Card> cards, boolean showBuyCost) {
        int number = 1;
        System.out.println("Heroes :");

        for (Card card : cards)
            if (card.getType().equals(CardType.HERO))
                showHero(number++, (Unit) card, showBuyCost);

        number = 1;
        System.out.println("Items : ");
        for (Card card : cards)
            if (card.getType().equals(CardType.USABLE_ITEM))
                showItem(number++, card, showBuyCost);

        number = 1;
        System.out.println("Cards : ");
        for (Card card : cards)
            switch (card.getType()) {
                case MINION:
                    showMinion(number++, (Unit) card, showBuyCost);
                    break;
                case SPELL:
                    showSpell(number++, card, showBuyCost);
                    break;
            }
    }

    public void showSpell(int num, Card spell, boolean showCost) {
        if (showCost)
            System.out.println(num + " : Type : Spell - Name : " + spell.getCardName()
                    + " - MP : " + spell.getManaCost()
                    + " - Special power : " + spell.getDescription()
                    + " - Buy Cost : " + spell.getPrice());
        else
            System.out.println(num + " : Type : Spell - Name : " + spell.getCardName()
                    + " - MP : " + spell.getManaCost()
                    + " - Desc : " + spell.getDescription()
                    + "- Sell Cost : " + spell.getSellCost());

    }

    public void showIDs(ArrayList<String> IDs) {
        for (String id : IDs)
            System.out.println(id);
    }

    public void showHero(int num, Unit unit, boolean showCost) {
        if (showCost)
            System.out.println(num + " : Name : " + unit.getCardName()
                    + " - AP : " + unit.getAttackPoint()
                    + " - HP : " + unit.getHealthPoint()
                    + " - Class : " + unit.getUnitType()
                    + " - Special power : " + unit.getSpecialPower().getDescription()
                    + " - Buy Cost : " + unit.getPrice());
        else
            System.out.println(num + " : Type : Minion - Name : " + unit.getCardName()
                    + " - Class : " + unit.getUnitType()
                    + " - AP : " + unit.getAttackPoint()
                    + " - HP : " + unit.getHealthPoint()
                    + " - Special power : " + unit.getSpecialPower().getDescription()
                    + "- Sell Cost : " + unit.getSellCost());
    }

    public void showMinion(int num, Unit unit, boolean showCost) {
        if (showCost)
            System.out.println(num + " : Type : Minion - Name : " + unit.getCardName()
                    + " - Class : " + unit.getUnitType()
                    + " - AP : " + unit.getAttackPoint()
                    + " - HP : " + unit.getHealthPoint()
                    + " - MP : " + unit.getManaCost()
                    + " - Special power : " + unit.getSpecialPower().getDescription()
                    + " - Buy Cost : " + unit.getPrice());
        else
            System.out.println(num + " : Type : Minion - Name : " + unit.getCardName()
                    + " - Class : " + unit.getUnitType()
                    + " - AP : " + unit.getAttackPoint()
                    + " - HP : " + unit.getHealthPoint()
                    + " - MP : " + unit.getManaCost()
                    + " - Special power : " + unit.getSpecialPower().getDescription()
                    + "- Sell Cost : " + unit.getSellCost());
    }

    public void showItem(int num, Card card, boolean showCost) {

    }
}
