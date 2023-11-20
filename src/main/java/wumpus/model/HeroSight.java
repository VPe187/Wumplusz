package wumpus.model;

/**
 * Hero sights.
 */
public enum HeroSight {
    NORTH, WEST, SOUTH, EAST;

    private static final HeroSight[] heroValues = values();

    public HeroSight left() {
        return heroValues[(this.ordinal() + 1) % heroValues.length];
    }

    public HeroSight right() {
        return heroValues[(this.ordinal() - 1 + heroValues.length) % heroValues.length];
    }
}
