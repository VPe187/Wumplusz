package wumpus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CommandUnknownTest {
    private static final String USER_INPUT = "cnrej";
    private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command.";
    private CommandUnknown underTest;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        underTest = new CommandUnknown();
    }

    @Test
    public void testUnknownMessageReturn() {
        // given
        // when
        underTest.process(USER_INPUT);
        // then
        assertEquals(UNKNOWN_COMMAND_MESSAGE, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testReturnTrueAlways() {
        // given
        // when
        boolean result = underTest.validateCommand(USER_INPUT);
        // then
        assertTrue(result);
    }

}