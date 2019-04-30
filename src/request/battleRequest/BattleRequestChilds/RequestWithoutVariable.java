package request.battleRequest.BattleRequestChilds;

import request.battleRequest.BattleRequest;

public class RequestWithoutVariable extends BattleRequest {

    private RequestWithoutVariableEnum request;

    public RequestWithoutVariable(RequestWithoutVariableEnum request) {

        this.request = request;
    }

    public RequestWithoutVariableEnum getEnumRequest() {

        return request;
    }
}
