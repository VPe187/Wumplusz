package wumpus;

/**
 * World class used to store Wumpus world.
 */
public class World {
    private final int worldSize;
    private final Cell[][] worldTable;
    private final int wumpusCount;

    public World(int worldSizeN) {
        this.worldSize = getCorrectWorldSize(worldSizeN);
        this.worldTable = new Cell[this.worldSize][this.worldSize];
        this.wumpusCount = countWumpusByWorldSize(this.worldSize);
    }

    private int getCorrectWorldSize(int worldSizeN) {
        int correctSize;
        if (worldSizeN > 20) {
            correctSize = 20;
        } else if (worldSizeN < 6) {
            correctSize = 6;
        } else {
            correctSize = worldSizeN;
        }
        return correctSize;
    }

    public int getWorldSize() {
        return worldSize;
    }

    public int getWumpusCount() {
        return wumpusCount;
    }

    private int countWumpusByWorldSize(int worldSizeN) {
        int wumpuses;
        if (worldSizeN <= 8) {
            wumpuses = 1;
        } else if (worldSizeN >= 9 && worldSizeN <= 14) {
            wumpuses = 2;
        } else {
            wumpuses = 3;
        }
        return wumpuses;
    }

    /**
     * Method used to print a map to screen.
     */
    public void typeWorld() {
        for (int i = 0; i < worldSize; i++) {
            for (int j = 0; j < worldSize; j++) {
                System.out.println(worldTable[j][i]);
            }
        }
    }
}
