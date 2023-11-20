package wumpus.wmap;

import java.util.List;
import java.util.regex.Pattern;

import wumpus.exceptions.MapParsingException;
import wumpus.model.Cell;
import wumpus.model.HeroSight;

/**
 * Map parser.
 */
public class WMapParser {

    private static final String VALID_SIZE_REGEX = "[0-9]+";
    private static final String VALID_HERO_COL_REGEX = "[A-Z]";
    private static final String VALID_HERO_ROW_REGEX = "[0-9]";
    private static final String VALID_HERO_SIGHT_REGEX = "[NSEW]";
    private static final String VALID_ROW_LETTERS = "[WUGP_]+";
    private static List<String> rows;
    private static int size;
    private static int heroCol;
    private static int heroRow;
    private static HeroSight heroSight;

    public WMapParser(List<String> rows) {
        WMapParser.rows = rows;
    }

    private void parseHeaderRow(String[] headerRow) throws MapParsingException {
        if (headerRow.length != 4) {
            throw new MapParsingException("Header contains invalid number of characters!");
        }
        if (!Pattern.matches(VALID_SIZE_REGEX, headerRow[0])) {
            throw new MapParsingException("Header size value contains invalid character!");
        } else {
            WMapParser.size = Integer.parseInt(headerRow[0]);
        }
        if (!Pattern.matches(VALID_HERO_COL_REGEX, headerRow[1])) {
            throw new MapParsingException("Header hero column value contains invalid character!");
        } else {
            WMapParser.heroCol = WMapTools.integerFromLetter(headerRow[1]);
        }
        if (!Pattern.matches(VALID_HERO_ROW_REGEX, headerRow[2])) {
            throw new MapParsingException("Header hero column value contains invalid character!");
        } else {
            WMapParser.heroRow = Integer.parseInt(headerRow[2]);
        }
        if (!Pattern.matches(VALID_HERO_SIGHT_REGEX, headerRow[3])) {
            throw new MapParsingException("Header hero sight value contains invalid character!");
        } else {
            switch (headerRow[3]) {
                case "N":
                    WMapParser.heroSight = HeroSight.NORTH;
                    break;
                case "S":
                    WMapParser.heroSight = HeroSight.SOUTH;
                    break;
                case "E":
                    WMapParser.heroSight = HeroSight.EAST;
                    break;
                case "W":
                    WMapParser.heroSight = HeroSight.WEST;
                    break;
                default:
                    break;
            }
        }

    }

    private Cell[][] parseRows(List<String> rows) throws MapParsingException {
        Cell[][] cells = new Cell[WMapParser.size][WMapParser.size];
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
        cells[heroCol - 1][heroRow - 1] = new Cell(heroCol, heroRow, "H");
        return cells;
    }

    /**
     * Give back map.
     */
    public WMap getMap() throws MapParsingException {
        parseHeaderRow(rows.get(0).split(" "));
        Cell[][] cells = parseRows(rows);
        return new WMap(size, cells, HeroSight.NORTH);
    }

}
