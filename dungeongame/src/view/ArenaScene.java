package dungeongame.src.view;

import dungeongame.src.controller.Arena;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ArenaScene extends Scene {

    /**Default Height*/
    private static final double HEIGHT = 600;
    /**Default Width*/
    private static final double WIDTH = 450;

    private Arena arena;

    /**
     * MapScene Constructor
     * @param parent Scene Parent
     */
    public ArenaScene(Arena theArena) {
        super(new ArenaBorderPane(WIDTH, HEIGHT, theArena), HEIGHT, WIDTH);
    }
}