package wumpus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import wumpus.exceptions.MapParsingException;
import wumpus.game.GameState;
import wumpus.wmap.WMapParser;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CommandMapTest {
    private static final String USER_INPUT = "map";
    private CommandMap underTest;
    private GameState gameState;
    private final List<String> rows = List.of(
            "6 B 5 E",
            "WWWWWW",
            "W___PW",
            "WUGP_W",
            "W____W",
            "W__P_W",
            "WWWWWW"
    );
    private final String RESULT_MAP =
            """
                    ┌───┬───┬───┬───┬───┬───┬───┐
                    │   │ A │ B │ C │ D │ E │ F │
                    ├───┼───┼───┼───┼───┼───┼───┤
                    │ 1 │ W │ W │ W │ W │ W │ W │
                    ├───┼───┼───┼───┼───┼───┼───┤
                    │ 2 │ W │ _ │ _ │ _ │ P │ W │
                    ├───┼───┼───╆━━━╅───┼───┼───┤
                    │ 3 │ W │ U ┃ G ┃ P │ _ │ W │
                    ├───┼───┼───╄━━━╃───┼───┼───┤
                    │ 4 │ W │ _ │ _ │ _ │ _ │ W │
                    ├───┼───╆━━━╅───┼───┼───┼───┤
                    │ 5 │ W ┃ H ┃ _ │ P │ _ │ W │
                    ├───┼───╄━━━╃───┼───┼───┼───┤
                    │ 6 │ W │ W │ W │ W │ W │ W │
                    └───┴───┴───┴───┴───┴───┴───┘
                    Start:B5, Sight:EAST, Steps:0
                    Arrows:0, Wumpuses:0, Gold:0
                    Pits:0, Walls:0, Empty:0
                    """;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
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

    @Test
    public void testRenderMapCommandProcessMap() throws MapParsingException {
        // given
        WMapParser wMapParser = new WMapParser(rows);
        gameState = new GameState(wMapParser.getMap(), null, false);
        underTest = new CommandMap(gameState);
        // when
        underTest.process(USER_INPUT);
        // then
        assertEquals(RESULT_MAP.trim().replace("\r",""), outputStreamCaptor.toString().trim().replace("\r",""));
    }

}