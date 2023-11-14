package wumpus;

/**
 * World matrix used to store Wumpus world cells.
 */
public class WorldTable {
    private final int rows;
    private final int columns;
    private int heroPosRow;
    private int heroPosColumn;
    private final Cell[][] cells;

    public WorldTable(int worldSize) {
        this.rows = worldSize;
        this.columns = worldSize;
        this.cells = new Cell[rows][columns];
    }

    /**
     * Fill WordTable with empty type cells.
     */
    public void fillTableEmptyCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.cells[j][i] = new Cell("_");
            }
        }
    }

    public void setCellValue(int row, int col, String value) {
        this.cells[row][col].setCellValue(value);
    }

    public String getCellValue(int row, int col) {
        return this.cells[row][col].getCellValue();
    }

    public void addCell(int row, int col, Cell cell) {
        this.cells[col][row] = cell;
    }

    public int getHeroPosRow() {
        return heroPosRow;
    }

    public void setHeroPosRow(int heroPosRow) {
        this.heroPosRow = heroPosRow;
    }

    public int getHeroPosColumn() {
        return heroPosColumn;
    }

    public void setHeroPosColumn(int heroPosColumn) {
        this.heroPosColumn = heroPosColumn;
    }

    public CellHero getHeroCell() {
        return (CellHero) this.cells[this.heroPosRow][this.heroPosColumn];
    }

}
