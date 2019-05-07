package models;
//change setter getter

public class Target {
    final int MAP_ROWS = 5;
    final int MAP_Columns = 9;
    private int rowsAffected;
    private int columnsAffected;
    private boolean isAffectHero;
    private boolean isAffectMinion;
    private boolean isAffectCells;
    private boolean isRandom;
    private TargetType targetType;
    private boolean isDependentToCardLocation;
    private boolean isForDeckCards;

    private boolean isTargetEnemy;

    public Target(boolean isAffectCells, boolean isAffectHero, boolean isTargetEnemy,
                  boolean isAffectMinion, boolean isDependentToHeroLocation, boolean isRandom,
                  int rowsAffected, int columnsAffected, TargetType targetType) {
        this.isAffectCells = isAffectCells;
        this.isTargetEnemy = isTargetEnemy;
        this.isAffectHero = isAffectHero;
        this.isAffectMinion = isAffectMinion;
        this.isDependentToCardLocation = isDependentToHeroLocation;
        this.isRandom = isRandom;
        this.rowsAffected = rowsAffected;
        this.columnsAffected = columnsAffected;
        this.targetType = targetType;
    }

    public boolean isAffectCells() {
        return isAffectCells;
    }

    public boolean isRandom() {
        return isRandom;
    }

    public int getColumnsAffected() {
        return columnsAffected;
    }

    public boolean isDependentToCardLocation() {
        return isDependentToCardLocation;
    }

    public int getRowsAffected() {
        return rowsAffected;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public boolean isAffectHero() {
        return isAffectHero;
    }

    public boolean isAffectMinion() {
        return isAffectMinion;
    }

    public boolean isTargetEnemy() {
        return isTargetEnemy;
    }

    public boolean isForDeckCards() {
        return isForDeckCards;
    }
}
