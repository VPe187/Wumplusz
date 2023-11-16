package wumpus.game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.PseudoText;
import wumpus.exceptions.MapParsingException;
import wumpus.exceptions.MapReadingException;
import wumpus.input.InputHandler;
import wumpus.input.InputReader;
import wumpus.map.BufferedMapReader;
import wumpus.map.MapParser;
import wumpus.map.MapReader;
import wumpus.model.Player;
import wumpus.ui.MapRenderer;

/**
 * Game controller class.
 */
public class GameController {
    static final String WORLD_INPUT_FILENAME = "wumpuszinput.txt";
    private final Player player = new Player();
    private final MapRenderer mapRenderer = new MapRenderer();
    private final GameState gameState;
    private final InputReader inputReader;
    private final InputHandler inputHandler;

    public GameController(GameState gameState, InputReader inputReader, InputHandler inputHandler) {
        this.gameState = gameState;
        this.inputReader = inputReader;
        this.inputHandler = inputHandler;
    }

    /**
     * Start game.
     */
    public void start() {
        this.welcomeText();
        this.inputUserName();
        System.out.println("Szia kedves " + this.player.getName() + "!");
        while (gameState.isRunning()) {
            String input = this.inputReader.readInput();
            inputHandler.handleInput(input);
        }
    }

    /**
     * Method used to load world and hero data from text file.
     */
    public void readWorldFromFile() throws MapReadingException, MapParsingException {
        InputStream inputStream = this.getClass().getResourceAsStream("/" + WORLD_INPUT_FILENAME);
        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            MapReader mapReader = new BufferedMapReader(reader);
            List<String> rows = mapReader.readMap();
            MapParser mph = new MapParser(rows);
            this.gameState.setCurrentMap(mph.getMap());
        }
    }

    /**
     * Method used to print welcome text.
     */
    public void welcomeText() {
        IRender render = new Render();
        IContextBuilder builder = render.newBuilder();
        builder.width(60).height(14);
        builder.element(new PseudoText("Wumplusz"));
        ICanvas canvas = render.render(builder.build());
        String s = canvas.getText();
        System.out.println(s);
        System.out.println("### NYE - Progtech Assigment 2023/2024/1 - VPe");
    }

    /**
     * Input Player name from console.
     */
    public void inputUserName() {
        System.out.print("Kérem adja meg a keresztnevét: ");
        Scanner input = new Scanner(System.in);
        String playerName = input.nextLine();
        if (playerName == null || playerName.isEmpty()) {
            this.player.setName("Unnamed");
        } else {
            this.player.setName(playerName);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public GameState getGameState() {
        return gameState;
    }
}
