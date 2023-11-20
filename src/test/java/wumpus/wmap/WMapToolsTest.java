package wumpus.wmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WMapToolsTest {
    public static final String LETTER_A = "A";
    public static final int INT_A = 1;
    public static final String LETTER_B = "B";
    public static final int INT_B = 2;
    public static final String LETTER_Z = "Z";
    public static final int INT_Z = 26;

    @Test
    public void testIntegerFromLetterGivMatchResultFirstLetter() {
        // given
        // when
        int $result = WMapTools.integerFromLetter(LETTER_A);
        // then
        assertEquals($result, INT_A);
    }

    @Test
    public void testIntegerFromLetterGivMatchResultSecondLetter() {
        // given
        // when
        int $result = WMapTools.integerFromLetter(LETTER_B);
        // then
        assertEquals($result, INT_B);
    }

    @Test
    public void testIntegerFromLetterGivMatchResultLastLetter() {
        // given
        // when
        int $result = WMapTools.integerFromLetter(LETTER_Z);
        // then
        assertEquals($result, INT_Z);
    }

}