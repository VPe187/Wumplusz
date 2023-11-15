package wumpus;

import wumpus.exceptions.MapParsingException;
import wumpus.exceptions.MapReadingException;

/**
 * Launcher class used to launch application.
 */
public class Main {

    /**
     * Entry point.
     */
    public static void main(String[] args) throws MapReadingException, MapParsingException {
        GameController gameController = new GameController();
        gameController.start();
    }

}
