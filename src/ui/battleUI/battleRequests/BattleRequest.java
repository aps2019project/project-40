package ui.battleUI.battleRequests;

import request.Request;
import view.battleView.BattleLog;

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

        while (true) {

            String command = scanner.nextLine().trim().toLowerCase();

            if (command.matches("game info"))
                return new RequestWithoutVariable(RequestWithoutVariableEnum.GAME_INFO_REQUEST);

            if (command.matches("show my minions"))
                return new RequestWithoutVariable(RequestWithoutVariableEnum.SHOW_MY_MINIONS_REQUEST);

            if (command.matches("show opponent minions"))
                return new RequestWithoutVariable(RequestWithoutVariableEnum.SHOW_OPPONENT_MINIONS_REQUEST);

            if (command.matches("show card info \\w+"))
                return showCardInfo(command);

            if (command.matches("select .+")) {

                BattleRequest request = select(command);
                if (request != null) return request;
                else continue;
            }

            if (command.matches("use special power"))
                return useSpecialPower();

            if (command.matches("enter graveyard")) {

                BattleRequest request = enterGraveYard();
                if (request != null) return request;
                else continue;
            }

            if (command.matches("insert \\w+ in \\(\\d+,\\d+\\)"))
                return insertCard(command);

            if (command.matches("show hand"))
                return new RequestWithoutVariable(RequestWithoutVariableEnum.SHOW_HAND_REQUEST);

            if (command.matches("end turn"))
                return new RequestWithoutVariable(RequestWithoutVariableEnum.END_TURN_REQUEST);

            if (command.matches("show collectibles"))
                return new RequestWithoutVariable(RequestWithoutVariableEnum.SHOW_COLLECTED_ITEM_REQUEST);

            if (command.matches("show next card"))
                return new RequestWithoutVariable(RequestWithoutVariableEnum.SHOW_NEXT_CARD_REQUEST);

            if (command.matches("help"))
                return new RequestWithoutVariable(RequestWithoutVariableEnum.HELP_REQUEST);

            if (command.matches("end game"))
                return new RequestWithoutVariable(RequestWithoutVariableEnum.END_GAME_REQUEST);

            BattleLog.errorInvalidCommand();
        }
    }

    private BattleRequest showCardInfo(String command) {

        ShowCardInfoRequest request = new ShowCardInfoRequest();
        request.setCardID(command.split("\\s")[3]);
        return request;
    }

    private BattleRequest select(String command) {

        SelectAndUseCardRequest selectAndUseCardRequest = new SelectAndUseCardRequest();
        selectAndUseCardRequest.setID(command.split("\\s")[1]);
        BattleLog.logCardSelected(selectAndUseCardRequest.getID());

        while (true) {

            String secondCommand = scanner.nextLine().trim().toLowerCase();

            if (secondCommand.matches("move to \\(\\d+,\\d+\\)"))
                return moveTo(secondCommand, selectAndUseCardRequest);

            if (secondCommand.matches("attack .+"))
                return attack(secondCommand, selectAndUseCardRequest);

            if (secondCommand.matches("attack combo .+( .+)+"))
                return attackCombo(secondCommand, selectAndUseCardRequest);

            if (secondCommand.matches("exit")) return null; //todo pay attention to null
        }
    }

    private BattleRequest useSpecialPower() {

        SelectAndUseCardRequest selectAndUseCardRequest = new SelectAndUseCardRequest();
        selectAndUseCardRequest.setForUseSpecialPower(true);
        return selectAndUseCardRequest;
    }

    private BattleRequest moveTo(String command, SelectAndUseCardRequest selectAndUseCardRequest) {

        selectAndUseCardRequest.setForMove(true);
        selectAndUseCardRequest.setRow(
                Integer.parseInt(
                        command.split("[\\(|\\)]")[1].split(",")[0]));

        selectAndUseCardRequest.setColumn(
                Integer.parseInt(
                        command.split("[\\(|\\)]")[1].split(",")[1]));

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

    private BattleRequest enterGraveYard() {

        EnterGraveYardRequest enterGraveYardRequest = new EnterGraveYardRequest();

        while (true) {

            String command = scanner.nextLine().trim().toLowerCase();

            if (command.matches("show info \\w+")) {

                enterGraveYardRequest.setForShowInfo(true);
                enterGraveYardRequest.setCardID(command.split("\\s")[2]);
                return enterGraveYardRequest;
            }

            if (command.matches("show cards")) {

                enterGraveYardRequest.setForShowCards(true);
                return enterGraveYardRequest;
            }

            if (command.matches("exit")) {

                enterGraveYardRequest.setForExit(true);
                return enterGraveYardRequest;
            }

            //todo error
        }
    }

    private BattleRequest insertCard(String command) {

        InsertCardRequest insertCardRequest = new InsertCardRequest();
        insertCardRequest.setCardName(command.split("\\s")[1]);
        insertCardRequest.setRow(Integer.parseInt(command.split("[\\(|\\)]")[1].split(",")[0]));
        insertCardRequest.setColumn(Integer.parseInt(command.split("[\\(|\\)]")[1].split(",")[1]));
        return insertCardRequest;
    }

}
