package view.battleView;

public class BattleError {

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
}
