package wumpus.model;

/**
 * Cell class used to store one field in Wumpus world.
 */
public class Cell {

    private String value;
    private final int col;
    private final int row;

    public static CellBuilder builder() {
        return new CellBuilder();
    }

    public Cell(int col, int row, String value) {
        this.col = col;
        this.row = row;
        this.value = value;
    }

    public void setCellValue(String value) {
        this.value = value;
    }

    public String getCellValue() {
        return this.value;
    }

    public String getValue() {
        return value;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    /**
     * Builder for Cell.
     */
    public static final class CellBuilder {
        private String value;
        private int col;
        private int row;

        private CellBuilder() {
        }

        public static CellBuilder builder() {
            return new CellBuilder();
        }

        /**
         * Cell builder with col, row value parameters.
         */
        public CellBuilder withColRowValue(int col, int row, String value) {
            this.col = col;
            this.row = row;
            this.value = value;
            return this;
        }

    }

}
