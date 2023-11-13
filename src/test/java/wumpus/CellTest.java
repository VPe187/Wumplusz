package wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellTest {
    Cell underTest = new Cell();

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