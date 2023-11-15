package wumpus.map;

import wumpus.Cell;

/**
 * Map class.
 */
public class Map {

    public static MapBuilder builder() {
        return new MapBuilder();
    }

    static final int MIN_WORLD_SIZE = 6;
    static final int MAX_WORLD_SIZE = 20;
    static final int WUMPUSES_EASY = 1;
    static final int WUMPUSES_MEDIUM = 2;
    static final int WUMPUSES_HARD = 3;
    private int size;
    private final Cell[][] cells;
    private final int wumpusCount;

    public Map(int size, Cell[][] cells) {
        this.setSize(size);
        this.cells = cells;
        this.wumpusCount = wumpusCountByWorldSize(this.size);
    }

    public Map(int size) {
        this.setSize(size);
        this.cells = new Cell[size][size];
        fillMapWithEmptyCells();
        this.wumpusCount = wumpusCountByWorldSize(this.size);
    }

    /**
     * Fill WordTable with empty type cells.
     */
    private void fillMapWithEmptyCells() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.cells[j][i] = new Cell(j, i, "_");
            }
        }
    }

    public int getSize() {
        return size;
    }

    /**
     * Method used to set world size.
     */
    public void setSize(int worldSize) {
        if (worldSize > MAX_WORLD_SIZE) {
            this.size = MAX_WORLD_SIZE;
        } else {
            this.size = Math.max(worldSize, MIN_WORLD_SIZE);
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public String getCellValue(int col, int row) {
        return this.cells[col][row].getValue();
    }

    private int wumpusCountByWorldSize(int worldSize) {
        if (worldSize <= 8) {
            return WUMPUSES_EASY;
        }
        if (worldSize <= 14) {
            return WUMPUSES_MEDIUM;
        } else {
            return WUMPUSES_HARD;
        }
    }

    /**
     * Map builder.
     */
    public static final class MapBuilder {
        private int size;
        private Cell[][] cells;

        private MapBuilder() {
        }

        public static MapBuilder builder() {
            return new MapBuilder();
        }

        public MapBuilder withSize(int size) {
            this.size = size;
            return this;
        }

        /**
         * Map builder.
         */
        public MapBuilder withSizeAndCells(int size, Cell[][] cells) {
            this.size = size;
            this.cells = cells;
            return this;
        }

    }
}


