package wumpus.command;

/**
 * Command interface.
 */
public interface Command {

    /**
     * Check the command exists in command's list.
     */
    boolean validateCommand(String input);

    /**
     * The given command processed.
     */
    void process(String input);

}