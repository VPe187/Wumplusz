package wumpus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import wumpus.command.Command;
import wumpus.command.CommandExit;
import wumpus.command.CommandHelp;
import wumpus.command.CommandLeft;
import wumpus.command.CommandMap;
import wumpus.command.CommandMove;
import wumpus.command.CommandQuit;
import wumpus.command.CommandRight;
import wumpus.command.CommandShoot;
import wumpus.command.CommandUnknown;
import wumpus.exceptions.MapParsingException;
import wumpus.exceptions.MapReadingException;
import wumpus.game.GameController;
import wumpus.game.GameState;
import wumpus.input.InputHandler;
import wumpus.input.InputReader;

/**
 * Wumplusz application. NYE 2023/24/1 assigment.
 * <a href="https://github.com/epam-nye-cooperation/epam-nye-progtech/blob/feature/2023_24/assignment/wumpus.md">Specification</a>
 * <a href="https://github.com/epam-nye-cooperation/epam-nye-progtech/tree/feature/2023_24/sudoku/complete/sudoku">Learning materials used</a>
 */
public class Main {
    /**
     * Application entry point.
     */
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            game(bufferedReader);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void game(BufferedReader bufferedReader) throws MapParsingException, MapReadingException, IOException {
        GameState gameState = GameState.builder().withCurrentMap(null).withStopped(false).build();
        List<Command> commands = List.of(
                new CommandQuit(gameState),
                new CommandExit(gameState),
                new CommandMap(gameState),
                new CommandLeft(gameState),
                new CommandRight(gameState),
                new CommandMove(gameState),
                new CommandShoot(gameState),
                new CommandHelp(),
                new CommandUnknown()
        );
        InputReader inputReader = new InputReader(bufferedReader);
        InputHandler inputHandler = new InputHandler(commands);
        GameController gameController = new GameController(gameState, inputReader, inputHandler);
        gameController.readWorldFromFile();
        gameController.start();
    }
}
