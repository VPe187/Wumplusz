package wumpus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wumpus.game.GameState;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link CommandQuit}
 */
class CommandQuitTest {
    private static final String INPUT_QUIT = "quit";
    private static final String INPUT_OTHER = "help";
    private GameState gameState;
    private CommandQuit underTest;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(null, false);
        underTest = new CommandQuit(gameState);
    }

    @Test
    public void testIsCommandIsQuitCommand() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT_QUIT);
        // then
        assertTrue(result);
    }

    @Test
    public void testIsCommandIsNotQuitCommand() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT_OTHER);
        // then
        assertFalse(result);
    }

    @Test
    public void testGameStoppedWhenInputIsQuit() {
        // given
        // when
        underTest.process(INPUT_QUIT);
        // then
        assertFalse(gameState.isRunning());
    }

    @Test
    public void testGameRunningWhenInputIsNotQuit() {
        // given
        gameState = new GameState(null, false);
        // when
        underTest.process(INPUT_OTHER);
        // then
        assertTrue(gameState.isRunning());
    }

}