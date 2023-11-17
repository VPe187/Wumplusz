package wumpus.exceptions;

/**
 * If map reading generate error, throws this exception.
 */
public class MapReadingException extends Exception {
    public MapReadingException(String message) {
        super(message);
    }
}
