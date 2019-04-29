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


        }

        if (battleView instanceof ShowCardInfoBattleView) {

            if (battleView instanceof ShowCardInfoBattleViewHero) {


            }

            if (battleView instanceof ShowCardInfoBattleViewMinion) {


            }

            if (battleView instanceof ShowCardInfoBattleViewSpell) {


            }
        }

        if (battleView instanceof ShowHandBattleView) {


        }

        if (battleView instanceof ShowCollectedItemsBattleView) {


        }

        if (battleView instanceof ShowSelectedItemInfoBattleView) {


        }

        if (battleView instanceof ShowCardsBattleView) {


        }
    }

    private void showGameInfoMode1(GameInfoBattleViewMode1 gameInfo) {

        System.out.println("HP Of player 1's Hero : " + gameInfo.getPlayer1HeroHP());
        System.out.println("HP Of player 2's Hero : " + gameInfo.getPlayer2HeroHP());
    }

    private void showGameInfoMode2(GameInfoBattleViewMode2 gameInfo) {

        System.out.println("There is a flag in row : " + gameInfo.getFlagCoordination().getRow() +
                " and column : " + gameInfo.getFlagCoordination().getColumn());

        if (gameInfo.getFlagHolderTeam() != null || gameInfo.getFlagHolderName() != null){

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


}
