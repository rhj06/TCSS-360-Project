package dungeongame.src.view;

import dungeongame.src.model.AbstractDungeonCharacter;
import dungeongame.src.model.HeroFactory;
import dungeongame.src.model.Maze;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the character selection screen where the player selects their character
 * and game difficulty before starting the dungeon adventure.
 */
public class CharacterSelectScreen extends AbstractScreen {
    private static final double BUTTON_WIDTH = 150;
    private static final double BUTTON_FONT_SIZE = 18;
    private static final String FONT_PATH = "file:.idea/resources/fonts/OldeEnglish.ttf";
    private static final String BUTTON_FONT_PATH = "file:.idea/resources/fonts/VIKING-N.TTF";
    private static final String BACKGROUND_IMAGE = "file:.idea/resources/characterselect.jpg";

    private final ButtonFactory myButtonFactory;
    private final RadioButtonHelper myRadioButtonHelper;
    private TextField playerNameField; // Text field for player name input
    private Stage myStage;

    /**
     * Constructs a new CharacterSelectScreen instance.
     * Initializes the button factory and difficulty radio button helper.
     */
    public CharacterSelectScreen() {
        myButtonFactory = new ButtonFactory(BUTTON_FONT_PATH, BUTTON_FONT_SIZE, BUTTON_WIDTH);
        myRadioButtonHelper = new RadioButtonHelper();
    }

    /**
     * Creates the scene for the character selection screen.
     *
     * @param theStage the primary stage where the scene will be displayed
     * @return the constructed Scene for the character selection screen
     */
    @Override
    public Scene createScene(Stage theStage) {
        VBox myMainLayout = new VBox(10,
                createTitleLabel(),
                createPlayerNameInput(),
                createCharacterPanels(theStage),
                createDifficultySection()
        );
        myMainLayout.setAlignment(Pos.CENTER);
        myMainLayout.setPadding(new Insets(0, 20, 20, 20));
        myMainLayout.setStyle("-fx-background-color: rgb(44,37,37);");

        BorderPane myRootLayout = new BorderPane(myMainLayout);
        myRootLayout.setBackground(createBackground(BACKGROUND_IMAGE));

        return new Scene(myRootLayout, 800, 600);
    }

    /**
     * Creates the title label for the character selection screen.
     *
     * @return the title label styled with a specific font and color
     */
    private Label createTitleLabel() {
        return LabelHelper.createCenteredLabel("Select Your Character", FONT_PATH, 50,
                "-fx-text-fill: rgb(120,18,4); -fx-font-weight: bold;");
    }

    /**
     * Creates an input field for the player to set their custom name.
     *
     * @return the VBox containing the label and text field
     */
    private HBox createPlayerNameInput() {
        Label nameLabel = LabelHelper.createCenteredLabel("Enter Your Name:", FONT_PATH, 20,
                "-fx-text-fill: rgb(120,18,4); -fx-font-weight: bold;");

        playerNameField = new TextField();
        playerNameField.setPromptText("Enter your hero's name");
        playerNameField.setMaxWidth(300);
        playerNameField.setStyle("-fx-background-color: grey; " + "-fx-text-fill: black; ");

        // Create an HBox to align the label and text field horizontally
        HBox nameInputBox = new HBox(10, nameLabel, playerNameField);
        nameInputBox.setAlignment(Pos.CENTER);
        nameInputBox.setPadding(new Insets(-5, 0, 20, 50));

        return nameInputBox;
    }

    /**
     * Creates the character selection panels, each representing a character type.
     *
     * @param theStage the primary stage used for transitioning to the next screen
     * @return the container for character selection panels
     */
    private HBox createCharacterPanels(Stage theStage) {
        CharacterSelectPanel myPanelCreator = new CharacterSelectPanel(myButtonFactory);

        HBox myCharacterPanels = new HBox(10,
                createCharacterPanel(myPanelCreator, "file:ThiefSelectTile.jpg", "Thief", theStage),
                createCharacterPanel(myPanelCreator, "file:WarriorSelectTile.jpeg", "Warrior", theStage),
                createCharacterPanel(myPanelCreator, "file:WizardSelect.png", "Wizard", theStage)
        );
        myCharacterPanels.setTranslateY(-20);
        myCharacterPanels.setAlignment(Pos.CENTER);
        return myCharacterPanels;
    }

    /**
     * Creates the difficulty selection section with a label and difficulty buttons.
     *
     * @return the container for difficulty selection elements
     */
    private HBox createDifficultySection() {
        // Create the label for the difficulty section
        Label myDifficultyLabel = LabelHelper.createCenteredLabel("Choose Your Difficulty:", FONT_PATH,
                20, "-fx-text-fill: rgb(120,18,4); -fx-font-weight: bold;");

        // Create the radio buttons using the existing helper
        HBox difficultyButtons = myRadioButtonHelper.createDifficultyButtons();
        difficultyButtons.setAlignment(Pos.CENTER_LEFT);

        // Combine the label and radio buttons in an HBox
        HBox difficultyLayout = new HBox(20, myDifficultyLabel, difficultyButtons);
        difficultyLayout.setAlignment(Pos.CENTER);
        difficultyLayout.setPadding(new Insets(-20, 50, 20, 50));

        return difficultyLayout;
    }

    /**
     * Creates a character selection panel for a specific character type.
     *
     * @param thePanelCreator the panel creator instance used for creating panels
     * @param theImagePath    the file path for the character's image
     * @param theCharacterType the type of character represented in the panel
     * @param theStage        the primary stage used for transitioning to the next screen
     * @return the character selection panel
     */
    private VBox createCharacterPanel(CharacterSelectPanel thePanelCreator, String theImagePath,
                                      String theCharacterType, Stage theStage) {
        return thePanelCreator.createCharacterPanel(theImagePath, theCharacterType, () ->
                handleCharacterSelection(theStage, theCharacterType));
    }

    /**
     * Handles the selection of a character and transitions to the game screen.
     *
     * @param theStage        the primary stage where the game screen will be displayed
     * @param theCharacterType the type of character selected by the player
     */
    private void handleCharacterSelection(Stage theStage, String theCharacterType) {
        String playerName = playerNameField.getText().trim();
        if (playerName.isEmpty()) {
            playerName = "Unknown Hero"; // Default name if no name is entered
        }
        Maze myMaze = Maze.getInstance();
        int myMazeSize = myRadioButtonHelper.getSelectedMazeSize();
        myMaze.setMazeSize(myMazeSize);
        myMaze.generateMaze();

        AbstractDungeonCharacter mySelectedHero = switch (theCharacterType.toLowerCase()) {
            case "wizard" -> HeroFactory.createHero("wizard", 100, 10, 20, 5, 10, 7, playerName);
            case "thief" -> HeroFactory.createHero("thief", 90, 15, 35, 10, 20, 5, playerName);
            case "warrior" -> HeroFactory.createHero("warrior", 120, 12, 18, 3, 8, 10, playerName);
            default -> throw new IllegalArgumentException("Invalid character type: " + theCharacterType);
        };
        GameScreen myGameScreen = new GameScreen(myMaze, mySelectedHero, theStage);
        theStage.setScene(myGameScreen.createScene(theStage));
        System.out.println("Switching to GameScreen with character: " + mySelectedHero);
        System.out.println("Maze Layout");
        myMaze.printMaze();
    }
}