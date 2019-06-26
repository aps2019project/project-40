package ui.battleUI.battleRequests;

public class RequestWithoutVariable extends BattleRequest {

    private RequestWithoutVariableEnum request;

    public RequestWithoutVariable(RequestWithoutVariableEnum request) {

        this.request = request;
    }

    public RequestWithoutVariableEnum getEnumRequest() {

        return request;
    }
}
