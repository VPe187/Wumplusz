package wumpus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.Label;
import com.indvd00m.ascii.render.elements.Table;

/**
 * World Wumpus class used to store Wumpus world.
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
    private WorldTable worldTable;
    private final int wumpusCount;
    private final Player player = new Player();

    public World() {
        this.loadWorldFromFile();
        this.wumpusCount = wumpusCountByWorldSize(this.worldSize);
    }

    public World(int worldSize) {
        this.setWorldSize(worldSize);
        this.worldTable = new WorldTable(this.worldSize);
        this.wumpusCount = wumpusCountByWorldSize(this.worldSize);
    }

    /**
     * Method used to set world size.
     */
    public void setWorldSize(int worldSize) {
        if (worldSize > MAX_WORLD_SIZE) {
            this.worldSize = MAX_WORLD_SIZE;
        } else {
            this.worldSize = Math.max(worldSize, MIN_WORLD_SIZE);
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
            int heroColumn;
            int heroRow;
            heroColumn = integerFromLetter(parameters[1].charAt(0));
            heroRow = Integer.parseInt(parameters[2]);
            HeroSight heroSight = switch (parameters[3]) {
                case "N" -> HeroSight.NORTH;
                case "W" -> HeroSight.WEST;
                case "E" -> HeroSight.EAST;
                case "S" -> HeroSight.SOUTH;
                default -> HeroSight.NONE;
            };
            worldTable = new WorldTable(this.worldSize);
            int row = 0;
            while (textFileBReader.ready()) {
                String line = textFileBReader.readLine();
                for (int j = 0; j < this.worldSize; j++) {
                    String cellValue = line.substring(j, j + 1);
                    if (cellValue.equalsIgnoreCase("H")) {
                        this.worldTable.addCell(j, row, new CellHero(cellValue, this.wumpusCountByWorldSize(this.worldSize)));
                        this.worldTable.setHeroPosRow(row);
                        this.worldTable.setHeroPosColumn(j);
                        this.worldTable.getHeroCell().setHeroSight(heroSight);
                    } else {
                        this.worldTable.addCell(j, row, new Cell(cellValue));
                    }
                }
                row++;
            }
            textFileBReader.close();
            this.worldTable.setCellValue(heroColumn - 1, heroRow - 1, "H");
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
        IRender render = new Render();
        IContextBuilder builder = render.newBuilder();
        builder.width((this.worldSize + 1) * 4 + 1).height((this.worldSize + 1) * 2 + 1);
        Table table = new Table(this.worldSize + 1, this.worldSize + 1);
        for (int i = 0; i < this.worldSize; i++) {
            table.setElement(i + 2, 1, new Label(" " + alphabet.charAt(i)), false);
        }
        for (int i = 0; i < this.worldSize; i++) {
            for (int j = 0; j < this.worldSize; j++) {
                table.setElement(1, j + 2, new Label(" " + (j + 1)), false);
                if (this.worldTable.getCellValue(j, i).equalsIgnoreCase("G") || this.worldTable.getCellValue(j, i).equalsIgnoreCase("H")) {
                    table.setElement(j + 2, i + 2, new Label(" " + this.worldTable.getCellValue(j, i)), true);
                } else {
                    table.setElement(j + 2, i + 2, new Label(" " + this.worldTable.getCellValue(j, i)));
                }
            }
        }
        builder.element(table);
        ICanvas canvas = render.render(builder.build());
        String s = canvas.getText();
        System.out.println(s);
    }

    public Player getPlayer() {
        return player;
    }


}
