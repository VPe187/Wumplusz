package wumpus;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Menu class, handle menu function.
 */
public class Menu {
    ArrayList<MenuItem> menuItems;
    String userName;

    public Menu() {
        menuItems = new ArrayList<MenuItem>();
    }

    public void addItem(MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Select from builded menu.
     */
    public MenuItem select() {
        int selection = 0;
        MenuItem selectedItem;
        Scanner input = new Scanner(System.in);
        while (!(selection > 0 && selection < this.menuItems.size() + 1)) {
            System.out.println();
            System.out.println("================================");
            System.out.println("Játékos: " + this.userName);
            System.out.println("================================");
            System.out.println("== M E N Ü - kérem válasszon: ==");
            System.out.println("================================");
            for (int i = 0; i < this.menuItems.size(); i++) {
                System.out.println(i + 1 + " - " + this.menuItems.get(i).getLabel());
            }
            selection = input.nextInt();
        }
        selection--;
        selectedItem = this.menuItems.get(selection);
        return selectedItem;
    }
}
