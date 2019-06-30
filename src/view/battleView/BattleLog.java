package view.battleView;

public class BattleLog {

    public static void logCardSelected(String cardID) {

        System.err.println(cardID + " selected");
    }

    public static void errorInvalidCardID() {

        System.err.println("Invalid card id");
    }

    public static void errorInvalidTarget() {

        System.err.println("Invalid target");
    }

    public static void logCardMoved(String cardID, int row, int column) {

        System.err.println(cardID + " moved to (" + row + ", " + column + ")");
    }

    public static void errorOpponentMinionUnavailableForAttak() {

        System.err.println("Opponent minion is unavailable for attack");
    }

    public static void errorCardCanNotAttack(String cardID) {

        System.err.println("Card with " + cardID + " can't attack");
    }

    public static void errorInvalidCardName() {

        System.err.println("Invalid card name");
    }

    public static void errorNotEnoughMana() {

        System.err.println("You don't have enough mana");
    }

    public static void logCardInserted(String cardName, String cardID, int row, int column) {

        System.err.println(cardName + " with " + cardID + " inserted to (" + row + ", " + column + ")");
    }

    public static void errorIsNotYourTurn() {

        System.err.println("It is not your turn");
    }

    public static void errorCellIsFill() {

        System.err.println("This cell is fill");
    }

    public static void errorCellIsNotFill() {

        System.err.println("there isn't any unit on this cell");
    }

    public static void errorCellNotAvailable() {

        System.err.println("This cell not available for you");
    }

    public static void showHelp() {

        System.err.println("Game info");
        System.err.println("Show my minions");
        System.err.println("Show opponent minions");
        System.err.println("Show card info [card id]");
        System.err.println("Select [card id]");
        System.err.println("    Move to ([x],[y])");
        System.err.println("    Attack [opponent card id]");
        System.err.println("    Attack combo [opponent card id] [my card id] [my card id] ...");
        System.err.println("    exit");
        System.err.println("Use special power ([x],[y])");
        System.err.println("Show hand");
        System.err.println("Insert [card name] in ([x],[y])");
        System.err.println("End turn");
        System.err.println("Show collectibles");
        System.err.println("Select [collectible id]");
        System.err.println("    Show info");
        System.err.println("    Use ([x],[y])");
        System.err.println("    exit");
        System.err.println("Show next card");
        System.err.println("Enter graveyard");
        System.err.println("    Show info [card id]");
        System.err.println("    Show cards");
        System.err.println("    exit");
        System.err.println("Help");
        System.err.println("End game");
    }

    public static void errorInvalidCommand() {

        System.err.println("Invalid command");
    }

    public static void errorUnitIsStunned() {

        System.err.println("Unit is stun");
    }

    public static void errorUnitAttacked() {

        System.err.println("This unit attacked previously");
    }

    public static void errorUnitMovedPreviously() {

        System.err.println("This unit moved previously");
    }

    public static void errorHasNotReserveCard() {

        System.err.println("You don't have reserve card");
    }

    public static void isDisarm() {
        System.err.println("defender is disarm");
    }

    public static void logTurnForWho(String userName) {

        System.out.println("This turn for *** " + userName + " ***");
    }

    public static void errorHasNotCombo() {

        System.err.println("card id you entered hasn't combo ability");
    }

    public static void errorDeckOut() {

        System.out.println("Deck has any card");
    }

    public static void logAttacked() {

        System.out.println("Unit attacked");
    }

    public static void errorItIsYourUnit() {

        System.out.println("It is your unit and not enemy");
    }

    public static void errorItIsUnitOfEnemy() {

        System.out.println("It is enemy unit");
    }

    public static void PlayerOneWins(){
        System.out.println("Player One Wins");
    }

    public static void PlayerTwoWins(){
        System.out.println("Player Two Wins");
    }
}
