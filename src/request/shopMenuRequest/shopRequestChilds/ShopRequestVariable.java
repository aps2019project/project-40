package request.shopMenuRequest.shopRequestChilds;

import request.shopMenuRequest.ShopRequest;

public class ShopRequestVariable extends ShopRequest {
    private CommandType commandType;
    private String nameOrId;

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public void setNameOrId(String nameOrId) {
        this.nameOrId = nameOrId;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String getNameOrId() {
        return nameOrId;
    }
}
