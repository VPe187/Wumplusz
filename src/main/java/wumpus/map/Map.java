package wumpus.map;

import wumpus.Cell;
import wumpus.HeroSight;

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
    private HeroSight heroSight;
    private int wumpusCells;
    private int arrowCount;
    private int emptyCells;
    private int wallCells;
    private int pitCells;
    private int goldCells;
    private int steps;


    public Map(int size, Cell[][] cells, HeroSight heroSight) {
        this.setSize(size);
        this.cells = cells;
        this.wumpusCells = wumpusCountByWorldSize(this.size);
        this.arrowCount = this.wumpusCells;
        this.heroSight = heroSight;
        this.countElements();
    }

    public Map(int size) {
        this.setSize(size);
        this.cells = new Cell[size][size];
        fillMapWithEmptyCells();
        this.wumpusCells = wumpusCountByWorldSize(this.size);
        this.arrowCount = this.wumpusCells;
        this.heroSight = HeroSight.NORTH;
        this.countElements();
    }

    private void countElements() {
        this.wallCells = 0;
        this.emptyCells = 0;
        this.wumpusCells = 0;
        this.pitCells = 0;
        this.goldCells = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                switch (this.cells[j][i].getCellValue()) {
                    case "W":
                        this.wallCells++;
                        break;
                    case "_":
                        this.emptyCells++;
                        break;
                    case "U":
                        this.wumpusCells++;
                        break;
                    case "P":
                        this.pitCells++;
                        break;
                    case "G":
                        this.goldCells++;
                        break;
                    default:
                        break;
                }
            }
        }
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

    public HeroSight getHeroSight() {
        return heroSight;
    }

    public int getArrowCount() {
        return arrowCount;
    }

    public int getWumpusCells() {
        return wumpusCells;
    }

    public int getEmptyCells() {
        return emptyCells;
    }

    public int getWallCells() {
        return wallCells;
    }

    public int getPitCells() {
        return pitCells;
    }

    public int getGoldCells() {
        return goldCells;
    }

    public int getSteps() {
        return steps;
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


