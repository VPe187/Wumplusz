package wumpus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wumpus.exceptions.MapParsingException;
import wumpus.game.GameState;
import wumpus.model.HeroSight;
import wumpus.wmap.WMapParser;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CommandRightTest {
    private static final String USER_INPUT = "right";
    private static final HeroSight afterRightDirection = HeroSight.SOUTH;
    private CommandRight underTest;
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

    @BeforeEach
    public void setUp() throws MapParsingException {
        WMapParser wMapParser = new WMapParser(rows);
        gameState = new GameState(wMapParser.getMap(), null, false);
        underTest = new CommandRight(gameState);
    }

    @Test
    public void testCanValidationShuldReturnTrueWhenInputIsRight() {
        // given
        // when
        boolean result = underTest.validateCommand(USER_INPUT);
        // then
        assertTrue(result);
    }

    @Test
    public void testCanHeroTurnRight() {
        // given
        // when
        underTest.process(USER_INPUT);
        // then
        assertEquals(afterRightDirection, gameState.getCurrentMap().getHeroSight());

    }



}