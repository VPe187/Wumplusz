package wumpus;

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
import wumpus.map.BufferedMapReader;
import wumpus.map.Map;
import wumpus.map.MapParser;
import wumpus.map.MapReader;
import wumpus.ui.MapRenderer;

/**
 * Game controller class.
 */
public class GameController {
    static final int MIN_WORLD_SIZE = 6;
    static final int MAX_WORLD_SIZE = 20;
    static final int WUMPUSES_EASY = 1;
    static final int WUMPUSES_MEDIUM = 2;
    static final int WUMPUSES_HARD = 3;
    static final String WORLD_INPUT_FILENAME = "wumpuszinput.txt";
    private int worldSize;
    private final Player player = new Player();
    private final Menu mainMenu;
    private final MenuItem mainMenuItemEditor;
    private final MenuItem mainMenuItemLoadFile;
    private final MenuItem mainMenuItemGame;
    private final MenuItem mainMenuItemQuit;
    private Map map;
    private final MapRenderer mapRenderer = new MapRenderer();

    public GameController() throws MapParsingException, MapReadingException {

        this.mainMenu = new Menu();
        this.mainMenuItemEditor = new MenuItem("Pályaszerkesztés");
        this.mainMenuItemLoadFile = new MenuItem("Betöltés fájlból");
        this.mainMenuItemGame = new MenuItem("Játék");
        this.mainMenuItemQuit = new MenuItem("Kilépés");
        this.mainMenu.addItem(mainMenuItemEditor);
        this.mainMenu.addItem(mainMenuItemLoadFile);
        this.mainMenu.addItem(mainMenuItemGame);
        this.mainMenu.addItem(mainMenuItemQuit);
    }

    protected void start() throws MapParsingException, MapReadingException {
        this.welcomeText();
        this.inputUserName();
        System.out.println("Szia kedves " + this.player.getName() + "!");
        this.readWorldFromFile();
        this.userInput(mainMenu);
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
            this.map = mph.getMap();
        }
    }

    protected void welcomeText() {
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

    protected void userInput(Menu menu) {
        MenuItem selectedItem;
        do {
            selectedItem = menu.select();
            if (selectedItem.equals(this.mainMenuItemGame)) {
                this.mapRenderer.render(this.map);
            }
        } while (!selectedItem.equals(this.mainMenuItemQuit));
    }




}
