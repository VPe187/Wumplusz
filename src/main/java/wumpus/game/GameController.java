package wumpus.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import wumpus.exceptions.MapParsingException;
import wumpus.exceptions.MapReadingException;
import wumpus.input.InputHandler;
import wumpus.input.InputReader;
import wumpus.model.Player;
import wumpus.ui.MapwRenderer;
import wumpus.wmap.BufferedWMapReader;
import wumpus.wmap.WMapParser;
import wumpus.wmap.WMapReader;

/**
 * Controll game, handle main loop and event.
 */
public class GameController {
    static final String WORLD_INPUT_FILENAME = "wumpuszinput.txt";
    private final GameState gameState;
    private final InputReader inputReader;
    private final InputHandler inputHandler;

    public GameController(GameState gameState, InputReader inputReader, InputHandler inputHandler) {
        this.gameState = gameState;
        this.inputReader = inputReader;
        this.inputHandler = inputHandler;
    }

    /**
     * Start game, main loop.
     */
    public void start() {
        MapwRenderer.welcomeText();
        this.inputUserName();
        System.out.println("Szia kedves " + getPlayer().getName() + "!");
        while (gameState.isRunning()) {
            String input = this.inputReader.readInput();
            inputHandler.handleInput(input);
        }
    }

    /**
     * Method used to load world and hero data from text file.
     */
    public void readWorldFromFile() throws MapReadingException, MapParsingException, IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("/" + WORLD_INPUT_FILENAME);
        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            WMapReader wmapReader = new BufferedWMapReader(reader);
            List<String> rows = wmapReader.readMap();
            WMapParser wmapParser = new WMapParser(rows);
            this.gameState.setCurrentMap(wmapParser.getMap());
            this.gameState.setStartMap(wmapParser.getMap());
            reader.close();
        }
    }

    /**
     * Input Player name from console.
     */
    public void inputUserName() {
        System.out.print("Kérem adja meg a keresztnevét ");
        String playerName = inputReader.readInput();
        Player player = new Player();
        if (playerName == null || playerName.isEmpty()) {
            player.setName("Unnamed");
        } else {
            player.setName(playerName);
        }
        this.gameState.setPlayer(player);
    }

    public Player getPlayer() {
        return this.gameState.getPlayer();
    }

    public GameState getGameState() {
        return gameState;
    }
}
