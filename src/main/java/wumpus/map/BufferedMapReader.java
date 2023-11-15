package wumpus.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import wumpus.exceptions.WorldReadingException;

/**
 * Buffered Map reader.
 */
public class BufferedMapReader implements MapReader {
    private final BufferedReader reader;

    public BufferedMapReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public List<String> readMap() throws WorldReadingException {
        String row;
        List<String> rows = new ArrayList<>();
        try {
            while ((row = reader.readLine()) != null) {
                rows.add(row);
            }
        } catch (IOException e) {
            throw new WorldReadingException("Failed to read world.");
        }
        return rows;
    }
}
