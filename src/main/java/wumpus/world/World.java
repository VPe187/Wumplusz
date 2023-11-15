package wumpus.world;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.Label;
import com.indvd00m.ascii.render.elements.Table;
import wumpus.Player;
import wumpus.exceptions.MapParsingException;
import wumpus.exceptions.WorldReadingException;
import wumpus.map.BufferedMapReader;
import wumpus.map.Map;
import wumpus.map.MapParser;
import wumpus.map.MapReader;
import wumpus.util.Utils;

/**
 * World Wumpus class used to store Wumpus world.
 */
public class World {

    static final int MIN_WORLD_SIZE = 6;
    static final int MAX_WORLD_SIZE = 20;
    static final int WUMPUSES_EASY = 1;
    static final int WUMPUSES_MEDIUM = 2;
    static final int WUMPUSES_HARD = 3;
    static final String WORLD_INPUT_FILENAME = "wumpuszinput.txt";
    private int worldSize;
    private final int wumpusCount;
    private final Player player = new Player();
    private Map map;

    public World() throws MapParsingException, WorldReadingException {
        this.readWorldFromFile();
        this.wumpusCount = wumpusCountByWorldSize(this.worldSize);
    }

    public World(int worldSize) {
        this.setWorldSize(worldSize);
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

    /**
     * Method used to load world and hero data from text file.
     */
    public void readWorldFromFile() throws WorldReadingException, MapParsingException {
        InputStream inputStream = this.getClass().getResourceAsStream("/" + WORLD_INPUT_FILENAME);
        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            MapReader mapReader = new BufferedMapReader(reader);
            List<String> rows = mapReader.readMap();
            MapParser mph = new MapParser(rows);
            this.map = mph.getMap();
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
        builder.width((this.map.getSize() + 1) * 4 + 1).height((this.map.getSize() + 1) * 2 + 1);
        Table table = new Table(this.map.getSize() + 1, this.map.getSize() + 1);
        for (int i = 0; i < this.map.getSize(); i++) {
            table.setElement(i + 2, 1, new Label(" " + Utils.letterFromInteger(i)), false);
        }
        for (int i = 0; i < this.map.getSize(); i++) {
            for (int j = 0; j < this.map.getSize(); j++) {
                table.setElement(1, j + 2, new Label(" " + (j + 1)), false);
                if (this.map.getCellValue(j, i).equalsIgnoreCase("G") || this.map.getCellValue(j, i).equalsIgnoreCase("H")) {
                    table.setElement(j + 2, i + 2, new Label(" " + this.map.getCellValue(j, i)), true);
                } else {
                    table.setElement(j + 2, i + 2, new Label(" " + this.map.getCellValue(j, i)));
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
