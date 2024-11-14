package dungeongame.src.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The MainMenu class represents the main menu screen for the Dungeon Adventure game.
 * It creates the layout and components for the main menu, including title and buttons
 * for starting a new game, loading a saved game, and exiting the application.
 */
public class MainMenu {

    /** Path to the background image for the main menu. */
    private static final String BACKGROUND_IMAGE = "file:dungeongame/resources/dungeonadventure1.jpg";

    /** Path to the primary font used for the title label. */
    private static final String FONT_PATH = "file:dungeongame/resources/fonts/OldeEnglish.ttf";

    /** Path to the secondary font used for button labels. */
    private static final String FONT_PATH2 = "file:dungeongame/resources/fonts/VIKING-N.TTF";

    /** Text displayed as the title of the main menu. */
    private static final String TITLE_TEXT = "Dungeon Adventure";

    /** Minimum width for buttons in the main menu. */
    private static final double BUTTON_WIDTH = 150;

    /** Font size for button labels in the main menu. */
    private static final double BUTTON_FONT_SIZE = 18;

    /** Font size for the title label in the main menu. */
    private static final double TITLE_FONT_SIZE = 90;

    /**
     * Creates the main menu scene for the application.
     *
     * @param theStage The primary stage of the application where the scene will be displayed.
     * @return A Scene object representing the main menu screen.
     */
    public Scene createMainMenuScene(Stage theStage) {
        VBox menu = new VBox(20, createTitleLabel(),
                createButton("New Game", () -> theStage.setScene(createGameScene())),
                createButton("Load Game", this::loadGame),
                createButton("Exit", theStage::close));

        menu.setAlignment(Pos.CENTER);
        menu.setPadding(new Insets(50, 0, 100, 0));

        BorderPane mainLayout = new BorderPane(menu);
        mainLayout.setBackground(createBackground());

        return new Scene(mainLayout, 800, 600);
    }

    /**
     * Creates the background for the main menu screen.
     *
     * @return A Background object configured with the specified background image.
     */
    private Background createBackground() {
        return new Background(new BackgroundImage(new Image(BACKGROUND_IMAGE),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)));
    }

    /**
     * Creates and configures the title label for the main menu.
     *
     * @return A Label object styled as the title for the main menu.
     */
    private Label createTitleLabel() {
        Label titleLabel = new Label(TITLE_TEXT);
        titleLabel.setFont(loadFont(FONT_PATH, TITLE_FONT_SIZE));
        titleLabel.setStyle("-fx-text-fill: rgba(186,8,8,0.99); -fx-font-weight: bold;");
        titleLabel.setPadding(new Insets(30, 0, 50, 0));
        return titleLabel;
    }

    /**
     * Creates a button with specified text and action.
     *
     * @param theText   The text to be displayed on the button.
     * @param theAction The action to be performed when the button is clicked.
     * @return A Button object with the specified properties and action.
     */
    private Button createButton(String theText, Runnable theAction) {
        Button button = new Button(theText);
        button.setFont(loadFont(FONT_PATH2, BUTTON_FONT_SIZE));
        button.setMinWidth(BUTTON_WIDTH);
        button.setPadding(new Insets(10, 20, 10, 20));
        button.setOnAction(e -> theAction.run());
        return button;
    }

    /**
     * Loads a font from a specified file path and size.
     *
     * @param theFontPath The file path to the font.
     * @param theSize     The font size to be loaded.
     * @return A Font object loaded with the specified font file and size.
     */
    private Font loadFont(String theFontPath, double theSize) {
        return Font.loadFont(theFontPath, theSize);
    }

    /**
     * Creates the game scene that is displayed when the player starts a new game.
     *
     * @return A Scene object representing the initial game screen.
     */
    private Scene createGameScene() {
        return new Scene(new BorderPane(), 800, 600);
    }

    /**
     * Placeholder method for loading a saved game.
     * Currently, this method does not contain implementation.
     */
    private void loadGame() {
        // Load game implementation
    }
}
