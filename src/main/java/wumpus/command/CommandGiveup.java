package wumpus.command;

import wumpus.game.GameState;
import wumpus.ui.MessagePrinter;

/**
 * Giveup command, reset the map to inital state.
 */
public class CommandGiveup implements Command {
    private static final String COMMAND_GIVEUP = "giveup";
    private final GameState gameState;

    public CommandGiveup(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND_GIVEUP.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        gameState.setCurrentMap(gameState.getStartMap());
        MessagePrinter.printMessage("The map has been restored to its initial state.");
    }
}
