package wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WorldTest {
    @BeforeEach
    void setup() {
    }

    @Test
    public void testNewWorldSizeWithCorrectSize() {
        // given
        World underTest = new World(14);
        // when
        int result = underTest.getWorldSize();
        // then
        Assertions.assertEquals(result, 14);
    }

    @Test
    public void testNewWorldSizeWithBiggerThanLimit() {
        // given
        World underTest = new World(32);
        // when
        int result = underTest.getWorldSize();
        // then
        Assertions.assertEquals(result, 20);
    }

    @Test
    public void testNewWorldSizeWithSmallerThanLimit() {
        // given
        World underTest = new World(2);
        // when
        int result = underTest.getWorldSize();
        // then
        Assertions.assertEquals(result, 6);
    }

    @Test
    public void testNewWorldSizeWithNegativeNumber() {
        // given
        World underTest = new World(-2);
        // when
        int result = underTest.getWorldSize();
        // then
        Assertions.assertEquals(result, 6);
    }

    @Test
    public void testWumpusCountOneBelowLimit() {
        // given
        World underTest = new World(7);
        // when
        int result = underTest.getWumpusCount();
        // then
        Assertions.assertEquals(result, 1);
    }

    public void testWumpusCountOneEqualLimit() {
        // given
        World underTest = new World(8);
        // when
        int result = underTest.getWumpusCount();
        // then
        Assertions.assertEquals(result, 1);
    }

    @Test
    public void testWumpusCountTwoBetweenLimits() {
        // given
        World underTest = new World(12);
        // when
        int result = underTest.getWumpusCount();
        // then
        Assertions.assertEquals(result, 2);
    }

    @Test
    public void testWumpusCountTwoEqualLowerLimits() {
        // given
        World underTest = new World(9);
        // when
        int result = underTest.getWumpusCount();
        // then
        Assertions.assertEquals(result, 2);
    }

    @Test
    public void testWumpusCountTwoEqualHigherLimits() {
        // given
        World underTest = new World(14);
        // when
        int result = underTest.getWumpusCount();
        // then
        Assertions.assertEquals(result, 2);
    }

    @Test
    public void testWumpusCountThreeOverLastLimit() {
        // given
        World underTest = new World(15);
        // when
        int result = underTest.getWumpusCount();
        // then
        Assertions.assertEquals(result, 3);
    }

    @Test
    public void testWumpusCountThreeOverWorldLimit() {
        // given
        World underTest = new World(22);
        // when
        int result = underTest.getWumpusCount();
        // then
        Assertions.assertEquals(result, 3);
    }


}