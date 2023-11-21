package wumpus.command;

import wumpus.game.GameState;
import wumpus.model.Cell;
import wumpus.model.HeroSight;
import wumpus.wmap.WMap;
import wumpus.wmap.WMapTools;

/**
 * Right command, rotate hero sight to right.
 */
public class CommandShoot implements Command {
    private static final String COMMAND_QUIT = "shoot";
    private final GameState gameState;

    public CommandShoot(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND_QUIT.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        WMap wmap = gameState.getCurrentMap();
        HeroSight direction = wmap.getHeroSight();
        Cell endCell = WMapTools.shootEndCell(wmap, direction);
        System.out.println("The hero has just shot the arrow. Number of arrows left 0.");
        System.out.println("Result:" + endCell.toString());
    }
}
