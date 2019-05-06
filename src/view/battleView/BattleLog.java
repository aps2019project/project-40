package view.battleView;

public class BattleLog {

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

        System.out.println("This cell is fill and can not insert minion on this cell");
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
}
