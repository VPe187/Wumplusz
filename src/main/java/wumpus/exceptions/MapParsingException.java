package wumpus.exceptions;

/**
 * If map parsing generate error, throws this exception.
 */
public class MapParsingException extends Exception {

    public MapParsingException(String message) {
        super(message);
    }

}