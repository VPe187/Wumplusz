package wumpus;

import java.util.Scanner;

import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.PseudoText;
import wumpus.exceptions.MapParsingException;
import wumpus.exceptions.WorldReadingException;
import wumpus.world.World;

/**
 * Game controller class.
 */
public class GameController {
    private final World world;
    private final Menu mainMenu;
    private final MenuItem mainMenuItemEditor;
    private final MenuItem mainMenuItemLoadFile;
    private final MenuItem mainMenuItemGame;
    private final MenuItem mainMenuItemQuit;

    public GameController() throws MapParsingException, WorldReadingException {
        this.world = new World();
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

    protected void start() {
        this.welcomeText();
        this.inputUserName();
        System.out.println("Szia kedves " + this.world.getPlayer().getName() + "!");
        this.userInput(mainMenu);
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
            this.world.getPlayer().setName("Unnamed");
        } else {
            this.world.getPlayer().setName(playerName);
        }
    }

    protected void userInput(Menu menu) {
        MenuItem selectedItem;
        do {
            selectedItem = menu.select();
            if (selectedItem.equals(this.mainMenuItemGame)) {
                this.world.renderWorld();
            }
        } while (!selectedItem.equals(this.mainMenuItemQuit));
    }
}
