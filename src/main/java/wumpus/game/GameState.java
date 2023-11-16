package wumpus.game;

import wumpus.map.Map;

/**
 * Game state class.
 */
public class GameState {

    public static GameStateBuilder builder() {
        return new GameStateBuilder();
    }

    private Map currentMap;
    private boolean stopped;

    public GameState(Map currentMap, boolean stopped) {
        this.currentMap = currentMap;
        this.stopped = stopped;
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
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
        private Map currentMap;
        private boolean stopped;

        private GameStateBuilder() {
        }

        public static GameStateBuilder builder() {
            return new GameStateBuilder();
        }

        public GameStateBuilder withCurrentMap(Map currentMap) {
            this.currentMap = currentMap;
            return this;
        }

        public GameStateBuilder withStopped(boolean stopped) {
            this.stopped = stopped;
            return this;
        }

        public GameState build() {
            return new GameState(this.currentMap, this.stopped);
        }
    }
}
