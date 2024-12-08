package dungeongame.src.view;

import dungeongame.src.model.MapTile;
import dungeongame.src.model.MapTileList;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * MapScene is a Scene
 * Used to create an instance of MapBorderPane
 */
public class MapScene extends Scene {

    /**Default Height*/
    private static final double HEIGHT = 600;
    /**Default Width*/
    private static final double WIDTH = 450;

    /**
     * MapScene Constructor
     * @param parent Scene Parent
     */
    public MapScene(Parent parent) {
        super(new MapBorderPane(WIDTH, HEIGHT), HEIGHT, WIDTH);
    }



}
