package wumpus.model;

/**
 * Hero sights.
 */
public enum HeroSight {
    NORTH("N"), WEST("W"), SOUTH("S"), EAST("E");
    private final String sign;

    /** HeroSight enums.
     *      *
     * @param sign as String
     */
    HeroSight(final String sign) {
        this.sign = sign;
    }

    public static HeroSight getByValue(String sign) {
        for (HeroSight value : values()) {
            if (value.sign.equals(sign)) {
                return value;
            }
        }
        return null;
    }

    public HeroSight left() {
        return values()[(this.ordinal() + 1) % values().length];
    }

    public HeroSight right() {
        return values()[(this.ordinal() - 1 + values().length) % values().length];
    }
}
