package dungeongame.src.view;

import dungeongame.src.model.Maze;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.scene.text.Font.loadFont;

/**
 * The CharacterSelectScreen represents the character selection screen for the game.
 * It allows the player to choose from three characters.
 */
public class CharacterSelectScreen {

    private static final String THIEF_IMAGE_PATH = "file:ThiefSelectTile.jpg";
    private static final String WARRIOR_IMAGE_PATH = "file:WarriorSelectTile.jpeg";
    private static final String WIZARD_IMAGE_PATH = "file:WizardSelect.png";
    private static final String FONT_PATH = "file:.idea/resources/fonts/OldeEnglish.ttf";

    /**
     * Creates the character selection screen.
     *
     * @param thePrimaryStage The main application stage where the scene will be displayed.
     * @return A Scene object for the character selection screen.
     */
    public Scene createCharacterSelectScene(Stage thePrimaryStage) {
        // Create the title label
        Label titleLabel = new Label("Select Your Character");
        titleLabel.setFont(loadFont(FONT_PATH, 50));
        titleLabel.setStyle("-fx-text-fill: rgb(21,1,1); -fx-font-weight: bold;");
        titleLabel.setPadding(new Insets(10, 0, 10, 0));

        CharacterSelectPanel panelCreator = new CharacterSelectPanel();

        VBox thiefPanel = panelCreator.createCharacterPanel(
                THIEF_IMAGE_PATH,
                "Thief",
                () -> handleCharacterSelection("Thief", thePrimaryStage)
        );
        VBox warriorPanel = panelCreator.createCharacterPanel(
                WARRIOR_IMAGE_PATH,
                "Warrior",
                () -> handleCharacterSelection("Warrior", thePrimaryStage)
        );
        VBox wizardPanel = panelCreator.createCharacterPanel(
                WIZARD_IMAGE_PATH,
                "Wizard",
                () -> handleCharacterSelection("Wizard", thePrimaryStage)
        );

        HBox characterLayout = new HBox(20, thiefPanel, warriorPanel, wizardPanel);
        characterLayout.setAlignment(Pos.CENTER);
        characterLayout.setPadding(new Insets(20));

        // Arrange the title and character panels in a vertical layout
        VBox mainLayout = new VBox(20, titleLabel, characterLayout);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        return new Scene(mainLayout, 800, 600);
    }

    /**
     * Handles the character selection process.
     *
     * @param theCharacterName The name of the selected character.
     * @param thePrimaryStage  The main application stage.
     */
    private void handleCharacterSelection(String theCharacterName, Stage thePrimaryStage) {
        Maze maze = Maze.getInstance();
        maze.setMazeSize(10);
        maze.generateMaze();

        GameScreen gameScreen = new GameScreen(thePrimaryStage, maze);
        thePrimaryStage.setScene(gameScreen.createGameScene());
    }
}