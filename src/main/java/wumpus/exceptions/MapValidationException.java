package wumpus.exceptions;

/**
 * If map content invalid, throws this exception.
 */
public class MapValidationException extends Exception {

    public MapValidationException(String message) {
        super(message);
    }

}
