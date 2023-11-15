package wumpus;

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
        try {
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return input;
    }
}
