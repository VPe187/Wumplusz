package wumpus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import wumpus.game.GameState;
import wumpus.model.Cell;
import wumpus.model.HeroSight;
import wumpus.ui.MapwRenderer;
import wumpus.wmap.WMap;

public class CommandMapTest {
    private static final String USER_INPUT = "map";
    private CommandMap underTest;
    private GameState gameState;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(null, false);
        underTest = new CommandMap(gameState);
    }

    @Test
    public void testCanValidationShuldReturnTrueWhenInputIsMap() {
        // given
        // when
        boolean result = underTest.validateCommand(USER_INPUT);
        // then
        assertTrue(result);
    }
}