package wumpus.command;

/**
 * Help Command.
 */
public class CommandHelp implements Command {

    private static final String COMMAND_HELP = "help";
    private static final String HELP_MESSAGE = "Available commands:\n" +
            "'HERO LEFT', 'HERO RIGT', 'MOVE', 'GIVE UP', 'SHOT', 'PLAY', 'EDIT', 'LOAD', 'EXIT', 'QUIT'";

    @Override
    public boolean validateCommand(String input) {
        return COMMAND_HELP.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        System.out.println(HELP_MESSAGE);
    }
}
