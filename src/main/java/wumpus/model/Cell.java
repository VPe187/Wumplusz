package wumpus.model;

import wumpus.wmap.WMapTools;

/**
 * Cell class used to store one field in Wumpus world.
 */
public class Cell {
    private final CellElement value;
    private final int col;
    private final int row;

    public static CellBuilder builder() {
        return new CellBuilder();
    }

    public Cell(int col, int row, CellElement value) {
        this.col = col;
        this.row = row;
        this.value = value;
    }

    public CellElement getCellValue() {
        return this.value;
    }

    public CellElement getValue() {
        return value;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return WMapTools.letterFromInteger(this.getCol()) + (this.getRow() + 1);

    }

    /**
     * Builder for Cell.
     */
    public static final class CellBuilder {
        private CellElement value;
        private int col;
        private int row;

        private CellBuilder() {
        }

        public static CellBuilder builder() {
            return new CellBuilder();
        }

        /**
         * Cell builder with col.
         */
        public CellBuilder withCol(int col) {
            this.col = col;
            return this;
        }

        /**
         * Cell builder with row.
         */
        public CellBuilder withRow(int row) {
            this.row = row;
            return this;
        }

        /**
         * Cell builder with value.
         */
        public CellBuilder withValue(CellElement value) {
            this.value = value;
            return this;
        }

        public Cell build() {
            return new Cell(this.col, this.row, this.value);
        }
    }

}
