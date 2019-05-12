package view.battleView;

public class BattleLog {

    public static void logCardSelected(String cardID) {

        System.out.println(cardID + " selected");
    }

    public static void errorInvalidCardID() {

        System.out.println("Invalid card id");
    }

    public static void errorInvalidTarget() {

        System.out.println("Invalid target");
    }

    public static void logCardMoved(String cardID, int row, int column) {

        System.out.println(cardID + " moved to (" + row + ", " + column + ")");
    }

    public static void errorOpponentMinionUnavailableForAttak() {

        System.out.println("Opponent minion is unavailable for attack");
    }

    public static void errorCardCanNotAttack(String cardID) {

        System.out.println("Card with " + cardID + " can't attack");
    }

    public static void errorInvalidCardName() {

        System.out.println("Invalid card name");
    }

    public static void errorNotEnoughMana() {

        System.out.println("You don't have enough mana");
    }

    public static void logCardInserted(String cardName, String cardID, int row, int column) {

        System.out.println(cardName + " with " + cardID + " inserted to (" + row + ", " + column + ")");
    }

    public static void errorIsNotYourTurn() {

        System.out.println("It is not your turn");
    }

    public static void errorCellIsFill() {

        System.out.println("This cell is fill");
    }

    public static void errorCellIsNotFill() {

        System.out.println("there is any unit on this cell");
    }

    public static void errorCellNotAvailable() {

        System.out.println("This cell not available for you");
    }

    public static void showHelp() {

        System.out.println("Game info");
        System.out.println("Show my minions");
        System.out.println("Show opponent minions");
        System.out.println("Show card info [card id]");
        System.out.println("Select [card id]");
        System.out.println("    Move to ([x],[y])");
        System.out.println("    Attack [opponent card id]");
        System.out.println("    Attack combo [opponent card id] [my card id] [my card id] ...");
        System.out.println("    exit");
        System.out.println("Use special power ([x],[y])");
        System.out.println("Show hand");
        System.out.println("Insert [card name] in ([x],[y])");
        System.out.println("End turn");
        System.out.println("Show collectibles");
        System.out.println("Select [collectible id]");
        System.out.println("    Show info");
        System.out.println("    Use ([x],[y])");
        System.out.println("    exit");
        System.out.println("Show next card");
        System.out.println("Enter graveyard");
        System.out.println("    Show info [card id]");
        System.out.println("    Show cards");
        System.out.println("    exit");
        System.out.println("Help");
        System.out.println("End game");
    }

    public static void errorInvalidCommand() {

        System.out.println("Invalid command");
    }

    public static void errorInvalidItemName() {

        System.out.println("Invalid item name");
    }

    public static void errorUnitIsStunned() {

        System.out.println("Unit is stun");
    }

    public static void errorUnitAttacked() {

        System.out.println("This unit attacked previously");
    }

    public static void errorUnitMovedPreviously() {

        System.out.println("This unit moved previously");
    }

    public static void errorHasNotReserveCard() {

        System.out.println("You don't have reserve card");
    }

    public static void isDisarm(){
        System.out.println("defender is disarm");
    }

    public static void logTurnSwitched() {

        System.out.println("Turn switched");
    }

    public static void logTurnForWho(String userName) {

        System.out.println("This turn for *** " + userName + " ***");
    }

    public static void errorHasNotCombo() {

        System.out.println("card id you entered hasn't combo ability");
    }
}
