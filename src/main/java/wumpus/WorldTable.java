package wumpus;

/**
 * World class used to store Wumpus world cells.
 */
public class WorldTable {
    private final int rows;
    private final int columns;
    private final Cell[][] cells;

    public WorldTable(int worldSize) {
        this.rows = worldSize;
        this.columns = worldSize;
        this.cells = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.cells[j][i] = new Cell();
            }
        }
    }

    public void setCellValue(int row, int col, char value) {
        this.cells[row][col].setCellValue(value);
    }

    public char getCellValue(int row, int col) {
        return this.cells[row][col].getCellValue();
    }
}
