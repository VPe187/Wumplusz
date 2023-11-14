package wumpus;

/**
 * Cell class used to store one field in Wumpus world.
 */
public class Cell {

    protected String value;

    public Cell(String value) {
        this.value = value;
    }

    public void setCellValue(String value) {
        this.value = value;
    }

    public String getCellValue() {
        return this.value;
    }


}
