package wumpus.game;

import wumpus.wmap.WMap;

/**
 * Game state class.
 */
public class GameState {

    public static GameStateBuilder builder() {
        return new GameStateBuilder();
    }

    private WMap currentWMap;
    private boolean stopped;

    public GameState(WMap currentWMap, boolean stopped) {
        this.currentWMap = currentWMap;
        this.stopped = stopped;
    }

    public WMap getCurrentMap() {
        return currentWMap;
    }

    public void setCurrentMap(WMap currentWMap) {
        this.currentWMap = currentWMap;
    }

    public boolean isRunning() {
        return !this.stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    /**
     * Gamestate builder class.
     */
    public static final class GameStateBuilder {
        private WMap currentWMap;
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

        public GameStateBuilder withStopped(boolean stopped) {
            this.stopped = stopped;
            return this;
        }

        public GameState build() {
            return new GameState(this.currentWMap, this.stopped);
        }
    }
}
