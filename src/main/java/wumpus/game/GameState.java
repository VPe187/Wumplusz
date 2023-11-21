package wumpus.game;

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

    public GameState(WMap currentWMap, Player player, boolean stopped) {
        this.currentWMap = currentWMap;
        this.stopped = stopped;
        this.player = player;
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

    public void setCurrentMap(WMap currentWMap) {
        this.currentWMap = currentWMap;
    }

    public void setStartMap(WMap startWMap) {
        this.startWMap = startWMap;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
        private WMap startMap;
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
