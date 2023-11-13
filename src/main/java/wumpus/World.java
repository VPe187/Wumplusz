package wumpus;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * World class used to store Wumpus world.
 */
public class World {
    static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVXYW";
    static final int MIN_WORLD_SIZE = 6;
    static final int MAX_WORLD_SIZE = 20;
    static final int WUMPUSES_EASY = 1;
    static final int WUMPUSES_MEDIUM = 2;
    static final int WUMPUSES_HARD = 3;
    static final String WORLD_INPUT_FILENAME = "wumpuszinput.txt";
    private int worldSize;
    private int heroColumn;
    private int heroRow;
    private Cell[][] worldTable;
    private final int wumpusCount;

    public World() {
        this.loadWorldFromFile();
        this.wumpusCount = wumpusCountByWorldSize(this.worldSize);
        renderWorld();
    }

    public World(int worldSize) {
        this.setWorldSize(worldSize);
        initWorldTable();
        this.wumpusCount = wumpusCountByWorldSize(this.worldSize);
    }

    private void initWorldTable() {
        this.worldTable = new Cell[this.worldSize][this.worldSize];
        for (int i = 0; i < this.worldSize; i++) {
            for (int j = 0; j < this.worldSize; j++) {
                this.worldTable[j][i] = new Cell();
            }
        }
    }

    /**
     * Method used to set world size.
     */
    public void setWorldSize(int worldSize) {
        if (worldSize > MAX_WORLD_SIZE) {
            this.worldSize = MAX_WORLD_SIZE;
        } else if (worldSize < MIN_WORLD_SIZE) {
            this.worldSize = MIN_WORLD_SIZE;
        } else {
            this.worldSize = worldSize;
        }
    }

    private int wumpusCountByWorldSize(int worldSize) {
        if (worldSize <= 8) {
            return WUMPUSES_EASY;
        }
        if (worldSize <= 14) {
            return WUMPUSES_MEDIUM;
        } else {
            return WUMPUSES_HARD;
        }
    }

    static int integerFromLetter(char letter) {
        int number = 0;
        for (int i = 0; i <= alphabet.length(); i++) {
            if (alphabet.charAt(i) == letter) {
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * Method used to load world and hero data from text file.
     */
    public void loadWorldFromFile() {
        try {
            String wordParametersRow;
            String[] parameters;
            InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("/" + WORLD_INPUT_FILENAME)));
            BufferedReader textFileBReader = new BufferedReader(inputStreamReader);
            wordParametersRow = textFileBReader.readLine();
            parameters = wordParametersRow.split(" ");
            this.worldSize = Integer.parseInt(parameters[0]);
            this.heroColumn = integerFromLetter(parameters[1].charAt(0));
            this.heroRow = Integer.parseInt(parameters[2]);
            initWorldTable();
            int row = 0;
            while (textFileBReader.ready()) {
                String line = textFileBReader.readLine();
                for (int j = 0; j < this.worldSize; j++) {
                    this.worldTable[j][row].setCellValue(line.charAt(j));
                    //System.out.print(line.charAt(j));
                }
                //System.out.println();
                row++;
            }
            textFileBReader.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public int getWorldSize() {
        return worldSize;
    }

    public int getWumpusCount() {
        return wumpusCount;
    }

    /**
     * Method used to print a map to screen.
     */
    public void renderWorld() {
        System.out.println(this.worldSize + " " + this.heroRow + " " + this.heroColumn);
        for (int i = 0; i < this.worldSize; i++) {
            for (int j = 0; j < this.worldSize; j++) {
                System.out.print(this.worldTable[j][i].getCellValue());
            }
            System.out.println();
        }
    }
}
