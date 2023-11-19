package wumpus.wmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import wumpus.exceptions.MapReadingException;

/**
 * Buffered Map reader.
 */
public class BufferedWMapReader implements WMapReader {
    private final BufferedReader reader;

    public BufferedWMapReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public List<String> readMap() throws MapReadingException {
        String row;
        List<String> rows = new ArrayList<>();
        try {
            while ((row = reader.readLine()) != null) {
                rows.add(row);
            }
        } catch (IOException e) {
            throw new MapReadingException("Failed to read map.");
        }
        return rows;
    }
}
