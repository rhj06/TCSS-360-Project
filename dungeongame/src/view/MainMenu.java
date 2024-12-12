package dungeongame.src.view;

import dungeongame.src.controller.GameSaver;
import dungeongame.src.model.AbstractDungeonCharacter;
import dungeongame.src.model.Maze;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the main menu screen of the Dungeon Adventure game.
 * Allows users to start a new game, load an existing game, or exit the application.
 *
 * @version 1.0
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 *
 */
public class MainMenu extends AbstractScreen {

    /** The background image for the main menu. */
    private static final String BACKGROUND_IMAGE = "file:.idea/resources/dungeonadventure1.jpg";

    /** The font path for the title label. */
    private static final String FONT_PATH = "file:.idea/resources/fonts/OldeEnglish.ttf";

    /** The font path for the buttons. */
    private static final String BUTTON_FONT_PATH = "file:.idea/resources/fonts/VIKING-N.TTF";

    /** The title text displayed on the main menu. */
    private static final String TITLE_TEXT = "Dungeon Adventure";

    /** The width of the buttons on the main menu. */
    private static final double BUTTON_WIDTH = 150;

    /** The font size for the buttons on the main menu. */
    private static final double BUTTON_FONT_SIZE = 18;

    /** The font size for the title label. */
    private static final int TITLE_FONT_SIZE = 90;

    /** Factory for creating styled buttons. */
    private final ButtonFactory myButtonFactory;

    /**
     * Constructs a new {@link MainMenu} instance and initializes the button factory.
     */
    public MainMenu() {
        myButtonFactory = new ButtonFactory(BUTTON_FONT_PATH, BUTTON_FONT_SIZE, BUTTON_WIDTH);
    }

    /**
     * Creates the main menu scene.
     *
     * @param theStage The primary stage for the application.
     * @return A {@link Scene} representing the main menu.
     */
    @Override
    public Scene createScene(Stage theStage) {
        VBox menu = new VBox(20, createTitleLabel(),
                myButtonFactory.createButton("New Game", () -> theStage.setScene(new CharacterSelectScreen().createScene(theStage))),
                myButtonFactory.createButton("Load Game", () -> {
                    GameSaver.getInstance().loadGame();
                    AbstractDungeonCharacter player = GameSaver.getInstance().getPlayer();
                    theStage.setScene(new GameScreen(Maze.getInstance(), player, theStage).createScene(theStage));
                }),
                myButtonFactory.createButton("Exit", theStage::close)
        );
        menu.setAlignment(Pos.CENTER);
        menu.setPadding(new Insets(50, 0, 100, 0));

        BorderPane mainLayout = new BorderPane(menu);
        mainLayout.setBackground(createBackground(BACKGROUND_IMAGE));

        return new Scene(mainLayout, 800, 600);
    }

    /**
     * Creates and returns a title label for the main menu.
     *
     * @return A styled {@link Label} for the title text.
     */
    private Label createTitleLabel() {
        Label titleLabel = LabelHelper.createLabel(
                TITLE_TEXT, FONT_PATH, TITLE_FONT_SIZE,
                "-fx-text-fill: rgba(186,8,8,0.99); -fx-font-weight: bold;"
        );
        titleLabel.setPadding(new Insets(30, 0, 50, 0));

        return titleLabel;
    }

    /**
     * Handles the "Load Game" button action.
     * Placeholder for the game loading logic.
     */
    private void loadGame() {
        GameSaver.getInstance().loadGame();
    }
}