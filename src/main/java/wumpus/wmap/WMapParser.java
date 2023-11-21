package wumpus.wmap;

import java.util.List;
import java.util.regex.Pattern;

import wumpus.exceptions.MapParsingException;
import wumpus.model.Cell;
import wumpus.model.CellElement;
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

    private static void parseHeaderRow(String[] headerRow) throws MapParsingException {
        if (headerRow.length != 4) {
            throw new MapParsingException("Header contains invalid number of characters!");
        }
        if (!Pattern.matches(VALID_SIZE_REGEX, headerRow[0])) {
            throw new MapParsingException("Header size value contains invalid character!");
        } else {
            size = Integer.parseInt(headerRow[0]);
        }
        if (!Pattern.matches(VALID_HERO_COL_REGEX, headerRow[1])) {
            throw new MapParsingException("Header hero column value contains invalid character!");
        } else {
            heroCol = WMapTools.integerFromLetter(headerRow[1]);
        }
        if (!Pattern.matches(VALID_HERO_ROW_REGEX, headerRow[2])) {
            throw new MapParsingException("Header hero column value contains invalid character!");
        } else {
            heroRow = Integer.parseInt(headerRow[2]);
        }
        if (!Pattern.matches(VALID_HERO_SIGHT_REGEX, headerRow[3])) {
            throw new MapParsingException("Header hero sight value contains invalid character!");
        } else {
            heroSight = HeroSight.getByValue(headerRow[3]);
        }

    }

    private static Cell[][] parseRows(List<String> rows) throws MapParsingException {
        Cell[][] cells = new Cell[WMapParser.size][WMapParser.size];
        int i = 0;
        for (String row : rows.subList(1, rows.size())) {
            if (!Pattern.matches(VALID_ROW_LETTERS, row)) {
                throw new MapParsingException("Row contains invalid character!");
            } else {
                for (int j = 0; j < row.length(); j++) {
                    //cells[j][i] = new Cell(j, i, CellElement.valueOf(row.split("")[j]));
                    cells[j][i] = new Cell(j, i, CellElement.getByValue(row.split("")[j]));
                }
            }
            i++;
        }
        cells[heroCol - 1][heroRow - 1] = new Cell(heroCol - 1, heroRow - 1, CellElement.HERO);
        return cells;
    }

    /**
     * Give back game wmap.
     */
    public static WMap getMap() throws MapParsingException {
        parseHeaderRow(rows.get(0).split(" "));
        Cell[][] cells = parseRows(rows);
        return WMap.builder().withSize(size).withCells(cells).withHeroSight(heroSight)
                .withStartCol(heroCol - 1).withStartRow(heroRow - 1).build();
    }

}
