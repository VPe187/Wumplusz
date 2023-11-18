package wumpus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wumpus.game.GameState;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link CommandExit}
 */
public class CommandExitTest {
    private static final String INPUT_EXIT = "exit";
    private static final String INPUT_OTHER = "map";
    private GameState gameState;
    private CommandExit underTest;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(null, false);
        underTest = new CommandExit(gameState);
    }

    @Test
    public void testIsCommandIsExitCommand() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT_EXIT);
        // then
        assertTrue(result);
    }

    @Test
    public void testIsCommandIsNotExitCommand() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT_OTHER);
        // then
        assertFalse(result);
    }

    @Test
    public void testGameStoppedWhenInputIsExit() {
        // given
        // when
        underTest.process(INPUT_EXIT);
        // then
        assertFalse(gameState.isRunning());
    }

    @Test
    public void testGameRunningWhenInputIsNotExit() {
        // given
        gameState = new GameState(null, false);
        // when
        underTest.process(INPUT_OTHER);
        // then
        assertTrue(gameState.isRunning());
    }

}