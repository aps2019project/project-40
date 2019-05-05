package request.accountMenuRequest;

import request.Request;
import request.accountMenuRequest.accountMenuRequestChilds.AccountCreate;
import request.accountMenuRequest.accountMenuRequestChilds.AccountLoginRequest;
import request.accountMenuRequest.accountMenuRequestChilds.AccountOptionList;
import request.accountMenuRequest.accountMenuRequestChilds.AccountSimpleRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountMenuRequest extends Request {
    private static AccountMenuRequest accountMenuRequest;

    public static AccountMenuRequest getInstance() {
        if (accountMenuRequest == null)
            accountMenuRequest = new AccountMenuRequest();
        return accountMenuRequest;
    }

    public String getPassWord() {
        System.out.println("password :");
        String passWord = scanner.nextLine().trim();
        return passWord;
    }

    public AccountMenuRequest getCommand() {
        String actualCommand = scanner.nextLine().trim();
        String command = actualCommand.toLowerCase();
        Pattern pattern = Pattern.compile("create account (\\w+)");
        Matcher matcher = pattern.matcher(command);
        if (matcher.find()) {
            AccountCreate accountLoginRequest = new AccountCreate();
            accountLoginRequest.setLine(actualCommand.split("\\s")[2]);
            return accountLoginRequest;
        }

        pattern = Pattern.compile("login (\\w+)");
        matcher = pattern.matcher(command);
        if (matcher.find()) {
            AccountLoginRequest accountLoginRequest = new AccountLoginRequest();
            accountLoginRequest.setLine(actualCommand.split("\\s")[1]);
            return accountLoginRequest;
        }

        if (command.indexOf("show leaderboard") >= 0) {
            AccountSimpleRequest accountSimpleRequest = new AccountSimpleRequest();
            accountSimpleRequest.setAccountOptionList(AccountOptionList.SHOW_LEADERBOARD);
            return accountSimpleRequest;
        }

        if (command.indexOf("save") >= 0) {
            AccountSimpleRequest accountSimpleRequest = new AccountSimpleRequest();
            accountSimpleRequest.setAccountOptionList(AccountOptionList.SAVE);
            return accountSimpleRequest;
        }

        if (command.indexOf("logout") >= 0) {
            AccountSimpleRequest accountSimpleRequest = new AccountSimpleRequest();
            accountSimpleRequest.setAccountOptionList(AccountOptionList.LOGOUT);
            return accountSimpleRequest;
        }

        if (command.indexOf("help") >= 0) {
            AccountSimpleRequest accountSimpleRequest = new AccountSimpleRequest();
            accountSimpleRequest.setAccountOptionList(AccountOptionList.HELP);
            return accountSimpleRequest;
        }

        return null;
    }

}
