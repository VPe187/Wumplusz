package wumpus.map;

import java.util.List;

import wumpus.exceptions.WorldReadingException;

/**
 * Interface used to read map data from source file.
 */
public interface MapReader {

    List<String> readMap() throws WorldReadingException;

}

