package ui.battleUI.battleViews;

import models.Coordination;

import java.util.ArrayList;
import java.util.HashMap;

public class BattleView {

    public void show(BattleView battleView) {

        if (battleView instanceof GameInfoBattleView) {

            GameInfoBattleView gameInfoBattleView = (GameInfoBattleView) battleView;
            System.out.println("player 1's mana : " + gameInfoBattleView.getPlayer1Mana());
            System.out.println("player 2's mana : " + gameInfoBattleView.getPlayer2Mana());

            if (battleView instanceof GameInfoBattleViewKillTheHero) {

                showGameInfoModeKillTheHero((GameInfoBattleViewKillTheHero) battleView);
                return;
            }

            if (battleView instanceof GameInfoBattleViewHoldTheFlag) {

                showGameInfoModeHoldTheFlag((GameInfoBattleViewHoldTheFlag) battleView);
                return;
            }

            if (battleView instanceof GameInfoBattleViewCollectTheFlags) {

                showGameInfoModeCollectTheFlags((GameInfoBattleViewCollectTheFlags) battleView);
                return;
            }
        }

        if (battleView instanceof ShowMinionsBattleView) {
//todo ooooooooooooooooooooooooo
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

    private void showGameInfoModeKillTheHero(GameInfoBattleViewKillTheHero gameInfo) {

        System.out.println("HP Of player 1's Hero : " + gameInfo.getPlayer1HeroHP());
        System.out.println("HP Of player 2's Hero : " + gameInfo.getPlayer2HeroHP());
    }

    private void showGameInfoModeHoldTheFlag(GameInfoBattleViewHoldTheFlag gameInfo) {

        System.out.println("There is a flag in (" + gameInfo.getFlagCoordination().getRow() +
                "," + gameInfo.getFlagCoordination().getColumn() + ")");

        if (gameInfo.getFlagHolderTeam() != null || gameInfo.getFlagHolderName() != null) {

            System.out.println(gameInfo.getFlagHolderName() + " Of " + gameInfo.getFlagHolderTeam() + " has flag");
        }
        //if any cards have flag getFlagHolderName() will null and cause exception
    }

    private void showGameInfoModeCollectTheFlags(GameInfoBattleViewCollectTheFlags gameInfo) {

        ArrayList<Coordination> coordinations = gameInfo.getFlagsCoordination();
        ArrayList<String[]> flagsHolders = gameInfo.getFlagHolders();

        for (Coordination coordination : coordinations) {

            System.out.println("There is a flag in row : " + coordination.getRow() +
                    " and column : " + coordination.getColumn());
        }

        for (String[] flagHolder : flagsHolders) {

            System.out.printf("%s of %s has %s flag(s)", flagHolder[0], flagHolder[1], flagHolder[2]);
        }
    }

    private void showMinions(ShowMinionsBattleView showMinions) {

        ArrayList<String> cardsID = showMinions.getCardsID();
        ArrayList<String> cardsName = showMinions.getCardsName();
        ArrayList<Integer> healthPoints = showMinions.getHealthPoints();
        ArrayList<Coordination> locations = showMinions.getLocations();
        ArrayList<Integer> attackPoints = showMinions.getAttackPoints();

        if (cardsID.size() != cardsName.size() || cardsName.size() != healthPoints.size() ||
                healthPoints.size() != locations.size() || locations.size() != attackPoints.size()) {

            System.err.println("In show minion View size of arrayList doesn't equal");
            return;
        }

        for (int i = 0; i < cardsID.size(); i++) {

            System.out.println(cardsID.get(i) + " : " + cardsName.get(i) + ", ");
            System.out.println("    health : " + healthPoints.get(i) + ", ");
            System.out.println("    location : (row :" + locations.get(i).getRow() + ", " +
                    "column :" + locations.get(i).getColumn() + "), ");
            System.out.println("    power : " + attackPoints.get(i));
        }
    }

    private void showCardInfoHero(ShowCardInfoBattleViewHero showInfo) {

        System.out.println("Hero:");
        System.out.println("    Name: " + showInfo.getName());
        System.out.println("    Cost: " + showInfo.getCost());
        System.out.println("    Desc:   " + showInfo.getDescription());
    }

    private void showCardInfoMinion(ShowCardInfoBattleViewMinion showInfo) {

        System.out.println("Minion:");
        System.out.println("    Name: " + showInfo.getName());
        System.out.println("    HP: " + showInfo.getHealthPoint() +
                " AP:" + showInfo.getAttackPoint() + " MP:" + showInfo.getManaPoint());
        System.out.println("    Range: " + showInfo.getRange());
        System.out.println("    Combo-ability: " + showInfo.hasComboAbility());
        System.out.println("    Cost: " + showInfo.getCost());
        System.out.println("    Desc:   " + showInfo.getDescription());
    }

    private void showCardInfoSpell(ShowCardInfoBattleViewSpell showInfo) {

        System.out.println("Spell:");
        System.out.println("    Name: " + showInfo.getName());
        System.out.println("    MP: " + showInfo.getManaPoint());
        System.out.println("    Cost: " + showInfo.getCost());
        System.out.println("    Desc:   " + showInfo.getDescription());
    }

    private void showCollectedItems(ShowCollectedItemsBattleView collectedItem) {

        HashMap<String, String> itemsInfo = collectedItem.getItemInfo();

        itemsInfo.forEach((name, description) ->
                System.out.println("name: " + name + "\n    description: " + description));
    }

    private void showSelectedItemInfo(ShowSelectedItemInfoBattleView itemInfo) {

        System.out.println("Name: " + itemInfo.getName());
        System.out.println("    Desc: " + itemInfo.getDescription());
    }

    private void showCards(ShowCardsBattleView showCards) {

        ArrayList<ShowCardInfoBattleView> cardsInfo = showCards.getCardsInfo();

        for (ShowCardInfoBattleView cardInfo : cardsInfo) {

            if (cardInfo instanceof ShowCardInfoBattleViewHero)
                showCardInfoHero((ShowCardInfoBattleViewHero) cardInfo);

            else if (cardInfo instanceof ShowCardInfoBattleViewSpell)
                showCardInfoSpell((ShowCardInfoBattleViewSpell) cardInfo);

            else if (cardInfo instanceof ShowCardInfoBattleViewMinion)
                showCardInfoMinion((ShowCardInfoBattleViewMinion) cardInfo);
        }
    }
}
