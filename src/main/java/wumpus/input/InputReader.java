package wumpus.input;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class reads input from console.
 */
public class InputReader {
    private final BufferedReader reader;

    public InputReader(BufferedReader reader) {
        this.reader = reader;
    }

    /**
     * Reads inputs from console.
     */
    public String readInput() {
        String input = null;
        System.out.print("#:");
        try {
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return input;
    }
}
