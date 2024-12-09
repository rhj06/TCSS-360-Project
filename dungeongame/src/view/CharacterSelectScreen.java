package dungeongame.src.view;

import dungeongame.src.model.AbstractDungeonCharacter;
import dungeongame.src.model.HeroFactory;
import dungeongame.src.model.Maze;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the character selection screen where the player selects their character
 * and game difficulty before starting the dungeon adventure.
 * <p>
 * This screen allows users to choose from different characters (e.g., Wizard, Warrior, Thief)
 * and set the game difficulty. Upon selection, the game transitions to the main game screen.
 * </p>
 */
public class CharacterSelectScreen extends AbstractScreen {
    /**
     * The fixed width of the character selection buttons.
     */
    private static final double BUTTON_WIDTH = 150;

    /**
     * The font size used for character selection buttons.
     */
    private static final double BUTTON_FONT_SIZE = 18;

    /**
     * The file path to the font used for screen labels.
     */
    private static final String FONT_PATH = "file:.idea/resources/fonts/OldeEnglish.ttf";

    /**
     * The file path to the font used for character selection buttons.
     */
    private static final String BUTTON_FONT_PATH = "file:.idea/resources/fonts/VIKING-N.TTF";

    /**
     * The file path to the background image for the character selection screen.
     */
    private static final String BACKGROUND_IMAGE = "file:.idea/resources/characterselect.jpg";

    /**
     * Factory for creating styled buttons used throughout the screen.
     */
    private final ButtonFactory myButtonFactory;

    /**
     * Helper for managing and retrieving the selected game difficulty.
     */
    private final RadioButtonHelper myRadioButtonHelper;

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
     * Creates the character selection panels, each representing a character type.
     *
     * @param theStage the primary stage used for transitioning to the next screen
     * @return the container for character selection panels
     */
    private HBox createCharacterPanels(Stage theStage) {
        CharacterSelectPanel myPanelCreator = new CharacterSelectPanel(myButtonFactory);

        HBox myCharacterPanels = new HBox(10,
                createCharacterPanel(myPanelCreator, "file:ThiefSelectTile.jpg", "Thief",
                        theStage),
                createCharacterPanel(myPanelCreator, "file:WarriorSelectTile.jpeg", "Warrior",
                        theStage),
                createCharacterPanel(myPanelCreator, "file:WizardSelect.png", "Wizard",
                        theStage)
        );
        myCharacterPanels.setAlignment(Pos.CENTER);
        return myCharacterPanels;
    }

    /**
     * Creates the difficulty selection section with a label and difficulty buttons.
     *
     * @return the container for difficulty selection elements
     */
    private VBox createDifficultySection() {
        Label myDifficultyLabel = LabelHelper.createCenteredLabel("Choose Your Difficulty", FONT_PATH,
                30, "-fx-text-fill: rgb(120,18,4); -fx-font-weight: bold;");

        HBox myDifficultyButtons = myRadioButtonHelper.createDifficultyButtons();

        VBox myDifficultyLayout = new VBox(10, myDifficultyLabel, myDifficultyButtons);
        myDifficultyLayout.setAlignment(Pos.CENTER);
        myDifficultyLayout.setPadding(new Insets(-10, 0, 0, 0));
        return myDifficultyLayout;
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
        Maze myMaze = Maze.getInstance();
        int myMazeSize = myRadioButtonHelper.getSelectedMazeSize(); // Use the shared instance
        myMaze.setMazeSize(myMazeSize);
        myMaze.generateMaze();

        AbstractDungeonCharacter mySelectedHero = switch (theCharacterType.toLowerCase()) {
            case "wizard" -> HeroFactory.createHero("wizard", 100, 10, 20,
                    5, 10, 15, "Gandalf");
            case "thief" -> HeroFactory.createHero("thief", 90, 8, 15, 10,
                    20, 10, "Locke");
            case "warrior" -> HeroFactory.createHero("warrior", 120, 12, 25,
                    3, 8, 20, "Aragorn");
            default -> throw new IllegalArgumentException("Invalid character type: " + theCharacterType);
        };

        GameScreen myGameScreen = new GameScreen(myMaze, mySelectedHero);
        theStage.setScene(myGameScreen.createScene(theStage));
        System.out.println("Switching to GameScreen with character: " + mySelectedHero);

        System.out.println("Items");
        myMaze.printMaze();
        System.out.println("Player");
        myMaze.printPlayerCordMaze();
        System.out.println("Monsters");
        myMaze.printMonsterMaze();
    }
}