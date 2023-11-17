package wumpus.command;

/**
 * Default command for commands that cannot be used.
 */
public class CommandUnknown implements Command {
    private static final String COMMAND_UNKNOWN_MESSAGE = "Unknown command.";

    @Override
    public boolean validateCommand(String input) {
        return true;
    }

    @Override
    public void process(String input) {
        System.out.println(COMMAND_UNKNOWN_MESSAGE);
    }
}
