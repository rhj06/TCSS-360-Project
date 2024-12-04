package dungeongame.src.view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

/**
 * Abstract base class for all screens in the game.
 * Provides common functionality for scene creation and utilities.
 */
public abstract class AbstractScreen {

    /**
     * Creates the scene for the screen.
     *
     * @param theStage The primary stage where the scene will be displayed.
     * @return The created Scene.
     */
    public abstract Scene createScene(Stage theStage);

    /**
     * Creates a background with the specified image path.
     *
     * @param theImagePath Path to the background image.
     * @return The Background object.
     */
    protected Background createBackground(String theImagePath) {
        return new Background(new BackgroundImage(new Image(theImagePath),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true)));
    }
}