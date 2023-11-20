package wumpus.command;

import wumpus.game.GameState;
import wumpus.model.HeroSight;

/**
 * Right command, rotate hero sight to right.
 */
public class CommandRight implements Command {
    private static final String COMMAND_QUIT = "right";
    private final GameState gameState;

    public CommandRight(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND_QUIT.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        HeroSight oldSight = this.gameState.getCurrentMap().getHeroSight();
        this.gameState.getCurrentMap().setHeroSight(oldSight.right());
        System.out.println("Hero look at " + this.gameState.getCurrentMap().getHeroSight()  + " now.");
    }
}
