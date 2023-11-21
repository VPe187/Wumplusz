package wumpus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wumpus.exceptions.MapParsingException;
import wumpus.game.GameState;
import wumpus.model.CellElement;
import wumpus.wmap.WMapParser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandShootTest {
    private final String INPUT_SHOOT = "shoot";
    private final String INPUT_LEFT = "left";
    private final int COL = 1;
    private final int ROW = 2;
    private GameState gameState;
    private CommandShoot underTest;
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
    public void setUp() {
    }

    @Test
    public void testIsCommandIsShootCommand() {
        // given
        underTest = new CommandShoot(gameState);
        // when
        boolean result = underTest.validateCommand(INPUT_SHOOT);
        // then
        assertTrue(result);
    }

    @Test
    public void testShootResult() throws MapParsingException {
        // given
        WMapParser wMapParser = new WMapParser(rows);
        gameState = new GameState(wMapParser.getMap(), null, false);
        gameState.countElements();
        CommandLeft commandLeft = new CommandLeft(gameState);
        commandLeft.process(INPUT_LEFT);
        underTest = new CommandShoot(gameState);
        // when
        underTest.process(INPUT_SHOOT);
        // then
        assertEquals(CellElement.EMPTY, gameState.getCurrentMap().getCells()[COL][ROW].getValue());
    }

}