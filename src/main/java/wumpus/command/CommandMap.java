package wumpus.command;

import wumpus.game.GameState;
import wumpus.ui.MapwRenderer;

/**
 * Displays a map of the current state of the game on the screen.
 */
public class CommandMap implements Command {
    private static final String COMMAND_PLAY = "map";
    private final GameState gameState;

    public CommandMap(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND_PLAY.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        MapwRenderer.render(this.gameState.getCurrentMap());
    }
}
