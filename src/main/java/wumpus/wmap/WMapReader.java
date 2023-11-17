package wumpus.wmap;

import java.util.List;

import wumpus.exceptions.MapReadingException;

/**
 * Interface used to read map data from source file.
 */
public interface WMapReader {

    List<String> readMap() throws MapReadingException;

}

