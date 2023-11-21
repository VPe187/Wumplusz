package wumpus.command;

import wumpus.game.GameState;
import wumpus.model.Cell;
import wumpus.model.CellElement;
import wumpus.model.HeroSight;
import wumpus.ui.MessagePrinter;
import wumpus.wmap.WMap;
import wumpus.wmap.WMapTools;

/**
 * Right command, rotate hero sight to right.
 */
public class CommandShoot implements Command {
    private static final String COMMAND_SHOOT = "shoot";
    private final GameState gameState;

    public CommandShoot(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND_SHOOT.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        WMap wmap = gameState.getCurrentMap();
        if (wmap.getArrowCount() > 0) {
            wmap.setArrowCount(wmap.getArrowCount() - 1);
            HeroSight direction = wmap.getHeroSight();
            Cell endCell = WMapTools.shootEndCell(wmap, direction);
            if (endCell.getValue().equals(CellElement.WUMPUS)) {
                wmap.getCells()[endCell.getCol()][endCell.getRow()] = Cell.builder().withCol(endCell.getCol())
                        .withRow(endCell.getRow()).withValue(CellElement.EMPTY).build();
                MessagePrinter.printMessage("It was a WUMPUS on %s. The Number of arrows left %d.",
                        endCell, wmap.getArrowCount());
            }
            if (endCell.getValue().equals(CellElement.WALL)) {
                MessagePrinter.printMessage("It was a WALL. The arrow has fallen on the %s field. The Number of arrows left %d.",
                        endCell, wmap.getArrowCount());
            }
        } else if (wmap.getArrowCount() <= 0) {
            MessagePrinter.printMessage("Unfortunately, the hero has no more arrows.%n");
        }
    }
}
