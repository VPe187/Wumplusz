package wumpus.command;

import wumpus.game.GameState;
import wumpus.wmap.WMapTools;

/**
 * Right command, rotate hero sight to right.
 */
public class CommandMove implements Command {
    private static final String COMMAND_QUIT = "move";
    private final GameState gameState;

    public CommandMove(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND_QUIT.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        System.out.println("The hero has just moved to " +
                WMapTools.letterFromInteger(this.gameState.getCurrentMap().getStartCol()) +
                this.gameState.getCurrentMap().getStartRow() + " field.");
    }
}
