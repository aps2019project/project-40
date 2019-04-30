package request.battleRequest;

import request.Request;
import java.util.regex.Pattern;

public class BattleRequest extends Request {

    private static BattleRequest battleRequest;

    public static BattleRequest getInstance() {

        if (battleRequest == null) {

            battleRequest = new BattleRequest();
        }
        return battleRequest;
    }

    private boolean isCommandValid() {

        return false;
    }

    private void initializeCommandPatterns() {

        if (isCommandPatternsInitialized) return;

        Pattern useSpecialPower = Pattern.compile("use special power\\s*\\(\\s*\\d+\\s*,\\s*\\d+\\s*\\)");
        Pattern showCardInfo = Pattern.compile("show card info \\w+");
        Pattern enterGraveYard = Pattern.compile("enter graveyard");
        Pattern insertCard =  Pattern.compile("insert \\w+ in \\(\\s*\\d+\\s*,\\s*\\d+\\s*\\)");
        Pattern select = Pattern.compile("select \\w+");
        Pattern gameInfo = Pattern.compile("game info");
        Pattern showMyMinions = Pattern.compile("show my minions");
        Pattern showOpponentMinions = Pattern.compile("show opponent minions");
//        Pattern moveTo = Pattern.compile("move to\\s*\\(\\s*\\d+\\s*,\\s*\\d+\\s*\\)");
//        Pattern attack = Pattern.compile("attack \\w+");
//        Pattern attackCombo = Pattern.compile("attack combo \\w+( \\w+)+");
        Pattern showHand = Pattern.compile("show hand");
        Pattern endTurn = Pattern.compile("end turn");
        Pattern showCollectables = Pattern.compile("show collectables");
//        Pattern showInfo = Pattern.compile("show info");
//        Pattern use = Pattern.compile("use\\s*\\(\\s*\\d+\\s*,\\s*\\d+\\s*\\)");
        Pattern showNextCard = Pattern.compile("show next card");
        Pattern showCards = Pattern.compile("show cards");
        Pattern help = Pattern.compile("help");
        Pattern endGame = Pattern.compile("end game");
        Pattern exit = Pattern.compile("exit");

        commandPattern.add(useSpecialPower);
        commandPattern.add(showCardInfo);
        commandPattern.add(enterGraveYard);
        commandPattern.add(insertCard);
        commandPattern.add(select);
        commandPattern.add(gameInfo);
        commandPattern.add(showMyMinions);
        commandPattern.add(showOpponentMinions);
//        commandPattern.add(moveTo);
//        commandPattern.add(attack);
//        commandPattern.add(attackCombo);
        commandPattern.add(showHand);
        commandPattern.add(endTurn);
        commandPattern.add(showCollectables);
//        commandPattern.add(showInfo);
//        commandPattern.add(use);
        commandPattern.add(showNextCard);
        commandPattern.add(showCards);
        commandPattern.add(help);
        commandPattern.add(endGame);
        commandPattern.add(exit);
    }
}
