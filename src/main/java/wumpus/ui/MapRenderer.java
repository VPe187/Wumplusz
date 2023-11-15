package wumpus.ui;

import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.Label;
import com.indvd00m.ascii.render.elements.Table;
import wumpus.map.Map;
import wumpus.util.Utils;

/**
 * Render map to console.
 */
public class MapRenderer {

    public MapRenderer() {
    }

    /**
     * Method used to render map with ASCII-renderer.
     */
    public void render(Map map) {
        IRender render = new Render();
        IContextBuilder builder = render.newBuilder();
        builder.width((map.getSize() + 1) * 4 + 1).height((map.getSize() + 1) * 2 + 1);
        Table table = new Table(map.getSize() + 1, map.getSize() + 1);
        for (int i = 0; i < map.getSize(); i++) {
            table.setElement(i + 2, 1, new Label(" " + Utils.letterFromInteger(i)), false);
        }
        for (int i = 0; i < map.getSize(); i++) {
            for (int j = 0; j < map.getSize(); j++) {
                table.setElement(1, j + 2, new Label(" " + (j + 1)), false);
                if (map.getCellValue(j, i).equalsIgnoreCase("G") || map.getCellValue(j, i).equalsIgnoreCase("H")) {
                    table.setElement(j + 2, i + 2, new Label(" " + map.getCellValue(j, i)), true);
                } else {
                    table.setElement(j + 2, i + 2, new Label(" " + map.getCellValue(j, i)));
                }
            }
        }
        builder.element(table);
        ICanvas canvas = render.render(builder.build());
        String s = canvas.getText();
        System.out.println(s);
    }


}
