package wumpus.wmap;

import java.util.Objects;

import wumpus.model.Cell;
import wumpus.model.CellElement;
import wumpus.model.HeroSight;

/**
 * Tools for WMap.
 */
public class WMapTools {

    static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Convert letter to Integer.
     */
    public static int integerFromLetter(String letter) {
        for (int i = 0; i <= alphabet.length(); i++) {
            if (Objects.equals(alphabet.split("")[i], letter)) {
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * Convert Integer to letter.
     */
    public static String letterFromInteger(int number) {
        return alphabet.substring(number, number + 1);
    }

    /**
     * Return {@code true} / {@code false} depends on target cell values.
     */
    public static boolean canMoveDirection(WMap wmap, HeroSight direction) {
        if (direction.equals(HeroSight.NORTH) && (wmap.getHeroCell().getRow() - 1) < 0) {
            return false;
        }
        if (direction.equals(HeroSight.SOUTH) && (wmap.getHeroCell().getRow() + 1) > wmap.getSize()) {
            return false;
        }
        if (direction.equals(HeroSight.WEST) && (wmap.getHeroCell().getCol() - 1) < 0) {
            return false;
        }
        if (direction.equals(HeroSight.EAST) && (wmap.getHeroCell().getCol() + 1) > wmap.getSize()) {
            return false;
        }
        Cell targetCell = getTargetCell(wmap, direction);
        return !targetCell.getCellValue().equals(CellElement.WALL);
    }

    /**
     * Return target cell when Hero wants move to his sight line.
     *
     * @param wmap the map ({@link WMap})
     * @param direction the direction of move ({@link HeroSight})
     */
    public static Cell getTargetCell(WMap wmap, HeroSight direction) {
        Cell heroCell = wmap.getHeroCell();
        Cell targetCell = null;
        if (direction.equals(HeroSight.NORTH)) {
            targetCell = wmap.getCells()[heroCell.getCol()][heroCell.getRow() - 1];
        } else if (direction.equals(HeroSight.SOUTH)) {
            targetCell = wmap.getCells()[heroCell.getCol()][heroCell.getRow() + 1];
        } else if (direction.equals(HeroSight.WEST)) {
            targetCell = wmap.getCells()[heroCell.getCol() - 1][heroCell.getRow()];
        } else if (direction.equals(HeroSight.EAST)) {
            targetCell = wmap.getCells()[heroCell.getCol() + 1][heroCell.getRow()];
        }
        return targetCell;
    }

    /**
     * Move Hero to validated target cell.
     *
     * @param wmap the map ({@link WMap})
     * @param targetCell the target cell ({@link Cell})
     */
    public static void moveHeroToCell(WMap wmap, Cell targetCell) {
        int targetCol = targetCell.getCol();
        int targetRow = targetCell.getRow();
        wmap.getHeroCell().setCellValue(CellElement.EMPTY);
        wmap.getCell(targetCol, targetRow).setCellValue(CellElement.HERO);
    }

    public static Cell shootEndCell(WMap wmap, HeroSight direction) {
        Cell targetCell = wmap.getHeroCell();
        boolean hit = false;
        int col = wmap.getHeroCell().getCol();
        int row = wmap.getHeroCell().getRow();
        int arrowStep = 1;
        if (direction.equals(HeroSight.NORTH)) {
            while ((row - arrowStep) >= 0 && !hit) {
                targetCell = wmap.getCell(col, row - arrowStep);
                if (!targetCell.getValue().equals(CellElement.WALL) && !targetCell.getValue().equals(CellElement.WUMPUS) ) {
                    arrowStep++;
                } else {
                    hit = true;
                }
            }
        }
        System.out.println("HIT:" + targetCell);
        return targetCell;
    }
}
