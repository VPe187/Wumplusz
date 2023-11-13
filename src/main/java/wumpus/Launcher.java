package wumpus;

import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.PseudoText;

/**
 * Launcher class used to launch application.
 */
public class Launcher {
    public static void main(String[] args) {
        welcomeText();
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
