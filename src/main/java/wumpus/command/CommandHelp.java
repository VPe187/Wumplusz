package wumpus.command;

/**
 * The help command allows the user to request a short description of the existing commands.
 */
public class CommandHelp implements Command {
    private static final String COMMAND_HELP = "help";
    private static final String HELP_MESSAGE = "Available commands: " +
            "map, left, right, move, giveup, shoot, edit, load, exit, quit";

    @Override
    public boolean validateCommand(String input) {
        return COMMAND_HELP.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        System.out.println(HELP_MESSAGE);
    }
}
