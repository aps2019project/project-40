import view.battleView.BattleView;
import view.battleView.GameInfoBattleViewMode1;

public class Main {

    public static void main(String[] args) {

        GameInfoBattleViewMode1 a = new GameInfoBattleViewMode1();
        a.setPlayer1HeroHP(10);
        a.setPlayer2HeroHP(10);
        a.setPlayer1Mana(20);
        a.setPlayer2HeroHP(20);

        BattleView b = new BattleView();
        b.show(a);
    }
}
