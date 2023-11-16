package wumpus.command;

import wumpus.game.GameState;

/**
 * Quit or exit Command.
 */
public class CommandQuit implements Command {

    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_QUIT = "quit";
    private final GameState gameState;

    public CommandQuit(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return (COMMAND_EXIT.equalsIgnoreCase(input) || COMMAND_QUIT.equalsIgnoreCase(input));
    }

    @Override
    public void process(String input) {
        this.gameState.setStopped(true);
    }
}
