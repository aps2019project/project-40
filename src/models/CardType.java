package models;

import java.io.Serializable;

public enum CardType implements Serializable {
    USABLE_ITEM,
    COLLECTIBLE_ITEM,
    MINION,
    HERO,
    FLAG,
    SPELL,
}
