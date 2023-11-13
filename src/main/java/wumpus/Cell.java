package wumpus;

/**
 * Cell class used to store one field in Wumpus world.
 */
public class Cell {

    private char value;

    public Cell() {
        this.value = '_';
    }

    public void setCellValue(char value) {
        this.value = value;
    }

    public char getCellValue() {
        return this.value;
    }


}
