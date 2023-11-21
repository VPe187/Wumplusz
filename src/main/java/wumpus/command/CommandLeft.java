package wumpus.command;

import wumpus.game.GameState;
import wumpus.model.HeroSight;

/**
 * Left command, rotate hero sight to left.
 */
public class CommandLeft implements Command {
    private static final String COMMAND_LEFT = "left";
    private final GameState gameState;

    public CommandLeft(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND_LEFT.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        HeroSight oldSight = this.gameState.getCurrentMap().getHeroSight();
        this.gameState.getCurrentMap().setHeroSight(oldSight.left());
        System.out.println("Hero look at " + this.gameState.getCurrentMap().getHeroSight()  + " now.");
    }
}
