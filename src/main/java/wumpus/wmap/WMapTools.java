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
    public static Cell canMoveDirection(WMap wmap, HeroSight direction) {
        Cell heroCell = wmap.getHeroCell();
        if (direction.equals(HeroSight.NORTH) && (heroCell.getRow() - 1) < 0) {
            return null;
        }
        if (direction.equals(HeroSight.SOUTH) && (heroCell.getRow() + 1) > (wmap.getSize() - 1)) {
            return null;
        }
        if (direction.equals(HeroSight.WEST) && (heroCell.getCol() - 1) < 0) {
            return null;
        }
        if (direction.equals(HeroSight.EAST) && (heroCell.getCol() + 1) > (wmap.getSize() - 1)) {
            return null;
        }
        Cell targetCell = getTargetCell(wmap, direction);
        if (!targetCell.getCellValue().equals(CellElement.WALL)) {
            return targetCell;
        } else {
            return null;
        }
    }

    /**
     * Return target cell when Hero wants move to his sight line.
     *
     * @param wmap the map ({@link WMap})
     * @param direction the direction of move ({@link HeroSight})
     *
     * @return targetCell as {@link Cell}
     */
    public static Cell getTargetCell(WMap wmap, HeroSight direction) {
        Cell heroCell = wmap.getHeroCell();
        return switch (direction) {
            case NORTH -> wmap.getCells()[heroCell.getCol()][heroCell.getRow() - 1];
            case SOUTH -> wmap.getCells()[heroCell.getCol()][heroCell.getRow() + 1];
            case WEST -> wmap.getCells()[heroCell.getCol() - 1][heroCell.getRow()];
            case EAST -> wmap.getCells()[heroCell.getCol() + 1][heroCell.getRow()];
        };
    }

    /**
     * Move Hero to validated target cell.
     *
     * @param wmap the map ({@link WMap})
     * @param targetCell the target cell ({@link Cell})
     */
    public static void moveHeroToCell(WMap wmap, Cell targetCell) {
        int heroCol = wmap.getHeroCell().getCol();
        int heroRow = wmap.getHeroCell().getRow();
        int targetCol = targetCell.getCol();
        int targetRow = targetCell.getRow();
        wmap.getCells()[heroCol][heroRow] = Cell.builder().withCol(heroCol).withRow(heroRow).withValue(CellElement.EMPTY).build();
        wmap.getCells()[targetCol][targetRow] = Cell.builder().withCol(targetCol).withRow(targetRow).withValue(CellElement.HERO).build();
    }

    /**
     * Shoot event.
     *
     * @param wmap as {@link WMap}
     * @param direction as {@link HeroSight}
     *
     * @return resultCell as {@link Cell}
     */
    public static Cell shootEndCell(WMap wmap, HeroSight direction) {
        Cell targetCell = wmap.getHeroCell();
        boolean hit = false;
        int col = wmap.getHeroCell().getCol();
        int row = wmap.getHeroCell().getRow();
        int arrowStep = 1;
        if (direction.equals(HeroSight.NORTH)) {
            while ((row - arrowStep) >= 0 && !hit) {
                targetCell = wmap.getCell(col, row - arrowStep);
                if (!targetCell.getValue().equals(CellElement.WALL) && !targetCell.getValue().equals(CellElement.WUMPUS)) {
                    arrowStep++;
                } else {
                    hit = true;
                }
            }
        }
        if (direction.equals(HeroSight.SOUTH)) {
            while ((row + arrowStep) <= wmap.getSize() && !hit) {
                targetCell = wmap.getCell(col, row + arrowStep);
                if (!targetCell.getValue().equals(CellElement.WALL) && !targetCell.getValue().equals(CellElement.WUMPUS)) {
                    arrowStep++;
                } else {
                    hit = true;
                }
            }
        }
        if (direction.equals(HeroSight.WEST)) {
            while ((col - arrowStep) >= 0 && !hit) {
                targetCell = wmap.getCell(col - arrowStep, row);
                if (!targetCell.getValue().equals(CellElement.WALL) && !targetCell.getValue().equals(CellElement.WUMPUS)) {
                    arrowStep++;
                } else {
                    hit = true;
                }
            }
        }
        if (direction.equals(HeroSight.EAST)) {
            while ((col + arrowStep) <= wmap.getSize() && !hit) {
                targetCell = wmap.getCell(col + arrowStep, row);
                if (!targetCell.getValue().equals(CellElement.WALL) && !targetCell.getValue().equals(CellElement.WUMPUS)) {
                    arrowStep++;
                } else {
                    hit = true;
                }
            }
        }
        return targetCell;
    }
}
