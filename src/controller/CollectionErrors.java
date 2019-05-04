package controller;

public enum CollectionErrors {
    CARD_NOT_FOUND("this card is not in collection"),
    ALREADY_IN_DECK("there is already a card with this ID in deck"),
    ALREADY_A_HERO_IN_DECK("there is already a hero in deck"),
    DECK_DOES_NOT_EXIST("deck with this name does not exist"),
    EXCESSIVE_CARD("there is already 20 cards in deck");

    CollectionErrors(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;

    public String toString() {
        return errorMessage;
    }
}
