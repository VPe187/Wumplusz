package wumpus;

/**
 * Game controller class.
 */
public class Controller {
    private final World world;
    private MenuItem selectedItem;
    private final Menu mainMenu;
    private final MenuItem mainMenuItem1;
    private final MenuItem mainMenuItem2;
    private final MenuItem mainMenuItem3;
    private final MenuItem mainMenuItem4;
    private final MenuItem mainMenuItem5;

    public Controller() {
        this.world = new World();
        this.mainMenu = new Menu();
        this.mainMenu.setPlayerName(world.getPlayer().getName());
        this.mainMenuItem1 = new MenuItem("Játékos neve");
        this.mainMenu.addItem(mainMenuItem1);
        this.mainMenuItem2 = new MenuItem("Pályaszerkesztés");
        this.mainMenu.addItem(mainMenuItem2);
        this.mainMenuItem3 = new MenuItem("Betöltés fájlból");
        this.mainMenu.addItem(mainMenuItem3);
        this.mainMenuItem4 = new MenuItem("Játék");
        this.mainMenu.addItem(mainMenuItem4);
        this.mainMenuItem5 = new MenuItem("Kilépés");
        this.mainMenu.addItem(mainMenuItem5);
        this.render();
        this.userInput(mainMenu);
    }

    protected void render() {
        world.welcomeText();
        world.inputUserName();
        this.mainMenu.setPlayerName(this.world.getPlayer().getName());
    }

    protected void userInput(Menu menu) {
        do {
            this.selectedItem = menu.select();
            if (this.selectedItem.equals(this.mainMenuItem1)) {
                this.world.inputUserName();
                menu.setPlayerName(world.getPlayer().getName());
            }
            if (this.selectedItem.equals(this.mainMenuItem4)) {
                this.world.renderWorld();
            }
        } while (!this.selectedItem.equals(this.mainMenuItem5));
    }
}
