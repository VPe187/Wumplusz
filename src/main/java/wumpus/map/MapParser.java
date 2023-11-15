package wumpus.map;

import java.util.List;
import java.util.regex.Pattern;

import wumpus.Cell;
import wumpus.exceptions.MapParsingException;

/**
 * Map parser.
 */
public class MapParser {

    private static final String VALID_SIZE_REGEX = "[0-9]+";
    private static final String VALID_HERO_COL_REGEX = "[A-Z]";
    private static final String VALID_HERO_ROW_REGEX = "[0-9]";
    private static final String VALID_HERO_SIGHT_REGEX = "[NSEW]";
    private static final String VALID_ROW_LETTERS = "[WUGP_]+";
    private static List<String> rows;
    private static int size;
    private static String heroCol;
    private static int heroRow;
    private static String heroSight;

    public MapParser(List<String> rows) {
        MapParser.rows = rows;
    }

    private void parseHeaderRow(String[] headerRow) throws MapParsingException {
        if (headerRow.length != 4) {
            throw new MapParsingException("Header contains invalid number of characters!");
        }

        if (!Pattern.matches(VALID_SIZE_REGEX, headerRow[0])) {
            throw new MapParsingException("Header size value contains invalid character!");
        } else {
            MapParser.size = Integer.parseInt(headerRow[0]);
        }

        if (!Pattern.matches(VALID_HERO_COL_REGEX, headerRow[1])) {
            throw new MapParsingException("Header hero column value contains invalid character!");
        } else {
            MapParser.heroCol = headerRow[1];
        }

        if (!Pattern.matches(VALID_HERO_ROW_REGEX, headerRow[2])) {
            throw new MapParsingException("Header hero column value contains invalid character!");
        } else {
            MapParser.heroRow = Integer.parseInt(headerRow[2]);
        }

        if (!Pattern.matches(VALID_HERO_SIGHT_REGEX, headerRow[3])) {
            throw new MapParsingException("Header hero sight value contains invalid character!");
        } else {
            MapParser.heroSight = headerRow[3];
        }

    }

    private Cell[][] parseRows(List<String> rows) throws MapParsingException {
        Cell[][] cells = new Cell[MapParser.size][MapParser.size];
        //for (int i = 1; i < rows.size(); i++) {
        int i = 0;
        for (String row : rows.subList(1, rows.size())) {
            if (!Pattern.matches(VALID_ROW_LETTERS, row)) {
                throw new MapParsingException("Row contains invalid character!");
            } else {
                for (int j = 0; j < row.length(); j++) {
                    cells[j][i] = new Cell(j, i, row.split("")[j]);
                }
            }
            i++;
        }
        return cells;
    }

    /**
     * Give back map.
     */
    public Map getMap() throws MapParsingException {
        parseHeaderRow(rows.get(0).split(" "));
        Cell[][] cells = parseRows(rows);
        return new Map(size, cells);
    }

}