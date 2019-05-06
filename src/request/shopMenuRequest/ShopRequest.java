package request.shopMenuRequest;

import request.Request;
import request.shopMenuRequest.shopRequestChilds.CommandType;
import request.shopMenuRequest.shopRequestChilds.ShopRequestVariable;
import request.shopMenuRequest.shopRequestChilds.ShopRequestWithOutVariable;
import request.shopMenuRequest.shopRequestChilds.ShopSimpleRequestList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopRequest extends Request {
    private static ShopRequest shopRequest;

    public static ShopRequest getInstance() {
        if (shopRequest == null)
            shopRequest = new ShopRequest();
        return shopRequest;
    }

    public ShopRequest getCommand() {
        String command = scanner.nextLine().toLowerCase().trim();

        Pattern pattern = Pattern.compile("sell (\\w+)");
        Matcher matcher = pattern.matcher(command);
        if (matcher.find())
            return sell(matcher.group(1));

        pattern = Pattern.compile("buy (\\w+)");
        matcher = pattern.matcher(command);
        if (matcher.find())
            return buy(matcher.group(1));

        pattern = Pattern.compile("search collection (\\w+)");
        matcher = pattern.matcher(command);
        if (matcher.find())
            return searchCollection(matcher.group(1));

        pattern = Pattern.compile("search (\\w+)");
        matcher = pattern.matcher(command);
        if (matcher.find())
            return search(matcher.group(1));

        if (command.matches("help"))
            return simpleCommand(ShopSimpleRequestList.HELP);

        if (command.matches("exit"))
            return simpleCommand(ShopSimpleRequestList.EXIT);

        if (command.matches("show collection"))
            return simpleCommand(ShopSimpleRequestList.SHOW_COLLECTION);

        if (command.matches("show"))
            return simpleCommand(ShopSimpleRequestList.SHOW);

        return null;
    }

    public ShopRequest simpleCommand(ShopSimpleRequestList shopSimpleRequestList) {
        ShopRequestWithOutVariable shopRequestWithOutVariable = new ShopRequestWithOutVariable();
        shopRequestWithOutVariable.setShopSimpleRequestList(shopSimpleRequestList);
        return shopRequestWithOutVariable;
    }

    public ShopRequest buy(String nameOrID) {
        ShopRequestVariable shopRequestVariable = new ShopRequestVariable();
        shopRequestVariable.setCommandType(CommandType.BUY);
        shopRequestVariable.setNameOrId(nameOrID);
        return shopRequestVariable;
    }

    public ShopRequest sell(String nameOrID) {
        ShopRequestVariable shopRequestVariable = new ShopRequestVariable();
        shopRequestVariable.setCommandType(CommandType.SELL);
        shopRequestVariable.setNameOrId(nameOrID);

        return shopRequestVariable;
    }

    public ShopRequest search(String nameOrID) {
        ShopRequestVariable shopRequestVariable = new ShopRequestVariable();
        shopRequestVariable.setCommandType(CommandType.SEARCH);
        shopRequestVariable.setNameOrId(nameOrID);

        return shopRequestVariable;
    }

    public ShopRequest searchCollection(String nameOrID) {
        ShopRequestVariable shopRequestVariable = new ShopRequestVariable();
        shopRequestVariable.setCommandType(CommandType.SEARCH_COLLECTION);
        shopRequestVariable.setNameOrId(nameOrID);

        return shopRequestVariable;
    }
}
