package wumpus.model;

/**
 * Hero sights.
 */
public enum CellElement {
    WALL("W"), WUMPUS("U"), PIT("P"), EMPTY("_"), GOLD("G"), HERO("H");
    private final String sign;

    /** Cell element enums.
     *
     * @param sign as String
     */
    CellElement(final String sign) {
        this.sign = sign;
    }

    /** Cell element by value.
     *
     * @param sign as String
     *
     * @return {@link CellElement}
     */
    public static CellElement getByValue(String sign) {
        for (CellElement value : values()) {
            if (value.sign.equals(sign)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.sign;
    }
}
