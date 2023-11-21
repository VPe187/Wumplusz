package wumpus.game;

import wumpus.model.Cell;
import wumpus.model.Player;
import wumpus.wmap.WMap;

/**
 * Game state class.
 */
public class GameState {

    public static GameStateBuilder builder() {
        return new GameStateBuilder();
    }

    private WMap currentWMap;
    private WMap startWMap;
    private Player player;
    private boolean stopped;
    private boolean heroDead;
    private boolean heroHasGold;
    private int arrowCount;
    private int wumpusCells;
    private int emptyCells;
    private int wallCells;
    private int pitCells;
    private int goldCells;
    private int steps;

    public GameState(WMap currentWMap, Player player, boolean stopped) {
        this.currentWMap = currentWMap;
        this.stopped = stopped;
        this.player = player;
    }

    protected void countElements() {
        wallCells = 0;
        emptyCells = 0;
        wumpusCells = 0;
        pitCells = 0;
        goldCells = 0;
        arrowCount = 0;
        for (int i = 0; i < getCurrentMap().getSize(); i++) {
            for (int j = 0; j < getCurrentMap().getSize(); j++) {
                switch (getCurrentMap().getCells()[j][i].getCellValue()) {
                    case WALL:
                        wallCells++;
                        break;
                    case EMPTY:
                        emptyCells++;
                        break;
                    case WUMPUS:
                        wumpusCells++;
                        arrowCount++;
                        break;
                    case PIT:
                        pitCells++;
                        break;
                    case GOLD:
                        goldCells++;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Decreases the number of arrows.
     */
    public void looseArrow() {
        if (arrowCount > 0) {
            arrowCount--;
        }
    }

    public WMap getCurrentMap() {
        return currentWMap;
    }

    public WMap getStartMap() {
        return startWMap;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWumpusCells() {
        return wumpusCells;
    }

    public int getArrowCount() {
        return arrowCount;
    }

    public int getEmptyCells() {
        return emptyCells;
    }

    public int getWallCells() {
        return wallCells;
    }

    public int getPitCells() {
        return pitCells;
    }

    public int getGoldCells() {
        return goldCells;
    }

    public int getSteps() {
        return steps;
    }

    public boolean isHeroAlive() {
        return !heroDead;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void setCurrentMap(WMap currentWMap) {
        this.currentWMap = currentWMap;
    }

    public void setStartMap(WMap startWMap) {
        this.startWMap = startWMap;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setHeroDead(boolean heroDead) {
        this.heroDead = heroDead;
    }

    public void setHeroHasGold(boolean heroHasGold) {
        this.heroHasGold = heroHasGold;
    }

    public boolean isRunning() {
        return !this.stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    /**
     * If Hero has gold, and position is start position, hero won.
     */
    public boolean checkHeroWon() {
        Cell heroCell = getCurrentMap().getHeroCell();
        return currentWMap.getStartCol() == heroCell.getCol() && currentWMap.getStartRow() == heroCell.getRow() && heroHasGold;
    }

    /**
     * Gamestate builder class.
     */
    public static final class GameStateBuilder {
        private WMap currentWMap;
        private Player player;
        private boolean stopped;

        private GameStateBuilder() {
        }

        public static GameStateBuilder builder() {
            return new GameStateBuilder();
        }

        public GameStateBuilder withCurrentMap(WMap currentWMap) {
            this.currentWMap = currentWMap;
            return this;
        }

        public GameStateBuilder withPlayer(Player player) {
            this.player = player;
            return this;
        }

        public GameStateBuilder withStopped(boolean stopped) {
            this.stopped = stopped;
            return this;
        }

        public GameState build() {
            return new GameState(this.currentWMap, this.player, this.stopped);
        }
    }
}
