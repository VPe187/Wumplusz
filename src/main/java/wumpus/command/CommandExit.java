package wumpus.command;

import wumpus.game.GameState;

/**
 * The exit command is used to exit the game (alias for the quit command).
 */
public class CommandExit implements Command {
    private static final String COMMAND_EXIT = "exit";
    private final GameState gameState;

    public CommandExit(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND_EXIT.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        this.gameState.setStopped(true);
    }
}
