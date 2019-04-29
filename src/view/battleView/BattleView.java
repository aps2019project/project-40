package view.battleView;

import models.Coordination;
import java.util.ArrayList;

public class BattleView {

    private static BattleView battleView;

    public static BattleView getInstance() {

        if (battleView == null) {

            battleView = new BattleView();
        }

        return battleView;
    }

    public void show(BattleView battleView) {

        if (battleView instanceof GameInfoBattleView) {

            GameInfoBattleView gameInfoBattleView = (GameInfoBattleView) battleView;
            System.out.println("player 1's mana : " + gameInfoBattleView.getPlayer1Mana());
            System.out.println("player 2's mana : " + gameInfoBattleView.getPlayer2Mana());

            if (battleView instanceof GameInfoBattleViewMode1) {

                showGameInfoMode1((GameInfoBattleViewMode1) battleView);
                return;
            }

            if (battleView instanceof GameInfoBattleViewMode2) {

                showGameInfoMode2((GameInfoBattleViewMode2) battleView);
                return;
            }

            if (battleView instanceof GameInfoBattleViewMode3) {

                showGameInfoMode3((GameInfoBattleViewMode3) battleView);
                return;
            }
        }

        if (battleView instanceof ShowMinionsBattleView) {

            showMinions((ShowMinionsBattleView) battleView);
            return;
        }

        if (battleView instanceof ShowCardInfoBattleView) {

            if (battleView instanceof ShowCardInfoBattleViewHero) {

                showCardInfoHero((ShowCardInfoBattleViewHero) battleView);
                return;
            }

            if (battleView instanceof ShowCardInfoBattleViewMinion) {

                showCardInfoMinion((ShowCardInfoBattleViewMinion) battleView);
                return;
            }

            if (battleView instanceof ShowCardInfoBattleViewSpell) {

                showCardInfoSpell((ShowCardInfoBattleViewSpell) battleView);
                return;
            }
        }

        if (battleView instanceof ShowHandBattleView) {

            showHand((ShowHandBattleView) battleView);
            return;
        }

        if (battleView instanceof ShowCollectedItemsBattleView) {

            showCollectedItems((ShowCollectedItemsBattleView) battleView);
            return;
        }

        if (battleView instanceof ShowSelectedItemInfoBattleView) {

            showSelectedItemInfo((ShowSelectedItemInfoBattleView) battleView);
            return;
        }

        if (battleView instanceof ShowCardsBattleView) {

            showCards((ShowCardsBattleView) battleView);
            return;
        }
    }

    private void showGameInfoMode1(GameInfoBattleViewMode1 gameInfo) {

        System.out.println("HP Of player 1's Hero : " + gameInfo.getPlayer1HeroHP());
        System.out.println("HP Of player 2's Hero : " + gameInfo.getPlayer2HeroHP());
    }

    private void showGameInfoMode2(GameInfoBattleViewMode2 gameInfo) {

        System.out.println("There is a flag in row : " + gameInfo.getFlagCoordination().getRow() +
                " and column : " + gameInfo.getFlagCoordination().getColumn());

        if (gameInfo.getFlagHolderTeam() != null || gameInfo.getFlagHolderName() != null) {

            System.out.println(gameInfo.getFlagHolderName() + " Of " + gameInfo.getFlagHolderTeam() + " has flag");
        }
        //if any cards have flag getFlagHolderName() will null and cause exception
    }

    private void showGameInfoMode3(GameInfoBattleViewMode3 gameInfo) {

        ArrayList<Coordination> coordinations = gameInfo.getFlagsCoordination();

        for (Coordination coordination : coordinations) {

            System.out.println("There is a flag in row : " + coordination.getRow() +
                    " and column : " + coordination.getColumn());
        }

        gameInfo.getFlagHoldersName().forEach((holderName, team) ->
                System.out.printf("%s of %s has flag\n", holderName, team));
    }

    private void showMinions(ShowMinionsBattleView showMinions) {

        ArrayList<String> cardsID = showMinions.getCardsID();
        ArrayList<String> cardsName = showMinions.getCardsName();
        ArrayList<Integer> healthPoints = showMinions.getHealthPoints();
        ArrayList<Coordination> locations = showMinions.getLocations();
        ArrayList<Integer> attackPoints = showMinions.getAttackPoints();

        if (cardsID.size() != cardsName.size() || cardsName.size() != healthPoints.size() ||
                healthPoints.size() != locations.size() || locations.size() != attackPoints.size()) {

            System.err.println("In show minion view size of arrayList doesn't equal");
            return;
        }

        for (int i = 0; i < cardsID.size(); i++) {

            System.out.println(cardsID.get(i) + " : " + cardsName.get(i) + ", ");
            System.out.println("health : " + healthPoints.get(i) + ", ");
            System.out.println("location : (row :" + locations.get(i).getRow() + ", " +
                    "column :" + locations.get(i).getColumn() + "), ");
            System.out.println("power : " + attackPoints.get(i));
        }
    }

    private void showCardInfoHero(ShowCardInfoBattleViewHero showInfo) {

        System.out.println("Hero:");
        System.out.println("Name: " + showInfo.getName());
        System.out.println("Cost: " + showInfo.getCost());
        System.out.println("Desc: " + showInfo.getDescription());
    }

    private void showCardInfoMinion(ShowCardInfoBattleViewMinion showInfo) {

        System.out.println("Minion:");
        System.out.println("Name: " + showInfo.getName());
        System.out.println("HP: " + showInfo.getHealthPoint() +
                " AP:" + showInfo.getAttackPoint() + " MP:" + showInfo.getManaPoint());
        System.out.println("Range: " + showInfo.getRange());
        System.out.println("Combo-ability: " + showInfo.hasComboAbility());
        System.out.println("Cost: " + showInfo.getCost());
        System.out.println("Desc: " + showInfo.getDescription());
    }

    private void showCardInfoSpell(ShowCardInfoBattleViewSpell showInfo) {

        System.out.println("Spell:");
        System.out.println("Name: " + showInfo.getName());
        System.out.println("MP: " + showInfo.getManaPoint());
        System.out.println("Cost: " + showInfo.getCost());
        System.out.println("Desc: " + showInfo.getDescription());
    }

    private void showHand(ShowHandBattleView showHand) {

        ArrayList<String> cardsName = showHand.getHandCardsName();

        for (String cardName : cardsName) {

            System.out.println("a hand card is: " + cardName);
        }

        if (showHand.getReserveCardName() != null) {

            System.out.println("reserve card is: " + showHand.getReserveCardName());
        }
    }

    private void showCollectedItems(ShowCollectedItemsBattleView collectedItem) {

        ArrayList<String> itemsName = collectedItem.getItemName();

        for (String itemName : itemsName) {

            System.out.println(itemName);
        }
    }

    private void showSelectedItemInfo(ShowSelectedItemInfoBattleView itemInfo) {

        System.out.println("Name: " + itemInfo.getName());
        System.out.println("Desc: " + itemInfo.getDescription());
    }

    private void showCards(ShowCardsBattleView showCards) {

        ArrayList<String> cards = showCards.getCards();

        for (String card : cards) {

            System.out.println(card);
        }
    }
}
