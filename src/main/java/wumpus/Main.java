package wumpus;

import wumpus.exceptions.MapParsingException;
import wumpus.exceptions.WorldReadingException;

/**
 * Launcher class used to launch application.
 */
public class Main {

    /**
     * Entry point.
     */
    public static void main(String[] args) throws WorldReadingException, MapParsingException {
        GameController gameController = new GameController();
        gameController.start();
    }

}
