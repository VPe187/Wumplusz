package wumpus.ui;

import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.Label;
import com.indvd00m.ascii.render.elements.PseudoText;
import com.indvd00m.ascii.render.elements.Table;
import wumpus.model.CellElement;
import wumpus.wmap.WMap;
import wumpus.wmap.WMapTools;

/**
 * Render map to console.
 */
public class MapwRenderer {

    public MapwRenderer() {
    }

    /**
     * Method used to print welcome text.
     */
    public static void welcomeText() {
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
     * Method used to render map with ASCII-renderer.
     */
    public static void render(WMap wmap) {
        IRender render = new Render();
        IContextBuilder builder = render.newBuilder();
        builder.width((wmap.getSize() + 1) * 4 + 1).height((wmap.getSize() + 1) * 2 + 1);
        Table table = new Table(wmap.getSize() + 1, wmap.getSize() + 1);
        for (int i = 0; i < wmap.getSize(); i++) {
            table.setElement(i + 2, 1, new Label(" " + WMapTools.letterFromInteger(i)), false);
        }
        for (int i = 0; i < wmap.getSize(); i++) {
            for (int j = 0; j < wmap.getSize(); j++) {
                table.setElement(1, j + 2, new Label(" " + (j + 1)), false);
                if (wmap.getCellValue(j, i).equals(CellElement.GOLD) || wmap.getCellValue(j, i).equals(CellElement.HERO)) {
                    table.setElement(j + 2, i + 2, new Label(" " + wmap.getCellValue(j, i)), true);
                } else {
                    table.setElement(j + 2, i + 2, new Label(" " + wmap.getCellValue(j, i)));
                }
            }
        }
        builder.element(table);
        ICanvas canvas = render.render(builder.build());
        String s = canvas.getText();
        System.out.println(s);
        System.out.println("Start:" +
                WMapTools.letterFromInteger(wmap.getStartCol()) +
                (wmap.getStartRow() + 1) +
                ", Sight:" + wmap.getHeroSight().toString() +
                ", Steps:" + wmap.getSteps());
        System.out.println("Arrows:" + wmap.getArrowCount() +
                ", Wumpuses:" + wmap.getWumpusCells() +
                ", Gold:" + wmap.getGoldCells());
        System.out.println("Pits:" + wmap.getPitCells() + ", " +
                "Walls:" + wmap.getWallCells() +
                ", Empty:" + wmap.getEmptyCells());
    }
}
