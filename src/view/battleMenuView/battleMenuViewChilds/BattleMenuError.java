package view.battleMenuView.battleMenuViewChilds;

public enum BattleMenuError {
    INVALID_DECK("selected deck is invalid"),
    INVALID_COMMAND("Invalid Command"),
    INVALID_DECK_SECOND_PLAYER("selected deck for second player is invalid"),
    INVALID_USER("Invalid User");

    BattleMenuError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;

    public String toString() {
        return errorMessage;
    }

}
