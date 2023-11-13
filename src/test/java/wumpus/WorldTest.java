package wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WorldTest {
    private World underTest;

    @BeforeEach
    void setup() {
    }

    @Test
    public void testNewWorldSizeWithCorrectSize() {
        // given
        underTest = new World(14);
        // when
        int result = underTest.getWorldSize();
        // then
        Assertions.assertEquals(result, 14);
    }

    @Test
    public void testNewWorldSizeWithBiggerThanLimit() {
        // given
        underTest = new World(32);
        // when
        int result = underTest.getWorldSize();
        // then
        Assertions.assertEquals(result, 20);
    }

    @Test
    public void testNewWorldSizeWithSmallerThanLimit() {
        // given
        underTest = new World(2);
        // when
        int result = underTest.getWorldSize();
        // then
        Assertions.assertEquals(result, 6);
    }

    @Test
    public void testNewWorldSizeWithNegativeNumber() {
        // given
        underTest = new World(-2);
        // when
        int result = underTest.getWorldSize();
        // then
        Assertions.assertEquals(result, 6);
    }

    @Test
    public void testWumpusCountOneBelowLimit() {
        // given
        underTest = new World(7);
        // when
        int result = underTest.getWumpusCount();
        // then
        Assertions.assertEquals(result, 1);
    }

    @Test
    public void testWumpusCountOneEqualLimit() {
        // given
        underTest = new World(8);
        // when
        int result = underTest.getWumpusCount();
        // then
        Assertions.assertEquals(result, 1);
    }

    @Test
    public void testWumpusCountTwoBetweenLimits() {
        // given
        underTest = new World(12);
        // when
        int result = underTest.getWumpusCount();
        // then
        Assertions.assertEquals(result, 2);
    }

    @Test
    public void testWumpusCountTwoEqualLowerLimits() {
        // given
        underTest = new World(9);
        // when
        int result = underTest.getWumpusCount();
        // then
        Assertions.assertEquals(result, 2);
    }

    @Test
    public void testWumpusCountTwoEqualHigherLimits() {
        // given
        underTest = new World(14);
        // when
        int result = underTest.getWumpusCount();
        // then
        Assertions.assertEquals(result, 2);
    }

    @Test
    public void testWumpusCountThreeOverLastLimit() {
        // given
        underTest = new World(15);
        // when
        int result = underTest.getWumpusCount();
        // then
        Assertions.assertEquals(result, 3);
    }

    @Test
    public void testWumpusCountThreeOverWorldLimit() {
        // given
        underTest = new World(22);
        // when
        int result = underTest.getWumpusCount();
        // then
        Assertions.assertEquals(result, 3);
    }

    @Test
    public void testIntegerFromLetterA() {
        // given
        underTest = new World(8);
        // when
        int result = World.integerFromLetter('A');
        // then
        Assertions.assertEquals(result, 1);
    }

    @Test
    public void testIntegerFromLetterB() {
        // given
        underTest = new World(8);
        // when
        int result = World.integerFromLetter('B');
        // then
        Assertions.assertEquals(result, 2);
    }

    @Test
    public void testIntegerFromLetterT() {
        // given
        underTest = new World(12);
        // when
        int result = World.integerFromLetter('T');
        // then
        Assertions.assertEquals(result, 20);
    }

}