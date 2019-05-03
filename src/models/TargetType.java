package models;

public class TargetType {
    private boolean isHybrid;
    private boolean isRanged;
    private boolean isMelee;
    public TargetType(boolean isHybrid, boolean isRanged, boolean isMelee) {
        this.isHybrid = isHybrid;
        this.isRanged = isRanged;
        this.isMelee = isMelee;
    }

    public boolean isMelee() {
        return isMelee;
    }

    public boolean isRanged() {
        return isRanged;
    }

    public boolean isHybrid() {
        return isHybrid;
    }
}

