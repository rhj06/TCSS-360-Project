package dungeongame.src.view;


import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * MapScene is a Scene
 * Used to create an instance of MapBorderPane
 *
 * @version 1.0
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 *
 */
public final class MapScene extends Scene {

    /**Default Height*/
    private static final double HEIGHT = 600;

    /**Default Width*/
    private static final double WIDTH = 450;

    /**
     * MapScene Constructor
     */
    public MapScene(Parent ignoredParent) {
        super(new MapBorderPane(WIDTH, HEIGHT), HEIGHT, WIDTH);
    }

}
