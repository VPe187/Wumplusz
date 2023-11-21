package wumpus.wmap;

import wumpus.model.Cell;
import wumpus.model.CellElement;
import wumpus.model.HeroSight;

/**
 * Map class.
 */
public class WMap {
    static final int MIN_WORLD_SIZE = 6;
    static final int MAX_WORLD_SIZE = 20;
    static final int WUMPUSES_EASY = 1;
    static final int WUMPUSES_MEDIUM = 2;
    static final int WUMPUSES_HARD = 3;
    private final int size;
    private final Cell[][] cells;

    public static MapBuilder builder() {
        return new MapBuilder();
    }

    private final int startCol;
    private final int startRow;
    private HeroSight heroSight;
    private int wumpusCells;
    private int arrowCount;
    private int emptyCells;
    private int wallCells;
    private int pitCells;
    private int goldCells;
    private int steps;

    public WMap(int size, Cell[][] cells, HeroSight heroSight, int startCol, int startRow) {
        this.size = size;
        this.cells = cells;
        this.wumpusCells = wumpusCountByWorldSize(this.size);
        this.heroSight = heroSight;
        this.startCol = startCol;
        this.startRow = startRow;
        setSteps(0);
        setArrowCount(this.wumpusCells);
        this.countElements();
    }

    private void countElements() {
        this.wallCells = 0;
        this.emptyCells = 0;
        this.wumpusCells = 0;
        this.pitCells = 0;
        this.goldCells = 0;
        this.arrowCount = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                switch (this.cells[j][i].getCellValue()) {
                    case WALL:
                        this.wallCells++;
                        break;
                    case EMPTY:
                        this.emptyCells++;
                        break;
                    case WUMPUS:
                        this.wumpusCells++;
                        this.arrowCount++;
                        break;
                    case PIT:
                        this.pitCells++;
                        break;
                    case GOLD:
                        this.goldCells++;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void setHeroSight(HeroSight heroSight) {
        this.heroSight = heroSight;
    }

    public void setArrowCount(int arrowCount) {
        this.arrowCount = arrowCount;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }



    public Cell[][] getCells() {
        return cells;
    }

    public Cell getCell(int col, int row) {
        return this.cells[col][row];
    }

    public CellElement getCellValue(int col, int row) {
        return this.cells[col][row].getValue();
    }

    public int getSize() {
        return size;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getStartRow() {
        return startRow;
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
     * Decreases the number of arrows.
     */
    public void looseArrow() {
        if (arrowCount > 0) {
            this.arrowCount--;
        }
    }

    /**
     * Return map of cell which is contains Hero.
     */
    public Cell getHeroCell() {
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                if (this.cells[j][i].getCellValue().equals(CellElement.HERO)) {
                    return this.cells[j][i];
                }
            }
        }
        return null;
    }

    /**
     * Map builder class.
     */
    public static final class MapBuilder {
        private int size;
        private Cell[][] cells;
        private HeroSight heroSight;
        private int startCol;
        private int startRow;

        private MapBuilder() {
        }

        public static MapBuilder builder() {
            return new MapBuilder();
        }

        /**
         * Method build withSize.
         */
        public MapBuilder withSize(int size) {
            if (size > MAX_WORLD_SIZE) {
                this.size = MAX_WORLD_SIZE;
            } else {
                this.size = Math.max(size, MIN_WORLD_SIZE);
            }
            return this;
        }

        /**
         * Map builder withCells.
         */
        public MapBuilder withCells(Cell[][] cells) {
            this.cells = cells;
            return this;
        }

        /**
         * Map builder withHeroSight.
         */
        public MapBuilder withHeroSight(HeroSight heroSight) {
            this.heroSight = heroSight;
            return this;
        }

        /**
         * Map builder withStartCol.
         */
        public MapBuilder withStartCol(int startCol) {
            this.startCol = startCol;
            return this;
        }

        /**
         * Map builder withStartRow.
         */
        public MapBuilder withStartRow(int startRow) {
            this.startRow = startRow;
            return this;
        }

        /**
         * Map build.
         */
        public WMap build() {
            return new WMap(this.size, this.cells, this.heroSight, startCol, startRow);
        }

    }
}


