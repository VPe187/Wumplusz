package wumpus.game;

import wumpus.input.InputHandler;
import wumpus.input.InputReader;

/**
 * This class make one event in game.
 */
public class GameTicker {

    private final InputReader inputReader;
    private final InputHandler inputHandler;

    public GameTicker(InputReader inputReader, InputHandler inputHandler) {
        this.inputReader = inputReader;
        this.inputHandler = inputHandler;
    }

    /**
     * Performs a game event.
     */
    public void performGameStep() {
        String input = inputReader.readInput();
        inputHandler.handleInput(input);
    }
}
