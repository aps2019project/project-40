package view.collectionMenuView;

import controller.CollectionErrors;
import models.*;
import view.View;

import java.util.ArrayList;

public class CollectionMenuView extends View {
    private static CollectionMenuView collectionMenuView;

    public static CollectionMenuView getInstance() {

        if (collectionMenuView == null)
            collectionMenuView = new CollectionMenuView();

        return collectionMenuView;
    }

    public void showError(CollectionErrors collectionErrors) {
        System.out.println(collectionErrors);
    }

    public void showHelp() {
        System.out.println("exit");
        System.out.println("show");
        System.out.println("search [card name|item name]");
        System.out.println("save");
        System.out.println("create deck [deck name]");
        System.out.println("delete deck [deck name]");
        System.out.println("add [card id | item id | hero id] to deck [deck name]");
        System.out.println("remove [card id | item id | hero id] from deck [deck name]");
        System.out.println("validate deck [deck name]");
        System.out.println("select deck [deck name]");
        System.out.println("show all decks");
        System.out.println("show deck [deck name]");
        System.out.println("help");
    }

    public void show(ArrayList<Card> cards,boolean showCost) {
        int number = 1;
        System.out.println("Heroes :");

        for (Card card : cards)
            if (card.getType().equals(CardType.HERO))
                showHero(number++, (Unit) card, showCost);

        number = 1;
        for (Card card : cards)
            if (card.getType().equals(CardType.USABLE_ITEM))
                showItem(number++,card,showCost);

        number = 1;
        for (Card card : cards)
            switch (card.getType()){
                case MINION:
                    showMinion(number++, (Unit) card, showCost);
                    break;
                case SPELL:
                    showSpell(number++, (Spell) card, showCost);
                    break;
            }
    }

    public void showDeck(Deck deck){
        ArrayList<Card> cards=deck.getCards();
        show(cards,false);
    }

    public void showAllDecks(Collection collection){
        int number=1;
        for (Deck deck:collection.getDecks()){
            System.out.println((number++)+" : "+deck.getDeckName()+" : ");
            showDeck(deck);
        }
    }

    public void showSpell(int num, Spell spell, boolean showCost) {
        if (showCost)
            System.out.println(num + " : Type : Spell - Name : " + spell.getCardName()
                    + " - MP : " + spell.getManaCost()
                    + " - Special power : " + spell.getDescription());
        else
            System.out.println(num + " : Type : Spell - Name : " + spell.getCardName()
                    + " - MP : " + spell.getManaCost()
                    + " - Desc : " + spell.getDescription()
                    + "- Sell Cost : " + spell.getPrice());

    }

    public void showHero(int num, Unit unit, boolean showCost) {
        if (showCost)
            System.out.println(num + " : Name : " + unit.getCardName()
                    + " - AP : " + unit.getAttackPoint()
                    + " - HP : " + unit.getHealthPoint()
                    + " - Class : " + unit.getUnitType()
                    + " - Special power : " + unit.getSpecialPower().getDescription());
        else
            System.out.println(num + " : Type : Minion - Name : " + unit.getCardName()
                    + " - Class : " + unit.getUnitType()
                    + " - AP : " + unit.getAttackPoint()
                    + " - HP : " + unit.getHealthPoint()
                    + " - Special power : " + unit.getSpecialPower().getDescription()
                    + "- Sell Cost : " + unit.getPrice());
    }

    public void showMinion(int num, Unit unit, boolean showCost) {
        if (showCost)
            System.out.println(num + " : Type : Minion - Name : " + unit.getCardName()
                    + " - Class : " + unit.getUnitType()
                    + " - AP : " + unit.getAttackPoint()
                    + " - HP : " + unit.getHealthPoint()
                    + " - MP : " + unit.getManaCost()
                    + " - Special power : " + unit.getSpecialPower().getDescription());
        else
            System.out.println(num + " : Type : Minion - Name : " + unit.getCardName()
                    + " - Class : " + unit.getUnitType()
                    + " - AP : " + unit.getAttackPoint()
                    + " - HP : " + unit.getHealthPoint()
                    + " - MP : " + unit.getManaCost()
                    + " - Special power : " + unit.getSpecialPower().getDescription()
                    + "- Sell Cost : " + unit.getPrice());
    }

    public void showItem(int num, Card card, boolean showCost) {

    }
}
