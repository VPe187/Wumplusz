package wumpus.command;

import wumpus.game.GameState;
import wumpus.model.Cell;
import wumpus.model.CellElement;
import wumpus.wmap.WMap;
import wumpus.wmap.WMapTools;

/**
 * Move command, move hero to next cell in sight.
 */
public class CommandMove implements Command {
    private static final String COMMAND_MOVE = "move";
    private final GameState gameState;

    public CommandMove(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND_MOVE.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        WMap wmap = gameState.getCurrentMap();
        Cell targetCell = WMapTools.canMoveDirection(wmap, wmap.getHeroSight());
        if (targetCell == null) {
            System.out.println("This move not possible because target cell contains wall.");
        } else {
            gameState.setSteps(gameState.getSteps() + 1);
            WMapTools.moveHeroToCell(wmap, targetCell);
            if (targetCell.getValue().equals(CellElement.WUMPUS)) {
                System.out.println("Your hero met a WUMPUS and died.");
                gameState.setHeroDead(true);
            } else if (targetCell.getValue().equals(CellElement.GOLD)) {
                System.out.println("Your hero pick up the gold.");
                gameState.setHeroHasGold(true);
            } else if (targetCell.getValue().equals(CellElement.PIT)) {
                System.out.println("Your hero has fallen into the pit and lost 1 arrow.");
                gameState.looseArrow();
            } else {
                System.out.println("The hero has just moved to " + targetCell + " field.");
                if (gameState.checkHeroWon()) {
                    System.out.println("You got the gold and you got out. You win.");
                    gameState.setStopped(true);
                }
            }
        }
    }
}
