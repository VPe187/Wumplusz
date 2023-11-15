package wumpus.exceptions;

/**
 * If map reading generate error, throws this exception.
 */
public class WorldReadingException extends Exception {

    public WorldReadingException(String message) {
        super(message);
    }

}
