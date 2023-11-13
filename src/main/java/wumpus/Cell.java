package wumpus;

/**
 * Cell class used to store one field in Wumpus world.
 */
public class Cell {

    private String value;

    public Cell() {
        this.value = "_";
    }

    public void setCellValue(String value) {
        this.value = value;
    }

    public String getCellValue() {
        return this.value;
    }


}
