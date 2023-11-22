package wumpus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wumpus.exceptions.MapParsingException;
import wumpus.game.GameState;
import wumpus.model.CellElement;
import wumpus.wmap.WMapParser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandMoveTest {
    private final String INPUT_MOVE = "move";
    private final String INPUT_LEFT = "left";
    private final int COL = 1; private final int ROW = 3;
    private final int COL2 = 1; private final int ROW2 = 2;
    private GameState gameState;
    private CommandLeft commandLeft;
    private CommandMove underTest;
    private final List<String> rows = List.of(
            "6 B 5 E",
            "WWWWWW",
            "W___PW",
            "WUGP_W",
            "W____W",
            "W__P_W",
            "WWWWWW"
    );

    @BeforeEach
    public void setUp() throws MapParsingException {
        WMapParser wMapParser = new WMapParser(rows);
        gameState = new GameState(wMapParser.getMap(), null, false);
        commandLeft = new CommandLeft(gameState);
        underTest = new CommandMove(gameState);
    }

    @Test
    public void testCanValidationShuldReturnTrueWhenInputIsMove() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT_MOVE);
        // then
        assertTrue(result);
    }

    @Test
    public void testMoveToNextFieldNorth() {
        // given
        commandLeft.process(INPUT_LEFT);
        // when
        underTest.process(INPUT_MOVE);
        // then
        assertEquals(CellElement.HERO, gameState.getCurrentMap().getCells()[COL][ROW].getValue());
        assertEquals(CellElement.EMPTY, gameState.getCurrentMap().getCells()[COL][ROW+1].getValue());
    }

    @Test
    public void testMoreMoveToNextFieldNorth() {
        // given
        // when
        underTest.process(INPUT_MOVE);
        // then
        assertEquals(CellElement.WUMPUS, gameState.getCurrentMap().getCells()[COL2][ROW2].getValue());
        assertEquals(CellElement.EMPTY, gameState.getCurrentMap().getCells()[COL2][ROW2+1].getValue());
    }
}