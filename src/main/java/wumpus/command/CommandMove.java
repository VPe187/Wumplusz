package wumpus.command;

import wumpus.game.GameState;
import wumpus.model.Cell;
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
        Cell targetCell = WMapTools.getTargetCell(gameState.getCurrentMap(), gameState.getCurrentMap().getHeroSight());
        boolean isPossible = WMapTools.canMoveDirection(wmap, gameState.getCurrentMap().getHeroSight());
        if (!isPossible) {
            System.out.println("This move not possible because target cell contains wall.");
        } else {
            WMapTools.moveHeroToCell(wmap, targetCell);
            wmap.setSteps(wmap.getSteps() + 1);
            System.out.println("The hero has just moved to " + targetCell + " field.");
        }
    }
}
