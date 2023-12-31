package wumpus.command;

import wumpus.game.GameState;

/**
 * The quit command is used to exit the game (alias for the exit command).
 */
public class CommandQuit implements Command {
    private static final String COMMAND_QUIT = "quit";
    private final GameState gameState;

    public CommandQuit(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND_QUIT.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        this.gameState.setStopped(true);
    }
}
