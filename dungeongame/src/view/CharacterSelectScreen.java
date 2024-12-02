package dungeongame.src.view;

import dungeongame.src.model.Maze;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CharacterSelectScreen {

    private static final String FONT_PATH = "file:.idea/resources/fonts/OldeEnglish.ttf";
    private final RadioButtonHelper difficultyButtonHelper = new RadioButtonHelper();

    public Scene createCharacterSelectScene(Stage thePrimaryStage) {
        // Title Label
        Label titleLabel = LabelHelper.createLabel("Select Your Character", FONT_PATH, 50, "-fx-text-fill: rgb(120,18,4); -fx-font-weight: bold;");

        // Character Panels
        CharacterSelectPanel panelCreator = new CharacterSelectPanel();
        HBox characterLayout = new HBox(20,
                panelCreator.createCharacterPanel("file:ThiefSelectTile.jpg", "Thief", () -> handleCharacterSelection(thePrimaryStage)),
                panelCreator.createCharacterPanel("file:WarriorSelectTile.jpeg", "Warrior", () -> handleCharacterSelection(thePrimaryStage)),
                panelCreator.createCharacterPanel("file:WizardSelect.png", "Wizard", () -> handleCharacterSelection(thePrimaryStage))
        );
        characterLayout.setAlignment(Pos.CENTER);
        characterLayout.setPadding(new Insets(-10));

        // Difficulty Selection
        VBox difficultyLayout = createDifficultySection();

        // Main Layout
        VBox mainLayout = new VBox(15, titleLabel, characterLayout, difficultyLayout);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(-20));
        mainLayout.setStyle("-fx-background-color: rgb(44,37,37);");

        return new Scene(mainLayout, 800, 600);
    }

    private VBox createDifficultySection() {
        Label difficultyLabel = LabelHelper.createLabel("Choose Your Difficulty", FONT_PATH, 30, "-fx-text-fill: rgb(120,18,4);");

        HBox difficultyButtons = difficultyButtonHelper.createDifficultyButtons();

        VBox difficultyLayout = new VBox(10, difficultyLabel, difficultyButtons);
        difficultyLayout.setAlignment(Pos.CENTER);
        difficultyLayout.setPadding(new Insets(-10, 0, 0, 0));

        return difficultyLayout;
    }

    private void handleCharacterSelection(Stage thePrimaryStage) {
        int mazeSize = difficultyButtonHelper.getSelectedMazeSize();
        Maze maze = Maze.getInstance();
        maze.setMazeSize(mazeSize);
        maze.generateMaze();

        GameScreen gameScreen = new GameScreen(thePrimaryStage, maze);
        thePrimaryStage.setScene(gameScreen.createGameScene());
    }
}