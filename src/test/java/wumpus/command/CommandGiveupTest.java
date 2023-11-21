package wumpus.command;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import wumpus.exceptions.MapParsingException;
import wumpus.game.GameState;
import wumpus.wmap.WMap;
import wumpus.wmap.WMapParser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class CommandGiveupTest {
    private final String INPUT_GIVEUP = "giveup";
    private GameState gameState;
    private CommandGiveup underTest;
    private final List<String> rows = List.of(
            "6 B 5 E",
            "WWWWWW",
            "W___PW",
            "WUGP_W",
            "W____W",
            "W__P_W",
            "WWWWWW"
    );;

    @Test
    public void testIsCommandIsGiveupCommand() {
        // given
        gameState = new GameState(null, null, false);
        underTest = new CommandGiveup(gameState);
        // when
        boolean result = underTest.validateCommand(INPUT_GIVEUP);
        // then
        assertTrue(result);
    }

    @Test
    public void testGameStepIsZeroWhenGiveUp() throws MapParsingException {
        // given
        WMapParser wMapParser = new WMapParser(rows);
        WMap map = wMapParser.getMap();
        gameState = new GameState(map, null, false);
        gameState.setSteps(10);
        underTest = new CommandGiveup(gameState);
        // when
        underTest.process(INPUT_GIVEUP);
        // then
        assertEquals(gameState.getSteps(), 0);
    }
}