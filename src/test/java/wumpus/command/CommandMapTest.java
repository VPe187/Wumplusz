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

    @BeforeEach
    public void setUp() {
        underTest = new CommandMap(new GameState(null, null, false));
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