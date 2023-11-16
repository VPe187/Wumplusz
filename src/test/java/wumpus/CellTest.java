package wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wumpus.model.Cell;

class CellTest {
    Cell underTest = new Cell(1, 1, "_");

    @BeforeEach
    void setup() {
    }

    @Test
    public void testCellValueSetter () {
        // given
        underTest.setCellValue("B");
        // when
        String result = underTest.getCellValue();
        // then
        Assertions.assertEquals(result, "B");
    }
}