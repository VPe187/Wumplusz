package wumpus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import wumpus.command.Command;
import wumpus.command.CommandHelp;
import wumpus.command.CommandQuit;
import wumpus.command.CommandUnknown;
import wumpus.exceptions.MapParsingException;
import wumpus.exceptions.MapReadingException;
import wumpus.game.GameController;
import wumpus.game.GameState;
import wumpus.input.InputHandler;
import wumpus.input.InputReader;

/**
 * Launcher class used to launch application.
 */
public class Main {

    /**
     * Entry point.
     */
    public static void main(String[] args) throws MapReadingException, MapParsingException {
        GameState gameState = new GameState(null, false);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        InputReader inputReader = new InputReader(bufferedReader);
        List<Command> commands = List.of(
            new CommandQuit(gameState),
            new CommandHelp(),
            new CommandUnknown()
        );
        InputHandler inputHandler = new InputHandler(commands);
        GameController gameController = new GameController(gameState, inputReader, inputHandler);
        gameController.readWorldFromFile();
        gameController.start();
    }

}
