package view.battleMenuView.battleMenuViewChilds;

public enum BattleMenuError {
    INVALID_DECK("selected deck is invalid");


    BattleMenuError(String errorMessage){
        this.errorMessage=errorMessage;
    }

    private String errorMessage;

    public String toString(){
        return errorMessage;
    }

}
