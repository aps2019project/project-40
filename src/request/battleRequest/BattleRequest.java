package request.battleRequest;

import request.Request;
import request.battleRequest.BattleRequestChilds.RequestWithoutVariable;
import request.battleRequest.BattleRequestChilds.RequestWithoutVariableEnum;
import request.battleRequest.BattleRequestChilds.SelectAndUseCardRequest;
import request.battleRequest.BattleRequestChilds.ShowCardInfoRequest;
import java.util.ArrayList;

public class BattleRequest extends Request {

    private static BattleRequest battleRequest;

    public static BattleRequest getInstance() {

        if (battleRequest == null) {

            battleRequest = new BattleRequest();
        }
        return battleRequest;
    }

    public BattleRequest getRequest() {

        String command;

        while (true) {

            command = scanner.nextLine().trim().toLowerCase();

            if (command.matches("game info"))
                return gameInfo();

            if (command.matches("show my minions"))
                return showMyMinions();

            if (command.matches("show opponent minions"))
                return showOpponentMinions();

            if (command.matches("show card info \\w+"))
                return showCardInfo(command);

            if (command.matches("select \\w+"))
                select(command);

            if (command.matches("use special power\\s*\\(\\s*\\d+\\s*,\\s*\\d+\\s*\\)")) {


            }

            if (command.matches("enter graveyard")) {


            }

            if (command.matches("insert \\w+ in \\(\\s*\\d+\\s*,\\s*\\d+\\s*\\)")) {


            }

            if (command.matches("show hand")) {


            }

            if (command.matches("end turn")) {


            }

            if (command.matches("show collectables")) {


            }

            if (command.matches("show next card")) {


            }

            if (command.matches("show cards")) {


            }

            if (command.matches("help")) {


            }

            if (command.matches("end game")) {


            }

            if (command.matches("exit")) {


            }
        }
    }

    private BattleRequest gameInfo() {

        RequestWithoutVariable request =
                new RequestWithoutVariable(RequestWithoutVariableEnum.GAME_INFO_REQUEST);
        return request;
    }

    private BattleRequest showMyMinions() {

        RequestWithoutVariable request =
                new RequestWithoutVariable(RequestWithoutVariableEnum.SHOW_MY_MINIONS_REQUEST);
        return request;
    }

    private BattleRequest showOpponentMinions() {

        RequestWithoutVariable request =
                new RequestWithoutVariable(RequestWithoutVariableEnum.SHOW_OPPONENT_MINIONS_REQUEST);
        return request;
    }

    private BattleRequest showCardInfo(String command) {

        ShowCardInfoRequest request = new ShowCardInfoRequest();
        request.setCardID(command.split("\\s")[3]);
        return request;
    }

    private BattleRequest select(String command) {

        SelectAndUseCardRequest selectAndUseCardRequest = new SelectAndUseCardRequest();
        selectAndUseCardRequest.setID(command.split("\\s")[1]);

        while (true) {

            String secondCommand = scanner.nextLine();

            if (secondCommand.matches("move to \\(\\d+,\\d+\\)"))
                return moveTo(secondCommand, selectAndUseCardRequest);

            if (secondCommand.matches("attack \\w+"))
                return attack(secondCommand, selectAndUseCardRequest);

            if (secondCommand.matches("attack combo \\w+( \\w+)+"))
                return attackCombo(secondCommand, selectAndUseCardRequest);

            if (secondCommand.matches("show info"))
                return selectAndUseCardRequest;

            if (secondCommand.matches("use \\(\\d+,\\d+\\)"))
                return selectAndUseCardRequest;

            //todo error if invalid
        }
    }

    private BattleRequest moveTo(String command, SelectAndUseCardRequest selectAndUseCardRequest) {

        selectAndUseCardRequest.setForMove(true);
        selectAndUseCardRequest.setRow(
                Integer.parseInt(
                        command.split("[\\(,\\)]")[1].split(",")[0]));

        selectAndUseCardRequest.setColumn(
                Integer.parseInt(
                        command.split("[\\(,\\)]")[1].split(",")[1]));

        return selectAndUseCardRequest;
    }

    private BattleRequest attack(String command, SelectAndUseCardRequest selectAndUseCardRequest) {

        selectAndUseCardRequest.setForAttack(true);
        selectAndUseCardRequest.setOpponentCardID(command.split("\\s")[1]);
        return selectAndUseCardRequest;
    }

    private BattleRequest attackCombo(String command, SelectAndUseCardRequest selectAndUseCardRequest) {

        String[] detailCommand = command.split("\\s");
        selectAndUseCardRequest.setForAttackCombo(true);
        selectAndUseCardRequest.setOpponentCardID(detailCommand[2]);

        ArrayList<String> myCardsID = new ArrayList<>();

        for (int i = 3; i < detailCommand.length; i++) {

            myCardsID.add(detailCommand[i]);
        }

        selectAndUseCardRequest.setMyCardsID(myCardsID);
        return selectAndUseCardRequest;
    }

    private BattleRequest showInfo(String command, SelectAndUseCardRequest selectAndUseCardRequest) {

        selectAndUseCardRequest.setForShowInfo(true);
        return selectAndUseCardRequest;
    }

    private BattleRequest use(String command, SelectAndUseCardRequest selectAndUseCardRequest) {

        selectAndUseCardRequest.setForUse(true);
        selectAndUseCardRequest.setRow(
                Integer.parseInt(
                command.split("[\\(,\\)]")[1].split(",")[0]));

        selectAndUseCardRequest.setColumn(
                Integer.parseInt(
                        command.split("[\\(,\\)]")[1].split(",")[1]));

        return selectAndUseCardRequest;
    }
}
