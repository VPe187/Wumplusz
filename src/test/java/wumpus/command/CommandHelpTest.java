package wumpus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link CommandHelp}
 */
public class CommandHelpTest {
    private static final String USER_INPUT_LOWER = "help";
    private static final String USER_INPUT_MIXED = "hElP";
    private static final String USER_INPUT_UPPER = "HELP";
    private static final String HELP_MESSAGE = "Available commands: " +
            "map, left, right, move, giveup, shot, edit, load, exit, quit";
    private CommandHelp underTest;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        underTest = new CommandHelp();
    }

    @Test
    public void testHelpMessageReturnLowerCaseInput() {
        // given
        // when
        underTest.process(USER_INPUT_LOWER);
        // then
        assertEquals(HELP_MESSAGE, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testHelpMessageReturnMixedCaseInput() {
        // given
        // when
        underTest.process(USER_INPUT_MIXED);
        // then
        assertEquals(HELP_MESSAGE, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testHelpMessageReturnUpperCaseInput() {
        // given
        // when
        underTest.process(USER_INPUT_UPPER);
        // then
        assertEquals(HELP_MESSAGE, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testReturnTrueAlways() {
        // given
        // when
        boolean result = underTest.validateCommand(USER_INPUT_LOWER);
        // then
        assertTrue(result);
    }
}