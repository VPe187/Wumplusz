package wumpus;

import java.io.IOException;

import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.PseudoText;

/**
 * Launcher class used to launch application.
 */
public class Launcher {
    /**
     * Entry point.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        welcomeText();
        World world = new World();
        world.renderWorld();
        String userName = world.inputUserName();
        MenuItem mainMenuItem1 = new MenuItem("Játékos neve");
        MenuItem mainMenuItem2 = new MenuItem("Pályaszerkesztés");
        MenuItem mainMenuItem3 = new MenuItem("Betöltés fájlból");
        MenuItem mainMenuItem4 = new MenuItem("Játék");
        MenuItem mainMenuItem5 = new MenuItem("Kilépés");
        Menu mainMenu = new Menu();
        mainMenu.setUserName(userName);
        mainMenu.addItem(mainMenuItem1);
        mainMenu.addItem(mainMenuItem2);
        mainMenu.addItem(mainMenuItem3);
        mainMenu.addItem(mainMenuItem4);
        mainMenu.addItem(mainMenuItem5);
        MenuItem selectedItem;
        do {
            selectedItem = mainMenu.select();
            if (selectedItem.equals(mainMenuItem1)) {
                userName = world.inputUserName();
                mainMenu.setUserName(userName);
            }
            if (selectedItem.equals(mainMenuItem4)) {
                world.renderWorld();
            }
        } while (!selectedItem.equals(mainMenuItem5));
    }

    private static void welcomeText() {
        IRender render = new Render();
        IContextBuilder builder = render.newBuilder();
        builder.width(60).height(14);
        builder.element(new PseudoText("Wumplusz"));
        ICanvas canvas = render.render(builder.build());
        String s = canvas.getText();
        System.out.println(s);
    }
}
