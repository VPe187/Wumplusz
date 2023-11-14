package wumpus;

import java.util.Scanner;

import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.PseudoText;

/**
 * Game controller class.
 */
public class Controller {
    private final World world;
    private final Menu mainMenu;
    private final MenuItem mainMenuItemEditor;
    private final MenuItem mainMenuItemLoadFile;
    private final MenuItem mainMenuItemGame;
    private final MenuItem mainMenuItemQuit;

    public Controller() {
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
        this.start();
        this.userInput(mainMenu);
    }

    protected void start() {
        this.welcomeText();
        this.inputUserName();
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
        this.world.getPlayer().setName(playerName);
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
