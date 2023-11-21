package wumpus.ui;

/**
 * Print messages and its parameters to console.
 */
public class MessagePrinter {
    public static void printMessage(String format, Object... parameters) {
        System.out.printf(format + "%n", parameters);
    }
}