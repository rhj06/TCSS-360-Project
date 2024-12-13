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
 *
 * @version 1.0
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 *
 */
public final class CharacterSelectScreen extends AbstractScreen {

    /** Constant of -20 */
    private static final int NEGATIVE_TWENTY = -20;

    /** Constant of -5 */
    private static final int NEGATIVE_FIVE = -5;

    /** Constant of 3 */
    private static final int THREE = 3;

    /** Constant of 5 */
    private static final int FIVE = 5;

    /** Constant of 7 */
    private static final int SEVEN = 7;

    /** Constant of 8 */
    private static final int EIGHT = 8;

    /** Constant of 10 */
    private static final int TEN = 10;

    /** Constant of 12 */
    private static final int TWELVE = 12;

    /** Constant of 15 */
    private static final int FIFTEEN = 15;

    /** Constant of 18 */
    private static final int EIGHTEEN = 18;

    /** Constant of 20 */
    private static final int TWENTY = 20;

    /** Constant of 35 */
    private static final int THIRTY_FIVE = 35;

    /** Constant of 50 */
    private static final int FIFTY = 50;

    /** Constant of 90 */
    private static final int NINETY = 90;

    /** Constant of 100 */
    private static final int ONE_HUNDRED = 100;

    /** Constant of 120 */
    private static final int ONE_HUNDRED_TWENTY = 120;

    /** Constant of 300 */
    private static final int THREE_HUNDRED = 300;

    /** Constant of 600 */
    private static final int SIX_HUNDRED = 600;

    /** Constant of 800 */
    private static final int EIGHT_HUNDRED = 800;

    /** Width of buttons in the character selection screen. */
    private static final double BUTTON_WIDTH = 150;

    /** Font size for buttons in the character selection screen. */
    private static final double BUTTON_FONT_SIZE = 18;

    /** Path to the font used for titles and labels. */
    private static final String FONT_PATH = "file:.idea/resources/fonts/OldeEnglish.ttf";

    /** Path to the font used for buttons. */
    private static final String BUTTON_FONT_PATH = "file:.idea/resources/fonts/VIKING-N.TTF";

    /** Path to the background image for the character selection screen. */
    private static final String BACKGROUND_IMAGE = "file:.idea/resources/characterselect.jpg";

    /** Factory instance for creating buttons with a consistent style. */
    private final ButtonFactory myButtonFactory;

    /** Helper for creating radio buttons for difficulty selection. */
    private final RadioButtonHelper myRadioButtonHelper;

    /** Text field for entering the player's name. */
    private TextField myPlayerNameField;

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
    public Scene createScene(final Stage theStage) {
        VBox myMainLayout = new VBox(TEN,
                createTitleLabel(),
                createPlayerNameInput(),
                createCharacterPanels(theStage),
                createDifficultySection()
        );
        myMainLayout.setAlignment(Pos.CENTER);
        myMainLayout.setPadding(new Insets(0, TWENTY, TWENTY, TWENTY));
        myMainLayout.setStyle("-fx-background-color: rgb(44,37,37);");

        BorderPane myRootLayout = new BorderPane(myMainLayout);
        myRootLayout.setBackground(createBackground(BACKGROUND_IMAGE));

        return new Scene(myRootLayout, EIGHT_HUNDRED, SIX_HUNDRED);
    }

    /**
     * Creates the title label for the character selection screen.
     *
     * @return the title label styled with a specific font and color
     */
    private Label createTitleLabel() {
        return LabelHelper.createCenteredLabel("Select Your Character", FONT_PATH, FIFTY,
                "-fx-text-fill: rgb(120,18,4); -fx-font-weight: bold;");
    }

    /**
     * Creates an input field for the player to set their custom name.
     *
     * @return the VBox containing the label and text field
     */
    private HBox createPlayerNameInput() {
        Label nameLabel = LabelHelper.createCenteredLabel("Enter Your Name:", FONT_PATH, TWENTY,
                "-fx-text-fill: rgb(120,18,4); -fx-font-weight: bold;");

        myPlayerNameField = new TextField();
        myPlayerNameField.setPromptText("Enter your hero's name");
        myPlayerNameField.setMaxWidth(THREE_HUNDRED);
        myPlayerNameField.setStyle("-fx-background-color: grey; " + "-fx-text-fill: black; ");

        HBox nameInputBox = new HBox(TEN, nameLabel, myPlayerNameField);
        nameInputBox.setAlignment(Pos.CENTER);
        nameInputBox.setPadding(new Insets(NEGATIVE_FIVE, 0, TWENTY, FIFTY));

        return nameInputBox;
    }

    /**
     * Creates the character selection panels, each representing a character type.
     *
     * @param theStage the primary stage used for transitioning to the next screen
     * @return the container for character selection panels
     */
    private HBox createCharacterPanels(final Stage theStage) {
        CharacterSelectPanel myPanelCreator = new CharacterSelectPanel(myButtonFactory);

        HBox myCharacterPanels = new HBox(TEN,
                createCharacterPanel(myPanelCreator, "file:ThiefSelectTile.jpg", "Thief", theStage),
                createCharacterPanel(myPanelCreator, "file:WarriorSelectTile.jpeg", "Warrior", theStage),
                createCharacterPanel(myPanelCreator, "file:WizardSelectTile.jpg", "Wizard", theStage)
        );
        myCharacterPanels.setTranslateY(NEGATIVE_TWENTY);
        myCharacterPanels.setAlignment(Pos.CENTER);
        return myCharacterPanels;
    }

    /**
     * Creates the difficulty selection section with a label and difficulty buttons.
     *
     * @return the container for difficulty selection elements
     */
    private HBox createDifficultySection() {
        Label myDifficultyLabel = LabelHelper.createCenteredLabel("Choose Your Difficulty:", FONT_PATH,
                TWENTY, "-fx-text-fill: rgb(120,18,4); -fx-font-weight: bold;");

        HBox difficultyButtons = myRadioButtonHelper.createDifficultyButtons();
        difficultyButtons.setAlignment(Pos.CENTER_LEFT);

        HBox difficultyLayout = new HBox(TWENTY, myDifficultyLabel, difficultyButtons);
        difficultyLayout.setAlignment(Pos.CENTER);
        difficultyLayout.setPadding(new Insets(NEGATIVE_TWENTY, FIFTY, TWENTY, FIFTY));

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
    private VBox createCharacterPanel(final CharacterSelectPanel thePanelCreator, final String theImagePath,
                                      final String theCharacterType, final Stage theStage) {
        return thePanelCreator.createCharacterPanel(theImagePath, theCharacterType, () ->
                handleCharacterSelection(theStage, theCharacterType));
    }

    /**
     * Handles the selection of a character and transitions to the game screen.
     *
     * @param theStage        the primary stage where the game screen will be displayed
     * @param theCharacterType the type of character selected by the player
     */
    private void handleCharacterSelection(final Stage theStage, final String theCharacterType) {
        String playerName = myPlayerNameField.getText().trim();
        if (playerName.isEmpty()) {
            playerName = "Unknown Hero";
        }
        Maze myMaze = Maze.getInstance();
        int myMazeSize = myRadioButtonHelper.getSelectedMazeSize();
        myMaze.setMazeSize(myMazeSize);
        myMaze.generateMaze();

        AbstractDungeonCharacter mySelectedHero = switch (theCharacterType.toLowerCase()) {
            case "wizard" -> HeroFactory.createHero("wizard", ONE_HUNDRED, TEN, TWENTY, FIVE, TEN, SEVEN, playerName);
            case "thief" -> HeroFactory.createHero("thief", NINETY, FIFTEEN, THIRTY_FIVE, TEN, TWENTY, FIVE, playerName);
            case "warrior" -> HeroFactory.createHero("warrior", ONE_HUNDRED_TWENTY, TWELVE, EIGHTEEN, THREE, EIGHTEEN, TEN, playerName);
            default -> throw new IllegalArgumentException("Invalid character type: " + theCharacterType);
        };
        GameScreen myGameScreen = new GameScreen(myMaze, mySelectedHero, theStage);
        theStage.setScene(myGameScreen.createScene(theStage));
        System.out.println("Switching to GameScreen with character: " + mySelectedHero);
        System.out.println("Maze Layout");
        myMaze.printMaze();
    }
}