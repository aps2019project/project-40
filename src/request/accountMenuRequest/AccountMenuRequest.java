package request.accountMenuRequest;

import request.Request;
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

    public AccountMenuRequest getPassWord() {
        String passWord = scanner.nextLine();
        AccountLoginRequest accountLoginRequest = new AccountLoginRequest();
        accountLoginRequest.setLine(passWord);
        return accountLoginRequest;
    }

    public AccountMenuRequest getCommand() {
        String command = scanner.nextLine().trim().toLowerCase();
        Pattern pattern = Pattern.compile("create account (\\w+)");
        Matcher matcher = pattern.matcher(command);
        if (matcher.find()) {
            AccountLoginRequest accountLoginRequest = new AccountLoginRequest();
            accountLoginRequest.setLine(matcher.group(1));
            return accountLoginRequest;
        }

        pattern = Pattern.compile("login (\\w+)");
        matcher = pattern.matcher(command);
        if (matcher.find()) {
            AccountLoginRequest accountLoginRequest = new AccountLoginRequest();
            accountLoginRequest.setLine(matcher.group(1));
            return accountLoginRequest;
        }

        if (command.indexOf("show leaderboard") >= 0) {
            AccountSimpleRequest accountSimpleRequest = new AccountSimpleRequest();
            accountSimpleRequest.setAccountOptionList(AccountOptionList.SHOW_LEADERBOARD);
            return accountSimpleRequest;
        }

        if (command.indexOf("save") >= 0) {
            AccountSimpleRequest accountSimpleRequest = new AccountSimpleRequest();
            accountSimpleRequest.setAccountOptionList(AccountOptionList.SHOW_LEADERBOARD);
            return accountSimpleRequest;
        }

        if (command.indexOf("logout") >= 0) {
            AccountSimpleRequest accountSimpleRequest = new AccountSimpleRequest();
            accountSimpleRequest.setAccountOptionList(AccountOptionList.SHOW_LEADERBOARD);
            return accountSimpleRequest;
        }

        if (command.indexOf("help") >= 0) {
            AccountSimpleRequest accountSimpleRequest = new AccountSimpleRequest();
            accountSimpleRequest.setAccountOptionList(AccountOptionList.SHOW_LEADERBOARD);
            return accountSimpleRequest;
        }
        return null;
    }

}
