package request.battleMenuRequest;

import models.MatchType;
import request.Request;
import request.battleMenuRequest.battleMenuRequestChilds.CustomGameRequest;
import request.battleMenuRequest.battleMenuRequestChilds.MultiPlayerMenuRequest;
import request.battleMenuRequest.battleMenuRequestChilds.RequestMatchType;
import view.battleMenuView.BattleMenuView;
import view.battleMenuView.battleMenuViewChilds.BattleMenuError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BattleMenuRequest extends Request {
    private static BattleMenuRequest battleMenuRequest;
    private BattleMenuView battleMenuView = BattleMenuView.getInstance();

    public static BattleMenuRequest getInstance() {

        if (battleMenuRequest == null)
            battleMenuRequest = new BattleMenuRequest();

        return battleMenuRequest;
    }

    public BattleMenuRequest getCommand() {
        String command = scanner.nextLine().trim().toLowerCase();
        if (command.equals("1"))
            return getSinglePlayerCommand();

        if (command.equals("2"))
            return getMultiPlayerCommand();

        if (command.equals("exit")) {
            RequestMatchType requestMatchType = new RequestMatchType();
            requestMatchType.setMatchType(null);
            return requestMatchType;
        }

        battleMenuView.showError(BattleMenuError.INVALID_COMMAND);
        return null;
    }

    private BattleMenuRequest getSinglePlayerCommand() {
        RequestMatchType requestMatchType = new RequestMatchType();
        requestMatchType.setMatchType(MatchType.SINGLE_PLAYER);

        battleMenuView.showSinglePlayerCommand();
        String command = scanner.nextLine();
        if (command.equals("1"))
            requestMatchType.setModeOfGame(MatchType.STORY);
        else if (command.equals("2"))
            requestMatchType.setModeOfGame(MatchType.CUSTOMGAME);
        else {
            battleMenuView.showError(BattleMenuError.INVALID_COMMAND);
            return null;
        }

        return requestMatchType;
    }

    private BattleMenuRequest getMultiPlayerCommand() {
        RequestMatchType requestMatchType = new RequestMatchType();
        requestMatchType.setMatchType(MatchType.MULTI_PLAYER);
        return requestMatchType;
    }

    public String getUserName() {
        String command = scanner.nextLine().trim().toLowerCase();
        Pattern pattern = Pattern.compile("select user (\\w+)");
        Matcher matcher = pattern.matcher(command);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            battleMenuView.showError(BattleMenuError.INVALID_COMMAND);
            return null;
        }
    }

    public BattleMenuRequest startMultiPlayerGameRequest() {
        String command = scanner.nextLine().trim().toLowerCase();
        Pattern pattern = Pattern.compile("start multiplayer game (\\d)");
        Matcher matcher = pattern.matcher(command);

        if (matcher.find()) {

            MultiPlayerMenuRequest multiPlayerMenuRequest = new MultiPlayerMenuRequest();
            multiPlayerMenuRequest.setMode(matcher.group(1));
            if (multiPlayerMenuRequest.getMode() != null) {

                if (multiPlayerMenuRequest.getMode().equals(MatchType.COLLECT_THE_FLAGS)) {
                    pattern = Pattern.compile("start multiplayer game (\\d) (\\d+)");
                    matcher = pattern.matcher(command);

                    if (matcher.find())
                        multiPlayerMenuRequest.setNumberOfFlags(matcher.group(2));
                    else {

                        battleMenuView.showError(BattleMenuError.INVALID_COMMAND);
                        return null;
                    }
                }

                return multiPlayerMenuRequest;
            } else
                return null;
        } else {

            battleMenuView.showError(BattleMenuError.INVALID_COMMAND);
            return null;
        }
    }

    public BattleMenuRequest customGame() {
        String command = scanner.nextLine().trim().toLowerCase();
        Pattern pattern = Pattern.compile("start game (\\w+) (\\d)");
        Matcher matcher = pattern.matcher(command);
        if (matcher.find()) {
            CustomGameRequest customGameRequest = new CustomGameRequest();
            customGameRequest.setDeckName(matcher.group(1));
            customGameRequest.setMode(Integer.parseInt(matcher.group(2)));
            if (customGameRequest.getMode().equals(MatchType.COLLECT_THE_FLAGS)) {
                pattern = Pattern.compile("start game (\\w+) (\\d) (\\d+)");
                matcher = pattern.matcher(command);

                if (matcher.find())
                    customGameRequest.setFlagsNumber(Integer.parseInt(matcher.group(3)));
                else {
                    battleMenuView.showError(BattleMenuError.INVALID_COMMAND);
                    return null;
                }
            }

            return customGameRequest;
        }
        return null;
    }

    public int storyGame() {
        battleMenuView.showStoryGame();
        String command = scanner.nextLine().toLowerCase().trim();
        int modeNumber = 0;
        if (command.matches("(\\d)"))
            modeNumber = Integer.parseInt(command);
        return modeNumber;
    }
}
