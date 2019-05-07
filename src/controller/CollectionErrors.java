package controller;

public enum CollectionErrors {
    CARD_NOT_FOUND("this card is not in collection"),
    ALREADY_IN_DECK("there is already a card with this ID in deck"),
    ALREADY_A_HERO_IN_DECK("there is already a hero in deck"),
    DECK_DOES_NOT_EXIST("deck with this name does not exist"),
    DECK_IS_NOT_VALID("Invalid deck"),
    EXCESSIVE_CARD("there is already 20 cards in deck"),
    DECK_IS_VALID("deck is valid and you are ready to play"),
    ALREADY_A_DECK_WITH_THIS_USERNAME("there is already a deck with this name in collection");

    CollectionErrors(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;

    public String toString() {
        return errorMessage;
    }
}
